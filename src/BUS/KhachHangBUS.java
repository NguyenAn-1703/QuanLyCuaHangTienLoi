package BUS;

import java.util.ArrayList;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;

public class KhachHangBUS {
	private static KhachHangBUS instance;
	private KhachHangDAO khachHangDAO;
	ArrayList<KhachHangDTO> listKH;
	
	private KhachHangBUS() {
		khachHangDAO = KhachHangDAO.getInstance();
		listKH = khachHangDAO.getAll();
	}
	
	public static KhachHangBUS getInstance() {
		if(instance == null) {
			instance = new KhachHangBUS();
		}
		return(instance);	
	}
	
	public void insert(KhachHangDTO khach) {
		if(khachHangDAO.insert(khach) > 0) {
			this.listKH.add(khach);
		}
	}
	
	public int update(KhachHangDTO khachMoi) {
		if(khachHangDAO.update(khachMoi) > 0) {
			int id = khachMoi.getiD();
			listKH.get(getIndexByMaKH(id)).setTen(khachMoi.getTen());
			listKH.get(getIndexByMaKH(id)).setSoDienThoai(khachMoi.getSoDienThoai());
			listKH.get(getIndexByMaKH(id)).setDiem(khachMoi.getDiem());
			return(1);
		}
		return(0);
	}
	
	public int delete(int id) {
		if(khachHangDAO.delete(id) > 0) {
			listKH.remove(getIndexByMaKH(id));
			return(1);
		}
		return(0);
	}
	
	public ArrayList<KhachHangDTO> search(String content, String choice) {
		content = content.toLowerCase();
		ArrayList<KhachHangDTO> result = new ArrayList<>();
		if(content.equals("")) {
			return(listKH);
		}
		else if(choice.equals("Tất cả")) {
			for(int i = 0; i < listKH.size(); i++) {
				if(listKH.get(i).getSoDienThoai().contains(content) || listKH.get(i).getTen().toLowerCase().contains(content)
					|| (listKH.get(i).getiD() + "").contains(content) || (listKH.get(i).getDiem() + "").contains(content)){
					result.add(listKH.get(i));
				}
			}
		}
		else if(choice.equals("Số điện thoại")) {
			for(int i = 0; i < listKH.size(); i++) {
				if(listKH.get(i).getSoDienThoai().contains(content)){
					result.add(listKH.get(i));
				}
			}
		}
		else if(choice.equals("Tên")) {
			for(int i = 0; i < listKH.size(); i++) {
				if(listKH.get(i).getTen().toLowerCase().contains(content)){
					result.add(listKH.get(i));
				}
			}
		}
		else if(choice.equals("Mã")) {
			for(int i = 0; i < listKH.size(); i++) {
				if((listKH.get(i).getiD() + "").contains(content)){
					result.add(listKH.get(i));
				}
			}
		}
		else if(choice.equals("Điểm")) {
			for(int i = 0; i < listKH.size(); i++) {
				if((listKH.get(i).getDiem() + "").contains(content)){
					result.add(listKH.get(i));
				}
			}
		}
		return(result);
	}
	
	public ArrayList<KhachHangDTO> getAll(){
		listKH = khachHangDAO.getAll();
		return listKH;
	}

	public KhachHangDAO getKhachHangDAO() {
		return khachHangDAO;
	}

	public void setKhachHangDAO(KhachHangDAO khachHangDAO) {
		this.khachHangDAO = khachHangDAO;
	}
	
	//Cho chức năng sửa, xóa
	public int getIndexByMaKH(int maKH) {
		for(int i = 0; i < listKH.size(); i++) {
			if(listKH.get(i).getiD() == maKH) {
				return(i);
			}
		}
		return(-1);
	}
	
}

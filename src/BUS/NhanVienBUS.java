package BUS;

import java.util.ArrayList;

import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;

public class NhanVienBUS {
	private static NhanVienBUS instance;
	private NhanVienDAO nhanVienDAO;
	ArrayList<NhanVienDTO> listNV;
	
	private NhanVienBUS() {
		nhanVienDAO = NhanVienDAO.getInstance();
		listNV = nhanVienDAO.getAll();
	}
	
	public static NhanVienBUS getInstance() {
		if(instance == null) {
			instance = new NhanVienBUS();
		}
		return(instance);	
	}
	
	public int insert(NhanVienDTO nhanVien) {
		if(nhanVienDAO.insert(nhanVien) > 0) {
			this.listNV.add(nhanVien);
			return(1);
		}
		return(0);
	}
	
	public int update(NhanVienDTO nhanVienMoi) {
		if(nhanVienDAO.update(nhanVienMoi) > 0) {
			int id = nhanVienMoi.getiD();
			listNV.get(getIndexByMaNV(id)).setTen(nhanVienMoi.getTen());
			listNV.get(getIndexByMaNV(id)).setGioiTinh(nhanVienMoi.getGioiTinh());
			listNV.get(getIndexByMaNV(id)).setSoDT(nhanVienMoi.getSoDT());
			listNV.get(getIndexByMaNV(id)).setDiaChi(nhanVienMoi.getDiaChi());
			return(1);
		}
		return(0);
	}
	
	public int delete(int id) {
		if(nhanVienDAO.delete(id) > 0) {
			listNV.remove(getIndexByMaNV(id));
			return(1);
		}
		return(0);
	}
	
	public ArrayList<NhanVienDTO> search(String content, String choice) {
		content = content.toLowerCase();
		ArrayList<NhanVienDTO> result = new ArrayList<>();
		if(content.equals("")) {
			return(listNV);
		}
		else if(choice.equals("Tất cả")) {
			for(int i = 0; i < listNV.size(); i++) {
				if(listNV.get(i).getSoDT().contains(content) || listNV.get(i).getTen().toLowerCase().contains(content)
					|| (listNV.get(i).getiD() + "").contains(content) || listNV.get(i).getGioiTinh().contains(content) 
					|| listNV.get(i).getDiaChi().toLowerCase().contains(content)){
					result.add(listNV.get(i));
				}
			}
		}
		else if(choice.equals("Số điện thoại")) {
			for(int i = 0; i < listNV.size(); i++) {
				if(listNV.get(i).getSoDT().contains(content)){
						result.add(listNV.get(i));
				}
			}
		}
		else if(choice.equals("Tên")) {
			for(int i = 0; i < listNV.size(); i++) {
				if(listNV.get(i).getTen().toLowerCase().contains(content)){
						result.add(listNV.get(i));
					}
			}
		}
		else if(choice.equals("Mã")) {
			for(int i = 0; i < listNV.size(); i++) {
				if((listNV.get(i).getiD() + "").contains(content)){
						result.add(listNV.get(i));
					}
			}
		}
		else if(choice.equals("Địa chỉ")) {
			for(int i = 0; i < listNV.size(); i++) {
				if(listNV.get(i).getDiaChi().toLowerCase().contains(content)){
						result.add(listNV.get(i));
					}
			}
		}
		return(result);
	}
	
	public ArrayList<NhanVienDTO> getAll(){
		listNV = nhanVienDAO.getAll();
		return listNV;
	}

	public NhanVienDAO getNhanVienDAO() {
		return nhanVienDAO;
	}

	public void setNhanVienDAO(NhanVienDAO nhanVienDAO) {
		this.nhanVienDAO = nhanVienDAO;
	}
	
	//Cho chức năng sửa, xóa
	public int getIndexByMaNV(int maNV) {
		for(int i = 0; i < listNV.size(); i++) {
			if(listNV.get(i).getiD() == maNV) {
				return(i);
			}
		}
		return(-1);
	}
	
}

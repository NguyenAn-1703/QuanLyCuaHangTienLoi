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
	
}

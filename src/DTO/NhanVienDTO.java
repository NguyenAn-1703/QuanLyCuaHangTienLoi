package DTO;

public class NhanVienDTO {
	private int iD;
	private String ten;
	private String gioiTinh;
	private String diaChi;
	private String soDT;
	
	public NhanVienDTO() {}
	
	public NhanVienDTO(int iD, String ten, String gioiTinh, String soDT, String diaChi) {
		this.iD = iD;
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.soDT = soDT;
		this.diaChi = diaChi;
	}
	
	public NhanVienDTO(String ten, String gioiTinh, String soDT, String diaChi) {
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.soDT = soDT;
		this.diaChi = diaChi;
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}
}

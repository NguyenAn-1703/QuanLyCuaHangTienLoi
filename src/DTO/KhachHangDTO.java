package DTO;

import java.math.BigDecimal;

public class KhachHangDTO {
	private int iD;
	private String ten;
	private String soDienThoai;
	private BigDecimal diem;
	
	public KhachHangDTO() {}
	
	public KhachHangDTO(int iD, String ten, String soDienThoai, BigDecimal diem) {
		this.iD = iD;
		this.ten = ten;
		this.soDienThoai = soDienThoai;
		this.diem = diem;
	}
	
	public KhachHangDTO(String ten, String soDienThoai, BigDecimal diem) {
		this.ten = ten;
		this.soDienThoai = soDienThoai;
		this.diem = diem;
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
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public BigDecimal getDiem() {
		return diem;
	}

	public void setDiem(BigDecimal diem) {
		this.diem = diem;
	}
	
}

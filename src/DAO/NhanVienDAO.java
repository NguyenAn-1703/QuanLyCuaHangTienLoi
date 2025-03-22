package DAO;


import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import config.JDBCUtil;

public class NhanVienDAO implements DAOInterface<NhanVienDTO>{
	private static NhanVienDAO instance;
	
	// Constructor private không cho phép khởi tạo đối tượng từ bên ngoài
	private NhanVienDAO() {}
	
	public static NhanVienDAO getInstance() {
		if(instance == null) {
			instance = new NhanVienDAO();
		}
		return instance;
	}

	@Override
	public int insert(NhanVienDTO t) {
		Connection con = JDBCUtil.getConnection();
		String sql = "INSERT INTO NHANVIEN (TENNV, GIOITINH, SODT, DIACHI) VALUES (?, ?, ?, ?)";
		int rowInserted = 0;
		try {
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, t.getTen());
			pst.setString(2, t.getGioiTinh());
			pst.setString(3, t.getSoDT());
			pst.setString(4, t.getDiaChi());
			
			rowInserted = pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()) {
				t.setiD(rs.getInt(1));
			}
			JDBCUtil.closeConnection(con);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(rowInserted);
	}

	@Override
	public int update(NhanVienDTO t) {
		int result = 0;
		Connection con = JDBCUtil.getConnection();
		String sql = "UPDATE NHANVIEN SET TENNV = ?, GIOITINH = ?, SODT = ?, DIACHI = ? WHERE MANV = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getTen());
			pst.setString(2, t.getGioiTinh());
			pst.setString(3, t.getSoDT());
			pst.setString(4, t.getDiaChi());
			pst.setInt(5, t.getiD());
			result = pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(int id) {
		int result = 0;
		Connection con = JDBCUtil.getConnection();
		String sql = "UPDATE NHANVIEN SET TRANGTHAI = 0 WHERE MANV = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public NhanVienDTO selectByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVienDTO> getAll() {
		Connection con = JDBCUtil.getConnection();
		ArrayList<NhanVienDTO> result = new ArrayList<>();
		
		String sql = "SELECT * FROM NHANVIEN WHERE TRANGTHAI = 1";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("MANV");
				String ten = rs.getString("TENNV"); 
				String gioiTinh = rs.getString("GIOITINH");
				String sdt = rs.getString("SODT");
				String diaChi = rs.getString("DIACHI");
				NhanVienDTO nhanVien = new NhanVienDTO(id, ten, gioiTinh, sdt, diaChi);
				result.add(nhanVien);
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}

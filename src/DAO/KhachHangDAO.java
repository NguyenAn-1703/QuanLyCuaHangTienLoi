package DAO;


import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.KhachHangDTO;
import config.JDBCUtil;

public class KhachHangDAO implements DAOInterface<KhachHangDTO>{
	private static KhachHangDAO instance;
	
	// Constructor private không cho phép khởi tạo đối tượng từ bên ngoài
	private KhachHangDAO() {}
	
	public static KhachHangDAO getInstance() {
		if(instance == null) {
			instance = new KhachHangDAO();
		}
		return instance;
	}

	@Override
	public int insert(KhachHangDTO t) {
		Connection con = JDBCUtil.getConnection();
		String sql = "INSERT INTO KHACHHANG (TENKH, SODT, DIEM) VALUES (?, ?, ?)";
		int rowInserted = 0;
		try {
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, t.getTen());
			pst.setString(2, t.getSoDienThoai());
			pst.setBigDecimal(3, t.getDiem());
			
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
	public int update(KhachHangDTO t) {
		int result = 0;
		Connection con = JDBCUtil.getConnection();
		String sql = "UPDATE KHACHHANG SET TENKH = ?, SODT = ?, DIEM = ? WHERE MAKH = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getTen());
			pst.setString(2, t.getSoDienThoai());
			pst.setBigDecimal(3, t.getDiem());
			pst.setInt(4, t.getiD());
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
		String sql = "UPDATE KHACHHANG SET TRANGTHAI = 0 WHERE MAKH = ?";
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
	public KhachHangDTO selectByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<KhachHangDTO> getAll() {
		Connection con = JDBCUtil.getConnection();
		ArrayList<KhachHangDTO> result = new ArrayList<>();
		
		String sql = "SELECT * FROM KHACHHANG WHERE TRANGTHAI = 1";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("MAKH");
				String ten = rs.getString("TENKH"); 
				String sdt = rs.getString("SODT");
				BigDecimal diem = rs.getBigDecimal("DIEM");
				KhachHangDTO khach = new KhachHangDTO(id, ten, sdt, diem);
				result.add(khach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(con);
		return result;
	}


	
	
	

}

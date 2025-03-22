package GUI.Dialog;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatClientProperties;

import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.Validation;
import Components.InputFormItem;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import net.miginfocom.swing.MigLayout;

public class NhanVienDialog extends JDialog{
	ArrayList<InputFormItem> listIFI;
	String[] listItem = {"Tên","Giới tính","Số điện thoại","Địa chỉ"};
	JButton btnThem, btnHuy, btnCapNhat;
	JFrame owner;
	NhanVienBUS nhanVienBUS = NhanVienBUS.getInstance();
	DefaultTableModel model;
	JTable table;
	String type; //loại: update, insert, view
	
	public NhanVienDialog(JFrame owner, String title, DefaultTableModel model, JTable table, String type) {
		super(owner, title, true);
		this.owner = owner;
		this.model = model;
		listIFI = new ArrayList<>();
		btnThem = new JButton("Thêm");
		btnHuy = new JButton("Hủy");
		btnCapNhat = new JButton("Sửa");
        this.table = table;
		this.type = type; 
		this.initComponent();
	}
	
	public void initComponent() {
		this.setLayout(new MigLayout("wrap 2"));
		this.getContentPane().setBackground(Color.white);
		JLabel title = new JLabel();
		if(type.equals("update")) {
			title.setText("Sửa Thông Tin Nhân Viên");
		}
		else if(type.equals("insert")) {
			title.setText("Thêm Nhân Viên");
		}
		else {
			title.setText("Thông Tin Nhân Viên");
		}
		
		title.setFont(new Font("Segoe UI", Font.BOLD, 17));
		title.setForeground(Color.decode("#213862"));
		
		this.add(title, "span, pushx, center, gap 0 0 15 15");
		for(int i = 0; i < listItem.length; i++) {
			InputFormItem item = new InputFormItem(listItem[i]);
			listIFI.add(item);
			this.add(listIFI.get(i), "span, grow, pushx");
		}
		
		//Cài đặt nút bấm trong dialog
		btnThem.setBackground(Color.white);
		btnHuy.setBackground(Color.white);
		btnCapNhat.setBackground(Color.white);
		btnThem.putClientProperty(FlatClientProperties.STYLE, "focusWidth:0; innerFocusWidth:0");
		btnHuy.putClientProperty(FlatClientProperties.STYLE, "focusWidth:0; innerFocusWidth:0");
		btnCapNhat.putClientProperty(FlatClientProperties.STYLE, "focusWidth:0; innerFocusWidth:0");
		
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//Lấy dữ liệu string
					String ten = listIFI.get(0).getContent();
					String gioiTinh = listIFI.get(1).getContent();
					String soDT = listIFI.get(2).getContent();
					String diaChi = listIFI.get(3).getContent();
					//Kiểm tra dữ liệu
					if(ValidateNhanVien(ten, gioiTinh, soDT, diaChi)) {
						insert();
					}
				}
			}
		);
		
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				int id = (int)model.getValueAt(selectedRow, 0);
				//Lấy dữ liệu mới đã sửa
				String ten = listIFI.get(0).getContent();
				String gioiTinh = listIFI.get(1).getContent();
				String soDT = listIFI.get(2).getContent();
				String diaChi = listIFI.get(3).getContent();
				
				if(ValidateNhanVien(ten, gioiTinh, soDT, diaChi)) {
					update();
				}
				
			}
		});
		
		btnHuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		//Cài đặt form Sửa
		if(type.equals("update")) {
			//thêm nút cập nhật
			int selectedRow = table.getSelectedRow();
			String ten = (String)model.getValueAt(selectedRow, 1);
			String gioiTinh = (String)model.getValueAt(selectedRow, 2);
			String soDT = (String)model.getValueAt(selectedRow, 3);
			String diaChi = (String)model.getValueAt(selectedRow, 4);
			
			listIFI.get(0).setContent(ten);
			listIFI.get(1).setContent(gioiTinh);
			listIFI.get(2).setContent(soDT);
			listIFI.get(3).setContent(diaChi);
			this.add(btnCapNhat, "gaptop 20");
			// Thêm nút hủy
			this.add(btnHuy);
		}
		else if(type.equals("insert")){
			//thêm nút thêm
			this.add(btnThem, "gaptop 20");
			// Thêm nút hủy
			this.add(btnHuy);
		}
		else if(type.equals("view")) {
			int selectedRow = table.getSelectedRow();
			String ten = (String)model.getValueAt(selectedRow, 1);
			String gioiTinh = (String)model.getValueAt(selectedRow, 2);
			String soDT = (String)model.getValueAt(selectedRow, 3);
			String diaChi = (String)model.getValueAt(selectedRow, 4);
			
			listIFI.get(0).setContent(ten);
			listIFI.get(1).setContent(gioiTinh);
			listIFI.get(2).setContent(soDT);
			listIFI.get(3).setContent(diaChi);
			
			for(InputFormItem i : listIFI) {
				//lấy textField
				i.getText().setFocusable(false);
				i.getText().setEditable(false);
			}
			// Thêm nút hủy
			this.add(btnHuy, "gaptop 20");
		}

		
		this.setSize(350, 475);
		this.setLocationRelativeTo(owner);
		
		this.setVisible(true);
	}
	
	public void insert() {
		String ten = listIFI.get(0).getContent();
		String gioiTinh = listIFI.get(1).getContent();
		String soDT = listIFI.get(2).getContent();
		String diaChi = listIFI.get(3).getContent();
		
		NhanVienDTO nhanVien = new NhanVienDTO(ten, gioiTinh, soDT, diaChi);
		if(nhanVienBUS.insert(nhanVien) != 0) {
			JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
			model.addRow(new Object[] {nhanVien.getiD(), nhanVien.getTen(), nhanVien.getGioiTinh(), nhanVien.getSoDT(), nhanVien.getDiaChi()});
		}
		else {
			JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại");
		}
		dispose();
	}
	
	
	public void delete() {
		
	}
	
	public void update() {
		int selectedRow = table.getSelectedRow();
		int id = (int)model.getValueAt(selectedRow, 0);
		//Lấy dữ liệu mới đã sửa
		String ten = listIFI.get(0).getContent();
		String gioiTinh = listIFI.get(1).getContent();
		String soDT = listIFI.get(2).getContent();
		String diaChi = listIFI.get(3).getContent();
		
		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa thông tin nhân viên này ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION) {	
			NhanVienDTO nhanVienDTO = new NhanVienDTO(id, ten, gioiTinh, soDT, diaChi);
			if(nhanVienBUS.update(nhanVienDTO) != 0) {
				model.setValueAt(id, selectedRow, 0);
				model.setValueAt(ten, selectedRow, 1);
				model.setValueAt(gioiTinh, selectedRow, 2);
				model.setValueAt(soDT, selectedRow, 3);
				model.setValueAt(diaChi, selectedRow, 4);
				JOptionPane.showMessageDialog(null, "Sửa thông tin thành công");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Sửa thất bại");
				dispose();
			}
		}
	}
	
	public boolean ValidateNhanVien(String ten, String gioiTinh, String soDT, String diaChi) {
		if(Validation.isEmpty(ten)) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên không được để trống");
			return(false);
		}
		else if(Validation.isEmpty(gioiTinh)) {
			JOptionPane.showMessageDialog(null, "Giới tính nhân viên không được để trống");
			return(false);
		}
		else if(Validation.isEmpty(soDT)) {
			JOptionPane.showMessageDialog(null, "Số điện thoại nhân viên không được để trống");
			return(false);
		}
		else if(!Validation.isPhoneNumber(soDT)) {
			JOptionPane.showMessageDialog(null, "Số điện thoại nhân viên phải đúng định dạng");
			return(false);
		}
		else if(Validation.isEmpty(diaChi)) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống");
			return(false);
		}

		return(true);
	}
	
}

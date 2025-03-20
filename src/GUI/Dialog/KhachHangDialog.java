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
import javax.swing.table.DefaultTableModel;

import BUS.KhachHangBUS;
import BUS.Validation;
import Components.InputFormItem;
import DTO.KhachHangDTO;
import net.miginfocom.swing.MigLayout;

public class KhachHangDialog extends JDialog{
	ArrayList<InputFormItem> listIFI;
	String[] listItem = {"Tên","Số điện thoại","Điểm"};
	JButton btnThem, btnHuy, btnCapNhat;
	JFrame owner;
	KhachHangBUS khachHangBUS = KhachHangBUS.getInstance();
	DefaultTableModel model;
	//Cho chức năng sửa 
	int selectedRow = -1; 
	
	public KhachHangDialog(JFrame owner, String title, DefaultTableModel model) {
		super(owner, title, true);
		this.owner = owner;
		this.model = model;
		listIFI = new ArrayList<>();
		btnThem = new JButton("Thêm");
		btnHuy = new JButton("Hủy");
		btnCapNhat = new JButton("Sửa");
		this.initComponent();
	}
	
	public KhachHangDialog(JFrame owner, String title, DefaultTableModel model, int selectedRow) {
		super(owner, title, true);
		this.owner = owner;
		this.model = model;
		this.selectedRow = selectedRow;
		listIFI = new ArrayList<>();
		btnThem = new JButton("Thêm");
		btnHuy = new JButton("Hủy");
		btnCapNhat = new JButton("Sửa");

		this.initComponent();
	}
	
	public void initComponent() {
		this.setLayout(new MigLayout("wrap 2"));
		this.getContentPane().setBackground(Color.white);
		
		JLabel title = new JLabel("Thêm Khách Hàng");
		title.setFont(new Font("Segoe UI", Font.BOLD, 17));
		title.setForeground(Color.decode("#213862"));
		
		this.add(title, "span, pushx, center, gap 0 0 15 15");
		for(int i = 0; i < listItem.length; i++) {
			InputFormItem item = new InputFormItem(listItem[i]);
			listIFI.add(item);
			this.add(listIFI.get(i), "span, grow, pushx");
		}
		btnThem.setBackground(Color.white);
		btnHuy.setBackground(Color.white);
		btnCapNhat.setBackground(Color.white);
		
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//Lấy dữ liệu string
					String ten = listIFI.get(0).getContent();
					String soDT = listIFI.get(1).getContent();
					String diemString = listIFI.get(2).getContent();
					//Kiểm tra dữ liệu
					if(ValidateKhachHang(ten, soDT, diemString)) {
						insert();
					}
				}
			}
		);
		
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int id = (int)model.getValueAt(selectedRow, 0);
					//Lấy dữ liệu mới đã sửa
					String ten = listIFI.get(0).getContent();
					String soDT = listIFI.get(1).getContent();
					String diemString = listIFI.get(2).getContent();
					
					if(ValidateKhachHang(ten, soDT, diemString)) {
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
		
		//Cài đặt dữ liệu cũ trước khi sửa
		if(selectedRow != -1) {
			String ten = (String)model.getValueAt(selectedRow, 1);
			String soDT = (String)model.getValueAt(selectedRow, 2);
			BigDecimal diem = (BigDecimal)model.getValueAt(selectedRow, 3);
			
			listIFI.get(0).setContent(ten);
			listIFI.get(1).setContent(soDT);
			listIFI.get(2).setContent(diem + "");
			this.add(btnCapNhat, "gaptop 20");
		}
		else {
			this.add(btnThem, "gaptop 20");
		}
		
		this.add(btnHuy);
		
		this.setSize(350, 475);
		this.setLocationRelativeTo(owner);
		
		this.setVisible(true);
	}
	
	public void insert() {
		int id = (int)model.getValueAt(selectedRow, 0);
		//Lấy dữ liệu mới đã sửa
		String ten = listIFI.get(0).getContent();
		String soDT = listIFI.get(1).getContent();
		String diemString = listIFI.get(2).getContent();
			
		BigDecimal diem = BigDecimal.valueOf(Double.parseDouble(listIFI.get(2).getContent()));
		
		KhachHangDTO khach = new KhachHangDTO(ten, soDT, diem);
		khachHangBUS.insert(khach);
		
		model.addRow(new Object[] {khach.getiD(), khach.getTen(), khach.getSoDienThoai(), khach.getDiem()});
		dispose();
	}
	
	
	public void delete() {
		
	}
	
	public void update() {
		int id = (int)model.getValueAt(selectedRow, 0);
		//Lấy dữ liệu mới đã sửa
		String ten = listIFI.get(0).getContent();
		String soDT = listIFI.get(1).getContent();
		String diemString = listIFI.get(2).getContent();
		
		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa thông tin khách hàng này ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION) {
			BigDecimal diem = BigDecimal.valueOf(Double.parseDouble(diemString));
			
			KhachHangDTO khachHangDTO = new KhachHangDTO(id, ten, soDT, diem);
			if(khachHangBUS.update(khachHangDTO) != 0) {
				model.setValueAt(id, selectedRow, 0);
				model.setValueAt(ten, selectedRow, 1);
				model.setValueAt(soDT, selectedRow, 2);
				model.setValueAt(diem, selectedRow, 3);
				JOptionPane.showMessageDialog(null, "Sửa thông tin thành công");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Sửa thất bại");
				dispose();
			}
		}
	}
	
	public boolean ValidateKhachHang(String ten, String soDT, String diem) {
		if(Validation.isEmpty(ten)) {
			JOptionPane.showMessageDialog(null, "Tên khách hàng không được để trống");
			return(false);
		}
		else if(Validation.isEmpty(soDT)) {
			JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng không được để trống");
			return(false);
		}
		else if(!Validation.isPhoneNumber(soDT)) {
			JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng phải nhập đúng định dạng");
			return(false);
		}
		else if(Validation.isEmpty(diem)) {
			JOptionPane.showMessageDialog(null, "Điểm khách hàng không được để trống");
			return(false);
		}
		else if(!Validation.isPositiveNumber(diem)) {
			JOptionPane.showMessageDialog(null, "Điểm khách hàng phải là số dương");
			return(false);
		}
		return(true);
	}
	
}

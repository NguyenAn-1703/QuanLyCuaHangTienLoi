package GUI.Dialog;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import BUS.KhachHangBUS;
import Components.InputFormItem;
import DTO.KhachHangDTO;
import net.miginfocom.swing.MigLayout;

public class KhachHangDialog extends JDialog{
	ArrayList<InputFormItem> listIFI;
	String[] listItem = {"Tên","Số điện thoại","Điểm"};
	JButton btnThem, btnHuy;
	JFrame owner;
	KhachHangBUS khachHangBUS = KhachHangBUS.getInstance();
	DefaultTableModel model;
	
	public KhachHangDialog(JFrame owner, String title, DefaultTableModel model) {
		super(owner, title, true);
		this.owner = owner;
		this.model = model;
		listIFI = new ArrayList<>();
		btnThem = new JButton("Thêm");
		btnHuy = new JButton("Hủy");

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
		
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ten = listIFI.get(0).getContent();
				String soDT = listIFI.get(1).getContent();
				int diem = Integer.parseInt(listIFI.get(2).getContent());
				
				KhachHangDTO khach = new KhachHangDTO(ten, soDT, diem);
				khachHangBUS.insert(khach);
				
				model.addRow(new Object[] {khach.getiD(), khach.getTen(), khach.getSoDienThoai(), khach.getDiem()});
				dispose();
			}
		});
		
		btnHuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.add(btnThem, "gaptop 20");
		this.add(btnHuy);
		
		this.setSize(350, 475);
		this.setLocationRelativeTo(owner);
		
		this.setVisible(true);
	}
	
}

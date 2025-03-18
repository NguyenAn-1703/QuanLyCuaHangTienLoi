package GUI.Panel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatClientProperties;

import BUS.KhachHangBUS;
import Components.Function;
import Components.FunctionBar;
import DTO.KhachHangDTO;
import GUI.Dialog.KhachHangDialog;
import GUI.Listener.FunctionKHListener;
import net.miginfocom.swing.MigLayout;

public class KhachHangPnl extends JPanel{
	FunctionBar functionBar;
	JPanel mainPnl;
	JTable tableKhachHang;
	JScrollPane scrollPane;
	DefaultTableModel model;
	String[] headerTable = {"Mã khách hàng","Tên khách hàng","Số điện thoại","Điểm"};
	KhachHangBUS khachHangBUS;
	FunctionKHListener fnl;
	JFrame mainFrame;
	
	public KhachHangPnl(JFrame mainFrame) {
		functionBar = new FunctionBar();
		mainPnl = new JPanel();
		tableKhachHang = new JTable();
		scrollPane = new JScrollPane();
		model = new DefaultTableModel();
		fnl = new FunctionKHListener(model);
		this.mainFrame = mainFrame;
		
		this.initComponent();
	}
	
	public void initComponent() {
		this.setLayout(new MigLayout("wrap 1, insets 0 5 0 5", "[grow]", "[][grow]"));
		this.setBackground(Color.decode("#213862"));
		
		this.mainPnl.putClientProperty(FlatClientProperties.STYLE, "arc:10; background:#EEEEEE");
		
		model.setColumnIdentifiers(headerTable);
		tableKhachHang.setModel(model);
		tableKhachHang.setFocusable(false);
		tableKhachHang.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableKhachHang);
		
		mainPnl.setLayout(new MigLayout("wrap 1, insets 20 10 20 10"));
		
		mainPnl.add(scrollPane, "grow, push");
		
		this.add(functionBar, "grow");
		this.add(mainPnl, "grow");
		
		functionBar.getListButtons().get(1).addMouseListener(fnl);
		functionBar.getListButtons().get(0).addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {
					new KhachHangDialog(mainFrame, "Nhập thông tin", model);
				};
				
			}
		);
		
	}
	
}

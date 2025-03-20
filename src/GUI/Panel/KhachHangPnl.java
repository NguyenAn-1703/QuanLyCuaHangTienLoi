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
		khachHangBUS = KhachHangBUS.getInstance();
		fnl = new FunctionKHListener(tableKhachHang, model, functionBar, mainFrame);
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
		
		for(int i = 0; i < functionBar.getListButtons().size(); i++) {
			functionBar.getListButtons().get(i).addMouseListener(fnl);
		}	
		
		loadDataTable();
	}
	
	public void loadDataTable() {
		model.setRowCount(0); //Xóa dữ liệu cũ
		ArrayList<KhachHangDTO> listKH = khachHangBUS.getAll(); 
		for(KhachHangDTO kh : listKH) {
			model.addRow(new Object[] {kh.getiD(), kh.getTen(), kh.getSoDienThoai(), kh.getDiem()});
		}
	}
	
}

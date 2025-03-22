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
import BUS.NhanVienBUS;
import Components.Function;
import Components.FunctionBar;
import Components.SearchBar;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import GUI.Dialog.KhachHangDialog;
import GUI.Listener.FunctionKHListener;
import GUI.Listener.FunctionNVListener;
import net.miginfocom.swing.MigLayout;

public class NhanVienPnl extends JPanel{
	private FunctionBar functionBar;
	private SearchBar searchBar;
	private String[] filter = {"Tất cả","Số điện thoại","Tên","Mã","Địa chỉ"};
	private JPanel topPanel;
	private JPanel mainPnl;
	private JTable tableNhanVien;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private String[] headerTable = {"Mã nhân viên","Tên nhân viên","Giới tính","Số điện thoại","Địa chỉ"};
	private NhanVienBUS nhanVienBUS;
	private FunctionNVListener fnl;
	private JFrame mainFrame;
	
	public NhanVienPnl(JFrame mainFrame) {
		functionBar = new FunctionBar();
		searchBar = new SearchBar(filter);
		topPanel = new JPanel();
		mainPnl = new JPanel();
		tableNhanVien = new JTable();
		scrollPane = new JScrollPane();
		model = new DefaultTableModel();
		nhanVienBUS = NhanVienBUS.getInstance();
		fnl = new FunctionNVListener(this);
		this.mainFrame = mainFrame;
		
		this.initComponent();
	}
	
	public void initComponent() {
		this.setLayout(new MigLayout("wrap 1, insets 0 5 0 5", "[grow]", "[][grow]"));
		this.setBackground(Color.decode("#213862"));
		this.topPanel.setLayout(new MigLayout("wrap 3", "[][grow][]"));
		this.topPanel.putClientProperty(FlatClientProperties.STYLE, "arc:10; background:#FFFFFF");
		this.mainPnl.putClientProperty(FlatClientProperties.STYLE, "arc:10; background:#EEEEEE");
		
		model.setColumnIdentifiers(headerTable);
		tableNhanVien.setModel(model);
		tableNhanVien.setFocusable(false);
		tableNhanVien.getTableHeader().setReorderingAllowed(false);
		tableNhanVien.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(tableNhanVien);
		
		topPanel.add(functionBar, "skip");
		topPanel.add(searchBar);
		
		mainPnl.setLayout(new MigLayout("wrap 1, insets 20 10 20 10"));
		mainPnl.add(scrollPane, "grow, push");
		
		this.add(topPanel, "grow");
		this.add(mainPnl, "grow");
		
		for(int i = 0; i < functionBar.getListButtons().size(); i++) {
			functionBar.getListButtons().get(i).addMouseListener(fnl);
		}	
		
		searchBar.getComboBox().addItemListener(fnl);
		searchBar.getTextFieldSearch().addKeyListener(fnl);
		loadDataTable(nhanVienBUS.getAll());
	}
	
	public void loadDataTable(ArrayList<NhanVienDTO> list) {
		model.setRowCount(0); //Xóa dữ liệu cũ
		for(NhanVienDTO nv : list) {
			model.addRow(new Object[] {nv.getiD(), nv.getTen(), nv.getGioiTinh(), nv.getSoDT(), nv.getDiaChi()});
		}
	}

	public FunctionBar getFunctionBar() {
		return functionBar;
	}

	public void setFunctionBar(FunctionBar functionBar) {
		this.functionBar = functionBar;
	}

	public SearchBar getSearchBar() {
		return searchBar;
	}

	public void setSearchBar(SearchBar searchBar) {
		this.searchBar = searchBar;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public JPanel getMainPnl() {
		return mainPnl;
	}

	public void setMainPnl(JPanel mainPnl) {
		this.mainPnl = mainPnl;
	}

	public JTable getTableNhanVien() {
		return tableNhanVien;
	}

	public void setTableNhanVien(JTable tableNhanVien) {
		this.tableNhanVien = tableNhanVien;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public String[] getHeaderTable() {
		return headerTable;
	}

	public void setHeaderTable(String[] headerTable) {
		this.headerTable = headerTable;
	}

	public NhanVienBUS getNhanVienBUS() {
		return nhanVienBUS;
	}

	public void setKhachHangBUS(NhanVienBUS nhanVienBUS) {
		this.nhanVienBUS = nhanVienBUS;
	}

	public FunctionNVListener getFnl() {
		return fnl;
	}

	public void setFnl(FunctionNVListener fnl) {
		this.fnl = fnl;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	
}

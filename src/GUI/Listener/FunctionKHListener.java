package GUI.Listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BUS.KhachHangBUS;
import Components.FunctionBar;
import Components.SearchBar;
import DTO.KhachHangDTO;
import GUI.Dialog.KhachHangDialog;
import GUI.Panel.KhachHangPnl;

public class FunctionKHListener implements MouseListener, ItemListener, KeyListener{

	JTable table;
	DefaultTableModel model;
	KhachHangBUS khachHangBUS;
	FunctionBar functionBar;
	SearchBar searchBar;
	JFrame mainFrame;
	KhachHangPnl pnlKhachHang;
	
	public FunctionKHListener(KhachHangPnl pnlKhachHang) {
		this.table = pnlKhachHang.getTableKhachHang();
		this.model = pnlKhachHang.getModel();
		this.functionBar = pnlKhachHang.getFunctionBar();
		this.searchBar = pnlKhachHang.getSearchBar();
		this.mainFrame = pnlKhachHang.getMainFrame();
		this.khachHangBUS = KhachHangBUS.getInstance();
		this.pnlKhachHang = pnlKhachHang;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// nút thêm
		if(e.getSource() == functionBar.getListButtons().get(0)) {
			new KhachHangDialog(mainFrame, "Nhập thông tin", model, table, "insert");
		}
		// nút xóa
		else if(e.getSource() == functionBar.getListButtons().get(1)) {
			int selectedRow = table.getSelectedRow();
			if(selectedRow != -1) {
				int maKH = (int)model.getValueAt(selectedRow, 0);
				int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa khách hàng này ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					if(khachHangBUS.delete(maKH) != 0) {
						JOptionPane.showMessageDialog(null, "Xóa thành công !");
						model.removeRow(selectedRow);
					}
					else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xóa");
			}
		}
		//nút sửa
		else if(e.getSource() == functionBar.getListButtons().get(2)) {
			int selectedRow = table.getSelectedRow();
			if(selectedRow != -1) {
				new KhachHangDialog(mainFrame, "Sửa thông tin khách hàng", model, table, "update");
			}
			else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa");
			}
		}
		// nút chi tiết
		else if(e.getSource() == functionBar.getListButtons().get(3)) {
			int selectedRow = table.getSelectedRow();
			if(selectedRow != -1) {
				new KhachHangDialog(mainFrame, "Chi tiết khách hàng", model, table, "view");
			}
			else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xem chi tiết");
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		String text = searchBar.getContent();
		String choice = searchBar.getChoice();
		pnlKhachHang.loadDataTable(khachHangBUS.search(text, choice));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		String text = searchBar.getContent();
		String choice = searchBar.getChoice();
		pnlKhachHang.loadDataTable(khachHangBUS.search(text, choice));
	}

}

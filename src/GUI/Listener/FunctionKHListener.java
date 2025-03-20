package GUI.Listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BUS.KhachHangBUS;
import Components.FunctionBar;
import DTO.KhachHangDTO;
import GUI.Dialog.KhachHangDialog;

public class FunctionKHListener implements MouseListener{

	JTable table;
	DefaultTableModel model;
	KhachHangBUS khachHangBUS;
	FunctionBar functionBar;
	JFrame mainFrame;
	
	public FunctionKHListener(JTable table, DefaultTableModel model, FunctionBar functionBar, JFrame mainFrame) {
		this.table = table;
		this.model = model;
		this.functionBar = functionBar;
		this.mainFrame = mainFrame;
		khachHangBUS = KhachHangBUS.getInstance();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// nút thêm
		if(e.getSource() == functionBar.getListButtons().get(0)) {
			new KhachHangDialog(mainFrame, "Nhập thông tin", model);
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
				new KhachHangDialog(mainFrame, "Sửa thông tin khách hàng", model, selectedRow);
			}
			else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa");
			}
		}
		// nút chi tiết
		else if(e.getSource() == functionBar.getListButtons().get(3)) {
			model.setRowCount(0); //Xóa dữ liệu cũ
			ArrayList<KhachHangDTO> listKH = khachHangBUS.getAll(); 
				
			for(KhachHangDTO kh : listKH) {
				model.addRow(new Object[] {kh.getiD(), kh.getTen(), kh.getSoDienThoai(), kh.getDiem()});
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

}

package GUI.Listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;

public class FunctionKHListener implements MouseListener{

	DefaultTableModel model;
	KhachHangBUS khachHangBUS;
	
	public FunctionKHListener(DefaultTableModel model) {
		this.model = model;
		khachHangBUS = KhachHangBUS.getInstance();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		model.setRowCount(0); //Xóa dữ liệu cũ
		ArrayList<KhachHangDTO> listKH = khachHangBUS.getAll(); 
			
		for(KhachHangDTO kh : listKH) {
			model.addRow(new Object[] {kh.getiD(), kh.getTen(), kh.getSoDienThoai(), kh.getDiem()});
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

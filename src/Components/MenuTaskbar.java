package Components;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.Panel.KhachHangPnl;
import GUI.Panel.NhanVienPnl;
import Main.MainFrame;
import net.miginfocom.swing.MigLayout;

public class MenuTaskbar extends JPanel{
	ArrayList<ItemTaskbar> listItem;
	MainFrame mainFrame;
	
	public MenuTaskbar(MainFrame mainFrame) {
		listItem = new ArrayList<>();
		this.mainFrame = mainFrame;
		this.initComponent();
	}

	public void initComponent() {
		this.setLayout(new MigLayout("wrap 1"));
		this.setBackground(Color.white);
		String[][] listItemName = {
			{"Trang chủ","icons8-home-office-48.png"},
			{"Quản lý hóa đơn","icons8-bill-48 (2).png"},
			{"Quản lý khách hàng","icons8-client-management-48.png"},
			{"Quản lý nhân viên","icons8-home-office-48.png"},
			{"Quản lý phiếu nhập","icons8-van-48.png"},
			{"Thống kê","icons8-graph-report-script-48.png"}
		};
		
		for(int i = 0; i < listItemName.length; i++) {
			ItemTaskbar itemTaskBar = new ItemTaskbar(listItemName[i][0], listItemName[i][1]);
			listItem.add(itemTaskBar);
			this.add(listItem.get(i), "grow");
		}
		
		listItem.get(2).addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mainFrame.setPnl(new KhachHangPnl(mainFrame));
			}
		});
		
		listItem.get(3).addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mainFrame.setPnl(new NhanVienPnl(mainFrame));
			}
		});
	}
}

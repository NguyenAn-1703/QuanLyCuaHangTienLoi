package Components;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MenuTaskbar extends JPanel{
	ArrayList<ItemTaskbar> listItem;
	
	public MenuTaskbar() {
		listItem = new ArrayList<>();
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
	}
}

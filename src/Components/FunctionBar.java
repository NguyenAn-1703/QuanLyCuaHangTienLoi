package Components;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

public class FunctionBar extends JPanel{
	private ArrayList<Function> listButtons;
	
	String[][] listFunction = {
			{"icons8-add-50.png", "Thêm", "icons8-add-50 h.png"},
			{"icons8-x-50.png", "Xóa", "icons8-x-50 h.png"},
			{"icons8-sync-50.png", "Sửa", "icons8-sync-50 h.png"},
			{"icons8-detail-50.png", "Chi tiết", "icons8-detail-50 h.png"},
	};
	
	public FunctionBar() {
		listButtons = new ArrayList<>();
		this.initComponent();
	}
	
	public void initComponent() {
		this.setBackground(Color.decode("#FFFFFF"));
		this.setLayout(new MigLayout("insets 10 30 10 0"));
		this.putClientProperty(FlatClientProperties.STYLE, "arc:10");
		for(int i = 0; i < listFunction.length; i++) {
			Function function = new Function(listFunction[i][0], listFunction[i][1], listFunction[i][2]);
			listButtons.add(function);
			this.add(listButtons.get(i));
		}
	}

	public ArrayList<Function> getListButtons() {
		return listButtons;
	}

	public void setListButtons(ArrayList<Function> listButtons) {
		this.listButtons = listButtons;
	}
}

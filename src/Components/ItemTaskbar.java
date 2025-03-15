package Components;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ItemTaskbar extends JPanel{
	JLabel content, img;
	
	public void initComponent() {
		this.setLayout(new MigLayout("wrap 2", "[][grow]"));
	}
	
	public ItemTaskbar() {
		this.initComponent();
	}

}

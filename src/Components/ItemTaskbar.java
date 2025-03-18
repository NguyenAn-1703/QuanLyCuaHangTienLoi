package Components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

public class ItemTaskbar extends JPanel implements MouseListener{
	JLabel content, img;
	
	public void initComponent() {
		this.setLayout(new MigLayout("wrap 2", "[][grow]"));
		this.setBackground(Color.white);
		this.putClientProperty(FlatClientProperties.STYLE, "arc : 10");
		this.add(img, "gap 0 10 0 0");
		this.add(this.content);
		this.addMouseListener(this);
	}
	
	public ItemTaskbar(String content, String link) {
		this.content = new JLabel(content) ;
		ImageIcon icon = new ImageIcon("src/img/" + link); 
		this.img = new JLabel();
		this.img.setIcon(icon);
		this.initComponent();
	}
	
	public void setForeground() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setBackground(Color.decode("#EEEEEE"));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setBackground(Color.decode("#FFFFFF"));
		
	}

}

package Components;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

public class Function extends JPanel implements MouseListener{
	String link, linkHover, content;
	private JLabel imgLabel, contentLabel;
	
	public Function(String link, String content) {
		this.link = link; 
		this.content = content;
		this.imgLabel = new JLabel(); 
		this.contentLabel = new JLabel();
		this.initComponent();
	}
	
	public Function(String link, String content, String linkHover) {
		this.link = link; 
		this.linkHover = linkHover; 
		this.content = content;
		this.imgLabel = new JLabel(); 
		this.contentLabel = new JLabel();
		this.initComponent();
	}
	
	public void initComponent() {
		this.setBackground(Color.white);
		this.setLayout(new MigLayout("wrap 1, insets 0 10 0 10"));
		this.imgLabel.setIcon(new ImageIcon("src/img/" + this.link));
		this.contentLabel.setText(this.content);
		
		this.add(imgLabel);
		this.add(contentLabel, "center");
		this.addMouseListener(this);
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
		if(linkHover != null) {
			imgLabel.setIcon(new ImageIcon("src/img/" + this.linkHover));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(linkHover != null) {
			imgLabel.setIcon(new ImageIcon("src/img/" + this.link));
		}
	}

	public JLabel getImgLabel() {
		return imgLabel;
	}

	public void setImgLabel(JLabel imgLabel) {
		this.imgLabel = imgLabel;
	}

	public JLabel getContentLabel() {
		return contentLabel;
	}

	public void setContentLabel(JLabel contentLabel) {
		this.contentLabel = contentLabel;
	}
	
	
}

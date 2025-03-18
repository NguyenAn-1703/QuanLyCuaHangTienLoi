package Main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import Components.MenuTaskbar;
import GUI.Panel.KhachHangPnl;
import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame{
	JPanel leftPanel, topPanel, mainPanel;
	JLabel avatar, tenNguoiDung;
	MenuTaskbar menuTaskBar;
	
	public MainFrame() {
		String[] tk = {"src/img/icons8-avatar-100.png", "Nguyễn Ân"};
		leftPanel = new JPanel();
		topPanel = new JPanel();
		mainPanel = new JPanel();
		menuTaskBar = new MenuTaskbar();
		menuTaskBar.setBackground(Color.decode("#1C2743"));
		menuTaskBar.putClientProperty(FlatClientProperties.STYLE, "arc : 10");
		
		avatar = new JLabel();
		tenNguoiDung = new JLabel();
		avatar.setIcon(new ImageIcon(tk[0]));
		tenNguoiDung.setText(tk[1]);
		tenNguoiDung.setFont(new Font("Segoe UI",Font.BOLD, 16));

		this.initComponent();
	}
	
	public void initComponent() {
		this.setTitle("Cửa hàng tiện lợi Phúc Khang");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.decode("#213862"));;
		
		this.setLayout(new MigLayout("wrap 2", "[][grow]"));
		
		leftPanel.setLayout(new MigLayout("wrap 1"));
		leftPanel.putClientProperty(FlatClientProperties.STYLE, "arc : 10");
		leftPanel.setBackground(Color.white);
		leftPanel.add(avatar, "center");
		leftPanel.add(tenNguoiDung, "center");
		leftPanel.add(menuTaskBar, "pushy, growy");
		this.add(leftPanel, "pushy, growy");
		
		this.setPnl();
		
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);
	}
	
	public void setPnl() {
		KhachHangPnl khachHangPnl = new KhachHangPnl(this);
		this.add(khachHangPnl, "grow");
	}
	
}

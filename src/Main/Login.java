package Main;

import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import Components.InputFormItem;
import net.miginfocom.swing.MigLayout;

public class Login extends JFrame{
	JLabel img, quenMK, dangNhap;
	JPanel inputForm, btnDangNhap;
	JLabel lblTitle;
	
	public Login() {
		img = new JLabel();
		quenMK = new JLabel();
		inputForm = new JPanel();
		lblTitle = new JLabel();
		btnDangNhap = new JPanel();
		dangNhap = new JLabel();
		this.initComponent();
	}
	
	public void initComponent() {
		this.setTitle("Đăng Nhập");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.decode("#213862"));
		
		this.setLayout(new MigLayout("wrap 2", "[50%][50%]", "[grow]"));
		
		Image signupImage = new ImageIcon("src/img/signup.png").getImage();
		ImageIcon signupIcon = new ImageIcon(signupImage.getScaledInstance(400, 350, Image.SCALE_SMOOTH));
		img.setIcon(signupIcon);
		
		inputForm.setLayout(new MigLayout("wrap 1, insets 80 10 0 10"));
		inputForm.putClientProperty(FlatClientProperties.STYLE, "background:#FFFFFF; arc:10");
		lblTitle.setText("WELCOME");
		lblTitle.putClientProperty(FlatClientProperties.STYLE, "foreground:#213862");
		lblTitle.setFont(new Font("Roboto",Font.BOLD, 25));
		
		InputFormItem userName = new InputFormItem("Tên đăng nhập");
		InputFormItem userPass = new InputFormItem("Mật khẩu", true);
		
		quenMK.setText("Quên mật khẩu");
		quenMK.setFont(new Font("Segoe UI",Font.ITALIC, 17));
		
		dangNhap.setText("ĐĂNG NHẬP");
		dangNhap.setFont(new Font("Segoe UI",Font.BOLD, 17));
		dangNhap.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
		btnDangNhap.putClientProperty(FlatClientProperties.STYLE, "arc:99; background:#213862");
		btnDangNhap.add(dangNhap);
		btnDangNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                JPanel panel = (JPanel)evt.getSource();
                panel.setBackground(Color.decode("#1C2743"));
            }

            @Override
            public void mousePressed(MouseEvent evt) {
            	pnlMousePress();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                JPanel panel = (JPanel)evt.getSource();
                panel.setBackground(Color.decode("#213862"));
            }
		});
		
		inputForm.add(lblTitle, "pushx, center");
		inputForm.add(userName, "grow");
		inputForm.add(userPass, "grow");
		inputForm.add(quenMK, "right");
		inputForm.add(btnDangNhap, "grow");
		
		this.add(img, "center");
		this.add(inputForm, "grow");
		
		this.setSize(900, 500);
		this.setLocationRelativeTo(null);
	}
	
	public void pnlMousePress() {
		this.dispose();
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
}

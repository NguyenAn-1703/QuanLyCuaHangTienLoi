package Components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

public class InputFormItem extends JPanel{
	JLabel tieuDe;
	JTextField text;
	JPasswordField pass;
	boolean isPass = false;
	
	public void initComponent() {
		this.setLayout(new MigLayout("wrap 1", "[grow]"));
		this.setBackground(Color.white);
		this.tieuDe.setPreferredSize(new Dimension(50, 1));
		if(this.isPass) {
			this.pass = new JPasswordField();
			this.pass.setPreferredSize(new Dimension(1, 30));
			this.pass.putClientProperty(FlatClientProperties.STYLE, "focusWidth:0; innerFocusWidth:0");
			this.add(tieuDe);
			this.add(pass, "grow");
		}
		else {
			this.text = new JTextField();
			this.text.putClientProperty(FlatClientProperties.STYLE, "focusWidth:0; innerFocusWidth:0");
			this.add(tieuDe);
			this.add(text, "grow");
		}
		
	}

	public InputFormItem(String tieuDe) {
		this.tieuDe = new JLabel(tieuDe);
		this.initComponent();
	}
	
	public InputFormItem(String tieuDe, boolean isPass) {
		this.tieuDe = new JLabel(tieuDe);
		this.isPass = isPass;
		this.initComponent();
	}
	
	public void setContent(String text) {
		this.text.setText(text);
	}
	
	public String getContent() {
		return(this.text.getText());
	}
	
	public char[] getPwd() {
		return(this.pass.getPassword());
	}
	
}

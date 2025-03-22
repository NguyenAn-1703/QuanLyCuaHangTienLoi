package Components;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JComboBox;
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
	JComboBox<String> ComboBox;
	
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
		else if(this.ComboBox != null) {
			this.ComboBox.setPreferredSize(new Dimension(1, 30));
			this.ComboBox.putClientProperty(FlatClientProperties.STYLE, "focusWidth:0; innerFocusWidth:0");
			this.add(tieuDe);
			this.add(ComboBox, "grow");
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
	
	public InputFormItem(String tieuDe, String type) {
		this.tieuDe = new JLabel(tieuDe);
		if(type.equals("combobox")) {
			this.ComboBox = new JComboBox<String>();
			this.initComponent();
		}
	}
	
	public JTextField getText() {
		return text;
	}

	public void setText(JTextField text) {
		this.text = text;
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
	
	public void setContentComboBox(String... list) {
		for(String i : list) {
			this.ComboBox.addItem(i);
		}
	}
	
	public String getContentComboBox() {
		return((String)this.ComboBox.getSelectedItem());
	}
	
}

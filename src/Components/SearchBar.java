package Components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

public class SearchBar extends JPanel{
	JComboBox<String> comboBox;
	JTextField textFieldSearch;
	
	public SearchBar(String... option) {
		comboBox = new JComboBox<>(option);
		textFieldSearch = new JTextField();
		this.initComponent();
	}
	
	public void initComponent() {
		this.setLayout(new MigLayout("wrap 2", "[][grow]"));
		comboBox.setFocusable(false);
		textFieldSearch.putClientProperty(FlatClientProperties.STYLE, "innerFocusWidth:0; focusWidth:0");
		textFieldSearch.putClientProperty("JTextField.placeholderText", "Nhập nội dung tìm kiếm ...");
		textFieldSearch.putClientProperty("JTextField.showClearButton", true);
		this.setPreferredSize(new Dimension(350, 30));
		this.setBackground(Color.white);
		this.add(comboBox, "grow");
		this.add(textFieldSearch, "grow");
	}
	
	public String getContent() {
		return(textFieldSearch.getText());
	}
	
	public String getChoice() {
		return((String)comboBox.getSelectedItem());
	}

	public JTextField getTextFieldSearch() {
		return textFieldSearch;
	}

	public void setTextFieldSearch(JTextField textFieldSearch) {
		this.textFieldSearch = textFieldSearch;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}
	
	
	
}

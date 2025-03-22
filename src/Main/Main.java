package Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatIntelliJLaf;

import config.JDBCUtil;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;

import javax.imageio.ImageIO;

public class Main extends JPanel {
	public static void main(String[] args) {
        Font globalFont = new Font("Segoe UI", Font.PLAIN, 16);
        UIManager.put("Label.font", globalFont);
        UIManager.put("Button.font", globalFont);
        UIManager.put("TextField.font", globalFont);
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("TableHeader.hoverBackground", Color.decode("#213862"));
        UIManager.put("TableHeader.background", Color.decode("#213862"));
        UIManager.put("TableHeader.foreground", Color.white);
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 12));
		FlatIntelliJLaf.setup();
		Login login = new Login();
		login.setVisible(true);
		
	}
}

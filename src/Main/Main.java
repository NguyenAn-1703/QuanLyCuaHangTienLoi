package Main;

import javax.swing.*;

import com.formdev.flatlaf.FlatIntelliJLaf;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main extends JPanel {
	public static void main(String[] args) {
        Font globalFont = new Font("Segoe UI", Font.PLAIN, 16);
        UIManager.put("Label.font", globalFont);
        UIManager.put("Button.font", globalFont);
        UIManager.put("TextField.font", globalFont);
        UIManager.put("PasswordField.showRevealButton", true);
		FlatIntelliJLaf.setup();
		Login login = new Login();
		login.setVisible(true);
	}
}

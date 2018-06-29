package poo.ricardo.pdv_ui.tabs;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import poo.ricardo.pdv_ui.MainWindow;

public class LoginPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JButton confirm=null;
	private JPanel panel1=null;
	
	public LoginPanel(MainWindow mw) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panel1=new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		confirm = new JButton("Fazer Login");
		confirm.setMaximumSize(new Dimension(100,20));
		add(Box.createVerticalGlue());
		add(panel1);
		panel1.add(Box.createHorizontalGlue());
		panel1.add(confirm);
		panel1.add(Box.createHorizontalGlue());
		add(Box.createVerticalGlue());
		
		confirm.addActionListener((a)->{
			mw.login();
		});
	}
}

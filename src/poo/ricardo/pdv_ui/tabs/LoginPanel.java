package poo.ricardo.pdv_ui.tabs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import poo.ricardo.pdv_ui.MainWindow;

public class LoginPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private final MainWindow parent;
	private JTextField login=null;
	private JPasswordField password=null;
	private JButton confirm=null;
	public LoginPanel(MainWindow p) {
		super(new GridBagLayout());
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));
		JPanel panel4 = new JPanel();
		panel4.setLayout(new BoxLayout(panel4,BoxLayout.X_AXIS));
		parent = p;

		login = new JTextField(30);
		password = new JPasswordField(30);
		confirm = new JButton("Fazer Login");

		panel1.add(Box.createHorizontalGlue());
		panel1.add(panel2);
		panel1.add(Box.createHorizontalGlue());
		
		panel2.add(Box.createVerticalGlue());
		panel2.add(panel3);
		panel2.add(panel4);
		
		panel3.add(new JLabel("Login: "));
		panel3.add(login);
		panel4.add(new JLabel("Senha: "));
		panel4.add(password);
		panel2.add(confirm);
		panel2.add(Box.createVerticalGlue());
		add(panel1,new GridBagConstraints());
		
		confirm.addActionListener((a)->{
			parent.login();
		});
	}
}
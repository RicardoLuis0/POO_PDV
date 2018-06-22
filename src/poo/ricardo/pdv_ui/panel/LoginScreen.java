package poo.ricardo.pdv_ui.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import poo.ricardo.pdv_ui.MainWindow;

public class LoginScreen extends JPanel{
	private static final long serialVersionUID = 1L;
	private final MainWindow parent;
	private JTextField login=null;
	private JPasswordField password=null;
	private JButton confirm=null;
	public LoginScreen(MainWindow p) {
		super(new GridBagLayout());
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS));
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		parent = p;

		login = new JTextField(30);
		password = new JPasswordField(30);
		confirm = new JButton("Fazer Login");

		panel2.add(new JLabel("Login: "));
		panel2.add(login);
		panel3.add(new JLabel("Senha: "));
		panel3.add(password);
		panel4.add(panel2);
		panel5.add(panel3);
		panel6.add(confirm);
		panel1.add(panel4);
		panel1.add(panel5);
		panel1.add(panel6);
		add(panel1,new GridBagConstraints());
		
		confirm.addActionListener((a)->{
			parent.login();
		});
	}
}

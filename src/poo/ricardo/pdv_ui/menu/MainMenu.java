package poo.ricardo.pdv_ui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import poo.ricardo.pdv_ui.MainWindow;

public class MainMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private final MainWindow parent;
	private JMenu window = null;
	private JMenuItem exit = null;
	private JMenu account = null;
	private JMenuItem m_logoff = null;
	
	public MainMenu(MainWindow p){
		super();
		parent = p;
		window = new JMenu("Janela");
		exit = new JMenuItem("Sair");
		account = new JMenu("Conta");
		m_logoff = new JMenuItem("Deslogar");

		this.add(window);
		this.add(account);
		window.add(exit);
		account.add(m_logoff);

		exit.addActionListener((a)->{
			System.exit(0);
		});

		m_logoff.addActionListener((a)->{
			parent.logoff();
		});

		logoff();
	}
	
	public void login() {
		account.setEnabled(true);
	}
	
	public void logoff() {
		account.setEnabled(false);
	}
}

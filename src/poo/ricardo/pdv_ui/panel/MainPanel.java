package poo.ricardo.pdv_ui.panel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import poo.ricardo.pdv_ui.MainWindow;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private final MainWindow parent;
	private JButton novaVenda;
	private JButton deslogar;
	
	public MainPanel(MainWindow p) {
		parent=p;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		novaVenda = new JButton("Nova Venda");
		deslogar = new JButton("Deslogar");
		add(novaVenda);
		add(deslogar);
		deslogar.addActionListener((arg0)->{
			parent.logoff();
		});
		novaVenda.addActionListener((arg0)->{
			parent.novaVenda();
		});
	}
}

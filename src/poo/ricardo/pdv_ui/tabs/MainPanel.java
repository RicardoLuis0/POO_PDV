package poo.ricardo.pdv_ui.tabs;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import poo.ricardo.pdv_ui.MainWindow;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private final MainWindow parent;
	private JPanel panel1;
	private JPanel panel2;
	private JButton novaVenda;
	private JButton deslogar;
	
	public MainPanel(MainWindow p) {
		parent=p;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		novaVenda = new JButton("Nova Venda");
		deslogar = new JButton("Deslogar");
		
		panel1=new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel2=new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		
		add(Box.createVerticalGlue());
		add(panel1);
		panel1.add(Box.createHorizontalGlue());
		panel1.add(novaVenda);
		panel1.add(Box.createHorizontalGlue());
		add(panel2);
		panel2.add(Box.createHorizontalGlue());
		panel2.add(deslogar);
		panel2.add(Box.createHorizontalGlue());
		add(Box.createVerticalGlue());
		
		deslogar.addActionListener((arg0)->{
			parent.logoff();
		});
		novaVenda.addActionListener((arg0)->{
			parent.novaVenda();
		});
	}
}

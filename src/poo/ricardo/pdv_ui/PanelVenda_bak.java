package poo.ricardo.pdv_ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import poo.ricardo.pdv_ui.tabs.FirstTab;
import poo.ricardo.pdv_ui.tabs.ClientPanel;

public class PanelVenda_bak extends JPanel {
	private static final long serialVersionUID = 1L;
	private final MainWindow parent;
	private JPanel topmenu = null;
	private JPanel bottommenu = null;
	private JTabbedPane tpane = null;
	private DadosVenda dados = null;
	private FirstTab tab1 = null;
	private ClientPanel tab2 = null;
	private JButton cancelar = null;
	public PanelVenda_bak(MainWindow p) {
		parent=p;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		topmenu = new JPanel();
		topmenu.setLayout(new BoxLayout(topmenu, BoxLayout.X_AXIS));
		add(topmenu);
		tpane = new JTabbedPane();
		add(tpane);
		bottommenu = new JPanel();
		bottommenu.setLayout(new BoxLayout(bottommenu, BoxLayout.X_AXIS));
		add(bottommenu);
		//tab1 = new FirstTab(this);
		//tab2 = new SecondTab(this);
		tpane.add(tab1.getTabName(), tab1);
		tpane.add(tab2.getTabName(), tab2);
		cancelar = new JButton("Cancelar Venda");
		cancelar.setAlignmentX(RIGHT_ALIGNMENT);
		bottommenu.add(cancelar);
		cancelar.addActionListener((a)->{
			cancelarVenda();
		});
	}
	
	public void iniciarVenda() {
		dados = new DadosVenda();
	}
	
	public void cancelarVenda() {
		dados=null;
		parent.sairVenda();
	}

	public DadosVenda getDados() {
		return dados;
	}

	public MainWindow get_parent() {
		return parent;
	}
}

package poo.ricardo.pdv_ui;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import poo.ricardo.pdv_ui.tabs.ClientPanel;
import poo.ricardo.pdv_ui.utils.Cliente;
import poo.ricardo.pdv_ui.utils.ClientCallOnConfirm;
import poo.ricardo.pdv_ui.utils.ClientCallOnCancel;
import poo.ricardo.pdv_ui.utils.StringableListModel;

public class PanelVenda extends JPanel {
	private static final long serialVersionUID = 1L;
	private final MainWindow parent;
	private DadosVenda dados = null;
	private CardLayout mainlayout=null;
	private JPanel panelvenda = null;
	private JPanel topmenu = null;
	private JPanel mainpanel=null;
	private JPanel mainpanelleft=null;
	private JPanel mainpanelright=null;
	private JPanel mainpanellefttop=null;
	private JPanel mainpanelleftbottom=null;
	private JPanel bottommenu = null;
	private JList<String> listaprod = null;
	private JScrollPane listaprodscroll=null;
	private JButton cancelar = null;
	private JButton addproduto = null;
	private JButton removeproduto = null;
	private JButton selectcliente=null;
	private JButton finalizarvenda=null;
	private ClientPanel clientpanel=null;
	private Cliente cliente=null;
	
	private JLabel clientelabel=null;
	
	public PanelVenda(MainWindow p) {
		mainlayout=new CardLayout();
		setLayout(mainlayout);
		parent=p;
		panelvenda=new JPanel();
		panelvenda.setLayout(new BoxLayout(panelvenda, BoxLayout.Y_AXIS));
		topmenu = new JPanel();
		topmenu.setLayout(new BoxLayout(topmenu, BoxLayout.X_AXIS));
		panelvenda.add(topmenu);
		
		add(panelvenda,"Venda");
		
		clientpanel=new ClientPanel(this,new ClientCallOnConfirm() {
			@Override
			public void confirm(Cliente c) {
				confirmarCliente(c);
			}
		},new ClientCallOnCancel() {
			@Override
			public void cancel() {
				sairCliente();
			}
		});
		
		add(clientpanel,"Cliente");
		
		mainpanel=new JPanel();
		mainpanelleft=new JPanel();
		mainpanelright=new JPanel();
		mainpanellefttop=new JPanel();
		mainpanelleftbottom=new JPanel();
		
		mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.X_AXIS));
		mainpanelleft.setLayout(new BoxLayout(mainpanelleft, BoxLayout.Y_AXIS));
		mainpanellefttop.setLayout(new BoxLayout(mainpanellefttop, BoxLayout.X_AXIS));
		mainpanelleftbottom.setLayout(new BoxLayout(mainpanelleftbottom, BoxLayout.X_AXIS));
		mainpanelright.setLayout(new BoxLayout(mainpanelright, BoxLayout.Y_AXIS));
		
		
		mainpanel.add(mainpanelleft);
		mainpanel.add(mainpanelright);
		mainpanelleft.add(mainpanellefttop);
		mainpanelleft.add(mainpanelleftbottom);
		
		panelvenda.add(mainpanel);
		StringableListModel model = new StringableListModel();
		listaprod=new JList<>(model);
		
		listaprodscroll=new JScrollPane(listaprod);
		
		mainpanellefttop.add(listaprodscroll);
		
		clientelabel=new JLabel("Sem Cliente");
		mainpanelright.add(clientelabel);
		bottommenu = new JPanel();
		bottommenu.setLayout(new BoxLayout(bottommenu, BoxLayout.X_AXIS));
		
		mainpanelleft.add(Box.createRigidArea(new Dimension(0,10)));
		
		
		mainpanelleft.add(bottommenu);
		
		
		cancelar = new JButton("Cancelar Venda");
		bottommenu.add(cancelar);
		cancelar.addActionListener((a)->{
			cancelarVenda();
		});
		addproduto=new JButton ("Adicionar Produto");
		mainpanelleftbottom.add(addproduto);
		selectcliente=new JButton ("Selecionar Cliente");
		selectcliente.addActionListener(a->{
			switchPanel("Cliente");
		});
		
		mainpanelright.add(selectcliente);
		
		bottommenu.add(Box.createRigidArea(new Dimension(25,0)));
		
		
		finalizarvenda=new JButton ("Finalizar Venda");
		bottommenu.add(finalizarvenda);
		
		
		
		//bottommenu.add(Box.createHorizontalGlue());
		//mainpanelleftbottom.add(Box.createHorizontalGlue());
		mainpanelright.add(Box.createVerticalGlue());
	}
	
	public void confirmarCliente(Cliente c) {
		if(c!=null) {
			cliente=c;
			clientelabel.setText(c.getString());
		}
		sairCliente();
	}
	
	public void sairCliente() {
		switchPanel("Venda");
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
	
	public void switchPanel(String newpanel) {
		mainlayout.show(this,newpanel);
	}
}

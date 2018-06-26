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
import poo.ricardo.pdv_ui.tabs.ProdutoPanel;
import poo.ricardo.pdv_ui.utils.Cliente;
import poo.ricardo.pdv_ui.utils.MyListModel;
import poo.ricardo.pdv_ui.utils.CallOnConfirm;
import poo.ricardo.pdv_ui.utils.CallOnCancel;

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
	private JList<ProdVenda> listaprod = null;
	private JScrollPane listaprodscroll=null;
	private JButton cancelar = null;
	private JButton addproduto = null;
	private JButton editproduto = null;
	private JButton removeproduto = null;
	private JButton selectcliente=null;
	private JButton finalizarvenda=null;
	private ClientPanel clientpanel=null;
	private ProdutoPanel produtopanel=null;
	private Cliente cliente=null;
	
	MyListModel<ProdVenda> data=null;
	
	private JLabel clientelabel=null;
	
	int prodmode=0;
	int prodind=0;
	
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
		
		clientpanel=new ClientPanel(this,new CallOnConfirm() {
			@Override
			public void confirm(Object c) {
				confirmarCliente((Cliente)c);
			}
		},new CallOnCancel() {
			@Override
			public void cancel() {
				sairCliente();
			}
		});
		
		add(clientpanel,"Cliente");
		
		produtopanel= new ProdutoPanel(this, new CallOnConfirm() {
			@Override
			public void confirm(Object p) {
				confirmarProduto((ProdVenda)p);
			}
		}, new CallOnCancel() {
			public void cancel() {
				sairProduto();
			}
		});
		
		add(produtopanel,"Produto");
		
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
		data= new MyListModel<ProdVenda>();
		listaprod=new JList<ProdVenda>(data);
		listaprodscroll=new JScrollPane(listaprod);
		
		mainpanellefttop.add(listaprodscroll);
		
		clientelabel=new JLabel("Sem Cliente");
		clientelabel.setHorizontalAlignment(JLabel.CENTER);

		
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

		addproduto=new JButton("Adicionar Produto");
		addproduto.addActionListener(a->{
			prodmode=0;
			switchPanel("Produto");
			produtopanel.Novo();
		});
		
		removeproduto=new JButton("Remover Produto");
		removeproduto.addActionListener(a->{
			
		});
		
		editproduto=new JButton("Editar Produto");
		editproduto.addActionListener(a->{
			ProdVenda temp=(ProdVenda)data.getElementAt(listaprod.getSelectedIndex());
			if(temp != null) {
				prodmode=1;
				prodind=listaprod.getSelectedIndex();
				switchPanel("Produto");
				produtopanel.Editar(temp.getCodigo(), temp.getAmt());
			}
		});

		mainpanelleftbottom.add(addproduto);
		mainpanelleftbottom.add(Box.createRigidArea(new Dimension(10,0)));
		mainpanelleftbottom.add(editproduto);
		mainpanelleftbottom.add(Box.createRigidArea(new Dimension(10,0)));
		mainpanelleftbottom.add(removeproduto);
		
		selectcliente=new JButton ("Selecionar Cliente");
		selectcliente.addActionListener(a->{
			switchPanel("Cliente");
			clientpanel.Novo();
		});
		
		mainpanelright.add(selectcliente);
		
		bottommenu.add(Box.createRigidArea(new Dimension(10,0)));
		
		
		finalizarvenda=new JButton ("Finalizar Venda");
		bottommenu.add(finalizarvenda);
		
		
		
		//bottommenu.add(Box.createHorizontalGlue());
		//mainpanelleftbottom.add(Box.createHorizontalGlue());
		mainpanelright.add(Box.createVerticalGlue());
	}
	
	/*
	private void refreshJList() {
		this.revalidate();
		this.repaint();
	}
	*/
	
	public void confirmarCliente(Cliente c) {
		if(c!=null) {
			cliente=c;
			clientelabel.setText(cliente.toString());
		}
		sairCliente();
	}
	
	public void confirmarProduto(ProdVenda p) {
		if(p!=null) {
			if(prodmode==0) {
				System.out.println(p.toString());
				data.addElement(p);
				System.out.println(data.getSize());
			}else if(prodmode==1) {
				data.set(prodind, p);
			}
		}
		sairProduto();
	}
	
	public void sairProduto() {
		switchPanel("Venda");
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

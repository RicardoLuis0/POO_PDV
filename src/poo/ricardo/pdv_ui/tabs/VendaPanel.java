package poo.ricardo.pdv_ui.tabs;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import poo.ricardo.pdv_ui.utils.Cliente;
import poo.ricardo.pdv_ui.utils.MyListModel;
import poo.ricardo.pdv_ui.utils.ProdVenda;
import poo.ricardo.pdv_ui.utils.CallOnConfirm;
import poo.ricardo.pdv_ui.utils.AcessoBanco;
import poo.ricardo.pdv_ui.utils.CallOnCancel;

public class VendaPanel extends JPanel {
	private static final long serialVersionUID = 1L;
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
	private JLabel totallabel=null;
	private JButton resetcliente=null;
	private FinalVendaPanel finalvendapanel=null;
	
	private AcessoBanco banco;
	
	MyListModel<ProdVenda> data=null;
	
	private JLabel clientelabel=null;
	
	int prodmode=0;
	int prodind=0;
	
	CallOnCancel call_cancel=null;
	
	public VendaPanel(AcessoBanco ba,CallOnCancel ca) {
		banco=ba;
		call_cancel=ca;
		mainlayout=new CardLayout();
		setLayout(mainlayout);
		panelvenda=new JPanel();
		panelvenda.setLayout(new BoxLayout(panelvenda, BoxLayout.Y_AXIS));
		topmenu = new JPanel();
		topmenu.setLayout(new BoxLayout(topmenu, BoxLayout.X_AXIS));
		panelvenda.add(topmenu);
		
		add(panelvenda,"Venda");
		
		clientpanel=new ClientPanel(banco,new CallOnConfirm() {
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
		
		produtopanel= new ProdutoPanel(banco,new CallOnConfirm() {
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
		
		finalvendapanel=new FinalVendaPanel(banco,new CallOnConfirm() {

			@Override
			public void confirm(Object data) {
				call_cancel.cancel();
			}
			
		},new CallOnCancel() {

			@Override
			public void cancel() {
				switchPanel("Venda");
			}
			
		});
		
		add(finalvendapanel,"Final");
		
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

		totallabel=new JLabel("");
		updateTotal();
		
		mainpanelright.add(clientelabel);
		
		bottommenu = new JPanel();
		bottommenu.setLayout(new BoxLayout(bottommenu, BoxLayout.X_AXIS));
		
		mainpanelleft.add(Box.createRigidArea(new Dimension(0,10)));
		
		
		mainpanelleft.add(bottommenu);
		
		

		addproduto=new JButton("Adicionar Produto");
		addproduto.addActionListener(a->{
			prodmode=0;
			switchPanel("Produto");
			produtopanel.Novo();
		});
		
		removeproduto=new JButton("Remover Produto");
		removeproduto.addActionListener(a->{
			removeProduto();
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
			if(cliente==null) {
				clientpanel.Novo();
			}else {
				clientpanel.Editar(cliente.getCodigo());
			}
		});
		
		resetcliente=new JButton("Remover Cliente");
		resetcliente.addActionListener(a->{
			if(cliente!=null) {
				cliente=null;
				clientelabel.setText("Sem Cliente");
			}
		});
		
		mainpanelright.add(selectcliente);
		mainpanelright.add(resetcliente);
		
		bottommenu.add(Box.createRigidArea(new Dimension(10,0)));
		

		cancelar = new JButton("Cancelar Venda");
		bottommenu.add(cancelar);
		cancelar.addActionListener((a)->{
			cancelarVenda();
		});
		
		finalizarvenda=new JButton ("Finalizar Venda");
		
		finalizarvenda.addActionListener(a->{
			switchPanel("Final");
			finalvendapanel.Novo(cliente, data.getList(new ArrayList<ProdVenda>()));
		});
		
		
		bottommenu.add(finalizarvenda);

		mainpanelright.add(Box.createVerticalGlue());
		
		mainpanelright.add(totallabel);
		
		mainpanelright.add(Box.createVerticalGlue());
	}

	public void updateTotal() {
		double total=0;
		for(Object o : data.toArray()) {
			ProdVenda p=(ProdVenda)o;
			total+=p.getTotal();
		}
		totallabel.setText("R$ "+String.format("%.2f",total));
	}
	
	public void confirmarCliente(Cliente c) {
		if(c!=null) {
			cliente=c;
			clientelabel.setText(cliente.toString());
		}
		sairCliente();
	}
	
	public void removeProduto() {
		int index=listaprod.getSelectedIndex();
		if(index!=-1) {
			data.remove(index);
		}
	}
	
	public void confirmarProduto(ProdVenda p) {
		if(p!=null) {
			if(prodmode==0) {
				data.addElement(p);
				updateTotal();
			}else if(prodmode==1) {
				data.set(prodind, p);
				updateTotal();
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
		cliente=null;
		clientelabel.setText("Sem Cliente");
		data.clear();
		switchPanel("Venda");
	}
	
	public void cancelarVenda() {
		call_cancel.cancel();
	}
	
	public void switchPanel(String newpanel) {
		mainlayout.show(this,newpanel);
	}
}

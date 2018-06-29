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
	private JPanel mainpanelbottom=null;
	private JPanel bottommenu = null;
	private JList<ProdVenda> listaprod = null;
	private JScrollPane listaprodscroll=null;
	private JButton cancelar = null;
	private JButton addproduto = null;
	private JButton editproduto = null;
	private JButton removeproduto = null;
	private JButton finalizarvenda=null;
	private ProdutoPanel produtopanel=null;
	private JLabel totallabel=null;
	private FinalVendaPanel finalvendapanel=null;
	
	private AcessoBanco banco;
	
	MyListModel<ProdVenda> data=null;
	
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
		mainpanelbottom=new JPanel();
		
		mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
		mainpanelbottom.setLayout(new BoxLayout(mainpanelbottom, BoxLayout.X_AXIS));
		
		
		
		
		panelvenda.add(mainpanel);
		data= new MyListModel<ProdVenda>();
		listaprod=new JList<ProdVenda>(data);
		listaprodscroll=new JScrollPane(listaprod);
		
		mainpanel.add(listaprodscroll);

		totallabel=new JLabel();
		updateTotal();
		mainpanel.add(totallabel);
		
		totallabel=new JLabel("");
		updateTotal();
		
		bottommenu = new JPanel();
		bottommenu.setLayout(new BoxLayout(bottommenu, BoxLayout.X_AXIS));
		
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		mainpanel.add(mainpanelbottom);
		mainpanel.add(bottommenu);
		
		

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

		mainpanelbottom.add(addproduto);
		mainpanelbottom.add(Box.createRigidArea(new Dimension(10,0)));
		mainpanelbottom.add(editproduto);
		mainpanelbottom.add(Box.createRigidArea(new Dimension(10,0)));
		mainpanelbottom.add(removeproduto);
		
		bottommenu.add(Box.createRigidArea(new Dimension(10,0)));
		

		cancelar = new JButton("Cancelar Venda");
		bottommenu.add(cancelar);
		cancelar.addActionListener((a)->{
			cancelarVenda();
		});
		
		finalizarvenda=new JButton ("Finalizar Venda");
		
		finalizarvenda.addActionListener(a->{
			switchPanel("Final");
			finalvendapanel.Novo(data.getList(new ArrayList<ProdVenda>()));
		});
		
		
		bottommenu.add(finalizarvenda);

		
	}

	public void updateTotal() {
		double total=0;
		for(Object o : data.toArray()) {
			ProdVenda p=(ProdVenda)o;
			total+=p.getTotal();
		}
		totallabel.setText("Total: R$ "+String.format("%.2f",total));
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

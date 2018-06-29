package poo.ricardo.pdv_ui.tabs;

import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import poo.ricardo.pdv_ui.utils.AcessoBanco;
import poo.ricardo.pdv_ui.utils.CallOnCancel;
import poo.ricardo.pdv_ui.utils.CallOnConfirm;
import poo.ricardo.pdv_ui.utils.Cliente;
import poo.ricardo.pdv_ui.utils.MyListModel;
import poo.ricardo.pdv_ui.utils.ProdVenda;

public class FinalVendaPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel panel1=null;
	
	private List<ProdVenda> pl;
	private Cliente c;
	private JButton confirmar;
	private JButton voltar;
	private JLabel clientelabel;
	private JLabel precolabel;
	private MyListModel<ProdVenda> data;
	private JList<ProdVenda> produtos;
	private JScrollPane scrollprodutos;
	private AcessoBanco banco;
	
	public FinalVendaPanel(AcessoBanco ba,CallOnConfirm call_confirm,CallOnCancel call_cancel){
		banco=ba;
		confirmar=new JButton("Confirmar Venda");
		voltar=new JButton("Voltar");
		clientelabel=new JLabel("Sem Cliente");
		precolabel=new JLabel("Total: R$ 0.00");
		data=new MyListModel<ProdVenda>();
		produtos=new JList<ProdVenda>(data);
		scrollprodutos=new JScrollPane(produtos);
		
		confirmar.addActionListener(a->{
			confirmarVenda();
			call_confirm.confirm(null);
		});
		
		voltar.addActionListener(a->{
			call_cancel.cancel();
		});
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panel1=new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		
		add(clientelabel);
		
		add(scrollprodutos);
		
		add(panel1);

		panel1.add(Box.createHorizontalGlue());
		
		panel1.add(voltar);

		panel1.add(Box.createHorizontalGlue());
		
		panel1.add(precolabel);

		panel1.add(Box.createHorizontalGlue());
		
		panel1.add(confirmar);
		
		panel1.add(Box.createHorizontalGlue());
	}
	
	public void refreshView() {
		if(c==null) {
			clientelabel.setText("Sem Cliente");
		}else {
			clientelabel.setText(c.toString());
		}
		if(pl==null) {
			data.clear();
		}else {
			data.replaceData(pl);
		}
		double t=0;
		for(ProdVenda p:pl) {
			t+=p.getTotal();
		}
		precolabel.setText("Total: R$ "+String.format("%.2f", t));
	}
	
	void Novo(Cliente c,List<ProdVenda> pl) {
		this.pl=pl;
		this.c=c;
		refreshView();
	}
	void confirmarVenda() {
		banco.novaVenda(c, pl);
	}
}

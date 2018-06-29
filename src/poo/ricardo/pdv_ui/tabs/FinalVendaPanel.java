package poo.ricardo.pdv_ui.tabs;

import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import poo.ricardo.pdv_ui.utils.AcessoBanco;
import poo.ricardo.pdv_ui.utils.CallOnCancel;
import poo.ricardo.pdv_ui.utils.CallOnConfirm;
import poo.ricardo.pdv_ui.utils.MyListModel;
import poo.ricardo.pdv_ui.utils.ProdVenda;

public class FinalVendaPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel panel1=null;
	
	private List<ProdVenda> pl;
	private JButton confirmar;
	private JButton voltar;
	private JLabel cpflabel;
	private JLabel precolabel;
	private MyListModel<ProdVenda> data;
	private JList<ProdVenda> produtos;
	private JPanel topbar;
	private JScrollPane scrollprodutos;
	private AcessoBanco banco;
	private JTextField cpfcnpj;
	
	public FinalVendaPanel(AcessoBanco ba,CallOnConfirm call_confirm,CallOnCancel call_cancel){
		banco=ba;
		confirmar=new JButton("Confirmar Venda");
		voltar=new JButton("Voltar");
		cpflabel=new JLabel("CPF/CNPJ");
		precolabel=new JLabel("Total: R$ 0.00");
		data=new MyListModel<ProdVenda>();
		produtos=new JList<ProdVenda>(data);
		scrollprodutos=new JScrollPane(produtos);
		cpfcnpj= new JTextField();
		
		cpfcnpj.setMaximumSize(new Dimension(cpfcnpj.getMaximumSize().width,20));
		
		topbar=new JPanel();
		topbar.setLayout(new BoxLayout(topbar, BoxLayout.X_AXIS));
		
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
		
		add(topbar);
		topbar.add(Box.createRigidArea(new Dimension(10, 0)));
		topbar.add(cpflabel);
		topbar.add(Box.createRigidArea(new Dimension(20, 0)));
		topbar.add(cpfcnpj);
		
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
	
	void Novo(List<ProdVenda> pl) {
		this.pl=pl;
		refreshView();
	}
	void confirmarVenda() {
		banco.novaVenda(cpfcnpj.getText(), pl);
	}
}

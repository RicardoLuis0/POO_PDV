package poo.ricardo.pdv_ui.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import poo.ricardo.pdv_ui.utils.AcessoBanco;
import poo.ricardo.pdv_ui.utils.CallOnCancel;
import poo.ricardo.pdv_ui.utils.CallOnConfirm;
import poo.ricardo.pdv_ui.utils.MyListModel;
import poo.ricardo.pdv_ui.utils.ProdVenda;
import poo.ricardo.pdv_ui.utils.Produto;

public class ProdutoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JScrollPane scroll = null;
	private JPanel scrollfill = null;
	private JPanel menupanel=null;
	private JButton confirmar = null;
	private JButton cancelar = null;
	private JSpinner amt_spinner = null;
	private SpinnerNumberModel spinner_model = null;
	MyListModel<Produto> data = null;
	JList<Produto> listaProdutos = null;
	AcessoBanco banco;

	public ProdutoPanel(AcessoBanco banco,CallOnConfirm call_confirm,CallOnCancel call_cancel){
		super();
		this.banco=banco;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		scrollfill = new JPanel(new BorderLayout());
		confirmar = new JButton("Confirmar");
		cancelar = new JButton("Cancelar");
		data = new MyListModel<Produto>();
		refreshJList();
		listaProdutos = new JList<Produto>(data);
		spinner_model=new SpinnerNumberModel(1, 1, 999, 1);
		amt_spinner=new JSpinner(spinner_model);
		
		amt_spinner.setMinimumSize(new Dimension(100, 20));
		amt_spinner.setPreferredSize(new Dimension(100, 20));
		amt_spinner.setMaximumSize(new Dimension(100, 20));
		confirmar.setAlignmentX(CENTER_ALIGNMENT);
		cancelar.setAlignmentX(CENTER_ALIGNMENT);
		
		scroll = new JScrollPane(listaProdutos);
		scrollfill.add(scroll,BorderLayout.CENTER);
		menupanel=new JPanel();
		menupanel.setLayout(new BoxLayout(menupanel, BoxLayout.X_AXIS));
		
		add(scrollfill);
		add(menupanel);

		menupanel.add(Box.createHorizontalGlue());
		menupanel.add(amt_spinner);
		menupanel.add(confirmar);
		menupanel.add(cancelar);
		menupanel.add(Box.createHorizontalGlue());
		
		confirmar.addActionListener(a->{
			int index=listaProdutos.getSelectedIndex();
			if(index>=0) {
				call_confirm.confirm(new ProdVenda((Produto)data.getElementAt(index),((Number)amt_spinner.getValue()).intValue()));
			}
		});
		cancelar.addActionListener(a->{
			call_cancel.cancel();
		});
	}
	private void refreshJList() {
		data.replaceData(banco.getListaProdutos());
	}
	public String getTabName() {
		return "Produtos";
	}
	public void Editar(int cod,int amt) {
		refreshJList();
		int codindex=-1;
		for(int i=0;i<data.getSize();i++) {
			if(((Produto)data.getElementAt(i)).getCodigo()==cod) {
				codindex=i;
				break;
			}
		}
		if(codindex==-1) {
			return;
		}
		listaProdutos.setSelectedIndex(codindex);
		if(amt<1)amt=1;
		if(amt>999)amt=999;
		spinner_model.setValue(amt);
	}
	public void Novo() {
		refreshJList();
		listaProdutos.clearSelection();
		spinner_model.setValue(1);
	}

}

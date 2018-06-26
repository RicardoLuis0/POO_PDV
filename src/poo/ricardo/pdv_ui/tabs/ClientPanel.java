package poo.ricardo.pdv_ui.tabs;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import poo.ricardo.pdv_ui.PanelVenda;
import poo.ricardo.pdv_ui.utils.CallOnCancel;
import poo.ricardo.pdv_ui.utils.CallOnConfirm;
import poo.ricardo.pdv_ui.utils.Cliente;
import poo.ricardo.pdv_ui.utils.MyListModel;

public class ClientPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final PanelVenda parent;
	private JList<Cliente> listaClientes = null;
	private JScrollPane scroll = null;
	private JPanel scrollfill = null;
	private JButton confirmar = null;
	private JButton cancelar = null;
	MyListModel<Cliente> lmodel = null;

	public ClientPanel(PanelVenda p,CallOnConfirm call_confirm,CallOnCancel call_cancel){
		super();
		parent=p;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		scrollfill = new JPanel(new BorderLayout());
		confirmar = new JButton("Confirmar");
		cancelar = new JButton("Cancelar");
		
		lmodel = new MyListModel<Cliente>();
		lmodel.replaceData(parent.get_parent().getBanco().getListaClientes());
		listaClientes = new JList<Cliente>(lmodel);

		scroll = new JScrollPane(listaClientes);
		
		scrollfill.add(scroll,BorderLayout.CENTER);
		add(scrollfill);
		add(confirmar);
		add(cancelar);
		confirmar.addActionListener(a->{
			call_confirm.confirm((Cliente)lmodel.getElementAt(listaClientes.getSelectedIndex()));
		});
		cancelar.addActionListener(a->{
			call_cancel.cancel();
		});
	}

	private void refreshJList() {
		lmodel.replaceData(parent.get_parent().getBanco().getListaClientes());
	}
	public String getTabName() {
		return "Cliente";
	}
	public void Novo() {
		refreshJList();
	}

}

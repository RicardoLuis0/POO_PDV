package poo.ricardo.pdv_ui.tabs;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import poo.ricardo.pdv_ui.PanelVenda;
import poo.ricardo.pdv_ui.utils.ClientCallOnCancel;
import poo.ricardo.pdv_ui.utils.ClientCallOnConfirm;
import poo.ricardo.pdv_ui.utils.Cliente;
import poo.ricardo.pdv_ui.utils.StringableListModel;

public class ClientPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final PanelVenda parent;
	private JList<String> listaClientes = null;
	private JScrollPane scroll = null;
	private JPanel scrollfill = null;
	private JButton confirmar = null;
	private JButton cancelar = null;
	StringableListModel lmodel = null;

	public ClientPanel(PanelVenda p,ClientCallOnConfirm call_confirm,ClientCallOnCancel call_cancel){
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		scrollfill = new JPanel(new BorderLayout());
		parent=p;
		confirmar = new JButton("Confirmar");
		cancelar = new JButton("Cancelar");
				
		refreshJList();

		scroll = new JScrollPane(listaClientes);
		
		scrollfill.add(scroll,BorderLayout.CENTER);
		add(scrollfill);
		add(confirmar);
		add(cancelar);
		confirmar.addActionListener(a->{
			call_confirm.confirm((Cliente)lmodel.getAt(listaClientes.getSelectedIndex()));
		});
		cancelar.addActionListener(a->{
			call_cancel.cancel();
		});
	}

	private void refreshJList() {
		lmodel = new StringableListModel(parent.get_parent().getBanco().getListaClientes());
		listaClientes = new JList<String>(lmodel);
	}
	public String getTabName() {
		return "Cliente";
	}
}

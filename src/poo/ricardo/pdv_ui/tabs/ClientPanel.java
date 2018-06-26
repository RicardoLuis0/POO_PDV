package poo.ricardo.pdv_ui.tabs;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import poo.ricardo.pdv_ui.utils.CallOnCancel;
import poo.ricardo.pdv_ui.utils.CallOnConfirm;
import poo.ricardo.pdv_ui.utils.Cliente;
import poo.ricardo.pdv_ui.utils.MyListModel;

public class ClientPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final VendaPanel parent;
	private JList<Cliente> listaClientes = null;
	private JScrollPane scroll = null;
	private JPanel scrollfill = null;
	private JButton confirmar = null;
	private JButton cancelar = null;
	MyListModel<Cliente> data = null;

	public ClientPanel(VendaPanel p,CallOnConfirm call_confirm,CallOnCancel call_cancel){
		super();
		parent=p;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		scrollfill = new JPanel(new BorderLayout());
		confirmar = new JButton("Confirmar");
		cancelar = new JButton("Cancelar");
		
		data = new MyListModel<Cliente>();
		data.replaceData(parent.get_parent().getBanco().getListaClientes());
		listaClientes = new JList<Cliente>(data);

		scroll = new JScrollPane(listaClientes);
		
		scrollfill.add(scroll,BorderLayout.CENTER);
		add(scrollfill);
		add(confirmar);
		add(cancelar);
		confirmar.addActionListener(a->{
			int index=listaClientes.getSelectedIndex();
			if(index>=0) {
				call_confirm.confirm((Cliente)data.getElementAt(index));
			}
		});
		cancelar.addActionListener(a->{
			call_cancel.cancel();
		});
	}

	private void refreshJList() {
		data.replaceData(parent.get_parent().getBanco().getListaClientes());
	}
	public String getTabName() {
		return "Cliente";
	}
	public void Novo() {
		refreshJList();
	}
	public void Editar(String cod) {
		refreshJList();
		int codindex=-1;
		for(int i=0;i<data.getSize();i++) {
			if(((Cliente)data.getElementAt(i)).getCodigo()==cod) {
				codindex=i;
				break;
			}
		}
		if(codindex==-1) {
			return;
		}
		listaClientes.setSelectedIndex(codindex);
	}
}

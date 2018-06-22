package poo.ricardo.pdv_ui.tabs;

import javax.swing.JPanel;

import poo.ricardo.pdv_ui.PanelVenda;

public class FirstTab extends JPanel {

	private static final long serialVersionUID = 1L;

	private final PanelVenda parent;

	public FirstTab(PanelVenda p){
		super();
		parent=p;
	}
	
	public String getTabName() {
		return "Produtos";
	}
}

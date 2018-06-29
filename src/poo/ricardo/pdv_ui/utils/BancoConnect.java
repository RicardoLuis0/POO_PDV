package poo.ricardo.pdv_ui.utils;

import java.util.ArrayList;
import java.util.List;

public class BancoConnect implements AcessoBanco {

	@Override
	public List<Produto> getListaProdutos() {
		return new ArrayList<Produto>();
	}

	@Override
	public Produto procurarProduto(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> getListaClientes() {
		return new ArrayList<Cliente>();
	}

	@Override
	public Cliente procurarCliente(String codigo) {
		return null;
	}

	@Override
	public void novaVenda(Cliente c, List<ProdVenda> p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean testLogin(LoginData d) {
		// TODO Auto-generated method stub
		return false;
	}

}

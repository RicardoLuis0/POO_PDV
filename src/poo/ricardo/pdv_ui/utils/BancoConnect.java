package poo.ricardo.pdv_ui.utils;

import java.util.ArrayList;
import java.util.List;

public class BancoConnect implements AcessoBanco {

	@Override
	public List<Produto> getListaProdutos() {
		return new ArrayList<Produto>();
	}

	@Override
	public List<Cliente> getListaClientes() {
		return new ArrayList<Cliente>();
	}

	@Override
	public void novaVenda(Cliente c, List<ProdVenda> p) {
	}

	@Override
	public boolean testLogin(LoginData d) {
		return false;
	}

	@Override
	public Produto procurarProduto(int codigo) {
		return null;
	}

	@Override
	public Cliente procurarCliente(int codigo) {
		return null;
	}
}

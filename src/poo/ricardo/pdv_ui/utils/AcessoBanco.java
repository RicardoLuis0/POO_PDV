package poo.ricardo.pdv_ui.utils;

import java.util.List;

public interface AcessoBanco {
	public List<Produto> getListaProdutos();
	public Produto procurarProduto(int codigo);
	public List<Cliente> getListaClientes();
	public Cliente procurarCliente(int codigo);
	public void novaVenda(Cliente c,List<ProdVenda> p);
	public boolean testLogin(LoginData d);
}

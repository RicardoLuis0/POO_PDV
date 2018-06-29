package poo.ricardo.pdv_ui.utils;

import java.util.List;

public interface AcessoBanco {
	public List<Produto> getListaProdutos();
	public Produto procurarProduto(String codigo);
	public List<Cliente> getListaClientes();
	public Cliente procurarCliente(String codigo);
	public void novaVenda(Cliente c,List<ProdVenda> p);
	public boolean testLogin(LoginData d);
}

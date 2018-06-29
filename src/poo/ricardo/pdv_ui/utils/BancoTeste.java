package poo.ricardo.pdv_ui.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Math;

public class BancoTeste implements AcessoBanco{
	private HashMap<Integer,Produto> bancoProdutos = null;
	private HashMap<Integer,Cliente> bancoClientes = null;
	public BancoTeste() {
		bancoProdutos = new HashMap<Integer,Produto>();
		bancoClientes = new HashMap<Integer,Cliente>();
		for(int i=0;i<1000;i++) {
			bancoProdutos.put(i,new Produto(i,"Produto"+i,Math.random()*100));
		}
		for(int i=0;i<100;i++) {
			bancoClientes.put(i,new Cliente(i,"Cliente"+i));
		}
	}

	@Override
	public List<Produto> getListaProdutos() {
		return new ArrayList<Produto>(bancoProdutos.values());
	}

	@Override
	public Produto procurarProduto(int codigo) {
		return bancoProdutos.get(codigo);
	}

	@Override
	public List<Cliente> getListaClientes() {
		return new ArrayList<Cliente>(bancoClientes.values());
	}

	@Override
	public Cliente procurarCliente(int codigo) {
		return bancoClientes.get(codigo);
	}

	@Override
	public void novaVenda(Cliente c, List<ProdVenda> pl) {
		System.out.println("Nova Venda: Cliente "+(c==null?"null":c.toString()));
		for(ProdVenda pv :pl) {
			System.out.println("\t"+pv.toString());
		}
		return;
	}

	@Override
	public boolean testLogin(LoginData d) {
		System.out.println("Login: "+d.login);
		System.out.println("Senha: "+d.pass);
		return true;
	}
}

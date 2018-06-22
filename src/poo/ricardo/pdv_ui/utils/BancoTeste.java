package poo.ricardo.pdv_ui.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Math;

public class BancoTeste implements AcessoBanco{
	private HashMap<String,Produto> bancoProdutos = null;
	private HashMap<String,Cliente> bancoClientes = null;
	public BancoTeste() {
		bancoProdutos = new HashMap<String,Produto>();
		bancoClientes = new HashMap<String,Cliente>();
		for(int i=0;i<1000;i++) {
			bancoProdutos.put(Integer.toString(i),new Produto(Integer.toString(i),"Produto"+i,Math.random()*100));
		}
		for(int i=0;i<100;i++) {
			bancoClientes.put(Integer.toString(i),new Cliente(Integer.toString(i),"Cliente"+i));
		}
	}

	@Override
	public List<Produto> getListaProdutos() {
		return new ArrayList<Produto>(bancoProdutos.values());
	}

	@Override
	public Produto procurarProduto(String codigo) {
		return bancoProdutos.get(codigo);
	}

	@Override
	public List<Cliente> getListaClientes() {
		return new ArrayList<Cliente>(bancoClientes.values());
	}

	@Override
	public Cliente procurarCliente(String codigo) {
		return bancoClientes.get(codigo);
	}
}

package poo.ricardo.pdv_ui.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Math;

public class BancoTeste implements AcessoBanco{
	private HashMap<Integer,Produto> bancoProdutos = null;
	public BancoTeste() {
		bancoProdutos = new HashMap<Integer,Produto>();
		for(int i=0;i<1000;i++) {
			bancoProdutos.put(i,new Produto(i,"Fabricante","Marca","Modelo","Categoria",Math.random()*100));
		}
	}

	@Override
	public List<Produto> getListaProdutos() {
		return new ArrayList<Produto>(bancoProdutos.values());
	}

	@Override
	public void novaVenda(String c, List<ProdVenda> pl) {
		System.out.println("Nova Venda: Cpf/Cnpj "+c);
		for(ProdVenda pv :pl) {
			System.out.println("\t"+pv.toString());
		}
		return;
	}
}

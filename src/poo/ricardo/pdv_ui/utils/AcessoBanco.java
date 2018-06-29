package poo.ricardo.pdv_ui.utils;

import java.util.List;

public interface AcessoBanco {
	public List<Produto> getListaProdutos();
	public void novaVenda(String cpfcnpj,List<ProdVenda> p);
}

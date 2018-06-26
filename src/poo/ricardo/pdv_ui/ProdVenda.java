package poo.ricardo.pdv_ui;

import poo.ricardo.pdv_ui.utils.Produto;

public class ProdVenda extends Produto{

	protected int amt;
	
	public ProdVenda(Produto p){
		this(p,1);
	}
	
	public ProdVenda(Produto p,int amt){
		super(p);
		this.amt=amt;
	}
	
	@Override
	public String toString() {
		return nome+" R$ "+String.format("%.2f",preco)+" x"+amt+" = R$ "+String.format("%.2f",(preco*amt));
	}
	
	public int getAmt() {
		return amt;
	}
	
	public void setAmt(int val) {
		amt=val;
	}
}

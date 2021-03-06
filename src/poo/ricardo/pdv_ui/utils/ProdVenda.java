package poo.ricardo.pdv_ui.utils;

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
		return super.toString()+" x"+amt+" = R$ "+String.format("%.2f",(getPreco()*amt));
	}
	
	public int getAmt() {
		return amt;
	}
	
	public void setAmt(int val) {
		amt=val;
	}
	
	public double getTotal() {
		return getPreco()*amt;
	}
}

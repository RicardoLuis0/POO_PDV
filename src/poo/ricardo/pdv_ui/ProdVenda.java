package poo.ricardo.pdv_ui;

import poo.ricardo.pdv_ui.utils.Stringable;

public class ProdVenda implements Stringable{
	private String name;
	private int key;
	private int amt;
	ProdVenda(int key,String name){
		this(key,name,1);
	}
	ProdVenda(int key,String name,int amt){
		this.key=key;
		this.name=name;
		this.amt=amt;
	}

	@Override
	public String getString() {
		return name+" x"+amt;
	}
	public int getKey() {
		return key;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int val) {
		amt=val;
	}
}

package poo.ricardo.pdv_ui.utils;

public class Produto{
	protected String codigo;
	protected String nome;
	protected double preco;
	public String getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public double getPreco() {
		return preco;
	}
	public Produto(String cod,String n,double p) {
		codigo=cod;
		preco=p;
		nome=n;
	}
	public Produto(Produto p) {
		codigo=p.getCodigo();
		preco=p.getPreco();
		nome=p.getNome();
	}
	@Override
	public String toString() {
		return codigo+": "+nome+" R$ "+String.format("%.2f",preco);
	}
}

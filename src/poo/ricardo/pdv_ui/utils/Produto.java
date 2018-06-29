package poo.ricardo.pdv_ui.utils;

public class Produto{
	private int codigo;
	private double preco;
	
	public String getFabricante() {
		return fabricante;
	}
	public String getMarca() {
		return marca;
	}
	public String getModelo() {
		return modelo;
	}
	public String getCategoria() {
		return categoria;
	}
	private String fabricante;
	private String marca;
	private String modelo;
	private String categoria;
	
	public int getCodigo() {
		return codigo;
	}
	public double getPreco() {
		return preco;
	}
	public Produto(int co,String fa,String ma,String mo,String ca,double pr) {
		codigo=co;
		preco=pr;
		fabricante=fa;
		marca=ma;
		modelo=mo;
		categoria=ca;
	}
	public Produto(Produto p) {
		codigo=p.getCodigo();
		preco=p.getPreco();
		fabricante=p.getFabricante();
		marca=p.getMarca();
		modelo=p.getModelo();
		categoria=p.getCategoria();
	}
	@Override
	public String toString() {
		return categoria+": "+fabricante+" - "+marca+" - "+modelo+" - R$ "+String.format("%.2f",preco);
	}
}

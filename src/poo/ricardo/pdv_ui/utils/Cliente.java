package poo.ricardo.pdv_ui.utils;

public class Cliente{
	private int codigo;
	private String name;
	public Cliente(int cod,String n) {
		codigo=cod;
		name=n;
	}
	public int getCodigo() {
		return codigo;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return name;
	}
	
}

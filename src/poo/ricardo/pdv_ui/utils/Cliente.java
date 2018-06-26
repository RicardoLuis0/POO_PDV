package poo.ricardo.pdv_ui.utils;

public class Cliente{
	public String codigo;
	public String name;
	public Cliente(String cod,String n) {
		codigo=cod;
		name=n;
	}
	@Override
	public String toString() {
		return name;
	}
	
}

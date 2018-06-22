package poo.ricardo.pdv_ui.utils;

public class Cliente implements Stringable{
	public String codigo;
	public String name;
	public Cliente(String cod,String n) {
		codigo=cod;
		name=n;
	}
	@Override
	public String getString() {
		return name;
	}
	
}

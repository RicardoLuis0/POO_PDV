package poo.ricardo.pdv_ui.utils;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

public class StringableListModel extends AbstractListModel<String> {
	private static final long serialVersionUID = 1L;
	DefaultListModel<Stringable> data=null;

	public StringableListModel(List<? extends Stringable> new_data) {
		data=new DefaultListModel<Stringable>();
		for(Stringable s:new_data) {
			data.addElement(s);
		}
	}
	
	public StringableListModel() {
		data=new DefaultListModel<Stringable>();
	}
	
	
	public void add(Stringable obj) {
		data.addElement(obj);
	}
	
	public boolean rem(int index) {
		try {
			data.remove(index);
			return true;
		}catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public Stringable getAt(int index){
		try {
			return data.getElementAt(index);
		}catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	@Override
	public String getElementAt(int index) {
		Stringable p=getAt(index);
		if(p!=null) {
			return p.getString();
		}else{
			return null;
		}
	}

	@Override
	public int getSize() {
		return data.getSize();
	}
}

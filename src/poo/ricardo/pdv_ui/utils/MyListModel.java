package poo.ricardo.pdv_ui.utils;

import java.util.List;
import javax.swing.DefaultListModel;

public class MyListModel<T> extends DefaultListModel<T> {
	
	private static final long serialVersionUID = 1L;

	public void replaceData(List<T> new_data) {
		clear();
		for(T s:new_data) {
			addElement(s);
		}
	}
}

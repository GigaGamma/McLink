package link.mc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class LoopUtil<T> {
	
	private List<T> values;
	private List<T> add;
	private List<T> remove;
	
	public LoopUtil() {
		this.values = new ArrayList<T>();
		this.add = new ArrayList<T>();
		this.remove = new ArrayList<T>();
	}
	
	public List<T> getValues() {
		return values;
	}
	
	public void add(T t) {
		add.add(t);
	}
	
	public void remove(T t) {
		remove.add(t);
	}
	
	public ListIterator<T> update() {
		ListIterator<T> ia = add.listIterator();
		ListIterator<T> ir = add.listIterator();
		
		while (ia.hasNext()) {
			values.add(ia.next());
			ia.remove();
		}
		
		while (ir.hasNext()) {
			values.remove(ir.next());
			ir.remove();
		}
		
		return values.listIterator();
	}
	
}

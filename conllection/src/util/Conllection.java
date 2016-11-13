package util;

import java.util.List;

public interface Conllection<T> {
	int size();
	boolean isEmpty();
	T get(int index);
	boolean contails(Object o);
	Object[] toArray();
	boolean add(T t);
	boolean add(int index,T t);
	T remove(int index);
	T fastRemove(T t);
	
	
}

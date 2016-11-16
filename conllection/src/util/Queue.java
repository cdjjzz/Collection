package util;

public interface Queue<T> {
	boolean add(T t);
	boolean offer(T t);
	T remove();
	T poll();
	T peek();
}

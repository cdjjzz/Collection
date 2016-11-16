package util;

import java.util.LinkedList;

/**
 * 
 * @author jjjj
 * 
 *
 * @param <T>
 */
public class CircleNode<T> implements Conllection<T> ,Queue<T>,Stack<T> {
	
	private class Node{
		private T t;
		private Node prev;
		private Node next;
		public Node(Node prev,T t,Node next) {
			this.prev=prev;
			this.t=t;
			this.next=next;
		}
		@Override
		public String toString() {
			return "Node [t=" + t + ", next=" + next +"]";
		}
		
		
		
		
	}
	transient Node first;
	transient Node last;
	private  int size;
	private  int modCount;
	
	@Override
	public boolean offer(T t) {
		return add(t);
	}
	public boolean offerFirst(T t) {
        return addFirst(t);
    }

	@Override
	public T remove() {
		return unlinkFirst();
	}

	@Override
	public T poll() {
		final Node f = first;
        return (f == null) ? null : unlinkFirst();
	}

	@Override
	public T peek() {
		 final Node f = first;
	     return (f == null) ? null : f.t;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public T get(int index) {
		return node(index).t;
	}

	@Override
	public boolean contails(Object o) {
		return indexOf(o)>-1;
	}

	@Override
	public Object[] toArray() {
		Object[] temp=new Object[size];
		Node n=first;
		for(int i=0;i<size;i++){
			temp[i]=n;
			n=n.next;
		}
		return temp;
	}

	@Override
	public boolean add(T t) {
		linkLast(t);
		return true;
	}
	public boolean addFirst(T t){
		linkFirst(t);
		return true;
	}

	@Override
	public boolean add(int index, T t) {
		linkIndex(index,t);
		return true;
	}

	@Override
	public T remove(int index) {
		return unlinkIndex(index);
	}
	
	@Override
	public T fastRemove(T t) {
		return unlink(t);
	}
	public int indexOf(Object o){
		if(o==null){
			Node n=first;
			for(int i=0;i<size;i++){
				if(n.t==null)
					return i;
				n=n.next;
			}
		}else{
			Node n=first;
			for(int i=0;i<size;i++){
				if(o.equals(n.t))
					return i;
				n=n.next;
			}
		}
		return -1;
	}
	
	
	@Override
	public T pop() {
		return remove();
	}
	@Override
	public boolean push(T t) {
		return addFirst(t);
	}
	
	private void linkLast(T t){
	  final Node l=last;
	  final Node temp=new Node(l, t,null);
	  last=temp;
	  if(l==null)
		  first=temp;
	  else
		  l.next=temp;
	  size++;
	  modCount++;
	}
	private void linkFirst(T t){
		final Node f = first;
        final Node newNode = new Node(null, t, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
		size++;
		modCount++;
	}
	private void linkIndex(int index,T t){
		checkRang(index);
		if(index==0){
			linkFirst(t);
			return;
		}
		if(index==size-1){
			linkLast(t);
			return;
		}
		Node currt=node(index);
		final Node prep=currt.prev;
		final Node newNode=new Node(prep,t,currt);
		currt.prev=newNode;
		if(prep==null)
			first=newNode;
		else
			prep.next=newNode;
		size++;
		modCount++;
	}
	private Node node(int index){
		checkRang(index);
		if(index>(size>>1)){
			Node l=last;
			for(int i=size-1;i>index;i--)
				l=l.prev;
				return l;
		}else{
			Node f=first;
			for(int i=0;i<index;i++)
				f=f.next;
				return f;
		}
	}
	private T unlinkFirst(){
		if(first==null)
			return null;
		final T t=first.t;
		final Node next=first.next;
		first.t=null;
		first.next=null;
		first=next;
		if(next.next==null)
			last=null;
		else
			next.prev=null;
		--size;
		modCount++;
		return t;
	}
	private T unlinkIndex(int index){
		checkRang(index);
		Node temp=node(index);
		final T t=temp.t;
		final Node prev=temp.prev;
		final Node next=temp.next;
		if(prev==null){
			first=next;
		}else{
			prev.next=next;
			temp.prev=null;
		}
		if(next==null)
			last=prev;
		else{
			next.prev=prev;
			temp.next=null;
		}
		--size;
		modCount++;
		return t;
	}
	private T unlink(Object o){
		Node n=null;
		if(o==null){
			Node f=first;
			for(int i=0;i<size;i++){
				if(f.t==null){
					n=f;
					break;
				}
				f=f.next;
			}
		}else{
			Node f=first;
			for(int i=0;i<size;i++){
				if(o.equals(f.t)){
					n=f;
					break;
				}
				f=f.next;
			}
		}
		final T t=n.t;
		final Node prev=n.prev;
		final Node next=n.next;
		if(prev==null){
			first=next;
		}else{
			prev.next=next;
			n.prev=null;
		}
		if(next==null)
			last=prev;
		else{
			next.prev=prev;
			n.next=null;
		}
		--size;
		modCount++;
		return t;
	}
	private void checkRang(int index) {
		if(index<0||index>size-1)
			throw new IndexOutOfBoundsException("the index is error");
	}
	public static void main(String[] args) {
		CircleNode<String>  cc=new CircleNode<>();
		cc.add("asdad");
		cc.add("asdad1");
		cc.add("asdad2");
		cc.add("asdad3");
		cc.add("asdad4");
		cc.add("asdad5");
		cc.add("asdad6");
		cc.add(5, "1111");
		System.out.println(cc.fastRemove("asdad"));
		for(int i=0;i<cc.size();i++){
			System.out.println(cc.get(i));
		}
	}

}

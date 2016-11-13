package util;

import java.util.TreeMap;



public class ListNode<T extends Comparable<T>> implements Conllection<T> {
	private class Node{
		private T t;
		private Node next;
		public Node(T t) {
			this.t=t;
		}
		public Node(T t,Node next){
			this.t=t;
			this.next=next;
		}
		@Override
		public String toString() {
			return "Node [t=" + t + ", next=" + next + "]";
		}
		
	}
	transient Node first;
	transient Node last;
	private int size;
	private int modCount;

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
		checkRang(index);
		return node(index).t;
	}

	@Override
	public boolean contails(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(T t) {
		LinkLast(t);
		return true;
	}
	public boolean addFirst(T t){
		LinkFirst(t);
		return true;
	}
	public boolean addSort(T t){
		LinkSort(t);
		return true;
	}

	@Override
	public boolean add(int index, T t) {
		checkRang(index);
		if(index==0){
			LinkFirst(t);
		}
		if(index==size-1)
			LinkLast(t);
		if(index>0&&index<size-1){
			Link(index, t);
		}
		return true;
	}
	public T remove(){
		return unLinkFirst();
	}

	@Override
	public T remove(int index) {
		checkRang(index);
		Node n=node(index-1);
		n.next=n.next.next;
		--size;
		return n.t;
	}
	public void revolve(){
		if(first==null)
			return;
		Node currt=first;
		Node prev=null;
		Node next=null;
		while(currt!=null){
			next=currt.next;
			currt.next=prev;
			prev=currt;
			currt=next;
		}
		last=first; 
		first= prev; 
	}

	@Override
	public T fastRemove(T t) {
		// TODO Auto-generated method stub
		return null;
	}
	private void LinkLast(T t){
		final Node n=new Node(t);
		if(first==null)
			first=n;
		else
			last.next=n;
		last=n;
		size++;
		modCount++;
	}
	private void LinkFirst(T t){
		final Node n=new Node(t,first);
		if(first==null)
			last=n;
		first=n;
		size++;
		modCount++;
	}
	private void LinkSort(T t){
		if(t==null)
			return;
		final Node n=new Node(t);
		if(first==null){
			first=last=n;
			size++;
		    return;
		}
		if(t.compareTo(last.t)>0){
			LinkLast(t);
			return;
		}
		Node currt=first;
		Node prev=null;
		while(currt!=null&&t.compareTo(currt.t)>0){
				prev=currt;
				currt=currt.next;
		}
		if(prev==null){
			LinkFirst(t);
			return;
		}
		n.next=prev.next;
		prev.next=n;
		size++;
		modCount++;
	}
	private T unLinkFirst(){
		final Node currt=first;
		first=null;
		first=currt.next;
		--size;
		modCount++;
		return currt.t;
	}
	private void Link(int index,T t){
		Node currt=node(index-1);
		final Node f=new Node(t,currt.next);
		currt.next=f;
		size++;
		modCount++;
	}
	private Node node(int index){
		for(Node node=first;node!=null;node=node.next){
			if((index--)==0)
				return node;
		}
		return null;
		
	}
	private void checkRang(int index){
		if(index<0||index>size-1){
			throw new IndexOutOfBoundsException("this index is error,the index>0 and"
					+ "index<size-1 is right. index:"+index+"  size:"+size);
		}
	}
	public static void main(String[] args) {
		ListNode<String> n=new ListNode<>();
		n.addFirst("z");
		n.add("z1");
		n.add("z2");
		for (int i = 0; i < n.size(); i++) {
			System.out.println(n.get(i));
		}
		System.out.println("-----------------");
		ListNode<String> l=new ListNode<>();
		l.addFirst("z");
		l.addFirst("z1");
		l.addFirst("z2");
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));
		}
		System.out.println("-------------------");
		ListNode<Integer> com=new ListNode<>();
		com.add(10);
		com.add(6);
		com.add(7);
		com.add(1);
		com.add(4);
		com.add(8);
		com.add(2, 5);
		com.add(0,3);
		System.out.println(111);
		com.revolve();
		for (int i = 0; i < com.size(); i++) {
			System.out.println(com.get(i));
		}
		
		
	}

}

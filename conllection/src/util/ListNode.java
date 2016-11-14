package util;

import java.util.LinkedList;

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
		return indexOf(o)>-1;
	}
	public int indexOf(Object o){
		int c=0;
		if(o==null){
			for(Node n=first;n!=null;n=n.next){
				if(n.t==null)
					return c;
					c++;
			}
		}else{
			for(Node n=first;n!=null;n=n.next){
				if(o.equals(n.t))
					return c;
					c++;
			}
		}
		return -1;
	}
	@Override
	public Object[] toArray() {
		Object[] o=new Object[size];
		int c=0;
		for(Node n=first;n!=null;n=n.next){
			o[c]=n.t;
			c++;
		}
		return o;
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
		return null;
	}
	public void removeMax(){
		if(first==null)
			return;
		Node max=first;
		Node currt=first;
		Node prev=null;
		while(currt.next!=null){
			//第一个和第二个比较
			if(max.t.compareTo(currt.next.t)<0){
				max=currt.next;
				prev=currt;
			}
			currt=currt.next;
		}
		if(prev==null)
			first=first.next;
		else
			prev.next=prev.next.next;
		--size;	
		modCount++;
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
		System.out.println(n.contails("z"));
		System.out.println(n.contails("z2"));
		for (int i = 0; i < n.size(); i++) {
			System.out.println(n.get(i));
		}
		Object[] ss=n.toArray();
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i]);
		}
	}

}

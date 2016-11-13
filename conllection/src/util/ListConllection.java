package util;

import java.util.Arrays;

public class ListConllection<T> implements Conllection<T>{
	 private static final int DEFAULT_CAPACITY=10;
	 private static final int MAX_VALUE_LENGTH=Integer.MAX_VALUE-8;
	 private static final Object[] EMPTY_ListConllection={};
	 private int size;
	 //fail-fast 
	 private int mondCount;
	 transient Object[] elementData;
	 public ListConllection() {
		 this(DEFAULT_CAPACITY);
	 }
	 public ListConllection(int initCapacity){
		 if(initCapacity>0)
			 this.elementData=new Object[initCapacity];
		 else if(initCapacity==0)
			 this.elementData=EMPTY_ListConllection;
		 else
			 throw new IllegalArgumentException("this is not right index:"+initCapacity);
	 }
	 public ListConllection(ListConllection<T> ListConllection){
		 //if ListConllection is child so toListConllection() return T[]  not  return Object[]
		 Object[] temp=toListConllection(ListConllection);
		 if((size=temp.length)!=0){
			 //T[] convert Object[]
			 if(elementData.getClass()!=Object[].class)
			 Arrays.copyOf(elementData, size,Object[].class);
		 }else{
			 this.elementData=EMPTY_ListConllection;
		 }
	 }
	 public void trimToSize(){
		 mondCount++;
		 if(elementData.length>size){
			 elementData=(size==0)?EMPTY_ListConllection:Arrays.copyOf(elementData,size);	
		 }
	 }
	 public Object[] toListConllection(ListConllection<T> ListConllection){
		 return Arrays.copyOf(elementData,size);
	 }
	 private void checkRang(int index){
		 if(index<0||index>size-1)
			 throw new IndexOutOfBoundsException(
					 "this index is error:index:"+index+",size:"+size);
	 }
	 public void ensureCapacity(int minCapacity){
		 int midEnd=(elementData!=EMPTY_ListConllection)
				 ?
			     0://when the elementdate is not {} return 0
			     DEFAULT_CAPACITY; //when the elementdate is {} return 10;
		 if(minCapacity>midEnd){//increase the array[] size.
			 ensureCapacityInternal(minCapacity);
		 }
	 }
	 /**
	  * check this array is need increase
	  * if need  grow array new length
	  * @param minCapacity
	  */
	 private  void ensureCapacityInternal(int minCapacity){
		 if(elementData==EMPTY_ListConllection){//if {} create a new array[] return it.
			 minCapacity=Math.max(DEFAULT_CAPACITY, minCapacity);
			 }
		 ensureExplicitCapacity(minCapacity);
	 }
    private void ensureExplicitCapacity(int minCapacity) {
        mondCount++;
        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
    private void grow(int minCapacity){
    	int old=elementData.length;
    	int newL=old+(old>>1);//newL=1.5(old)
    	if(newL<minCapacity){ //if old is minuns
    		newL=minCapacity;
    	}
    	if(newL>MAX_VALUE_LENGTH)
    		newL=hugeCapacity(minCapacity);
    	elementData=Arrays.copyOf(elementData, newL);
    	
    }
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_VALUE_LENGTH) ?
            Integer.MAX_VALUE :
            MAX_VALUE_LENGTH;
    }
    public int size(){
    	return size;
    }
 
    public boolean isEmpty(){
    	return size==0;
    }
    public boolean contails(Object o){
    	return indexOf(o)>0;
    }
    public int indexOf(Object o){
    	if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
    	return -1;
    }
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
    
    public Object[] toArray(){
    	return Arrays.copyOf(elementData, size);
    }
    @SuppressWarnings("unchecked")
	public T get(int index){
    	checkRang(index);
    	return (T)elementData[index];
    }
    
    public T set(int index,T data){
    	checkRang(index);
    	T t=get(index);
    	elementData[index]=data;
    	return t;
    }
    public boolean add(T data){
    	ensureCapacityInternal(size+1);
    	elementData[size++]=data;
    	return true;
    }
    public boolean add(int index,T data){
    	checkRang(index);
    	ensureCapacityInternal(size+1);
    	System.arraycopy(elementData, index, elementData, index+1, size-index);
    	elementData[index]=data;
    	return true;
    }
    public T remove(int index){
    	checkRang(index);
    	T t=get(index);
    	System.arraycopy(elementData, index+1, elementData, index, size-index-1);
    	elementData[--size]=null;
    	return t;
    }
    public T fastRemove(Object o){
    	if(o==null){
    		for(int i=0;i<size;i++){
    			if(elementData[i]==null){
    				System.arraycopy(elementData, i+1, elementData, i, size-i-1);
    				elementData[--size]=null;
    				return null;
    			}
    		}
    	}else{
    		for(int i=0;i<size;i++){
    			if(o.equals(elementData[i])){
    				T t=get(i);
    				System.arraycopy(elementData, i+1, elementData, i, size-i-1);
    				elementData[--size]=null;
    				return t;
    			}
    		}
    	}
    	return null;
    }
    public void clear(){
    	mondCount++;
    	for (int i = 0; i <size; i++) {
			elementData[--size]=null;
		}
    	size=0;
    }
    public boolean addAll(ListConllection<T> t){
    	Object[] o=t.toArray();
    	ensureCapacityInternal(size+o.length);
    	System.arraycopy(o, 0, elementData, size+1,o.length);
    	size+=o.length ;
    	return o.length != 0;
    }
}

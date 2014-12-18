package com.yanbin.util;



import java.util.Queue;

import com.sun.org.apache.bcel.internal.generic.RETURN;



public class SequentialSearchST<Key,Value> {
	private int N;
	private int compareTimes=0;
	private Node first;
	private class Node{
		private Key key;
		private Value value;
		private Node next;
		public Node(Key key,Value value,Node node){
			this.key=key;
			this.value=value;
			this.next=node;
		}
	}
	public SequentialSearchST(){}
	public int size(){return N;}
	public boolean isEmpty() {
		return size()==0;
	}
	public Value get(Key key) {
		for(Node xNode=first;xNode!=null;xNode=xNode.next){
			if(key.equals(xNode.key)){
				return xNode.value;
			}
		}
		return null;
	}
	public void delete(Key key) {
		first=delete(first, key);
	}
	/**
	 * 比较巧妙利用了递归，删除单向列表的元素
	 * @param x
	 * @param key
	 * @return
	 */
	private Node delete(Node x, Key key){
		if(x==null) return null;
		if(key.equals(x.key)){N--;return x.next;}
		x.next=delete(x.next, key);
		return x;
	}
	public void put(Key key,Value value) {
		if(value==null){delete(key);return ;}
		for(Node x = first;x!=null;x=x.next){
			compareTimes++;
			if(x.key.equals(key)){
				x.value=value;
				return ;
			}
		}
		first = new Node(key,value,first);
	}
	public boolean contains(Key key) {
	        return get(key) != null;
	    }
	public int getCompareTimes() {
		int temp=compareTimes;
		compareTimes=0;
		return temp;
	}
}

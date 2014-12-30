package com.yanbin.search;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import sun.net.www.HeaderParser;

public class BinaryHeap<Key extends Comparable<Key>,Value> {
	//数组下标从1开始
	private Node[] heap;
	private int size=0;
	class Node {
		private Key key;
		private Value value;
		
		public Key getKey() {
			return key;
		}

		public Value getValue() {
			return value;
		}

		
		public  Node(Key key,Value value) {
			this.key=key;
			this.value=value;
		}
			
	}
	//堆的大小
	public  BinaryHeap(int max){
		//创建泛型数组？？？
		heap=(Node[])Array.newInstance(Node.class, max);
	}
	public void insert(Key key,Value value){
		heap[++size]=new Node(key, value);
		
		upHeap();
	}
	//删除堆顶的节点，并返回被删除节点
	public Node deleteTop() {
		if(size<1) return null;
		Node temp = heap[1];
		heap[1]=heap[--size];
		downHeap(1);
		return temp;
	}
	public Node[] getHeap() {
		return heap;
	}
	//向上调整堆结构
	private void upHeap(){
		if(size<=1) return;
		Node start = heap[size];
		int current = size;
		
		while(current>1&&start.key.compareTo(heap[current/2].key)>0){
			heap[current]=heap[current/2];
			current=current/2;
		}
		heap[current]=start;
	}
	//向下调整堆结构
	private void downHeap(int begin){
		if(size<=1) return;
		if(begin>=size) return;
		Node start = heap[begin];
		while(heap[begin*2]!=null){
			heap[begin]=heap[getMaxNode(begin*2, begin*2+1)];
			begin=getMaxNode(begin*2, begin*2+1);
		}
		heap[begin]=start;
		
	}
	private int getMaxNode(int a,int b){
		if(heap[b]!=null){
			return heap[a].key.compareTo(heap[b].key)>0?a:b;
		}	
		else{
			return a;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryHeap<Integer, String> heap = new BinaryHeap<Integer, String>(20);
		for (int i = 0; i < 15; i++) {
			heap.insert(i,String.valueOf(i));
		}
		for (int i = 1; i < 15; i++) {
			System.out.println(heap.getHeap()[i].getKey().toString());
		}
		System.out.println("aa");
	}

}


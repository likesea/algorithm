package com.yanbin.search;

import javafx.scene.transform.Rotate;
import sun.net.www.content.audio.x_aiff;

public class BST<Key extends Comparable<Key>,Value>  {
	private Node root;
	class Node{
		private Key key;
		private Value value;
		private Node left,right;
		private int N;//number of nodes in the subtree
		
		public Node(Key key,Value value,int N){
			this.key=key;
			this.value=value;
			this.N=N;
		}
		public int size() {
			return size(root);
		}
		private int size(Node x) {
			if(x==null) return 0;
			return x.N;
		}
		public boolean isEmpty() {
			return size()==0;
		}
		public void put(Key key,Value value) {
			if(value==null) {delete(key);return;}
			root = put(root, key,value);
		}
		private Node put(Node x,Key key,Value value){
			if(x==null) return new Node(key, value, 1);
			int cmp = key.compareTo(x.key);
			if(cmp>0)x.right=put(x.right, key, value);
			else if(cmp<0) x.left=put(x.left, key, value);
			else x.value=value;
			// update the number of subtree of a node
			x.N=1+size(x.left)+size(x.right);
			return x;
		}
		public void delete(Key key) {
			if(key==null) return ;
			if(root==null) return;
			root=delete(root, key);
		}
		private Node delete(Node x, Key key) {
			int cmp = key.compareTo(x.key);
			if(cmp>0) x.right=delete(x.right, key);
			else if(cmp<0) x.left = delete(x.left, key);
			else{
				if(x.left==null) return x.right;
				if(x.right==null) return x.left;
				Node t = x;
				x=min(t.right);
				x.right=deleteMin(t.right);
				x.left=t.left;
			}
			x.N=size(x.left)+size(x.right)+1;
			return x;
		}
		public void deleteMax(){
			if(root ==null)return ;
			root= deleteMax(root);
		}
		private Node deleteMax(Node x) {
			if(x.right==null) return (x.left);
			x.right=deleteMax(x.right);
			x.N=size(x.left)+size(x.right)+1;
			return x;
		}
		public void deleteMin() {
			if(root==null) return;
			root=deleteMax(root);
		}
		private Node deleteMin(Node x){
			if(x.left==null) return x.right;
			x.left = deleteMin(x);
			x.N=size(x.left)+size(x.right)+1;
			return x;
		}
		public Node max() {
			if(isEmpty()) return null;
			return max(root);
		}
		private Node max(Node x) {
			while(x.right!=null)x=x.right;
			return x;
		}
		public Node min() {
			if(isEmpty()) return null;
			return min(root);
		}
		private Node min(Node x) {
			while(x.left!=null)x=x.left;
			return x;
		}
		public Node get(Key key) {
			if(key==null) return null;
			Node temp = root;
			while(temp!=null){
				int cmp = key.compareTo(temp.key);
				if(cmp>0) temp=temp.right;
				else if(cmp<0) temp=temp.left;
				else return temp;
			}
			return null;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

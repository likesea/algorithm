package com.yanbin.search;

import java.util.NoSuchElementException;

import jdk.nashorn.internal.ir.ReturnNode;

public class RedBlackBST<Key extends Comparable<Key>,Value> {
	private static final boolean RED= true;
	private static final boolean BLACK =false;
	private Node root;
	private class Node{
		private Key key;
		private Value val;
		private Node left,right;
		private boolean color ;
		private int N;
		
		public Node(Key key,Value val,boolean color ,int N){
			this.key=key;
			this.val=val;
			this.color=color;
			this.N=N;
		}
	}
	// is node x red; false if x is null ?
    private boolean isRed(Node x) {
        if (x == null) return false;
        return (x.color == RED);
    }

    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    } 
    public int size() {
		return size(root);
	}
    public boolean isEmpty() {
		return root==null;
	}
	
 // value associated with the given key; null if no such key
    public Value get(Key key) { return get(root, key); }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    // is there a key-value pair with the given key?
    public boolean contains(Key key) {
        return (get(key) != null);
    }
    /*************************************************************************
     *  red-black put
     *************************************************************************/
    public void put(Key key,Value value) {
			if(key==null) return ;
			root=put(root, key, value);
			root.color=BLACK;
	}
    private Node put(Node h,Key key,Value value){
    	if(h==null) return new Node(key, value, RED, 1);
    	int cmp =key.compareTo(h.key);
    	if(cmp>0) h.right= put(h.right, key, value);
    	else if(cmp<0) h.left= put(h.left, key, value);
    	else{
    		h.val=value;
    	}
    	balance(h);
    	h.N=size(h.left)+size(h.right)+1;
    	return h;
    }
    /*************************************************************************
     *  Red-black deletion
     *************************************************************************/

     // delete the key-value pair with the minimum key
     public void deleteMin() {
         if (isEmpty()) throw new NoSuchElementException("BST underflow");

         // if both children of root are black, set root to red
         if (!isRed(root.left) && !isRed(root.right))
             root.color = RED;

         root = deleteMin(root);
         if (!isEmpty()) root.color = BLACK;
         // assert check();
     }
     private Node deleteMin(Node h) {
		if(h.left==null) return null;
		if(!isRed(h.left)&&!isRed(h.left.left))
			moveRedLeft(h);
		h.left=deleteMin(h.left);
		balance(h);
		return h;
	}
     // delete the key-value pair with the maximum key
     public void deleteMax(){
    	 if(isEmpty()) throw new NoSuchElementException("BST underflow");

         // if both children of root are black, set root to red
         if (!isRed(root.left) && !isRed(root.right))
             root.color = RED;
         root= deleteMax(root);
         if(!isEmpty())root.color=BLACK;
     }
     private Node deleteMax(Node h){
    	 /**
    	  * ��Ϊ��ڵ�����к�ɫ�����Բ�������жϣ����ҽ��û�к�ɫ������ɾ����С���ʱ��
    	  * û������ж�
    	  * ��������һ��3-�ڵ㣬ֱ��������ת���ȳ�һ�������ұ��ֵܽڵ�
    	  * ��ʱ��������if��䲻������
    	  */
    	 if(isRed(h.left))
    		 h=rotateRight(h);
    	 if(h.right==null) return null;
    	 /**
    	  * ���ɾ����С�ڵ����ƣ�
    	  */
    	 if(!isRed(h.right)&&!isRed(h.right.left))
    		 h=moveRedRight(h);
    	 h.right=deleteMax(h.right);
    	 return balance(h);
     }
     
     public void delete(Key key){
    	 if(!contains(key)){
    		 System.err.println("symbol table does not contain " + key);
             return;
    	 }
    	 if(!isRed(root.left)&&!isRed(root.right))
    		 root.color=RED;
    	 root=delete(root,key);
    	 if(!isEmpty())root.color=BLACK;
     }
     private Node delete(Node h,Key key){
    	 //�Ƚ����ڵ㣬�������
    	 if(key.compareTo(h.key)<0){
    		 if(!isRed(h.left)&&!isRed(h.left.left))
    			 h=moveRedLeft(h);
    		 h.left=delete(h.left, key);
    	 }
    	 //�Ƚ��Ҳ��Լ�������
    	 else{
    		 if(isRed(h.left))
    			 h=rotateRight(h);
    		 //��ǰ���ΪҪɾ���ڵ㣬���Ҳ��Ϊnull������null
    		 if(key.compareTo(h.key)==0&&h.right==null)
    			 return null;
    		 //���ӽ��Ϊ2-���
    		 if(!isRed(h.right)&&!isRed(h.right.left))
    			 h=moveRedRight(h);
    		 //����ҵ���ִ��ɾ������
    		 if(key.compareTo(h.key)==0){
    			 Node x = min(h.right);
    			 h.key=key;
    			 h.val=x.val;
    			 h.right=deleteMin(h.right);
    		 }
    		 //���򣬵ݹ�
    		 else {
 				h.right=delete(h.right,key);
			}
    	 }
    	 return balance(h);
     }
    
    /*************************************************************************
     *  red-black tree helper functions
     *************************************************************************/
    private Node rotateLeft(Node h) {
		Node xNode = h.right;
		h.right=xNode.right;
		xNode.left=h;
		//xNode.color=h.color;
		xNode.color=xNode.left.color;
		h.color=RED;
		xNode.N=h.N;
		h.N=size(h.left)+size(h.right)+1;
		return xNode;
	}
    private Node rotateRight(Node h) {
		Node xNode = h.left;
		h.left=xNode.right;
		xNode.right=h;
		xNode.color=xNode.right.color;
		xNode.right.color=RED;
		xNode.N=h.N;
		h.N=size(h.left)+size(h.right)+1;
		return xNode;
	}
    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //     || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h){
    	  // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);
    	flipColors(h);
    	/**���h.right.left�Ǻ�ɫ�ģ�����˵��h�����ӽڵ���ұߵ��ֵܽڵ���һ��3-�ڵ㣬���Խ�����С��һ��
    	ת�Ƶ����ڵ��У������ڵ���С��ת�Ƶ�ԭ�ȵģ����ӽ�㣩2-��㣬�γ�һ��3-���
    	����Ͳ�ת��
    	��Ϊ�Ѿ�flipcolor�ˣ��ڵ�h�������ڵ㶼��2-�ڵ㣬���Ǿ��γ���һ��4-��㣬o yeah
    	**/
    	if(isRed(h.right.left)){
    		h.right=rotateRight(h.right);
    		h=rotateLeft(h);
    	}
    	return h;
    }
    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h){
    	flipColors(h);
    	if(isRed(h.left.left)){
    		h=rotateRight(h);
    	}
    	return h;
    }
    private Node balance(Node h){
    	if(isRed(h.right)) h=rotateLeft(h);
    	if(isRed(h.left)&&isRed(h.left.left)) h= rotateRight(h);
    	if(isRed(h.left)&&isRed(h.right)) flipColors(h);
    	h.N=size(h.left)+size(h.right)+1;
    	return h;
    }
    /*************************************************************************
    *  Ordered symbol table methods.
    *************************************************************************/

    // the smallest key; null if no such key
    public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    } 

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) { 
        // assert x != null;
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 

    // the largest key; null if no such key
    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    } 

    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) { 
        // assert x != null;
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

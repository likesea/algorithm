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
    	  * 因为左节点可能有红色，所以才有这个判断，而右结点没有红色，所以删除最小结点时候
    	  * 没有这个判断
    	  * 如果左边是一个3-节点，直接向右旋转，匀出一个结点给右边兄弟节点
    	  * 此时，第三个if语句不会运行
    	  */
    	 if(isRed(h.left))
    		 h=rotateRight(h);
    	 if(h.right==null) return null;
    	 /**
    	  * 这和删除最小节点相似，
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
    	 //比较左侧节点，相对容易
    	 if(key.compareTo(h.key)<0){
    		 if(!isRed(h.left)&&!isRed(h.left.left))
    			 h=moveRedLeft(h);
    		 h.left=delete(h.left, key);
    	 }
    	 //比较右侧以及相等情况
    	 else{
    		 if(isRed(h.left))
    			 h=rotateRight(h);
    		 //当前结点为要删除节点，而右侧就为null，返回null
    		 if(key.compareTo(h.key)==0&&h.right==null)
    			 return null;
    		 //右子结点为2-结点
    		 if(!isRed(h.right)&&!isRed(h.right.left))
    			 h=moveRedRight(h);
    		 //如果找到，执行删除操作
    		 if(key.compareTo(h.key)==0){
    			 Node x = min(h.right);
    			 h.key=key;
    			 h.val=x.val;
    			 h.right=deleteMin(h.right);
    		 }
    		 //否则，递归
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
    	/**如果h.right.left是红色的，就是说明h的左子节点的右边的兄弟节点是一个3-节点，可以将其中小的一个
    	转移到父节点中，将父节点中小的转移到原先的（左子结点）2-结点，形成一个3-结点
    	否则就不转移
    	因为已经flipcolor了，节点h的两个节点都是2-节点，于是就形成了一个4-结点，o yeah
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

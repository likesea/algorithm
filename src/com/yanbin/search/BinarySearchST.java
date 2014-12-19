package com.yanbin.search;

import java.util.NoSuchElementException;
import java.util.Queue;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class BinarySearchST<Key extends Comparable<Key>,Value> {

	private static final int INIT_CAPACITY =2;
	private Key[] keys;
	private Value[] values;
	private int N = 0;
	private int compareTimes=0;
	
	public int getCompareTimes() {
		int temp =compareTimes;
		setCompareTimes(0);
		return temp;
	}
	public void setCompareTimes(int compareTimes) {
		this.compareTimes = compareTimes;
	}
	public BinarySearchST() {
		// TODO Auto-generated constructor stub
		this(INIT_CAPACITY);
	}
	public BinarySearchST(int compacity) {
		keys = (Key[])new Comparable[compacity];
		values = (Value[]) new Object[compacity];
	}
	private void resize(int capacity){
		assert capacity>=N;
		Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }
        values = tempv;
        keys = tempk;
	}
	public int  size() {
		return N;
	}
	public boolean isEmpty() {
		return size()==0;
	}
	 public boolean contains(Key key) {
	        return get(key) != null;
	    }
	 public Value get(Key key) {
	        if (isEmpty()) return null;
	        int i = rank(key); 
	        if (i < N && keys[i].compareTo(key) == 0) return values[i];
	        return null;
	 }
	 public void delete(Key key){
		 if(isEmpty()) return;
		 int i =rank(key);
		 if(i==N||keys[i].compareTo(key)!=0){
			 return;
		 }
		 for(int j=N;j>i;j--){
				keys[j]=keys[j+1];values[j]=values[j+1];
		 }
		 N--;
		 keys[N] = null;  // to avoid loitering
		 values[N] = null;
		 if(N>0&&N==keys.length/4) resize(keys.length/2);
		 assert check();
	 }
	public void put(Key key,Value value) {
		 if (value == null) { delete(key); return; }
		int mid =rank(key);
		if(mid<N&&keys[mid].compareTo(key)==0) {
			values[mid]=value;
			return;
		}
		if (N == keys.length) resize(2*keys.length);
		for(int j=N;j>mid;j--){
			compareTimes++;
			keys[j]=keys[j-1];values[j]=values[j-1];
		}
		keys[mid]=key;
		values[mid]=value;
		N++;
	}
	/**
	 * 递归二分查找
	 * @param key
	 * @param lo
	 * @param hi
	 * @return
	 */
	public int rank(Key key,int lo, int hi){
		int mid = lo+(hi-lo)/2;
		if(key.compareTo(keys[mid])>0){
			compareTimes++;
			return rank(key, mid+1, hi);
		}else if(key.compareTo(keys[mid])<0) {
			compareTimes++;
			return rank(key, lo, mid-1);
		}else {
			return mid;
		}
	}
	/**
	 * 迭代二分查找
	 * @param key
	 * @return
	 */
	public int rank(Key key) {
		int lo=0;
		int hi=N;
		while(lo<hi){
			int mid=lo+(hi-lo)/2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp>0)
				lo=mid+1;
			else if(cmp<0)hi=mid-1;
			else return mid;
		}
		return lo;
	}
	// delete the minimum key and its associated value
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(min());
    }

    // delete the maximum key and its associated value
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(max());
    }


   /*****************************************************************************
    *  Ordered symbol table methods
    *****************************************************************************/
    public Key min() {
        if (isEmpty()) return null;
        return keys[0]; 
    }

    public Key max() {
        if (isEmpty()) return null;
        return keys[N-1];
    }

    public Key select(int k) {
        if (k < 0 || k >= N) return null;
        return keys[k];
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i-1];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == N) return null; 
        else return keys[i];
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }



   
	/*****************************************************************************
	    *  Check internal invariants
	    *****************************************************************************/

	    private boolean check() {
	        return isSorted() && rankCheck();
	    }

	    // are the items in the array in ascending order?
	    private boolean isSorted() {
	        for (int i = 1; i < size(); i++)
	            if (keys[i].compareTo(keys[i-1]) < 0) return false;
	        return true;
	    }

	    // check that rank(select(i)) = i
	    private boolean rankCheck() {
	        for (int i = 0; i < size(); i++)
	            if (i != rank(select(i))) return false;
	        for (int i = 0; i < size(); i++)
	            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
	        return true;
	    }




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

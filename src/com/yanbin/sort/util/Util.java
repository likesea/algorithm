package com.yanbin.sort.util;

public class Util {
	/**
	 * 判断v是否小于w，如果是返回true，否则返回fale
	 * @param v
	 * @param w
	 * @return
	 */
	public static boolean  less(Comparable v,Comparable w)
	{
		return v.compareTo(w)<0;
	}
	/**
	 * 交换可比较数组a中 位于索引i处和j处的元素
	 * @param a
	 * @param i
	 * @param j
	 */
	public static void exch(Comparable[]a, int i,int j) {
		Comparable t = a[i];
		a[i]=a[j];
		a[j]=t;
	}
	/**
	 * 判断是否有序
	 * @param a
	 * @return
	 */
	public static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if(less(a[i], a[i-1])) return false;
		}
		return true;
	}
}

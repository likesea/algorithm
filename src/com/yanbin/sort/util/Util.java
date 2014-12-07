package com.yanbin.sort.util;

public class Util {
	/**
	 * �ж�v�Ƿ�С��w������Ƿ���true�����򷵻�fale
	 * @param v
	 * @param w
	 * @return
	 */
	public static boolean  less(Comparable v,Comparable w)
	{
		return v.compareTo(w)<0;
	}
	/**
	 * �����ɱȽ�����a�� λ������i����j����Ԫ��
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
	 * �ж��Ƿ�����
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

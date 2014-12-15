package com.yanbin.sort;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

import com.yanbin.sort.util.*;

public class Sort {
	/**
	 * ѡ������ ��ʵ�� ���ȶ�������[4,3,4,1,2]
	 * ��Ϊѡ�������ǽ�����ǰֵ��ʣ��Ԫ�ص���Сֵ���ǽ����������ٵ������㷨
	 * @param a
	 */
	public static void selectionSort(Comparable[] a) {
		int length = a.length;
		for(int i=0;i<length;i++){
			int min = i;//��СԪ������
			for(int j =i;j<length;j++){
				if(Util.less(a[j], a[min])) min=j;
			}
			Util.exch(a, i, min);
		}
	}
	/**
	 * �������� ��ʵ�֣��ȶ��㷨����ǰԪ����߶�������ŵģ���ǰԪ���Դ���ǰ����ֱ�����ʵ�λ��
	 * @param a
	 */
	public static void insertSort(Comparable[] a) {
		int length = a.length;
		for (int i = 1; i < a.length; i++) {
			for(int j =i; j>0&&Util.less(a[j], a[j-1]);j--)
			{
				Util.exch(a, j, j-1);
			}
		}
	}
	/**
	 * �Ż�1
	 * @param a
	 */
	public static void insertSort1(Comparable[] a) {
		int length = a.length;
		for (int i = 0; i < a.length; i++) {
			Comparable temp = a[i];
			int j=i;
			for(; j>0&&Util.less(temp, a[j-1]);j--)
			{
				a[j]=a[j-1];
			}
			a[j]=temp;
		}
	}
	/**
	 * 3���з�ʵ�֣������ظ�Ԫ�رȽ϶������Ƚ���Ч��Ҳ������Ԫ����Ϊ�ָ���
	 * @param a
	 * @param lo
	 * @param hi
	 */
	public static void quick3Way(Comparable[]  a,int lo,int hi){
		if(hi<=lo) return;
		int lt=lo,i=lo+1,gt=hi;
		Comparable v =a[lo];
		while(i<=gt){
			int cmp=a[i].compareTo(v);
			if(cmp<0) 
				Util.exch(a, lt++, i++);
			else if(cmp>0) 
				Util.exch(a, i, gt--);
			else 
				i++;
		}
		quick3Way(a, lo, lt-1);
		quick3Way(a, gt+1, hi);
	}
	public static void main(String[] args)
	{
		String test="fkasjkfhsaoif";
		//List<Character> list= new ArrayList<Character>();
		Character[] list = {'r','g','w','a','r','s','b','r','y','w','b','r'};
		quick3Way(list,0,11);
		for (Character character : list) {
			System.out.print (character+" ");
		}
		
	}
}

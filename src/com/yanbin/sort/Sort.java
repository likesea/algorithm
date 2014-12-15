package com.yanbin.sort;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

import com.yanbin.sort.util.*;
import com.yanbin.util.StdRandom;

public class Sort {
	/**
	 * �������飬���ڹ鲢����
	 */
	private static Comparable[] aux;
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
	 * �Ż�1,�ڲ�ѭ��������Ԫ�ؽ�������
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
	public static void insertSort(Comparable [] a,int lo,int hi) {
		for (int i = lo; i <=hi; i++) {
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
	 * ϣ������
	 * @param args
	 */
	public static void shellSort(Comparable[] a){
		int length = a.length;
		int h = 1;
		while(h<length/3) h=3*h+1;
		while(h>=1){
			for (int i = h; i < length; i++) {
				for (int j = i; j>=h&&Util.less(a[j], a[j-h]); j-=h) {
					Util.exch(a, j, j-h);
				}
			}
			h=h/3;
		}
	}
	/**
	 * �鲢������ڵ��÷������Զ����µ�
	 * @param a
	 */
	public static void mergeSort(Comparable[] a) {
		int length = a.length;
		aux =new Comparable[length];
		mergeSortAux(a, 0, length-1);
	}
	/**
	 * �鲢�����Ե����ϣ�����������
	 * @param a
	 */
	public static void mergeSort1(Comparable[] a){
		int length = a.length;
		aux=new Comparable[length];
		for (int sz = 1; sz <length; sz+=sz) {
			/**����������ԭ����ÿ��i����һ����sz��
			 * ������������������
			 * ��һ���������һ��mergeʱ����ʵÿ������ֻ��һ��Ԫ�أ���һ���������õ�
			 * �ڶ�������Ҫ���ǣ��������޷��������ģ���Ϊ��2*sz>length��Ҳ�����һ������������ʱ��
			 * �ڲ�ѭ��ֻ�Ǵ����������������ĸ���merge����û�н���������merge
			 */
			for (int i = 0; i < length; i+=sz+sz) {
				merge(a, i, i+sz-1, Math.min(i+sz+sz-1, length-1));
			}
		}
	}
	/**
	 * �鲢���򣬵��õĵݹ鷽��������˼�������,�������СС��15ʱ��
	 * ���ò�������
	 * @param a
	 * @param lo
	 * @param hi
	 */
	private static void mergeSortAux(Comparable[] a,int lo,int hi) {
		if(hi<=lo) return;
		/*if(hi<=lo+15){
			insertSort(a, lo, hi);
			return;
		}*/
		int mid = lo+(hi-lo)/2;
		mergeSortAux(a, lo, mid);
		mergeSortAux(a, mid+1, hi);
		//�Ż�������Ѿ����򣬾Ͳ����ٵ���merge����
		if(!Util.less(a[mid],a[mid+1])){
			merge(a, lo, mid, hi);
		}
		
	}
	/**
	 * �鲢�����е�merge��������
	 * @param a
	 * @param lo
	 * @param mid
	 * @param hi
	 */
	private static void merge(Comparable[] a,int lo,int mid,int hi) {
		for (int m = lo; m <=hi; m++) {
			aux[m]=a[m];
		}
		int i=lo;
		int j=mid+1;
		for (int k = lo; k <=hi; k++) {
			if(i>mid) a[k]=aux[j++];
			else if (j>hi) a[k]=aux[i++];
			else if(Util.less(aux[j], aux[i])) a[k]=a[j++];
			else a[k]=aux[i++];
		}
	}
	public static void quickSort(Comparable[] a) {
		StdRandom.shuffle(a);
		quick(a, 0, a.length-1);
	}
	private static void quick(Comparable[]a,int lo,int hi) {
		if(hi<=lo) return;
		int j = partion(a,lo,hi);
		quick(a, lo, j-1);
		quick(a, j+1, hi);
	}
	/**
	 * �з֣�����Ԫ�طŵ����ʵ�λ��
	 * ������Լ�ʵ��ʱ�����ǵ�����������������ǰ�ģ���������������
	 * һ��ʵ�ֵ��鷳��������before ��after�������Ԫ�صĴ�С���Լ������������ȵ�
	 * ����Ҫ���Ƕ�����Ԫ���������С�ģ��������λ�ý������󣬷������ǲ���
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	private static int partion(Comparable[]a,int lo,int hi) {
		Comparable v = a[lo];
		int before = lo;
		int after = hi+1;
		while(true){
			while(Util.less(a[++before], v))
				if(before==hi) break;
			while(Util.less(v, a[--after])){
				//�������before==hi������ģ���Ϊ�з�Ԫ�ؾ���v=a[lo],�������ܱ��Լ�С�����
				//�������code����ɾ��
				if(before==hi) break;
			}
				 
			if(before>=after) break;
			Util.exch(a, before, after);
		}
		Util.exch(a, lo, after);
		return after;
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

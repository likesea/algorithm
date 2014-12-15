package com.yanbin.sort;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

import com.yanbin.sort.util.*;
import com.yanbin.util.StdRandom;

public class Sort {
	/**
	 * 辅助数组，用于归并排序
	 */
	private static Comparable[] aux;
	/**
	 * 选择排序 简单实现 不稳定，例如[4,3,4,1,2]
	 * 因为选择排序是交换当前值和剩余元素的最小值，是交换次数最少的排序算法
	 * @param a
	 */
	public static void selectionSort(Comparable[] a) {
		int length = a.length;
		for(int i=0;i<length;i++){
			int min = i;//最小元素索引
			for(int j =i;j<length;j++){
				if(Util.less(a[j], a[min])) min=j;
			}
			Util.exch(a, i, min);
		}
	}
	/**
	 * 插入排序 简单实现，稳定算法，当前元素左边都是排序号的，当前元素以此向前交换直到合适的位置
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
	 * 优化1,内部循环，减少元素交换次数
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
	 * 希尔排序
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
	 * 归并排序，入口调用方法，自顶向下的
	 * @param a
	 */
	public static void mergeSort(Comparable[] a) {
		int length = a.length;
		aux =new Comparable[length];
		mergeSortAux(a, 0, length-1);
	}
	/**
	 * 归并排序，自底向上，代码量更少
	 * @param a
	 */
	public static void mergeSort1(Comparable[] a){
		int length = a.length;
		aux=new Comparable[length];
		for (int sz = 1; sz <length; sz+=sz) {
			/**子数组排序，原本是每次i递增一倍的sz，
			 * 这样带来了两个问题
			 * 第一在子数组第一次merge时候，其实每个数组只有一个元素，这一步是无作用的
			 * 第二，更重要的是，这样是无法完成排序的，因为在2*sz>length，也即最后一次子数组排序时候
			 * 内部循环只是处理了最大两个数组的各自merge，而没有将它们两个merge
			 */
			for (int i = 0; i < length; i+=sz+sz) {
				merge(a, i, i+sz-1, Math.min(i+sz+sz-1, length-1));
			}
		}
	}
	/**
	 * 归并排序，调用的递归方法，分治思想的体现,在数组大小小于15时候，
	 * 调用插入排序
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
		//优化，如果已经有序，就不用再调用merge方法
		if(!Util.less(a[mid],a[mid+1])){
			merge(a, lo, mid, hi);
		}
		
	}
	/**
	 * 归并排序中的merge辅助方法
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
	 * 切分，将首元素放到合适的位置
	 * 在最初自己实现时，考虑的是两个索引都是向前的，这样带来的问题
	 * 一是实现的麻烦，纠结于before 和after相对与首元素的大小，以及交换和自增等等
	 * 更重要的是对于首元素如果是最小的，还会出现位置交换错误，反正就是不行
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
				//测试田间before==hi是冗余的，因为切分元素就是v=a[lo],他不可能比自己小，因此
				//下面这句code可以删除
				if(before==hi) break;
			}
				 
			if(before>=after) break;
			Util.exch(a, before, after);
		}
		Util.exch(a, lo, after);
		return after;
	}
	/**
	 * 3向切分实现，对于重复元素比较多的数组比较有效，也是以首元素作为分割点的
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

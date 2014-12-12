package com.yanbin.sort;
import java.util.ArrayList;
import java.util.List;

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
	public static void main(String[] args)
	{
		String test="fkasjkfhsaoif";
		//List<Character> list= new ArrayList<Character>();
		Character[] list = {'w','r','a','g'};
		insertSort1(list);
		for (Character character : list) {
			System.out.print (character+" ");
		}
		
	}
}

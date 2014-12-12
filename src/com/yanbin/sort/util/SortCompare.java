package com.yanbin.sort.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method; 
import java.util.Timer;

import com.yanbin.util.StdOut;
import com.yanbin.util.StdRandom;

public class SortCompare {
	public static double time(String alg,Double[] a) 
	{	
		long sysDate=0;
		long sysDate1=0;
		try {
			Class c;
			c = Class.forName("com.yanbin.sort.Sort");
			Method m = c.getMethod(alg, new Class[]{Comparable[].class});
			sysDate = System.currentTimeMillis();
			m.invoke(c, new Object[]{a});
			sysDate1 = System.currentTimeMillis();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return sysDate1-sysDate;
		
	}
	
	/**
	 * T个长度为N的数组
	 * @param alg
	 * @param N
	 * @param T
	 * @return
	 */
	public static double timeRandomInput(String alg,int N,int T)
	{
		double total = 0.0;
		Double[] a = new Double[N];
		for (int i = 0; i < T; i++) {
			for (int j = 0; j < N; j++) {
				a[j]=StdRandom.uniform();
			}
			total+=time(alg, a);
		}
		return total;
	}
	public static void main(String[] args){
		String alg1 = args[0];
		String alg2 = args[1];
		int N =Integer.parseInt(args[2]);
		int T =Integer.parseInt(args[3]);
		double t1 = timeRandomInput(alg1, N, T); // total for alg1
		double t2 = timeRandomInput(alg2, N, T); // total for alg2
		StdOut.printf("For %d random Doubles\n %s is", N, alg1);
		StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);
		
	}
}

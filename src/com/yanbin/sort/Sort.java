package com.yanbin.sort;
import com.yanbin.sort.util.*;

public class Sort {
	public static void selectionSort(Comparable[] a) {
		int length = a.length;
		for(int i=0;i<length;i++){
			int min = i;//最小元素索引
			for(int j =i;j<length;i++){
				if(Util.less(a[j], a[min])) min=j;
				Util.exch(a, i, min);
			}
		}
	}
}

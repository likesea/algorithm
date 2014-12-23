package com.yanbin.search;

import com.yanbin.util.ST;
import com.yanbin.util.SequentialSearchST;
import com.yanbin.util.StdIn;
import com.yanbin.util.StdOut;
import com.yanbin.util.VisualAccumulator;
public class FluencyCounter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int distinct = 0,words=0;
		int minlen = Integer.parseInt(args[0]);
		
		//BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(1000);
		
		SequentialSearchST<String, Integer> st =new SequentialSearchST<String, Integer>();
		//BST<String, Integer> st = new BST<String, Integer>();
		
		
		String keyString;
		int compare=0;
		VisualAccumulator accumulator = new VisualAccumulator(15000, 500);
		while(!StdIn.isEmpty()){
			keyString=StdIn.readString();
			if(keyString.length()<minlen) continue;
			words++;
			
			if(st.contains(keyString)){
				
				st.put(keyString, st.get(keyString)+1);
			}
			else{
				st.put(keyString, 1);
				distinct++;
			}
			compare= st.getCompareTimes();
			accumulator.addDataValue((double)compare);
		}
		System.out.println(accumulator.mean());
		
	}

}

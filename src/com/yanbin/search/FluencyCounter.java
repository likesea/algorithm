package com.yanbin.search;

import com.yanbin.util.ST;
import com.yanbin.util.StdIn;
import com.yanbin.util.StdOut;
public class FluencyCounter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int distinct = 0,words=0;
		int minlen = Integer.parseInt(args[0]);
		ST<String, Integer> st = new ST<String, Integer>();
		String keyString;
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
		}
		String maxString="";
		st.put(maxString, 0);
		for (String word : st.keys()) {
			if(st.get(word)>st.get(maxString)){
				maxString=word;
			}
		}
		StdOut.println(maxString + " " + st.get(maxString));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
	}

}

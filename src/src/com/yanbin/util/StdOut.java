package com.yanbin.util;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class StdOut {
	private static final String CHARSET_NAME="UTF-8";
	private static final Locale LOCALE = Locale.US;
	private static PrintWriter out;
	static{
		try{
			out = new PrintWriter(new OutputStreamWriter(System.out,CHARSET_NAME),true);
		}
		catch(UnsupportedEncodingException e){System.out.println(e);}
	}
	private StdOut(){};
	
	public static void close() {
		out.close();
	}
	public static void println() {
		out.println();
	}
	/**
	 * generic method
	 * @param x
	 */
	public static <T> void  println(T x) {
		out.println(x);
	}
	public static void print() {
		out.flush();
	}
	/**
	 * generic method
	 * @param xObject
	 */
	public static <T> void print(T xObject) {
		out.print(xObject);
		out.flush();
	}
	public static void printf(String format,Object...args) {
		out.printf(LOCALE,format, args);
		out.flush();
	}
	public static void printf(Locale locale,String format,Object...args){
		out.printf(locale, format,args);
		out.flush();
	}
	/**
	 * The method is just here to test methods;
	 * @param args
	 */
	public static void main(String[] args) {

        // write to stdout
        StdOut.println("Test");
        StdOut.println(17);
        StdOut.println(true);
        StdOut.printf("%.6f\n", 1.0/7.0);
    }
}

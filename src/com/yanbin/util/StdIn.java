/**
 * 参考Robert Sedgewick的算法第四版源码
 */
package com.yanbin.util;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;


public class StdIn {
	private  StdIn() {}
	private static Scanner scanner;
	private static final String CHARSET_NAME = "UTF-8";
	private static final Locale LOCALE = Locale.US;
	private static final Pattern EMPTY_PATTERN=Pattern.compile("");
	private static final Pattern WHITESPACE_PATTERN=Pattern.compile("\\p{javaWhitespace}+");
	private static final Pattern EVERYTHING_PATTERN=Pattern.compile("\\A");
	
	public static boolean isEmpty() {
		return !scanner.hasNext();
	}
	public static boolean hasNextLine() {
		return scanner.hasNextLine();
	}
	public static boolean hasNextChar() {
		scanner.useDelimiter(EMPTY_PATTERN);
		boolean result = scanner.hasNext();
		scanner.useDelimiter(WHITESPACE_PATTERN);
		return result;
	}
	public static String readLine() {
		String line;
		try {
			line=scanner.nextLine();
		} catch (Exception e) {
			// TODO: handle exception
			line=null;
		}
		return line;
	}
	public static char readChar() {
		scanner.useDelimiter(EMPTY_PATTERN);
		String ch=scanner.next();
		assert(ch.length()==1):"Internal (Std)In.readChar() error!"
			+"Please contact the authors.";
		scanner.useDelimiter(WHITESPACE_PATTERN);
		return ch.charAt(0);
	}
	public static String readAll() {
		if(!scanner.hasNextLine()) return "";
		String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
		scanner.useDelimiter(WHITESPACE_PATTERN);
		return result;
	}
    public static String readString() {
        return scanner.next();
    }
    public static int readInt() {
        return scanner.nextInt();
    }
    public static double readDouble() {
        return scanner.nextDouble();
    }


    public static float readFloat() {
        return scanner.nextFloat();
    }

    public static long readLong() {
        return scanner.nextLong();
    }


    public static short readShort() {
        return scanner.nextShort();
    }


    public static byte readByte() {
        return scanner.nextByte();
    }
    
    public static boolean readBoolean() {
		String string = readString();
		if(string.equalsIgnoreCase("true")) return true;
		if(string.equalsIgnoreCase("false")) return false;
		if(string.equals("1")) return true;
		if(string.equals("0")) return false;
		throw new InputMismatchException();
	}
    public static String[] readAllStrings() {
    	 // we could use readAll.trim().split(), but that's not consistent
        // because trim() uses characters 0x00..0x20 as whitespace
		String[] tokens = WHITESPACE_PATTERN.split(readAll());
		if(tokens.length==0||tokens[0].length()>0) 
			return tokens;
		String[] decapitokenStrings = new String[tokens.length-1];
		for (int i = 0; i < tokens.length; i++) {
			decapitokenStrings[i]=tokens[i+1];
		}
		return decapitokenStrings;
	}
    public static String[] readAllLines() {
        ArrayList<String> lines = new ArrayList<String>();
        while (hasNextLine()) {
            lines.add(readLine());
        }
        return lines.toArray(new String[0]);
    }
    public static int[] readAllInts() {
        String[] fields = readAllStrings();
        int[] vals = new int[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Integer.parseInt(fields[i]);
        return vals;
    }
    public static double[] readAllDoubles() {
        String[] fields = readAllStrings();
        double[] vals = new double[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Double.parseDouble(fields[i]);
        return vals;
    }
    static{
    	resync();
    }
    private static void resync() {
		setScanner(new Scanner(new java.io.BufferedInputStream(System.in),CHARSET_NAME));
	}
    private static void setScanner(Scanner scanner) {
		StdIn.scanner=scanner;
		StdIn.scanner.useLocale(LOCALE);
	}
    public static int[] readInts() {
        return readAllInts();
    }
    public static double[] readDoubles() {
        return readAllDoubles();
    }
    public static String[] readStrings() {
        return readAllStrings();
    }
    
    public static void main(String[] arg) {
    	StdOut.println("type string:");
    	String s = StdIn.readLine();
    	System.out.println("your string was "+s);
    	
    	System.out.println();
    	System.out.println("Type an int: ");
        int a = StdIn.readInt();
        System.out.println("Your int was: " + a);
        System.out.println();

        System.out.println("Type a boolean: ");
        boolean b = StdIn.readBoolean();
        System.out.println("Your boolean was: " + b);
        System.out.println();

        System.out.println("Type a double: ");
        double c = StdIn.readDouble();
        System.out.println("Your double was: " + c);
        System.out.println();
    }
}

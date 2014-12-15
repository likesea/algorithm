package src.com.yanbin.sort.test;

import java.util.Random;

import com.yanbin.util.StdOut;

public class ThreeSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = getInts(10);
		for (int i = 0; i < array.length; i++) {
			StdOut.println(array[i]);
		}
	}
	private static int[] getInts(int num) {
		int[] intArray = new int[num];
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			intArray[i]=random.nextInt(1000);
		}
		return intArray;
	}

}

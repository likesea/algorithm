package src.com.yanbin.sort.test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

import src.com.yanbin.sort.Sort;

import com.yanbin.sort.util.*;
import com.yanbin.sort.*;
public class UnitTest {

	@Test
	public void testMethod()
	{
		System.out.println(Util.less("aa", "bbb"));
	}
	@Test
	public void selection() {
		Integer[] aIntegers={new Integer(5),new Integer(3)};
		
		
		Sort.selectionSort(aIntegers);
		for (int i = 0; i < aIntegers.length; i++) {
			System.out.println(aIntegers[i]);
		}
	}
}

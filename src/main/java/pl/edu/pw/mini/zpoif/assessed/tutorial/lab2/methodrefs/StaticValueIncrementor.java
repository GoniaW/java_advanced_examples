package pl.edu.pw.mini.zpoif.assessed.tutorial.lab2.methodrefs;

import java.util.Random;

/**
 * 
 */
public class StaticValueIncrementor {
	
	public static int incrementByOddValue(int valueToIncrement) {
		int result;
		return (result = (new Random()).nextInt()) % 2 != 0 ? result : result + 1;
	}
	
	public static int incrementByEvenValue(int valueToIncrement) {
		int result;
		return (result = (new Random()).nextInt()) % 2 == 0 ? result : result + 1;
	}
	
}

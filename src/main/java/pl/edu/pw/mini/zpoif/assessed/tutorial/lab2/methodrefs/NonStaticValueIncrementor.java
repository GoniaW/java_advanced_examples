package pl.edu.pw.mini.zpoif.assessed.tutorial.lab2.methodrefs;

import java.util.Random;

/**
 * 
 */
public class NonStaticValueIncrementor {
	
	public int incrementByOddValueNonStatic(int valueToIncrement) {
		int result;
		return (result = (new Random()).nextInt()) % 2 != 0 ? result : result + 1;
	}
	
	public int incrementByEvenValueNonStatic(int valueToIncrement) {
		int result;
		return (result = (new Random()).nextInt()) % 2 == 0 ? result : result + 1;
	}
	
}

package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.staticnestedclasses;

import java.util.ArrayList;
/**
 * 
 * 
 * 
 */
public class StaticClassTutorial {
	
	private static String staticValue = "First value...";
	private String nonStaticValue = "Second value";
	
	/*
	 * Statyczna klasa wewnêtrzna, publiczna
	 */
	public static class StaticNestedClass {
		
		public void changeValues() {
			staticValue = "Value changed";//metody klasy statycznej maj¹ dostêp do pól statycznych klasy-w³aœciciela.
			//Linijka poni¿ej nie kompiluje siê->
			//nonStaticValue = "Second value changed...";
			//B³¹d. Metody klasy statycznej nie maj¹ dostêpu do pól klasy-w³aœciciela.
		}
		
	}
	/*
	 * Statyczna klasa wewnêtrzna, prywatna.
	 * Klasy wewnêtrzne mog¹ dziedziczyæ po innych klasach i implementowaæ interfejsy
	 */
	private static class PrivateStaticNestedClass extends ArrayList<String> implements Comparable<String> {
		
		private static String staticString = "My ststic string...";
		
		@Override
		public int compareTo(String arg0) {
			return 0;
		}
		
	}
	
	/*
	 * Statyczna klasa wewnêtrzna, publiczna.
	 * Klasy wewnêtrzne mog¹ dziedziczyæ po innych klasach wewnêtrznych
	 */
	public static class ExtendedPrivateStaticNestedClass extends PrivateStaticNestedClass {
		
		public static void sayHello() {
			System.out.println("Public static method is saying hello!");
		}
		
		private static void sayHelloPrivate() {
			System.out.println("Private static method is saying hello!");
		}
		
		public void sayHelloNonStatic() {
			System.out.println("Public NON static method is saying hello!");
		}
		
		private void sayHelloNonStaticPrivate() {
			System.out.println("Private NON static method is saying hello!");
		}
		
	}
	
	public static class CheckVisibility {
		//Linijka poni¿ej nie kompiluje siê-> poniewa¿ metody statyczne (nawet publiczne) nie s¹ widoczne dla innych klas wewnêtrznych.
		//ExtendedPrivateStaticNestedClass.sayHello();
	}
	
	public void doWork() {
		
		StaticClassTutorial.StaticNestedClass staticNestedClass = new StaticClassTutorial.StaticNestedClass();//Inicjalizacja 
		StaticClassTutorial.PrivateStaticNestedClass privateStaticNestedClass = new StaticClassTutorial.PrivateStaticNestedClass();
		//Klasê prywatn¹ mo¿na zainicjalizowaæ wewn¹trz klasy-w³aœciciela, poniewa¿ ma ona do niej dostêp jak do ka¿dego pola prywatnego.
		
		ExtendedPrivateStaticNestedClass.sayHello();
		
		//Metoda sayHelloPrivate jest prywatna, a mimo jest widoczna... dla klasy StaticClassTutorial, w której zosta³a ona zadeklarowana.
		ExtendedPrivateStaticNestedClass.sayHelloPrivate();
		
	}
	
	/*
	 * Je¿eli nasz klasa posiada metody, które przyjmuj¹ jakieœ parametry i s¹ to instancje klas, 
	 * to je¿eli s¹ one wykorzytywane tylko do tego celu, to mo¿na stworzyæ widoczn¹ na zewn¹trz kasê wewnêtrzn¹, 
	 * s³u¿¹c¹ do tego celu. 
	 */
	public class Triangle{
		
		private int base;
		private int height;
		
		public Triangle(int base, int height) {
			super();
			this.base = base;
			this.height = height;
		}
		
		public int getBase() {
			return base;
		}
		
		public int getHeight() {
			return height;
		}
		
	}
	
	public double calculateSurfaceArea(Triangle triangle) {
		return 0.5 * triangle.getBase() * triangle.getHeight();
	}
	
}

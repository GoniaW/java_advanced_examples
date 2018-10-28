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
	 * Statyczna klasa wewn�trzna, publiczna
	 */
	public static class StaticNestedClass {
		
		public void changeValues() {
			staticValue = "Value changed";//metody klasy statycznej maj� dost�p do p�l statycznych klasy-w�a�ciciela.
			//Linijka poni�ej nie kompiluje si�->
			//nonStaticValue = "Second value changed...";
			//B��d. Metody klasy statycznej nie maj� dost�pu do p�l klasy-w�a�ciciela.
		}
		
	}
	/*
	 * Statyczna klasa wewn�trzna, prywatna.
	 * Klasy wewn�trzne mog� dziedziczy� po innych klasach i implementowa� interfejsy
	 */
	private static class PrivateStaticNestedClass extends ArrayList<String> implements Comparable<String> {
		
		private static String staticString = "My ststic string...";
		
		@Override
		public int compareTo(String arg0) {
			return 0;
		}
		
	}
	
	/*
	 * Statyczna klasa wewn�trzna, publiczna.
	 * Klasy wewn�trzne mog� dziedziczy� po innych klasach wewn�trznych
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
		//Linijka poni�ej nie kompiluje si�-> poniewa� metody statyczne (nawet publiczne) nie s� widoczne dla innych klas wewn�trznych.
		//ExtendedPrivateStaticNestedClass.sayHello();
	}
	
	public void doWork() {
		
		StaticClassTutorial.StaticNestedClass staticNestedClass = new StaticClassTutorial.StaticNestedClass();//Inicjalizacja 
		StaticClassTutorial.PrivateStaticNestedClass privateStaticNestedClass = new StaticClassTutorial.PrivateStaticNestedClass();
		//Klas� prywatn� mo�na zainicjalizowa� wewn�trz klasy-w�a�ciciela, poniewa� ma ona do niej dost�p jak do ka�dego pola prywatnego.
		
		ExtendedPrivateStaticNestedClass.sayHello();
		
		//Metoda sayHelloPrivate jest prywatna, a mimo jest widoczna... dla klasy StaticClassTutorial, w kt�rej zosta�a ona zadeklarowana.
		ExtendedPrivateStaticNestedClass.sayHelloPrivate();
		
	}
	
	/*
	 * Je�eli nasz klasa posiada metody, kt�re przyjmuj� jakie� parametry i s� to instancje klas, 
	 * to je�eli s� one wykorzytywane tylko do tego celu, to mo�na stworzy� widoczn� na zewn�trz kas� wewn�trzn�, 
	 * s�u��c� do tego celu. 
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

package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.nestedclasses;

public class NestedClassTutorial {
	
	private String internalValue;
	private int secretNumber = 18;
	
	public NestedClassTutorial(String internalValue) {
		super();
		this.internalValue = internalValue;
	}
	
	public class NestedClass {
		
		/*
		 * Wewn�trzna klasa niestatyczna (nazywana "nested class" albo "inner class") ma dost�p do p�l instancji klasy z kt�rej zosta�a zainicjalizowana. 
		 */
		public void doSomething() {
			System.out.println("Print secret number: " + secretNumber);
		}
		
		public String getInternalValue() {
			return internalValue;
		}
		
	}
	
	public void sayHello() {
		System.out.println("Hello it is main class (NestedClassTutorial).");
	}
	
	public class Refer2MyMethod {
		
		public void sayHello() {
			System.out.println("Hello it is a nested class (Refer2MyMethod).");
		}
		
		/*
		 * Zauwa�my, �e istnieje metoda sayHello() istnieje zar�wno w klasie g��wnej jak i wewn�trznej. 
		 */
		public void doSomething() {
			
			/*
			 * Poni�ej widoczne jest wywo�anie metody z wn�trzna klasy wewn�trznej. 
			 */
			sayHello();
			this.sayHello();
			
			/*
			 * Tutaj wywolujemy metod� z klasy zawieraj�cej klas� NestedClass (NestedClassTutorial).
			 */
			NestedClassTutorial.this.sayHello();
			
		}
		
	}
	
	/*
	 * Niestatyczna klasa wewn�trzna mo�e dziedziczy� po innych klasach oraz implementowa� interfejsy. 
	 */
	public class ExtendingClass extends Thread implements Comparable<ExtendingClass> {

		@Override
		public int compareTo(ExtendingClass o) {
			//Do it...
			return 0;
		}
		
	}
	
	public void doWork() {
		/*
		 * Wewn�trz klasy zawieraj�cej klas� wewn�trzn�, mo�na si� do niej odwo�ywa�, 
		 * bez konieczno�ci stosowania takiej sk�adni: NestedClassTutorial.NestedClass. 
		 */
		NestedClass myNestedClass = new NestedClass();
		
		/*
		 * Mo�na te� i tak: 
		 */
		NestedClassTutorial.NestedClass nestedClassInitializedInAnotherWay = (new NestedClassTutorial("Second")).new NestedClass();
		NestedClassTutorial.NestedClass nestedClassInitializedInAnotherWay2 = (new NestedClassTutorial("Third")).new NestedClass();
		
		/*
		 * ... lub tak:
		 */
		NestedClassTutorial.NestedClass nestedClassInitializedPayAnother = this.new NestedClass();
		
		/*
		 * Nale�y jednak pami�ta�, i� niestatyczna klasa wewn�trzna jest stowarzyszona (ma dost�p do p�l) z INSTANCJ� klasy w kt�rej zosta�a zadeklarowana. 
		 * Instancja klasy NestedClassTutorial ma pole internalValue. Jak� warto�� zwr�ci wi�c metoda getInternalValue z klasy wewn�trznej NestedClass? 
		 * Tak�, jaka zosta�a ustawiona w instancji klasy NestedClassTutorial. 
		 * nestedClassInitializedInAnotherWay.getInternalValue() zwr�ci "Second", a nestedClassInitializedInAnotherWay2 getInternalValue() - "Third";
		 */
		
		System.out.println("\nmyNestedClass-internalValue: " + myNestedClass.getInternalValue() 
							+ " \nnestedClassInitializedInAnotherWay-internalValue: " + nestedClassInitializedInAnotherWay.getInternalValue() 
								+ " \nnestedClassInitializedInAnotherWay2-internalValue: " + nestedClassInitializedInAnotherWay2.getInternalValue() 
									+ " \nnestedClassInitializedPayAnother-internalValue: " + nestedClassInitializedPayAnother.getInternalValue());
			
		
		
	}
}

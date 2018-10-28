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
		 * Wewnêtrzna klasa niestatyczna (nazywana "nested class" albo "inner class") ma dostêp do pól instancji klasy z której zosta³a zainicjalizowana. 
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
		 * Zauwa¿my, ¿e istnieje metoda sayHello() istnieje zarówno w klasie g³ównej jak i wewnêtrznej. 
		 */
		public void doSomething() {
			
			/*
			 * Poni¿ej widoczne jest wywo³anie metody z wnêtrzna klasy wewnêtrznej. 
			 */
			sayHello();
			this.sayHello();
			
			/*
			 * Tutaj wywolujemy metodê z klasy zawieraj¹cej klasê NestedClass (NestedClassTutorial).
			 */
			NestedClassTutorial.this.sayHello();
			
		}
		
	}
	
	/*
	 * Niestatyczna klasa wewnêtrzna mo¿e dziedziczyæ po innych klasach oraz implementowaæ interfejsy. 
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
		 * Wewn¹trz klasy zawieraj¹cej klasê wewnêtrzn¹, mo¿na siê do niej odwo³ywaæ, 
		 * bez koniecznoœci stosowania takiej sk³adni: NestedClassTutorial.NestedClass. 
		 */
		NestedClass myNestedClass = new NestedClass();
		
		/*
		 * Mo¿na te¿ i tak: 
		 */
		NestedClassTutorial.NestedClass nestedClassInitializedInAnotherWay = (new NestedClassTutorial("Second")).new NestedClass();
		NestedClassTutorial.NestedClass nestedClassInitializedInAnotherWay2 = (new NestedClassTutorial("Third")).new NestedClass();
		
		/*
		 * ... lub tak:
		 */
		NestedClassTutorial.NestedClass nestedClassInitializedPayAnother = this.new NestedClass();
		
		/*
		 * Nale¿y jednak pamiêtaæ, i¿ niestatyczna klasa wewnêtrzna jest stowarzyszona (ma dostêp do pól) z INSTANCJ¥ klasy w której zosta³a zadeklarowana. 
		 * Instancja klasy NestedClassTutorial ma pole internalValue. Jak¹ wartoœæ zwróci wiêc metoda getInternalValue z klasy wewnêtrznej NestedClass? 
		 * Tak¹, jaka zosta³a ustawiona w instancji klasy NestedClassTutorial. 
		 * nestedClassInitializedInAnotherWay.getInternalValue() zwróci "Second", a nestedClassInitializedInAnotherWay2 getInternalValue() - "Third";
		 */
		
		System.out.println("\nmyNestedClass-internalValue: " + myNestedClass.getInternalValue() 
							+ " \nnestedClassInitializedInAnotherWay-internalValue: " + nestedClassInitializedInAnotherWay.getInternalValue() 
								+ " \nnestedClassInitializedInAnotherWay2-internalValue: " + nestedClassInitializedInAnotherWay2.getInternalValue() 
									+ " \nnestedClassInitializedPayAnother-internalValue: " + nestedClassInitializedPayAnother.getInternalValue());
			
		
		
	}
}

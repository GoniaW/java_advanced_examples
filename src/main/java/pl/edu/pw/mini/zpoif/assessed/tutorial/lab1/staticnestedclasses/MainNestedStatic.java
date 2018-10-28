package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.staticnestedclasses;
/**
 * 
 * 
 * 
 */
public class MainNestedStatic {

	public static void main(String[] args) {
		test();
	}
	
	private static void test() {
		
		/*
		 * Klasa StaticNestedClass jest statyczna (zachowuje siê jak pole statyczne). 
		 * Mo¿na zatem odwo³aæ siê do niej poprzez klasê StaticClassTutorial.
		 */
		StaticClassTutorial.StaticNestedClass staticNestedClass = new StaticClassTutorial.StaticNestedClass();
		
		/*
		 * Linijka poni¿ej nie kompiluje siê.
		 * B³¹d. Wewnêtrzna klasa prywatna nie jest widoczna z punktu widzenia innej klasy (MainClasses). 
		 * Mo¿na j¹ zainicjalizowaæ tylko wewn¹trz klasy (ClassTutorial) w której jest ona dostêpna. 
		 */
		//ClassTutorial.PrivateStaticNestedClass privateStaticNestedClass = new ClassTutorial.PrivateStaticNestedClass();
		
		//Nie kompiluje siê-> poniewa¿ metoda sayHelloPrivate() jest prywatna, wiêc nie jest widoczna z punktu widzenia niniejszego wywo³ania
		//StaticClassTutorial.ExtendedPrivateStaticNestedClass.sayHelloPrivate();
		
	}

}

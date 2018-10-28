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
		 * Klasa StaticNestedClass jest statyczna (zachowuje si� jak pole statyczne). 
		 * Mo�na zatem odwo�a� si� do niej poprzez klas� StaticClassTutorial.
		 */
		StaticClassTutorial.StaticNestedClass staticNestedClass = new StaticClassTutorial.StaticNestedClass();
		
		/*
		 * Linijka poni�ej nie kompiluje si�.
		 * B��d. Wewn�trzna klasa prywatna nie jest widoczna z punktu widzenia innej klasy (MainClasses). 
		 * Mo�na j� zainicjalizowa� tylko wewn�trz klasy (ClassTutorial) w kt�rej jest ona dost�pna. 
		 */
		//ClassTutorial.PrivateStaticNestedClass privateStaticNestedClass = new ClassTutorial.PrivateStaticNestedClass();
		
		//Nie kompiluje si�-> poniewa� metoda sayHelloPrivate() jest prywatna, wi�c nie jest widoczna z punktu widzenia niniejszego wywo�ania
		//StaticClassTutorial.ExtendedPrivateStaticNestedClass.sayHelloPrivate();
		
	}

}

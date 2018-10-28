package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.nestedclasses;

import pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.nestedclasses.NestedClassTutorial.NestedClass;

public class MainNested {

	public static void main(String[] args) {
		
		/*
		 * Warto�� "First" jest ustawiona na instancji klasy, aby pokaza� w jakich okoliczno�ciach jest ona 
		 * dost�pna dla klasy wewn�trznej. 
		 * 
		 * Patrz metoda doWork().
		 */
		NestedClassTutorial nestedClassTutorial = new NestedClassTutorial("First");
		nestedClassTutorial.doWork();
		
		/*
		 * Je�eli chcemy zainstancjonowa� klas� wewn�trzn�, to musimy utworzy� obiekt 
		 * W metodzie doWork klasy NestedClassTutorial, nie trzeba tego robi�, poniewa� skoro mo�na wywo�a� niestatyczn� metod� doWork, 
		 * to znaczy �e instancja klasy NestedClassTutorial ju� istnieje. 
		 */
		NestedClassTutorial.NestedClass nestedClassInstantiatedExternally = (new NestedClassTutorial("Tenth")).new NestedClass();
		
		/*
		 * Co� takiego (znanego z metody doWork si� nie uda). 
		 */
		//NestedClass nestedClass = new NestedClass();
		
	}

}

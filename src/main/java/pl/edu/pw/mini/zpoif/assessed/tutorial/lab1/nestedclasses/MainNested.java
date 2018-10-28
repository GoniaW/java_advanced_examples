package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.nestedclasses;

import pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.nestedclasses.NestedClassTutorial.NestedClass;

public class MainNested {

	public static void main(String[] args) {
		
		/*
		 * Wartoœæ "First" jest ustawiona na instancji klasy, aby pokazaæ w jakich okolicznoœciach jest ona 
		 * dostêpna dla klasy wewnêtrznej. 
		 * 
		 * Patrz metoda doWork().
		 */
		NestedClassTutorial nestedClassTutorial = new NestedClassTutorial("First");
		nestedClassTutorial.doWork();
		
		/*
		 * Je¿eli chcemy zainstancjonowaæ klasê wewnêtrzn¹, to musimy utworzyæ obiekt 
		 * W metodzie doWork klasy NestedClassTutorial, nie trzeba tego robiæ, poniewa¿ skoro mo¿na wywo³aæ niestatyczn¹ metodê doWork, 
		 * to znaczy ¿e instancja klasy NestedClassTutorial ju¿ istnieje. 
		 */
		NestedClassTutorial.NestedClass nestedClassInstantiatedExternally = (new NestedClassTutorial("Tenth")).new NestedClass();
		
		/*
		 * Coœ takiego (znanego z metody doWork siê nie uda). 
		 */
		//NestedClass nestedClass = new NestedClass();
		
	}

}

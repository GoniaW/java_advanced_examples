package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.staticnestedclasses;
/**
 * 
 * Istnieje mo¿liwoœæ dziedziczenia po klasach zawieraj¹cych klasy wewnêtrzne statyczne. 
 * Z racji swej "statycznoœci" i odpowiednich modyfikatorów s¹ one widoczne.
 * 
 */
public class ExtendedStaticClassTutorial extends StaticClassTutorial{
	
	/*
	 Deklaracje klas statycznych s¹ widoczne w klasach dziedzicz¹cych, 
	 podobnie jak pola o ile nie wyklucza tego u¿yty modyfikator.
	 */
	
	public void showClasses() {
		StaticNestedClass s;//Klasa ta jest widoczna
		//Linijka poni¿ej nie kompiluje siê->
		//PrivateStaticNestedClass privateStaticNestedClass;
		//Definicja tej klasy jest oznaczona jako "private", nie jest wiêc widoczna 
	}
	
}

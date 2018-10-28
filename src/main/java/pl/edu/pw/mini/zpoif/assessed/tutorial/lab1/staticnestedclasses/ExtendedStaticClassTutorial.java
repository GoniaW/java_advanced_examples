package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.staticnestedclasses;
/**
 * 
 * Istnieje mo�liwo�� dziedziczenia po klasach zawieraj�cych klasy wewn�trzne statyczne. 
 * Z racji swej "statyczno�ci" i odpowiednich modyfikator�w s� one widoczne.
 * 
 */
public class ExtendedStaticClassTutorial extends StaticClassTutorial{
	
	/*
	 Deklaracje klas statycznych s� widoczne w klasach dziedzicz�cych, 
	 podobnie jak pola o ile nie wyklucza tego u�yty modyfikator.
	 */
	
	public void showClasses() {
		StaticNestedClass s;//Klasa ta jest widoczna
		//Linijka poni�ej nie kompiluje si�->
		//PrivateStaticNestedClass privateStaticNestedClass;
		//Definicja tej klasy jest oznaczona jako "private", nie jest wi�c widoczna 
	}
	
}

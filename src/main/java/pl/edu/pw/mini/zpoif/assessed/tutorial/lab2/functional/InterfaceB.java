package pl.edu.pw.mini.zpoif.assessed.tutorial.lab2.functional;

@FunctionalInterface
/*
 * Powy�sza adnotacja (@FunctionalInterface) m�wi kompilatorowi, i� ten interfejs
 * ma by� interfejsem funkcyjnym, co oznacza, �e mo�e mie� conajwy�ej jedn� metod� do zaimplementownia. 
 * Je�eli odkomentowaliby�my metod� doSomethingElse();, kompilator wy�wietli�by nam b��d. 
 * Zatem interfejsy z tak� adnotacj� s� w pewnym sensie "pilnowane" aby nikt nigdy nie wstawi� dodatkowej metody, 
 * co uniemo�liwi�oby zaimplementowanie tego interfejsu za pomoc� wyra�enia lambda. 
 * 
 * Prosz� odkomentowa� na chwil� i zobaczy�, co si� stanie. 
 * 
 * Po co si� tego u�ywa? Dla wymuszenia porz�dku w projekcie. Nikt, kto b�dzie p�zniej rozwija� dan� aplikacj� nie 
 * wpadnie na pomys�, �eby rozwin�� ten interfejs o dodatkow� metod�. Jest to zatem rodzaj informacji "dla potomnych", 
 * wspierany dodatkowo przez kompilator.
 * 
 * Interfejs bez adnotacji r�wnie� mo�e by� zaimplementowany za pomoc� lambd. Dodanie nowej metody, nie spowoduje b��du 
 * kompilatora w tym miejscu. 
 * 
 */
public interface InterfaceB {
	void doSomething();
	//void doSomethingElse();
}

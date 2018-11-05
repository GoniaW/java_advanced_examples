package pl.edu.pw.mini.zpoif.assessed.tutorial.lab2.functional;

@FunctionalInterface
/*
 * Powy¿sza adnotacja (@FunctionalInterface) mówi kompilatorowi, i¿ ten interfejs
 * ma byæ interfejsem funkcyjnym, co oznacza, ¿e mo¿e mieæ conajwy¿ej jedn¹ metodê do zaimplementownia. 
 * Je¿eli odkomentowalibyœmy metodê doSomethingElse();, kompilator wyœwietli³by nam b³¹d. 
 * Zatem interfejsy z tak¹ adnotacj¹ s¹ w pewnym sensie "pilnowane" aby nikt nigdy nie wstawi³ dodatkowej metody, 
 * co uniemo¿liwi³oby zaimplementowanie tego interfejsu za pomoc¹ wyra¿enia lambda. 
 * 
 * Proszê odkomentowaæ na chwilê i zobaczyæ, co siê stanie. 
 * 
 * Po co siê tego u¿ywa? Dla wymuszenia porz¹dku w projekcie. Nikt, kto bêdzie pózniej rozwija³ dan¹ aplikacjê nie 
 * wpadnie na pomys³, ¿eby rozwin¹æ ten interfejs o dodatkow¹ metodê. Jest to zatem rodzaj informacji "dla potomnych", 
 * wspierany dodatkowo przez kompilator.
 * 
 * Interfejs bez adnotacji równie¿ mo¿e byæ zaimplementowany za pomoc¹ lambd. Dodanie nowej metody, nie spowoduje b³êdu 
 * kompilatora w tym miejscu. 
 * 
 */
public interface InterfaceB {
	void doSomething();
	//void doSomethingElse();
}

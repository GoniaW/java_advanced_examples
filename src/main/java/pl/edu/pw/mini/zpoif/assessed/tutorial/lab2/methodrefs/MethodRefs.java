package pl.edu.pw.mini.zpoif.assessed.tutorial.lab2.methodrefs;

import java.util.Random;
import java.util.function.Consumer;

public class MethodRefs {
	/*
	 * Klasa ta b�dzie jednocze�nie klas� startow� jak i demonstracyjn�. 
	 */
	public static void main(String[] args) {
		MethodRefs methodRefs = new MethodRefs();
		methodRefs.doWork();
	}

	private interface Agregator <T, U, V, R> {
		R agregate(T type, U firstArgument, V secondArgument);
	}
	
	private void doWork() {
		/*
		 * Za��my, �e mamy dwa interfejsy: OddIncrementable i EvenIncrementable, kt�re zawieraj� nag��wki metod
		 * dodawaj�cych do danej liczby odpowiednio: losow� nieparzyst� oraz losow� patrzyst� liczb�. 
		 * Chcemy zaimplementowa� je za pomoc� wyra�e� lambda. 
		 */
		
		StaticValueIncrementor valueIncrementor = new StaticValueIncrementor();
		System.out.println("Warto�� powi�kszona o losow� liczb� nieparzyst�: " + StaticValueIncrementor.incrementByOddValue(10));
		System.out.println("Warto�� powi�kszona o losow� liczb� parzyst�: " + valueIncrementor.incrementByEvenValue(10));
	
		OddIncrementable oddIncrementable = valueToIncrement -> {
			int result = 0;
			return (result = (new Random()).nextInt()) % 2 != 0 ? result : result + 1;
		};
		
		EvenIncrementable evenIncrementable = valueToIncrement -> {
			int result = 0;
			return (result = (new Random()).nextInt()) % 2 == 0 ? result : result + 1;
		};
	
		/*
		 * Uda�o si�. Za��my teraz, �e implementacja tych interfejs�w ju� gdzie� istnieje 
		 * Jest to klasa ValueIncrementor, kt�r� wcze�niej zdefiniowano. Zamiast kodowa� na nowo implementacj� 
		 * interfejs�w (tak jak to zrobiono powy�ej) mo�na poprostu odnie�� si� do istniej�cych metod. 
		 */
		
		
		/*
		 * 										Statyczne referencje. 
		 */
		/*
		 * Stworzyli�my wi�c wyra�anie lambda, kt�re w swoim ciele wywo�uje istniej�c� statyczn� metod� nale��c� 
		 * do klasy ValueIncrementor. 
		 */
		oddIncrementable = valueToIncrement -> StaticValueIncrementor.incrementByOddValue(valueToIncrement);
		/*
		 * Czy mo�na pro�ciej? Tak. Isnieje mo�liwo�� prostrzego zapisu. 
		 * Wskazujemy metod� statyczn�, kt�ra pobiera takie same argumenty jak implementowana metoda z interfejsu. 
		 * Kompilator sam zorientuje si�, �e warto�� valueToIncrement mo�e powinna by� przekazana do metody incrementByOddValue.
		 */
		oddIncrementable = StaticValueIncrementor::incrementByOddValue;
		
		/*
		 * Dwa powy�sze wyra�enia s� sobie r�wnowa�ne. 
		 */
		
		evenIncrementable = valueToIncrement -> StaticValueIncrementor.incrementByEvenValue(valueToIncrement);
		evenIncrementable = StaticValueIncrementor::incrementByEvenValue;
		
		/*
		 * 										Niestatyczne referencje. 
		 */
		/*
		 * Za��my, ze nasze wyra�enie lambda wywo�uje jak�� niestatyczn� metod� NA OBIEKCIE, 
		 * kt�ry przekazany zosta� jako argument. 
		 * 
		 */
		ValueIncrementorUsable valueIncrementorUsable = (nonStaticValueIncrementor, valueToIncrement) -> nonStaticValueIncrementor.incrementByOddValueNonStatic(valueToIncrement);
		/*
		 * Za pomoc� referencji na metod�, mo�emy powy�sze wyra�enie upro�ci�. 
		 * Nale�y zwr�ci� uwag�, i� pierwszym argumentem implementowanego interfejsu musi by� referencja na obiekt klasy 
		 * NonStaticValueIncrementor. Koniecznie musi by� podany odno�nik na obiekt w ramach instancji kt�rego zdefiniowana zostanie 
		 * implementowana metoda z racji swej niestatuczno�ci (musi istnie� obiekt na kt�rym b�dzie ona wywo�ana).
		 */
		valueIncrementorUsable = NonStaticValueIncrementor::incrementByOddValueNonStatic;
		
		/*
		 * Inne przyk�ady...
		 */
		Consumer<String> consum1 = x -> System.out.println(">>>" + x);
		Consumer<String> consum2 = System.out::println;
		
		
		
		/*
		 * Pos��my si� lokalnym interfejsem Agregator, stworzonym wewn�trz klasy MethodRefs, 
		 * kt�rego metoda robi co� z dwoma argumentami i zwraca zagregowany wynik. Niech b�dzie, �e 
		 * je sumuje i wylicza �redni�.
		 */
		
		/*Stw�rzmy pomocnicz� klas� lokaln�...*/
		class AgregatingClass {
			/*Czy pami�tamy dlaczego mo�na tutaj u�y� metody prywatnej i j� wywo�a� na klasie Summary?*/
			/*Lepiej jest si� pos�ugiwa� opakowanymii typami ni� prymitywami.*/
			private Double justDoSum(Integer a, Integer b) {
				return (a + b) / 2.0;
			}
		}
		
		/*Niech dany b�dzie istniej�cy interfejs funkcyjny. Np. BiFunction zosta� wr�cz stworzony do takich zada�...*/
		Agregator<AgregatingClass, Integer, Integer, Double> agregator;
		
		/*
		 * Zaimplementujmy go na pocz�tek u�ywaj�c klasy anonimowej.
		 */
		agregator = new Agregator<AgregatingClass, Integer, Integer, Double>() {

			@Override
			public Double agregate(AgregatingClass type, Integer firstArgument, Integer secondArgument) {
				return (firstArgument + secondArgument)/2.0;
			}
			
		};
		
		/*
		 * Teraz u�yjmy wyra�enia lambda.
		 */
		agregator = (agregatingClass, firstArgument, secondArgument) -> agregatingClass.justDoSum(firstArgument, secondArgument);
		
		
		/*
		 * Natomiast teraz upro��my jeszcze wyra�enie...
		 */
		agregator = AgregatingClass::justDoSum;
		
		/*
		 * Wa�na sprawa! 
		 * 1. Kontrolujmy typy pod wzgl�dem tego, czy si� zgadzaj�!
		 * 2. Kontrolujmy co wchodzi do metod i co wychodzi. Wywo�ywane metody niestatyczne MUSZ� pasowa� do interfejsu, 
		 * inaczej kompilator si� nie domy�li, co i jak chcemy wywo�a�. 
		 * 
		 */
			
		};
}
	

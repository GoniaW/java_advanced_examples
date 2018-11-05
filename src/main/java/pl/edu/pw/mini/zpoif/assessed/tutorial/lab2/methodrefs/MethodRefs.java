package pl.edu.pw.mini.zpoif.assessed.tutorial.lab2.methodrefs;

import java.util.Random;
import java.util.function.Consumer;

public class MethodRefs {
	/*
	 * Klasa ta bêdzie jednoczeœnie klas¹ startow¹ jak i demonstracyjn¹. 
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
		 * Za³ó¿my, ¿e mamy dwa interfejsy: OddIncrementable i EvenIncrementable, które zawieraj¹ nag³ówki metod
		 * dodawaj¹cych do danej liczby odpowiednio: losow¹ nieparzyst¹ oraz losow¹ patrzyst¹ liczbê. 
		 * Chcemy zaimplementowaæ je za pomoc¹ wyra¿eñ lambda. 
		 */
		
		StaticValueIncrementor valueIncrementor = new StaticValueIncrementor();
		System.out.println("Wartoœæ powiêkszona o losow¹ liczbê nieparzyst¹: " + StaticValueIncrementor.incrementByOddValue(10));
		System.out.println("Wartoœæ powiêkszona o losow¹ liczbê parzyst¹: " + valueIncrementor.incrementByEvenValue(10));
	
		OddIncrementable oddIncrementable = valueToIncrement -> {
			int result = 0;
			return (result = (new Random()).nextInt()) % 2 != 0 ? result : result + 1;
		};
		
		EvenIncrementable evenIncrementable = valueToIncrement -> {
			int result = 0;
			return (result = (new Random()).nextInt()) % 2 == 0 ? result : result + 1;
		};
	
		/*
		 * Uda³o siê. Za³ó¿my teraz, ¿e implementacja tych interfejsów ju¿ gdzieœ istnieje 
		 * Jest to klasa ValueIncrementor, któr¹ wczeœniej zdefiniowano. Zamiast kodowaæ na nowo implementacjê 
		 * interfejsów (tak jak to zrobiono powy¿ej) mo¿na poprostu odnieœæ siê do istniej¹cych metod. 
		 */
		
		
		/*
		 * 										Statyczne referencje. 
		 */
		/*
		 * Stworzyliœmy wiêc wyra¿anie lambda, które w swoim ciele wywo³uje istniej¹c¹ statyczn¹ metodê nale¿¹c¹ 
		 * do klasy ValueIncrementor. 
		 */
		oddIncrementable = valueToIncrement -> StaticValueIncrementor.incrementByOddValue(valueToIncrement);
		/*
		 * Czy mo¿na proœciej? Tak. Isnieje mo¿liwoœæ prostrzego zapisu. 
		 * Wskazujemy metodê statyczn¹, która pobiera takie same argumenty jak implementowana metoda z interfejsu. 
		 * Kompilator sam zorientuje siê, ¿e wartoœæ valueToIncrement mo¿e powinna byæ przekazana do metody incrementByOddValue.
		 */
		oddIncrementable = StaticValueIncrementor::incrementByOddValue;
		
		/*
		 * Dwa powy¿sze wyra¿enia s¹ sobie równowa¿ne. 
		 */
		
		evenIncrementable = valueToIncrement -> StaticValueIncrementor.incrementByEvenValue(valueToIncrement);
		evenIncrementable = StaticValueIncrementor::incrementByEvenValue;
		
		/*
		 * 										Niestatyczne referencje. 
		 */
		/*
		 * Za³ó¿my, ze nasze wyra¿enie lambda wywo³uje jak¹œ niestatyczn¹ metodê NA OBIEKCIE, 
		 * który przekazany zosta³ jako argument. 
		 * 
		 */
		ValueIncrementorUsable valueIncrementorUsable = (nonStaticValueIncrementor, valueToIncrement) -> nonStaticValueIncrementor.incrementByOddValueNonStatic(valueToIncrement);
		/*
		 * Za pomoc¹ referencji na metodê, mo¿emy powy¿sze wyra¿enie uproœciæ. 
		 * Nale¿y zwróciæ uwagê, i¿ pierwszym argumentem implementowanego interfejsu musi byæ referencja na obiekt klasy 
		 * NonStaticValueIncrementor. Koniecznie musi byæ podany odnoœnik na obiekt w ramach instancji którego zdefiniowana zostanie 
		 * implementowana metoda z racji swej niestatucznoœci (musi istnieæ obiekt na którym bêdzie ona wywo³ana).
		 */
		valueIncrementorUsable = NonStaticValueIncrementor::incrementByOddValueNonStatic;
		
		/*
		 * Inne przyk³ady...
		 */
		Consumer<String> consum1 = x -> System.out.println(">>>" + x);
		Consumer<String> consum2 = System.out::println;
		
		
		
		/*
		 * Pos³ó¿my siê lokalnym interfejsem Agregator, stworzonym wewn¹trz klasy MethodRefs, 
		 * którego metoda robi coœ z dwoma argumentami i zwraca zagregowany wynik. Niech bêdzie, ¿e 
		 * je sumuje i wylicza œredni¹.
		 */
		
		/*Stwórzmy pomocnicz¹ klasê lokaln¹...*/
		class AgregatingClass {
			/*Czy pamiêtamy dlaczego mo¿na tutaj u¿yæ metody prywatnej i j¹ wywo³aæ na klasie Summary?*/
			/*Lepiej jest siê pos³ugiwaæ opakowanymii typami ni¿ prymitywami.*/
			private Double justDoSum(Integer a, Integer b) {
				return (a + b) / 2.0;
			}
		}
		
		/*Niech dany bêdzie istniej¹cy interfejs funkcyjny. Np. BiFunction zosta³ wrêcz stworzony do takich zadañ...*/
		Agregator<AgregatingClass, Integer, Integer, Double> agregator;
		
		/*
		 * Zaimplementujmy go na pocz¹tek u¿ywaj¹c klasy anonimowej.
		 */
		agregator = new Agregator<AgregatingClass, Integer, Integer, Double>() {

			@Override
			public Double agregate(AgregatingClass type, Integer firstArgument, Integer secondArgument) {
				return (firstArgument + secondArgument)/2.0;
			}
			
		};
		
		/*
		 * Teraz u¿yjmy wyra¿enia lambda.
		 */
		agregator = (agregatingClass, firstArgument, secondArgument) -> agregatingClass.justDoSum(firstArgument, secondArgument);
		
		
		/*
		 * Natomiast teraz uproœæmy jeszcze wyra¿enie...
		 */
		agregator = AgregatingClass::justDoSum;
		
		/*
		 * Wa¿na sprawa! 
		 * 1. Kontrolujmy typy pod wzglêdem tego, czy siê zgadzaj¹!
		 * 2. Kontrolujmy co wchodzi do metod i co wychodzi. Wywo³ywane metody niestatyczne MUSZ¥ pasowaæ do interfejsu, 
		 * inaczej kompilator siê nie domyœli, co i jak chcemy wywo³aæ. 
		 * 
		 */
			
		};
}
	

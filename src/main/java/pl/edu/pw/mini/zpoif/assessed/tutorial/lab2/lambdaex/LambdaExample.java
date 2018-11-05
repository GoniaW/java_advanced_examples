package pl.edu.pw.mini.zpoif.assessed.tutorial.lab2.lambdaex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class LambdaExample {

	private int jedenBok = 5;
	
	/**
	 * Metoda zawiera przyk³ady u¿ycia wyra¿eñ lambda i nie tylko. 
	 */
	public void doExample() {
		
		/*
		 * Niech dany bêdzie interfejs SmallInterfaceToImplement
		 */
		SurfaceCalculator surfaceCalculator; 
		
		/*
		 * Za³ó¿my, ¿e mamy klasê JustCalculator, która posiada metodê pobieraj¹c¹ coœ, 
		 * co impementuje interfejs SurfaceCalculator (implementacjê kalkulatora licz¹c¹ pole powierzchni prostok¹ta)
		 * i uruchamia j¹ w ciele metody. Sama NIE CHCE zajmowaæ siê obliczeniami. Za³ó¿my, ¿e woli korzystaæ z implementacji, 
		 * które jej metoda przyjmuje jako parametr. 
		 */
		JustCalculator justCalculator = new JustCalculator();
		/*
		 * Instancja klasy JustCalculator zawiera tylko jedn¹ metodê (calculate), która przyjmuje obiekt implementuj¹cy 
		 * wspomniany powy¿ej interfejs i uruchamia obliczanie pola powierzchni.
		 * justCalculator.calculate(coœ, co policzy nam pole powierzchni).
		 */
		
		/*
		 * Mo¿na stworzyæ gdzieœ w pakietach klasê implementuj¹c¹ go (SurfaceCalculator) i jej u¿yæ.
		 * Czynnoœci zmierzaj¹ce do dostarczenia implementacji pod postaci¹ klasy:
		 * 	1. utworzyæ osobny plik
		 *  2. nadaæ nazwê
		 *  3. zdefiniowaæ cia³o
		 *  4. zdefiniowaæ nag³ówek implementowanej metody
		 *  5. zaimplementowaæ cia³o metody
		 *  
		 *  Je¿eli  
		 */
		surfaceCalculator = new SurfaceCalculatorImpl();
		System.out.println("Pole prostok¹ta wg. normalnej klasy implementuj¹cej: " + surfaceCalculator.calculateSurfaceAreaOfARectangle(10, 12));
		/*
		 * Jak widaæ implementacja interfejsu dzia³a, wiêc mo¿na j¹ przekazaæ do metodu klasy JustCalculator
		 */
		justCalculator.calculate(surfaceCalculator);
		
		/*
		 * Nic nie stoi na przeszkodzie, ¿eby klasê, która dostarcza implementacji stworzyæ na miejscu w tej metodzie.
		 * Czynnoœci zmierzaj¹ce do dostarczenia implementacji pod postaci¹ klasy:
		 *  1. nadaæ nazwê
		 *  2. zdefiniowaæ cia³o
		 *  3. zdefiniowaæ nag³ówek implementowanej metody
		 *  4. zaimplementowaæ cia³o metody
		 */
		class SurfaceCalculatorImplemented implements SurfaceCalculator{
			@Override
			public int calculateSurfaceAreaOfARectangle(int a, int b) {
				return 0;
			}
		}
		SurfaceCalculatorImplemented surfaceCalculatorImplemented = new SurfaceCalculatorImplemented(); 
		System.out.println("Pole prostok¹ta wg. lokalnej klasy implementuj¹cej: " + surfaceCalculatorImplemented.calculateSurfaceAreaOfARectangle(10, 12));
		/*
		 * Jak widaæ implementacja interfejsu dzia³a, wiêc mo¿na j¹ przekazaæ do metodu klasy JustCalculator
		 */
		justCalculator.calculate(surfaceCalculatorImplemented);
		
		/*
		 * Mo¿na te¿ u¿yæ klasy anonimowej, jeœli nie bêdziemy zbyt czêsto jej instancjonowaæ (bo wtedy za ka¿dym razem trzeba bêdzie kopiowaæ implementacjê.
		 * Czynnoœci zmierzaj¹ce do dostarczenia implementacji pod postaci¹ klasy:
		 *  1. zdefiniowaæ cia³o
		 *  2. zdefiniowaæ nag³ówek implementowanej metody
		 *  3. zaimplementowaæ cia³o metody
		 */
		surfaceCalculator = new SurfaceCalculator() {
			@Override
			public int calculateSurfaceAreaOfARectangle(int a, int b) {
				return a * b;
			}
		};
		System.out.println("Pole prostok¹ta wg. anonimowej klasy implementuj¹cej: " + surfaceCalculator.calculateSurfaceAreaOfARectangle(10, 12));
		/*
		 * Jak widaæ implementacja interfejsu dzia³a, wiêc mo¿na j¹ przekazaæ do metodu klasy JustCalculator
		 */
		justCalculator.calculate(surfaceCalculatorImplemented);

		/*
		 * Je¿eli sytuacja nie wymaga korzystania z wszelkich dobrodziejstw klas, a zale¿y nam tylko na przekazaniu gdzieœ (najczêœciej jednorazowo) pewnego prostego 
		 * kodu, to mo¿emy u¿yæ wyra¿eñ lambda. Jak widaæ po poni¿szej liœcie czynnoœci, mo¿na to zrobiæ ze znacz¹co mniejszym nak³adem pracy w stosunku do rozwi¹zañ 
		 * przedstawionych powy¿ej. 
		 * 
		 * Czynnoœci zmierzaj¹ce do dostarczenia implementacji pod postaci¹ klasy:
		 *  1. zaimplementowaæ cia³o metody
		 *  
		 *  Uda³o siê zmniejszyæ iloœæ koniecznych czynnoœci zwi¹zanych z zaimplementowaniem interfejsu SurfaceCalculator do jednej.
		 */
		surfaceCalculator = (a, b) -> a * b;
		System.out.println("Sposób obliczenia pola prostok¹ta zdefiniowany za pomoc¹ wyra¿enia lambda wynosi: " + surfaceCalculator.calculateSurfaceAreaOfARectangle(5, 2));
		justCalculator.calculate(surfaceCalculator);
		
		/*
		 * W powy¿szym przyk³adzie zdefiniowaliœmy co trzeba zrobiæ i uda³o siê tego dokonaæ w tylko jednej linijce kodu. 
		 * Ca³a reszta czynnoœci (koniecznych w poprzednich przyk³adach) potrzebna do powo³ania do ¿ycia obiektu implementuj¹cego ten interfejs zosta³a utworzona niejawnie. 
		 */
		System.out.println("Name: " + surfaceCalculator + " Czy stworzony obiekt implementuje interfejs? " + (surfaceCalculator instanceof SurfaceCalculator));
		
		/*
		 * To samo mo¿emy osi¹gn¹æ w taki sposób:
		 */
		justCalculator.calculate((a, b) -> a * b);
		
		/*
		 * Je¿eli metoda ma coœ zwracaæ, to w przypadku, gdy nasza implementacja ma d³ugoœæ tylko jednej linijki kodu, 
		 * to nie musimy dodawaæ s³owa kluczowego return, choæ mo¿emy (nikt nam tego nie zabroni... poza stra¿nikiem dobrych praktyk kodowania). 
		 * Zatem poprzedni¹ linikê mo¿na zaimplementowaæ równie¿ i tak:
		 */
		justCalculator.calculate((a, b) -> {return (a * b);});
		
		
		/*
		 * Wyra¿enie lambda mo¿e mieæ równie¿ wiêcej linijek, co widaæ poni¿ej. Wtedy jednak nie mo¿emy zapomnieæ o s³owie 
		 * return, je¿eli zaimplementowana metoda ma coœ zwracaæ. 
		 */
		surfaceCalculator = (a, b) -> {
			if(a > 100 && b > 100) {
				System.out.println("Uhuhuuuu du¿y prostok¹t..."); 
			} else if (a < 2 && b < 2){
				System.out.println("I to ma byæ prostok¹t?");
			}
			Date date = new Date();
			System.out.println("Uwaga, uwaga, za chwilê nast¹pi obliczenie pola powierzchni prostok¹ta. Ma to miejsce dnia: " + date);
			return a * b;
		};
		justCalculator.calculate(surfaceCalculator);
		
		/*
		 * Je¿eli implemetowana metoda ma jeden parametr, to mo¿emy pos³u¿yæ siê skrótem i odnieœæ siê do niego bez nawiasów.
		 * Niech dany bêdzie interfejs: 
		 */
		TextSurrounder textSurrounder = text -> System.out.println("[Lewa otoczka tekstu]" + text + " [prawa otoczka tekstu]");
		textSurrounder.surroundText("Tekst do otoczenia");
		
		/*
		 * Zasiêg wyra¿eñ lambda jest wzglêdny w stosunku do miejsca, w którym zosta³y one zaimplementowane.
		 * Poni¿ej wyra¿enie labmda nie korzysta z parametrów a i b, ¿eby zademonstrowaæ mo¿liwoœæ odwo³ania siê 
		 * do pola klasy LambdaExample i zmiennej lokalnej drugiBok.
		 */
		int drugiBok = 11;
		surfaceCalculator = (a, b) -> this.jedenBok * drugiBok;
		justCalculator.calculate(surfaceCalculator);
		
		/*
		 * Mo¿na te¿ tak, ¿eby by³o szybciej.
		 */
		justCalculator.calculate((a, b) -> this.jedenBok * drugiBok);
		
		
		/*
		 * Jak zaimplementowaæ w¹tek.
		 */
		Runnable run = () -> System.out.println("W¹tek napisany w lambdzie...");
		(new Thread(run)).start();
		
		/*
		 * Sortowanie po d³ugoœci Stringa.
		 */
		Comparator<String> comparatorFromLambda = (a, b) -> a.length() > b.length() ? 1 : -1;
		/*
		 * Sprawdzmy, czy dzia³a
		 */
		List<String> list = new ArrayList<String>(Arrays.asList("Krótki", "Taki œredni", "d³uuuuuuugi", "Baaaaaaardzi d³uuuuuuugi"));
		list.sort(comparatorFromLambda);
		System.out.println("Posortowana lista: " + list);
		
		
		/*
		 * Nale¿y pamiêtaæ, i¿ wyra¿eñ lambda u¿ywamy w przypadku, gdy problem da siê rozwi¹zaæ kodem wewn¹trz danej metody. 
		 * Je¿eli nie ma odnoszenia siê do dodatkowych metod i pól klasy implementuj¹cej dany interfejs. 
		 */
		
		/*
		 * Zadanie napisaæ implementacjê interfejsu java.util.Comparator<String>, która porównuje Stringi alfabetycznie oraz 
		 * za co trzecim porównaniem wypisuje na konsolê informacjê: "Co trzecie porównanie..."
		 */
		Comparator<String> comparator = new Comparator<String>() {
			private int count;
			public int compare(String string1, String string2) {
				if(++count % 3 == 0) {
					System.out.println("Co trzecie porównanie...");
				}
				return string1.compareTo(string2);
			}
		};
		/*
		 * Mo¿emy sobie trzymaæ licznik wewn¹trz klasy anonimowej, poniewa¿ jest on potrzebny tylko tej klasie (i ¿adnej innej).	
		 */
		/*
		 * Sprawdzmy, czy dzia³a...
		 */
		List<String> list2 = new ArrayList<String>(Arrays.asList("Raz", "Dwa", "Trzy", "Cztery", "A nawet piêæ"));
		list2.sort(comparator);
		System.out.println("Posortowana lista: " + list2);
		
		/*
		 * Wyra¿enie lambda jest kodem metody, wiêc nie ma tam miejsca na pamiêæ. Owszem, teoretycznie mo¿na t¹ informacjê trzymaæ gdzieœ 
		 * w jakimœ polu do którego cia³o wyrazenia lambda ma dostêp ale by³o by to karygodnym b³êdem koncepcyjnym (przechowywanie informacji 
		 * poza obszarem który jest ni¹ wy³¹cznie zainteresowany.
		 * 
		 */
		
		/*
		 * Wyra¿enia lambda nie mo¿na u¿yæ w stosunku do interfejsów nie funkcyjnych (tzn. takich, które maj¹ wiêcj ni¿ jedn¹ metodê).
		 */
		//BiggerInterfaceToImplement biggerInterfaceToImplement = (a, h) -> a * h;//<---To siê nie skompiluje:
		
	}

}

package pl.edu.pw.mini.zpoif.assessed.tutorial.lab2.lambdaex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class LambdaExample {

	private int jedenBok = 5;
	
	/**
	 * Metoda zawiera przyk�ady u�ycia wyra�e� lambda i nie tylko. 
	 */
	public void doExample() {
		
		/*
		 * Niech dany b�dzie interfejs SmallInterfaceToImplement
		 */
		SurfaceCalculator surfaceCalculator; 
		
		/*
		 * Za��my, �e mamy klas� JustCalculator, kt�ra posiada metod� pobieraj�c� co�, 
		 * co impementuje interfejs SurfaceCalculator (implementacj� kalkulatora licz�c� pole powierzchni prostok�ta)
		 * i uruchamia j� w ciele metody. Sama NIE CHCE zajmowa� si� obliczeniami. Za��my, �e woli korzysta� z implementacji, 
		 * kt�re jej metoda przyjmuje jako parametr. 
		 */
		JustCalculator justCalculator = new JustCalculator();
		/*
		 * Instancja klasy JustCalculator zawiera tylko jedn� metod� (calculate), kt�ra przyjmuje obiekt implementuj�cy 
		 * wspomniany powy�ej interfejs i uruchamia obliczanie pola powierzchni.
		 * justCalculator.calculate(co�, co policzy nam pole powierzchni).
		 */
		
		/*
		 * Mo�na stworzy� gdzie� w pakietach klas� implementuj�c� go (SurfaceCalculator) i jej u�y�.
		 * Czynno�ci zmierzaj�ce do dostarczenia implementacji pod postaci� klasy:
		 * 	1. utworzy� osobny plik
		 *  2. nada� nazw�
		 *  3. zdefiniowa� cia�o
		 *  4. zdefiniowa� nag��wek implementowanej metody
		 *  5. zaimplementowa� cia�o metody
		 *  
		 *  Je�eli  
		 */
		surfaceCalculator = new SurfaceCalculatorImpl();
		System.out.println("Pole prostok�ta wg. normalnej klasy implementuj�cej: " + surfaceCalculator.calculateSurfaceAreaOfARectangle(10, 12));
		/*
		 * Jak wida� implementacja interfejsu dzia�a, wi�c mo�na j� przekaza� do metodu klasy JustCalculator
		 */
		justCalculator.calculate(surfaceCalculator);
		
		/*
		 * Nic nie stoi na przeszkodzie, �eby klas�, kt�ra dostarcza implementacji stworzy� na miejscu w tej metodzie.
		 * Czynno�ci zmierzaj�ce do dostarczenia implementacji pod postaci� klasy:
		 *  1. nada� nazw�
		 *  2. zdefiniowa� cia�o
		 *  3. zdefiniowa� nag��wek implementowanej metody
		 *  4. zaimplementowa� cia�o metody
		 */
		class SurfaceCalculatorImplemented implements SurfaceCalculator{
			@Override
			public int calculateSurfaceAreaOfARectangle(int a, int b) {
				return 0;
			}
		}
		SurfaceCalculatorImplemented surfaceCalculatorImplemented = new SurfaceCalculatorImplemented(); 
		System.out.println("Pole prostok�ta wg. lokalnej klasy implementuj�cej: " + surfaceCalculatorImplemented.calculateSurfaceAreaOfARectangle(10, 12));
		/*
		 * Jak wida� implementacja interfejsu dzia�a, wi�c mo�na j� przekaza� do metodu klasy JustCalculator
		 */
		justCalculator.calculate(surfaceCalculatorImplemented);
		
		/*
		 * Mo�na te� u�y� klasy anonimowej, je�li nie b�dziemy zbyt cz�sto jej instancjonowa� (bo wtedy za ka�dym razem trzeba b�dzie kopiowa� implementacj�.
		 * Czynno�ci zmierzaj�ce do dostarczenia implementacji pod postaci� klasy:
		 *  1. zdefiniowa� cia�o
		 *  2. zdefiniowa� nag��wek implementowanej metody
		 *  3. zaimplementowa� cia�o metody
		 */
		surfaceCalculator = new SurfaceCalculator() {
			@Override
			public int calculateSurfaceAreaOfARectangle(int a, int b) {
				return a * b;
			}
		};
		System.out.println("Pole prostok�ta wg. anonimowej klasy implementuj�cej: " + surfaceCalculator.calculateSurfaceAreaOfARectangle(10, 12));
		/*
		 * Jak wida� implementacja interfejsu dzia�a, wi�c mo�na j� przekaza� do metodu klasy JustCalculator
		 */
		justCalculator.calculate(surfaceCalculatorImplemented);

		/*
		 * Je�eli sytuacja nie wymaga korzystania z wszelkich dobrodziejstw klas, a zale�y nam tylko na przekazaniu gdzie� (najcz�ciej jednorazowo) pewnego prostego 
		 * kodu, to mo�emy u�y� wyra�e� lambda. Jak wida� po poni�szej li�cie czynno�ci, mo�na to zrobi� ze znacz�co mniejszym nak�adem pracy w stosunku do rozwi�za� 
		 * przedstawionych powy�ej. 
		 * 
		 * Czynno�ci zmierzaj�ce do dostarczenia implementacji pod postaci� klasy:
		 *  1. zaimplementowa� cia�o metody
		 *  
		 *  Uda�o si� zmniejszy� ilo�� koniecznych czynno�ci zwi�zanych z zaimplementowaniem interfejsu SurfaceCalculator do jednej.
		 */
		surfaceCalculator = (a, b) -> a * b;
		System.out.println("Spos�b obliczenia pola prostok�ta zdefiniowany za pomoc� wyra�enia lambda wynosi: " + surfaceCalculator.calculateSurfaceAreaOfARectangle(5, 2));
		justCalculator.calculate(surfaceCalculator);
		
		/*
		 * W powy�szym przyk�adzie zdefiniowali�my co trzeba zrobi� i uda�o si� tego dokona� w tylko jednej linijce kodu. 
		 * Ca�a reszta czynno�ci (koniecznych w poprzednich przyk�adach) potrzebna do powo�ania do �ycia obiektu implementuj�cego ten interfejs zosta�a utworzona niejawnie. 
		 */
		System.out.println("Name: " + surfaceCalculator + " Czy stworzony obiekt implementuje interfejs? " + (surfaceCalculator instanceof SurfaceCalculator));
		
		/*
		 * To samo mo�emy osi�gn�� w taki spos�b:
		 */
		justCalculator.calculate((a, b) -> a * b);
		
		/*
		 * Je�eli metoda ma co� zwraca�, to w przypadku, gdy nasza implementacja ma d�ugo�� tylko jednej linijki kodu, 
		 * to nie musimy dodawa� s�owa kluczowego return, cho� mo�emy (nikt nam tego nie zabroni... poza stra�nikiem dobrych praktyk kodowania). 
		 * Zatem poprzedni� linik� mo�na zaimplementowa� r�wnie� i tak:
		 */
		justCalculator.calculate((a, b) -> {return (a * b);});
		
		
		/*
		 * Wyra�enie lambda mo�e mie� r�wnie� wi�cej linijek, co wida� poni�ej. Wtedy jednak nie mo�emy zapomnie� o s�owie 
		 * return, je�eli zaimplementowana metoda ma co� zwraca�. 
		 */
		surfaceCalculator = (a, b) -> {
			if(a > 100 && b > 100) {
				System.out.println("Uhuhuuuu du�y prostok�t..."); 
			} else if (a < 2 && b < 2){
				System.out.println("I to ma by� prostok�t?");
			}
			Date date = new Date();
			System.out.println("Uwaga, uwaga, za chwil� nast�pi obliczenie pola powierzchni prostok�ta. Ma to miejsce dnia: " + date);
			return a * b;
		};
		justCalculator.calculate(surfaceCalculator);
		
		/*
		 * Je�eli implemetowana metoda ma jeden parametr, to mo�emy pos�u�y� si� skr�tem i odnie�� si� do niego bez nawias�w.
		 * Niech dany b�dzie interfejs: 
		 */
		TextSurrounder textSurrounder = text -> System.out.println("[Lewa otoczka tekstu]" + text + " [prawa otoczka tekstu]");
		textSurrounder.surroundText("Tekst do otoczenia");
		
		/*
		 * Zasi�g wyra�e� lambda jest wzgl�dny w stosunku do miejsca, w kt�rym zosta�y one zaimplementowane.
		 * Poni�ej wyra�enie labmda nie korzysta z parametr�w a i b, �eby zademonstrowa� mo�liwo�� odwo�ania si� 
		 * do pola klasy LambdaExample i zmiennej lokalnej drugiBok.
		 */
		int drugiBok = 11;
		surfaceCalculator = (a, b) -> this.jedenBok * drugiBok;
		justCalculator.calculate(surfaceCalculator);
		
		/*
		 * Mo�na te� tak, �eby by�o szybciej.
		 */
		justCalculator.calculate((a, b) -> this.jedenBok * drugiBok);
		
		
		/*
		 * Jak zaimplementowa� w�tek.
		 */
		Runnable run = () -> System.out.println("W�tek napisany w lambdzie...");
		(new Thread(run)).start();
		
		/*
		 * Sortowanie po d�ugo�ci Stringa.
		 */
		Comparator<String> comparatorFromLambda = (a, b) -> a.length() > b.length() ? 1 : -1;
		/*
		 * Sprawdzmy, czy dzia�a
		 */
		List<String> list = new ArrayList<String>(Arrays.asList("Kr�tki", "Taki �redni", "d�uuuuuuugi", "Baaaaaaardzi d�uuuuuuugi"));
		list.sort(comparatorFromLambda);
		System.out.println("Posortowana lista: " + list);
		
		
		/*
		 * Nale�y pami�ta�, i� wyra�e� lambda u�ywamy w przypadku, gdy problem da si� rozwi�za� kodem wewn�trz danej metody. 
		 * Je�eli nie ma odnoszenia si� do dodatkowych metod i p�l klasy implementuj�cej dany interfejs. 
		 */
		
		/*
		 * Zadanie napisa� implementacj� interfejsu java.util.Comparator<String>, kt�ra por�wnuje Stringi alfabetycznie oraz 
		 * za co trzecim por�wnaniem wypisuje na konsol� informacj�: "Co trzecie por�wnanie..."
		 */
		Comparator<String> comparator = new Comparator<String>() {
			private int count;
			public int compare(String string1, String string2) {
				if(++count % 3 == 0) {
					System.out.println("Co trzecie por�wnanie...");
				}
				return string1.compareTo(string2);
			}
		};
		/*
		 * Mo�emy sobie trzyma� licznik wewn�trz klasy anonimowej, poniewa� jest on potrzebny tylko tej klasie (i �adnej innej).	
		 */
		/*
		 * Sprawdzmy, czy dzia�a...
		 */
		List<String> list2 = new ArrayList<String>(Arrays.asList("Raz", "Dwa", "Trzy", "Cztery", "A nawet pi��"));
		list2.sort(comparator);
		System.out.println("Posortowana lista: " + list2);
		
		/*
		 * Wyra�enie lambda jest kodem metody, wi�c nie ma tam miejsca na pami��. Owszem, teoretycznie mo�na t� informacj� trzyma� gdzie� 
		 * w jakim� polu do kt�rego cia�o wyrazenia lambda ma dost�p ale by�o by to karygodnym b��dem koncepcyjnym (przechowywanie informacji 
		 * poza obszarem kt�ry jest ni� wy��cznie zainteresowany.
		 * 
		 */
		
		/*
		 * Wyra�enia lambda nie mo�na u�y� w stosunku do interfejs�w nie funkcyjnych (tzn. takich, kt�re maj� wi�cj ni� jedn� metod�).
		 */
		//BiggerInterfaceToImplement biggerInterfaceToImplement = (a, h) -> a * h;//<---To si� nie skompiluje:
		
	}

}

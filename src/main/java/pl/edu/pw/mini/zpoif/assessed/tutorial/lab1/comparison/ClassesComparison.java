package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.comparison;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * Klasa zwiera porównanie klas wewnêtrznych statycznych, niestatycznych lokalnych i anonimowych.
 * Jest ona nienaturalnie d³uga, co normalnie nie powinno mieæ miejsca. 
 * Jednak intencj¹ jest zaprezentowanie u¿ycia ró¿nych typów klas w jednym miejscu, 
 * co powinno wzmocniæ obserwacje porównawcze. Z racji tego, i¿ bêdzie ich sporo nawigacjê proponuje siê realizowaæ 
 * za pomoc¹ ctrl + wskazanie wskaznikiem myszy + lewy przycisk. Bêdzie ³atwiej.  
 * Nie ma jednej sekcji pól, metod i klas wewnêtrznych. Pola s¹ deklarowane tam, gdzie s¹ potrzebne (aby byæ w jednym miejscu).
 * 
 * Niniejszy kod powsta³ w celu zaprezentowania mo¿liwoœci jêzyka Java w kwestii klas wewnêtrznych. 
 * Ma pokazaæ co siê da zrobiæ, a nie jakie s¹ dobre praktyki. Proszê pamiêtaæ, i¿ nadu¿ywanie tego typu rozwi¹zañ nie 
 * nale¿y do dobrych pomys³ów. 
 * 
 * Klasa zajmuje siê sortowaniem rekordów reprezentuj¹cych zwierzêta oraz formatowanie ich daty urodzenia za pomoc¹ wymienionych powy¿ej klas.
 * (wypisywanie wed³ug ustalonego wzorca).  
 * 
 */
public class ClassesComparison {
	
	/*
	 * Je¿eli chcemy zdefiniowaæ klasê do u¿ytku "wewnêtrznego", to mo¿emy to zrobiæ w ciele klasy opakowuj¹cej. 
	 * Klasy te nie musz¹ mieæ dostêpu do instancji klasy opakowuj¹cej (MainClassesComparison), nie korzystaj¹ z pól ani z metod, 
	 * wiêc mog¹ byæ statyczne. Po klasie Pet dziedzicz¹ klasy Dog i Cat.
	 */
	private static abstract class Pet {
		private String name;
		private String ownerName;
		private Date togetherSince;
		
		public Pet(String name, String ownerName, String togetherSince) {
			super();
			this.name = name;
			this.ownerName = ownerName;
			try {
				this.togetherSince = (new SimpleDateFormat("yyyy-MM-dd")).parse(togetherSince);
			} catch (ParseException e) {
				this.togetherSince = new Date();
			}
		}
		public String getName() {
			return name;
		}
		public String getOwnerName() {
			return ownerName;
		}
		public Date getTogetherSince() {
			return togetherSince;
		}
	}
	/*
	 * Klasa MainClassesComparison bêdzie mia³a metodê umo¿liwiaj¹c¹ dodanie psa do wewnêtrznej kolekcji. 
	 * Musi byæ zatem widoczna na zewn¹trz. 
	 */
	public static class Dog extends Pet {
		private Integer age;
		
		public Dog(String name, String ownerName, String togetherSince, Integer age) {
			super(name, ownerName, togetherSince);
			this.age = age;
		}

		public Integer getAge() {
			return age;
		}
	}
	/*
	 * Nie jest przewidziana mo¿liwoœæ dodawania kotów przez na zewn¹trz klasy MainClassesComparison, 
	 * wiêc nie ma przes³anek do tego aby Cat by³ publiczny. 
	 */
	private static class Cat extends Pet {
		public Cat(String name, String ownerName, String togetherSince) {
			super(name, ownerName, togetherSince);
			// TODO Auto-generated constructor stub
		}
		//Koty maj¹ siedem ¿yæ, wiêc umieszczanie wieku w klasie jest lekk¹ przesad¹. 
	}
	
	private List<Pet> pets;
	
	/*
	 * Zbudujmy listê ze zwierzakami, na której bêd¹ pracowaæ metody naszej klasy.
	 */
	public ClassesComparison() {
		pets = new ArrayList<>();
		pets.add(new Dog("Fafik", "Roman", "2018-12-31", 4));
		pets.add(new Dog("Reksio", "Maciek", "2009-12-31", 12));
		pets.add(new Cat("Piga", "Maciek", "2010-12-31"));
		pets.add(new Cat("Muniek", "Wac³awa", "2005-12-31"));
		pets.add(new Dog("Traper", "Stanis³aw", "2016-12-31", 16));
		pets.add(new Cat("Michelle", "Janina", "2017-12-31"));
		pets.add(new Dog("Kruczek", "Stanis³aw", "2005-12-31", 20));
		
	}
	
	/*
	 * Mo¿emy dodaæ Pas z zewn¹trz za pomoc¹ metody klasy MainClassesComparison. 
	 * Zrobimy to za pomoc¹ poni¿szej metody. 
	 */
	public void addDog(Dog dog) {
		pets.add(dog);
	}
	
	//Za³ó¿my, ¿e jest wspólny dla wszystkich format daty, u¿ywane przez wiêcej ni¿ jeden byt. 
	private String globalDateFormat = "dd-MM-YYYY";
	
	/*
	 * Stwórzmy klasê, która formatuje datê, korzystaj¹c z formatu zapisanego jako pole w klasie ClassesComparison.
	 * Ponadto bêdzie ona zapamiêtywa³a liczbê formatowañ i umieszcza³a stosown¹ informacjê. 
	 */
	/*
	 * Je¿eli chcemy stworzyæ klasê formatuj¹c¹ datê, i bêdzie ona wykorzystywana przez wiêcej ni¿ jeden blok kodu, 
	 * to powinna byæ to klasa wewnêtrzna. Jeœli bêdzie korzystaæ z pól klasy opakowuj¹cej to musimy j¹ zadeklarowaæ
	 *  jako nie statyczn¹ (poniewa¿ musi ona mieæ dostêp do pól instancji klasy ClassesComparison). 
	 *  U¿ywamy minimum tego, co jest potrzebne, zatem jeœli klasa nie musi byæ widoczna na zewn¹trz, to czynimy j¹ prywatn¹. 
	 */
	private class CommonDateFormatPrinter{
		private int formatCounter;
		public String getFormattedDate(Date date) {
			//Tutaj nastêpuje odwo³anie siê do pola klasy ClassesComparison (globalDateFormat)
			SimpleDateFormat format = new SimpleDateFormat(globalDateFormat);
			return format.format(date) + "(" + "formatowanie nr: " + formatCounter++ + ")";
		}
	}
	
	/*
	 * Powy¿sz¹ klase wykorzystuj¹ dwie ponisze metody:
	 */
	public void printFirstPet() {
		Iterator<Pet> petIterator = pets.iterator();
		if(petIterator.hasNext()) {
			Pet pet = petIterator.next();
			//Wykorzystujemy tutaj nasz¹ klasê wewnêtrzn¹.
			CommonDateFormatPrinter commonDateFormatPrinter = new CommonDateFormatPrinter();
			System.out.println("Pet: " + pet.getName() + ", " + pet.ownerName + ", "
										+ commonDateFormatPrinter.getFormattedDate(pet.getTogetherSince()));
		}
	}
	
	public void printAllPets() {
		for(Pet pet : pets) {
			//Wykorzystujemy tutaj nasz¹ klasê wewnêtrzn¹.
			CommonDateFormatPrinter commonDateFormatPrinter = new CommonDateFormatPrinter();
			System.out.println("Pet: " + pet.getName() + ", " + pet.ownerName + ", "
										+ commonDateFormatPrinter.getFormattedDate(pet.getTogetherSince()));
		}
	}
	
	//KLASY ANONIMOWE
	/*
	 * W przypadku, gdy mamy prosty interfejs do zaimplementowania lub klasê do rozszerzenia 
	 * i bêdziemy instancjonowaæ taki byt tylko raz, to jest do dobry pretekst do u¿ycia klasy anonimowej. 
	 */
	private void printSortedPets(Comparator<Pet> comparator) {
		List<Pet> sortedPets = new ArrayList<>(pets);
		sortedPets.sort(comparator);
		System.out.println("Sorted pets: ");
		for(Pet pet : sortedPets) {
			
			/*
			 * Pole name klasy Pet jest prywatne, a mimo to jest widoczne z punktu widzenia klasy ClassesComparison.
			 * Dzieje siê tak, poniewa¿ klasa ClassesComparison ma dostêp do pól prywatnych swoich klas wewnêtrznych.
			 */
			System.out.println(pet.name + " " + pet.getOwnerName());
		}
	}
	
	public void printSortedPetsByName() {
		//W tym przypadku instancjonujemy klasê tylko raz, wiêc mo¿emy u¿yæ klasy anonimowej. 
		printSortedPets(new Comparator<ClassesComparison.Pet>() {
			@Override
			public int compare(Pet pet1, Pet pet2) {
				return pet1.getName().compareTo(pet2.getName());
			}
		});
	}
	
	/*
	 * W przypadku, gdy chcemy stworzyæ naprêdce jakieœ zwierzê (coœ, co dziedziczy po klasie Pet), 
	 * to mo¿emy u¿yæ klasy anonimowej w celu jednorazowego wyprodukowania nowego zwierzaka. 
	 */
	public Pet getMouse() {
		return new Pet("Mickey", "Donald", "2015-12-31") {
			
		};
	}
	
	//KLASY LOKALNE
	
	/*
	 * Kiedy w takim razie u¿ywaæ klas lokalnych, skoro wszystko da siê osi¹gn¹æ za pomoc¹ klas anonimowych? 
	 * Je¿eli np. bêdziemy musieli stworzyæ instancjê takiej klasy wiêcej ni¿ raz. W takim wypadku 
	 * (gdy np. chcielibyœmy stworzyæ 3 instancje klasy o tym samym kodzie, musielibyœmy stworzyæ 3 instancje klas anonimowych 
	 * i 3 razy przepisaæ t¹ implementacjê. Z³y pomys³. 
	 */
	/*
	 * Zrealizujmy ten z³y pomys³. Tworzymy trzy w¹tki, z których ka¿dy robi to samo: wypisuje coœ na konsoli.
	 * Przy ka¿dym instancjonowaniu powtarzamy definicjê tego samego kodu.
	 */
	public void runThreads() {

		System.out.println("Run threads.");
		
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				//Doing something
				System.out.println("I am doing the same what other threads.");
				//Doing something else
			}
		};
		
		//Jeszcze raz powielamy ten sam kod.
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				//Doing something
				System.out.println("I am doing the same what other threads.");
				//Doing something else
			}
		};
		
		//Po raz kolejny powielamy ten sam kod.
		Thread thread3 = new Thread() {
			@Override
			public void run() {
				//Doing something
				System.out.println("I am doing the same what other threads.");
				//Doing something else
			}
		};
		
		thread1.start();
		thread2.start();
		thread3.start();
		
	}
	
	/*
	 * W poprzedniej metodzie u¿ycie klas anonimowych niezbyt siê op³aca³o. 
	 * Zróbmy to lepiej. Oczywiœcie zak³adamy, ¿e ka¿dy w¹tek ma pracowaæ na obszarze odseparowanym od innych w¹tków.
	 */
	public void runThreadsInABetterWay() {
		System.out.println("Run threads in a better way.");
		class MyThread extends Thread {
			@Override
			public void run() {
				//Doing something
				System.out.println("I am doing the same what other threads.");
				//Doing something else
			}
		};
		(new MyThread()).start();
		(new MyThread()).start();
		(new MyThread()).start();
	}
	
}

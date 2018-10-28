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
 * Klasa zwiera por�wnanie klas wewn�trznych statycznych, niestatycznych lokalnych i anonimowych.
 * Jest ona nienaturalnie d�uga, co normalnie nie powinno mie� miejsca. 
 * Jednak intencj� jest zaprezentowanie u�ycia r�nych typ�w klas w jednym miejscu, 
 * co powinno wzmocni� obserwacje por�wnawcze. Z racji tego, i� b�dzie ich sporo nawigacj� proponuje si� realizowa� 
 * za pomoc� ctrl + wskazanie wskaznikiem myszy + lewy przycisk. B�dzie �atwiej.  
 * Nie ma jednej sekcji p�l, metod i klas wewn�trznych. Pola s� deklarowane tam, gdzie s� potrzebne (aby by� w jednym miejscu).
 * 
 * Niniejszy kod powsta� w celu zaprezentowania mo�liwo�ci j�zyka Java w kwestii klas wewn�trznych. 
 * Ma pokaza� co si� da zrobi�, a nie jakie s� dobre praktyki. Prosz� pami�ta�, i� nadu�ywanie tego typu rozwi�za� nie 
 * nale�y do dobrych pomys��w. 
 * 
 * Klasa zajmuje si� sortowaniem rekord�w reprezentuj�cych zwierz�ta oraz formatowanie ich daty urodzenia za pomoc� wymienionych powy�ej klas.
 * (wypisywanie wed�ug ustalonego wzorca).  
 * 
 */
public class ClassesComparison {
	
	/*
	 * Je�eli chcemy zdefiniowa� klas� do u�ytku "wewn�trznego", to mo�emy to zrobi� w ciele klasy opakowuj�cej. 
	 * Klasy te nie musz� mie� dost�pu do instancji klasy opakowuj�cej (MainClassesComparison), nie korzystaj� z p�l ani z metod, 
	 * wi�c mog� by� statyczne. Po klasie Pet dziedzicz� klasy Dog i Cat.
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
	 * Klasa MainClassesComparison b�dzie mia�a metod� umo�liwiaj�c� dodanie psa do wewn�trznej kolekcji. 
	 * Musi by� zatem widoczna na zewn�trz. 
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
	 * Nie jest przewidziana mo�liwo�� dodawania kot�w przez na zewn�trz klasy MainClassesComparison, 
	 * wi�c nie ma przes�anek do tego aby Cat by� publiczny. 
	 */
	private static class Cat extends Pet {
		public Cat(String name, String ownerName, String togetherSince) {
			super(name, ownerName, togetherSince);
			// TODO Auto-generated constructor stub
		}
		//Koty maj� siedem �y�, wi�c umieszczanie wieku w klasie jest lekk� przesad�. 
	}
	
	private List<Pet> pets;
	
	/*
	 * Zbudujmy list� ze zwierzakami, na kt�rej b�d� pracowa� metody naszej klasy.
	 */
	public ClassesComparison() {
		pets = new ArrayList<>();
		pets.add(new Dog("Fafik", "Roman", "2018-12-31", 4));
		pets.add(new Dog("Reksio", "Maciek", "2009-12-31", 12));
		pets.add(new Cat("Piga", "Maciek", "2010-12-31"));
		pets.add(new Cat("Muniek", "Wac�awa", "2005-12-31"));
		pets.add(new Dog("Traper", "Stanis�aw", "2016-12-31", 16));
		pets.add(new Cat("Michelle", "Janina", "2017-12-31"));
		pets.add(new Dog("Kruczek", "Stanis�aw", "2005-12-31", 20));
		
	}
	
	/*
	 * Mo�emy doda� Pas z zewn�trz za pomoc� metody klasy MainClassesComparison. 
	 * Zrobimy to za pomoc� poni�szej metody. 
	 */
	public void addDog(Dog dog) {
		pets.add(dog);
	}
	
	//Za��my, �e jest wsp�lny dla wszystkich format daty, u�ywane przez wi�cej ni� jeden byt. 
	private String globalDateFormat = "dd-MM-YYYY";
	
	/*
	 * Stw�rzmy klas�, kt�ra formatuje dat�, korzystaj�c z formatu zapisanego jako pole w klasie ClassesComparison.
	 * Ponadto b�dzie ona zapami�tywa�a liczb� formatowa� i umieszcza�a stosown� informacj�. 
	 */
	/*
	 * Je�eli chcemy stworzy� klas� formatuj�c� dat�, i b�dzie ona wykorzystywana przez wi�cej ni� jeden blok kodu, 
	 * to powinna by� to klasa wewn�trzna. Je�li b�dzie korzysta� z p�l klasy opakowuj�cej to musimy j� zadeklarowa�
	 *  jako nie statyczn� (poniewa� musi ona mie� dost�p do p�l instancji klasy ClassesComparison). 
	 *  U�ywamy minimum tego, co jest potrzebne, zatem je�li klasa nie musi by� widoczna na zewn�trz, to czynimy j� prywatn�. 
	 */
	private class CommonDateFormatPrinter{
		private int formatCounter;
		public String getFormattedDate(Date date) {
			//Tutaj nast�puje odwo�anie si� do pola klasy ClassesComparison (globalDateFormat)
			SimpleDateFormat format = new SimpleDateFormat(globalDateFormat);
			return format.format(date) + "(" + "formatowanie nr: " + formatCounter++ + ")";
		}
	}
	
	/*
	 * Powy�sz� klase wykorzystuj� dwie ponisze metody:
	 */
	public void printFirstPet() {
		Iterator<Pet> petIterator = pets.iterator();
		if(petIterator.hasNext()) {
			Pet pet = petIterator.next();
			//Wykorzystujemy tutaj nasz� klas� wewn�trzn�.
			CommonDateFormatPrinter commonDateFormatPrinter = new CommonDateFormatPrinter();
			System.out.println("Pet: " + pet.getName() + ", " + pet.ownerName + ", "
										+ commonDateFormatPrinter.getFormattedDate(pet.getTogetherSince()));
		}
	}
	
	public void printAllPets() {
		for(Pet pet : pets) {
			//Wykorzystujemy tutaj nasz� klas� wewn�trzn�.
			CommonDateFormatPrinter commonDateFormatPrinter = new CommonDateFormatPrinter();
			System.out.println("Pet: " + pet.getName() + ", " + pet.ownerName + ", "
										+ commonDateFormatPrinter.getFormattedDate(pet.getTogetherSince()));
		}
	}
	
	//KLASY ANONIMOWE
	/*
	 * W przypadku, gdy mamy prosty interfejs do zaimplementowania lub klas� do rozszerzenia 
	 * i b�dziemy instancjonowa� taki byt tylko raz, to jest do dobry pretekst do u�ycia klasy anonimowej. 
	 */
	private void printSortedPets(Comparator<Pet> comparator) {
		List<Pet> sortedPets = new ArrayList<>(pets);
		sortedPets.sort(comparator);
		System.out.println("Sorted pets: ");
		for(Pet pet : sortedPets) {
			
			/*
			 * Pole name klasy Pet jest prywatne, a mimo to jest widoczne z punktu widzenia klasy ClassesComparison.
			 * Dzieje si� tak, poniewa� klasa ClassesComparison ma dost�p do p�l prywatnych swoich klas wewn�trznych.
			 */
			System.out.println(pet.name + " " + pet.getOwnerName());
		}
	}
	
	public void printSortedPetsByName() {
		//W tym przypadku instancjonujemy klas� tylko raz, wi�c mo�emy u�y� klasy anonimowej. 
		printSortedPets(new Comparator<ClassesComparison.Pet>() {
			@Override
			public int compare(Pet pet1, Pet pet2) {
				return pet1.getName().compareTo(pet2.getName());
			}
		});
	}
	
	/*
	 * W przypadku, gdy chcemy stworzy� napr�dce jakie� zwierz� (co�, co dziedziczy po klasie Pet), 
	 * to mo�emy u�y� klasy anonimowej w celu jednorazowego wyprodukowania nowego zwierzaka. 
	 */
	public Pet getMouse() {
		return new Pet("Mickey", "Donald", "2015-12-31") {
			
		};
	}
	
	//KLASY LOKALNE
	
	/*
	 * Kiedy w takim razie u�ywa� klas lokalnych, skoro wszystko da si� osi�gn�� za pomoc� klas anonimowych? 
	 * Je�eli np. b�dziemy musieli stworzy� instancj� takiej klasy wi�cej ni� raz. W takim wypadku 
	 * (gdy np. chcieliby�my stworzy� 3 instancje klasy o tym samym kodzie, musieliby�my stworzy� 3 instancje klas anonimowych 
	 * i 3 razy przepisa� t� implementacj�. Z�y pomys�. 
	 */
	/*
	 * Zrealizujmy ten z�y pomys�. Tworzymy trzy w�tki, z kt�rych ka�dy robi to samo: wypisuje co� na konsoli.
	 * Przy ka�dym instancjonowaniu powtarzamy definicj� tego samego kodu.
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
	 * W poprzedniej metodzie u�ycie klas anonimowych niezbyt si� op�aca�o. 
	 * Zr�bmy to lepiej. Oczywi�cie zak�adamy, �e ka�dy w�tek ma pracowa� na obszarze odseparowanym od innych w�tk�w.
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

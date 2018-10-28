package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.localclasses;
/**
 * Klasa zawiera kilka przyk³adów u¿ycia klas lokalnych.
 * @author mabd
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class LocalClassOwner {
	
	private List<Person> persons;
	
	public LocalClassOwner() {
		persons = new ArrayList<>();
		persons.add(new Person(1, "John", "Bravo", 55, true));
		persons.add(new Person(2, "Amelia", "Smith", 23, false));
		persons.add(new Person(3, "Todd", "Rodd", 23, true));
		persons.add(new Person(4, "Kill", "Bill", 41, true));
		persons.add(new Person(5, "Alicia", "Cooper", 1, false));
		persons.add(new Person(6, "Monika", "Jankowska", 31, false));
		persons.add(new Person(7, "Kamil", "Milowicz", 31, true));
	}
	
	
	
	/*
	 * Za³ó¿my, ¿e chcemy u¿yæ pewnej klasy tylko na terenie danej metody. 
	 * Poza ni¹ nie bêdzie ona nigdy wykorzystywana. Mo¿na w takim razie pokusiæ siê o zdefiniowanie 
	 * klasy lokalnej, u¿ywanej tylko na potrzeby danego bloku kodu i nigdzie indziej. 
	 * 
	 */
	/*
	 * Niech poni¿sza metoda zwraca jedno imiê ¿eñskie, losowo wybrane z tych, które maj¹ wiêcej ni¿ 20 lat, 
	 * wraz z momentem w którym dana osoba by³a iterowana. Zak³adamy, i¿ listê mo¿na przejœæ tylko raz 
	 * (ktoœ tak powiedzia³ i tak ma byæ - musimy siê do tego dostosowaæ). 
	 */
	public String getFemaleName() {
		
		class FemaleInfo {
			
			private Person person;
			private long time;
			
			public FemaleInfo(Person person) {
				super();
				this.person = person;
				this.time = System.currentTimeMillis();
			}

			@Override
			public String toString() {
				return person.getName() + " " + time;
			}
			
		}
		List<FemaleInfo> females = new ArrayList<>();
		
		for(Person person : persons) {
			if(!person.isMale() && person.getAge() > 20) {
				System.out.println("Added: " + person.getAge());
				females.add(new FemaleInfo(person));
			}
		}
		
		return females.get(((new Random()).nextInt(females.size()))).toString();
		
	}
	
	
	/*
	 * Mo¿na równie¿ u¿ywaæ klas lokalnych dziedzicz¹cych po innych klasach, 
	 * lub implementuj¹cych interfejsy. 
	 */
	public void printSorted() {
		
		class LocalComparator implements Comparator<Person> {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getAge().compareTo(o2.getAge());
			}
			
		}
		LocalComparator localComparator = new LocalComparator();
		List<Person> personsToSort = new ArrayList<>(persons);
		
		Collections.sort(personsToSort, localComparator);
		
		for(Person person : personsToSort) {
			System.out.println(person.toString());
		}
		
	}
	
}

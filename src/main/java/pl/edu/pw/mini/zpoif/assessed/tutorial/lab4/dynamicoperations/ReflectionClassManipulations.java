package pl.edu.pw.mini.zpoif.assessed.tutorial.lab4.dynamicoperations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pl.edu.pw.mini.zpoif.assessed.tutorial.lab4.reflections.common.KlasaJakKlasa;

/**
 * Niniejszy zestaw przyk³adów pokazuje w jaki sposób (u¿ywaj¹c metod refleksyjnych)
 * mo¿na instancjonowaæ obiekty, wywo³ywaæ metody, otrzymywaæ wartoœci pól i zmieniaæ ich modyfikatory.
 *
 */
public class ReflectionClassManipulations {
	
	public static void main(String[] args) {
		
		instancjonowanie();
		dostepDoPol();
		wywolywanieMetod();
		refleksjeNadRefleksjami();
		
	}
	
	/**
	 * Instancjonowanie.
	 */
	public static void instancjonowanie() {
		
		/*
		 * Za³ó¿my, ¿e w naszym kodzie dysponujemy pe³n¹ nazw¹ klasy, któr¹ chcielibyœmy utworzyæ. 
		 * Niech bêdzie to klasa: KlasaJakKlasa
		 */
		 String pelnaNazwaKlasy = "pl.edu.pw.mini.zpoif.assessed.tutorial.lab4.reflections.common.KlasaJakKlasa";
		 /*
		  * Mo¿emy posiadaj¹c t¹ informacjê utworzyæ obiekt (instancjê tej klasy), tak jak robiliœmy to za pomoc¹ 
		  * operatora "new"
		  */
		 try {
			Class<?> odnosnikDoObiektuOpisujacegoKlase = Class.forName(pelnaNazwaKlasy);
			/*
			 * O ile podana nazwa klasy znajduje siê w zasiêgu Class Loadera, otrzymujemy obiekt typu Class<?>,
             * który reprezentuje klasê: KlasaJakKlasa
			 */
			
			try {
				Object object = odnosnikDoObiektuOpisujacegoKlase.newInstance();
				/*
				 * Otrzymany obiekt jest obiektem klasy: KlasaJakKlasa. Mo¿na to sprawdziæ. 
				 */
				System.out.println(" Czy obiekt jest instancj¹ klasy KlasaJakKlasa? " + (object instanceof KlasaJakKlasa));
				
				/*
				 * Mo¿na poddaæ go castingowi:
				 */
				KlasaJakKlasa utworzonyPrzezRefleksjê = (KlasaJakKlasa)object;
				utworzonyPrzezRefleksjê.metodaPubliczna();
				
				
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
			/*
			 * W powy¿szym przypadku instancjonowanie siê powiod³o, poniewa¿ klasa mia³a konstruktor domyœlny (bezparametrowy).
			 * Nietrudno siê domyœliæ, co siê stanie, gdy w ten sam sposób bêdziemy próbowaæ instancjonowaæ klasê, która takowego 
			 * konstruktora nie posiada. Stwórzmy sobie tymczasow¹ klasê, doceniaj¹c zarazem mo¿liwoœæ tworzenia klas lokalnych.
			 */
			class KlasaBezKonstruktoraBezparametrowego{
				
				private int a;
				private String b;
				
				KlasaBezKonstruktoraBezparametrowego(int a){}

				public KlasaBezKonstruktoraBezparametrowego(int a, String b) {
					super();
					this.a = a;
					this.b = b;
				}

			}
			
			/*
			 * Spróbujmy zainstancjonowaæ sobie klasê w ramach refleksji. 
			 * Przy okazji zauwa¿my jak wygl¹da pe³na nazwa klasy lokalnej.
			 */
			Class<?> referencjaNaKlase = Class.forName("pl.edu.pw.mini.zpoif.assessed.tutorial.lab4.dynamicoperations.ReflectionClassManipulations$1KlasaBezKonstruktoraBezparametrowego");
			
			try {
				Object object = referencjaNaKlase.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				/*
				 * W przypadku uruchomienia powy¿szego bloku wyrzucony zostanie wyj¹tek. 
				 * Klasa 
				 */
				System.out.println("Wyj¹tek!!! - klasa lokalna nie posiada konstruktora bezparametrowego!");
			}
			
			/*
			 * Utwórzmy zatem obiekt na podstawie konstruktora dwuparametrowego klasy lokalnej. 
			 */
			try {
				Constructor constructor = referencjaNaKlase.getConstructor(int.class, String.class);
				/*
				 * Teraz instancjonujemy nasz obiekt wywo³uj¹c konstruktor dwuparametrowy.
				 */
				try {
					Object object = constructor.newInstance(new Object[] {10, "Hello!"});
					System.out.println("Obiekt zosta³ stworzony za pomoc¹ konstruktora dwuparametrowego: " + object);
					System.out.println("Czy jest to obiekt klasy KlasaBezKonstruktoraBezparametrowego? " + (object instanceof KlasaBezKonstruktoraBezparametrowego));
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			
			/*
			 * Mo¿emy sobie wylistowaæ konstruktory.
			 */
			Constructor constructors[] = referencjaNaKlase.getConstructors();
			for(Constructor constructor : constructors) {
				System.out.println("Konstruktor: " + constructor.getName() + " Liczba parametrów: " + constructor.getParameterCount());
				/*
				 * Pamiêtamy constructor. i ctrl + space. Bêdzie tam (podobnie jak w przypadku metod) 
				 */
				constructor.getParameterTypes();//itd.
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 
	}
	
	/**
	 * Dostêp do pól istniej¹cych obiektów (instancji klas)
	 */
	public static void dostepDoPol() {
		String pelnaNazwaKlasy = "pl.edu.pw.mini.zpoif.assessed.tutorial.lab4.reflections.common.KlasaJakKlasa";
		/*
		 * Instancjonujemy. 
		 * P.S. Nazwa class jest zastrze¿ona, wiêc czasmi u¿ywa siê nazwy clazz. Jest to modne. 
		 * Jeœli ktoœ chce byæ na czasie, to mo¿e u¿ywaæ takiego nazewnictwa. 
		 */
		try {
			Class<?> clazz = Class.forName(pelnaNazwaKlasy);
			try {
				Object object = clazz.newInstance();
				/*
				 * Klasa KlasaJakKlasa ma pole jakiesInnePole. Uzyskajmy jego wartoœæ.
				 */
				try {
					Field field = clazz.getField("jakiesInnePole");
					/*
					 * W jaki sposób mo¿na uzyskaæ wartoœæ tego pola w konkretnym obiekcie?
					 * Wywo³ujemy metodê "get" na obiekcie opisuj¹cym pole, podaj¹c jako argument 
					 * konkretny obiekt 
					 */
					Object fieldValue = field.get(object);
					System.out.println("Wartoœæ pola: " + fieldValue);
					
					/*
					 * Je¿eli znamy klasê wartoœci danego pola mo¿emy pokusiæ siê o jego casting.
					 * W tym przeypadku wiemy, ¿e jest too String. 
					 */
					String fieldValueAsString = (String)field.get(object);
					System.out.println("Wartoœæ pola jako String: " + fieldValueAsString);
					
				} catch (NoSuchFieldException | SecurityException e) {
					e.printStackTrace();
				}
				
				/*
				 * Co siê stanie, je¿eli bêdziemy chcieli uzyskaæ dostêp do pola niedostêpnego (np. prywatnego)
				 */
				try {
					Field intField = clazz.getDeclaredField("poleInt");
					try {
						intField.get(object);
					} catch(IllegalAccessException illegalAccessException) {
						/*
						 * Ten wyj¹tek bêdzie wyrzucony.
						 */
						System.out.println("Wyj¹tek! Nie mo¿na uzyskaæ dostêpu do pola prywatnego!");
					}
				} catch (NoSuchFieldException | SecurityException e) {
					e.printStackTrace();
				}
				
				/*
				 * 
				 * Co robiæ? Zmieniæ modyfikator. 
				 * W Javie przewidziano tak¹ ewentualnoœæ i wprowadzono mo¿liwoœæ zmiany dostêpnoœci pola lub metody.
				 * Aby uzyskaæ dostêp do tego pola mo¿na na chwilê (lub do koñca dzia³ania programu) uczyniæ go dostêpnym. 
				 * 
				 */
				try {
					/*
					 * Czemu getDeclaredField, a nie po prostu getField? Bo getField odnosi siê tylko do metod publicznych.
					 */
					try {
						Field intField = clazz.getDeclaredField("poleInt");
						intField.setAccessible(true);
						Object gotObject = intField.get(object);
						/*
						 * Tutaj wyj¹tek nie bêdzie rzucony, bo dziêki metodzie setAccessible pole sta³o siê dostêpne. 
						 * To samo mo¿na zrobiæ i z metodami.
						 */
						System.out.println("Wartoœæ pola prywatnego uzyskanego za pomoc¹ modyfikaji dostêpu do niego: " + gotObject);
					
						/*
						 * Je¿eli pole zawiera typ liczbowy, to mo¿emy pos³u¿yæ siê metodami klasy Field.
						 */
						int value = intField.getInt(object);
						System.out.println("Wartoœæ pola uzyskana za pomoc¹ getInt: " + value);
					} catch(IllegalAccessException illegalAccessException) {
						illegalAccessException.printStackTrace();
					}
					
				} catch (NoSuchFieldException | SecurityException e) {
					System.out.println("AWyj¹tek! Nie mo¿na uzyskaæ dostêpu do pola prywatnego!");
				}
				
				Field fields[] = clazz.getDeclaredFields();
				for(Field field : fields) {
					//Mo¿na coœ zrobiæ z metodami udostêpnianymi przez klasê Field
					field.setAccessible(true);
					System.out.println("Pole: " + field.get(object));
				}
				
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Wywo³ywanie metod obiektów (instancji klas)
	 */
	public static void wywolywanieMetod() {
		String pelnaNazwaKlasy = "pl.edu.pw.mini.zpoif.assessed.tutorial.lab4.reflections.common.KlasaJakKlasa";
		/*
		 * Instancjonujemy. 
		 * P.S. Nazwa class jest zastrze¿ona, wiêc czasmi u¿ywa siê nazwy clazz. Jest to modne. 
		 * Jeœli ktoœ chce byæ na czasie, to mo¿e u¿ywaæ takiego nazewnictwa. 
		 */

		Class<?> clazz;
		try {
			clazz = Class.forName(pelnaNazwaKlasy);
			try {
				Object object = clazz.newInstance();
				/*
				 * Za³ó¿my, ¿e chcemy wywo³aæ metodê (z jakimiœ parametrami) znaj¹c jej nazwê i typy 
				 * przyjmowanych argumentów. Niech bêdzie to "normalnaMetoda" przyjmuj¹ca dwa argumenty (int, int i String)
				 */
				try {
					Method normalnaMetodaMethod = clazz.getMethod("normalnaMetoda", int.class, int.class, String.class);
					/*
					 * Wywo³ujemy metodê na konkretnej instancji obiektu (object), podaj¹c jednoczeœnie listê argumentów.
					 */
					try {
						normalnaMetodaMethod.invoke(object, new Integer(1), new Integer(2), "Hej!");
						Object o[] = {new Integer(1), new Integer(2), "Hej!"};
						/*
						 * Mo¿na te¿ i tak...
						 */
						normalnaMetodaMethod.invoke(object, o);
						
					} catch (IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
				
				/*
				 * Za³ó¿my, ze chcemy wywo³aæ metodê, która jest prywatna. 
				 */
				
				try {
					Method normalnaMetodaMethod = clazz.getDeclaredMethod("prywatnaMetodaKtoraCosZwraca", Integer.class);
					
					try {
						normalnaMetodaMethod.invoke(object, 133);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						System.out.println("Wyj¹tek! Nast¹pi³a próba dostêpu do metody prywatnej!");
					}
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
				
				/*
				 * Je¿eli bêdziemy chcieli wywo³aæ metodê, która nie jest dostêpna (np. prywatna),
				 * to podobnie jak w przypadku pól mo¿emy zmieniæ dostepnoœæ danej metody.
				 */
				try {
					Method normalnaMetodaMethod = clazz.getDeclaredMethod("prywatnaMetodaKtoraCosZwraca", Integer.class);
					try {
						normalnaMetodaMethod.setAccessible(true);
						normalnaMetodaMethod.invoke(object, 133);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						System.out.println("Wyj¹tek! Nast¹pi³a próba dostêpu do metody prywatnej!");
					}
				} catch (NoSuchMethodException | SecurityException e) {
					System.out.println("Wyj¹tek! Nast¹pi³a próba dostêpu do metody prywatnej!");
				}
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Podsumowanie
	 */
	private static void refleksjeNadRefleksjami(){
		/*
		 Na myœl przychodzi pytanie. Po co to wszystko??? 
		 Po co tak utrudniaæ sobie ¿ycie, jak mo¿na u¿yæ operatora "new", a na istniej¹cym obiekcie poprostu wywo³aæ metodê 
		 tak jak to robiliœmy do tej pory. 
		 */
		KlasaJakKlasa klasaJakKlasa = new KlasaJakKlasa();
		String wartoscPola = klasaJakKlasa.jakiesInnePole;
		klasaJakKlasa.metodaPubliczna();
		
		/*
		 * Wyobrazmy sobie sytuacjê, ¿e mamy dostarczyæ algorytm, który wywo³uje metody z biblioteki, 
		 * która jeszcze nie zosta³a stworzona. Kod wywo³uj¹cy metodê nie bêdzie pózniej rozwijany, 
		 * a jedyn¹ informacj¹ do tego bêdzie nazwa tej metody.
		 */
		
		/*
		 * Za³ó¿my, ¿e chcemy dostarczyæ oprogramowanie testuj¹ce jakiœ zestaw klas. Ktoœ na drugim koñcu œwiata 
		 * napisa³ program w Javie i my jako osoby neutralne chcemy sprawdziæ, czy to dzia³a. 
		 * Klasy testowane maj¹ sporo niedostêpnych pól (np. prywatnych), 
		 * od których zale¿y ich dzia³anie. Wyobrazmy sobie, i¿ chcemy sprawdziæ co siê stanie jeœli te pola 
		 * bêd¹ mia³y takie a takie wartoœci. Dodatkowo NIE MO¯EMY zmieniaæ kodu klas testowanych nawet na chwilê. 
		 * Bez refleksji tego nie zrobimy.
		 */
		
		/*
		 * Wyobrazmy sobie sytuacjê, w której nasz kod bêdzie chcia³ utworzyæ instancjê obiektu, który 
		 * bêdzie stworzony za rok. Jak mamy stworzyæ instancjê obiektu dziœ za pomoc¹ operatora "new", 
		 * skoro on jeszcze nie istnieje. 
		 */
		
		/*
		 * I tak dalej i tak dalej...
		 */
		
		/*
		 * Pamiêtaj, mechanizmu refleksji u¿ywamy wtedy, gdy danego problemu nie mo¿na rozwi¹zaæ w inny sposób. 
		 * Nie nadu¿ywamy tego mechanizmu, choæby dlatego, ¿e w niektórych miejscach omija on naturaln¹ ochronê, 
		 * któr¹ zapewnie kompilator. Niektóre problemy mog¹ nie byæ wykryte automatycznie w czasie fazy kompilacji, 
		 * tylko ujrz¹ œwiat³o dzienne dopiero w fazie uruchomienia (a tego zawsze chcielibyœmy unikn¹æ). 
		 * Np. 
		 */
		
		KlasaJakKlasa naszaKlasa = new pl.edu.pw.mini.zpoif.assessed.tutorial.lab4.reflections.common.KlasaJakKlasa();
		/*
		 * Podaliœmy tutaj pe³n¹ nazwê klasy razem z pakietem (mo¿na tak zrobiæ). Je¿eli takie klasy nie bêdzie w zasiêgu
		 * kompilatora, to jeszcze przed uruchomieniem wska¿e on nam b³¹d. Proszê odkomentowaæ i zobaczyæ.
		 */
		
		//naszaKlasa = new pl.edu.pw.NIE.MA.TAKIEJ.KLASY.I.NIE.BEDZIE.common.KlasaJakKlasa();
		
		/*
		 * Je¿eli odkomentujemy powy¿sz¹ linijkê, to program nawet nie zostanie zbudowany, a co dopiero mówiæ o jego uruchomieniu.
		 */
		
		/*
		 * Natomiast w poni¿szym przypadku, gdy klasê naszaKlasa KlasaJakKlasa instancjonujemy poprzez "tekstowe" podanie jej nazwy
		 * to w przypadku, gdy nazwa bêdzie b³êdna (taka klasa nie bêdzie istnia³a), to problem wyjdzie dopiero po uruchomieniu. 
		 */
		
		try {
			Class<?> clazz = Class.forName("pl.edu.pw.NIE.MA.TAKIEJ.KLASY.I.NIE.BEDZIE.common.KlasaJakKlasa");
			
		} catch (ClassNotFoundException e) {
			/*
			 * Jak widaæ, wyj¹tek siê pojawi, wiêc b³¹d ujrzy œwiat³o dzienne mimo ¿e kompilator nie znalaz³ b³êdu i dopuœci³ 
			 * kod do budowy i uruchomienia.
			 */
			System.out.println("Wyjatek!!! Nie ma takiej klasy!");
		}
		
		
	}
	
}

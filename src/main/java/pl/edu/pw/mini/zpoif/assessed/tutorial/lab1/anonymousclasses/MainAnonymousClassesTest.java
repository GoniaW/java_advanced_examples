package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.anonymousclasses;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainAnonymousClassesTest {
	
	public static void main(String[] args) {
		
		AnonymousClassOwner anonymousClassOwner = new AnonymousClassOwner();
		
		String localDateFormat = "dd-MM-YYYY";
		/*
		 * Je¿eli jednorazowo musimy wyprodukowaæ obiekt, który bêdzie u¿ywany tylko w konkretym miejscu, 
		 * to mo¿emy pójœc na skróty i zaimplementowaæ go bez potrzeby tworzenia klasy implementuj¹cej ten interfejs. 
		 */
		anonymousClassOwner.processTask(new TaskRunnable() {
			
			private long taskRunTime;
			
			@Override
			public String getFormattedDateOfLastTaskRun() {
				
				/*
				 * Jak widaæ istnieje mo¿liwoœæ odwo³ania siê do zmiennej lokalnej, 
				 * jednak trzeba mieæ na uwadze fakt i¿ musi byæ ona finalna lub niezmienna 
				 * (w kodzie nie mo¿e byæ linijek zmieniaj¹cych jej wartoœæ).
				 */
				SimpleDateFormat format = new SimpleDateFormat(localDateFormat);
				return format.format(new Date(taskRunTime));
				
			}
			
			@Override
			public void doWork() {
				taskRunTime = System.currentTimeMillis();
				System.out.println("Hello hello, I am doing a hard work!");
			}
			
		});
		
		/*
		 * Gdyby odkomentowano poni¿sz¹ linijkê kodu, kompilator wskaza³by b³¹d - zmienna lokalna u¿ywana 
		 * w klasach anonimowych nie mo¿e podlegaæ zmianom. 
		 */
		//localDateFormat = "yyyy-MM-dd HH:mm:ss";
		
		/*
		 * Mo¿emy równie¿ wyprodukowaæ anonimowe rozszerzenie klasy abstrakcyjnej.
		 */
		anonymousClassOwner.processAnotherTask(new TaskRunner() {
			
			@Override
			String getFormattedDateOfLastTaskRun() {
				
				SimpleDateFormat format = new SimpleDateFormat(localDateFormat);
				return format.format(new Date());
				
			}
			
		});
		
	}

}

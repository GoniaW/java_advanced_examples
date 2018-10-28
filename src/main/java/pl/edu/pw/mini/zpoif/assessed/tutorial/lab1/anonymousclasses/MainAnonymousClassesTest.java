package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.anonymousclasses;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainAnonymousClassesTest {
	
	public static void main(String[] args) {
		
		AnonymousClassOwner anonymousClassOwner = new AnonymousClassOwner();
		
		String localDateFormat = "dd-MM-YYYY";
		/*
		 * Je�eli jednorazowo musimy wyprodukowa� obiekt, kt�ry b�dzie u�ywany tylko w konkretym miejscu, 
		 * to mo�emy p�j�c na skr�ty i zaimplementowa� go bez potrzeby tworzenia klasy implementuj�cej ten interfejs. 
		 */
		anonymousClassOwner.processTask(new TaskRunnable() {
			
			private long taskRunTime;
			
			@Override
			public String getFormattedDateOfLastTaskRun() {
				
				/*
				 * Jak wida� istnieje mo�liwo�� odwo�ania si� do zmiennej lokalnej, 
				 * jednak trzeba mie� na uwadze fakt i� musi by� ona finalna lub niezmienna 
				 * (w kodzie nie mo�e by� linijek zmieniaj�cych jej warto��).
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
		 * Gdyby odkomentowano poni�sz� linijk� kodu, kompilator wskaza�by b��d - zmienna lokalna u�ywana 
		 * w klasach anonimowych nie mo�e podlega� zmianom. 
		 */
		//localDateFormat = "yyyy-MM-dd HH:mm:ss";
		
		/*
		 * Mo�emy r�wnie� wyprodukowa� anonimowe rozszerzenie klasy abstrakcyjnej.
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

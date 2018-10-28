package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.anonymousclasses;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AnonymousClassOwner {
	
	private String globalDateFormat = "HH:mm:ss.SSS";
	
	public void processTask(TaskRunnable taskRunnable) {
		taskRunnable.doWork();
	}
	
	public void processAnotherTask(TaskRunner taskRunner) {
		//do something
		taskRunner.doWork();
		//do something else
	}
	
	/*
	 * Mo�emy wyprodukowa� implementacj� interfejsu za pomoc� klasy anonimowej.
	 */
	public TaskRunnable createTask() {
		
		return new TaskRunnable() {
			
			/*
			 * Jak wida� istnieje mo�liwo�� odwo�ania si� do pola klasy na kt�rej terenie zosta�a zdefiniowana klasa anonimowa, 
			 * jednak trzeba mie� na uwadze fakt i� w odr�nieniu od odniesienia do zmiennaj lokalnej (tak jak to mia�o miejsce 
			 * w klasie MainAnonymousClassesTest mo�na zmienia� warto�� pola.
			 */
			@Override
			public String getFormattedDateOfLastTaskRun() {
				SimpleDateFormat format = new SimpleDateFormat(globalDateFormat);
				globalDateFormat = "";
				return format.format(new Date());
			}
			
			@Override
			public void doWork() {
				// TODO Auto-generated method stub
				System.out.println("Working...");
			}
		};
		
		
		
	}
	/*
	 * Klas� anonimow� mo�na zdefiniowa� r�wnie� jako rozszerzeniej innej klasy. 
	 */
	public TaskRunner createTaskRunner() {
		return new TaskRunner() {
			
			@Override
			String getFormattedDateOfLastTaskRun() {
				SimpleDateFormat format = new SimpleDateFormat(globalDateFormat);
				return format.format(new Date());
			}
		};
	}
	
}

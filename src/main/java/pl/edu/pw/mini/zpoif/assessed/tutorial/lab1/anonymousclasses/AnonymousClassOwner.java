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
	 * Mo¿emy wyprodukowaæ implementacjê interfejsu za pomoc¹ klasy anonimowej.
	 */
	public TaskRunnable createTask() {
		
		return new TaskRunnable() {
			
			/*
			 * Jak widaæ istnieje mo¿liwoœæ odwo³ania siê do pola klasy na której terenie zosta³a zdefiniowana klasa anonimowa, 
			 * jednak trzeba mieæ na uwadze fakt i¿ w odró¿nieniu od odniesienia do zmiennaj lokalnej (tak jak to mia³o miejsce 
			 * w klasie MainAnonymousClassesTest mo¿na zmieniaæ wartoœæ pola.
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
	 * Klasê anonimow¹ mo¿na zdefiniowaæ równie¿ jako rozszerzeniej innej klasy. 
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

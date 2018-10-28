package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.anonymousclasses;

public abstract class TaskRunner {
	private long lastCall;
	abstract String getFormattedDateOfLastTaskRun();
	
	void doWork() {
		lastCall = System.currentTimeMillis();
		System.out.println("Hello, hello I am doing hard work...");
	}
}

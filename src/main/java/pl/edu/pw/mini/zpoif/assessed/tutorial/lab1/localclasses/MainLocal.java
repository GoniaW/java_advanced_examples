package pl.edu.pw.mini.zpoif.assessed.tutorial.lab1.localclasses;

public class MainLocal {

	public static void main(String[] args) {
		LocalClassOwner localClassOwner = new LocalClassOwner();
		System.out.println(localClassOwner.getFemaleName()); 
		localClassOwner.printSorted();
	}

}

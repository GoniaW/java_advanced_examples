package pl.edu.pw.mini.zpoif.assessed.tutorial.lab2.lambdaex;

public class SurfaceCalculatorImpl implements SurfaceCalculator{

	@Override
	public int calculateSurfaceAreaOfARectangle(int a, int b) {
		return a * b;
	}
	
	public void doSomethingElse() {
		System.out.println("I am doing something else...");
	}
	
}

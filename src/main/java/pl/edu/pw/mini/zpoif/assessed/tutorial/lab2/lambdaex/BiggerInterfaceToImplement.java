package pl.edu.pw.mini.zpoif.assessed.tutorial.lab2.lambdaex;
/**
 * Interfejs zawiera wiêcej ni¿ jedn¹ metodê do zaimplementowania. 
 * Nie mo¿e byæ on zaimplementowany "na skróty" za pomoc¹ wyra¿enia lambda, 
 * poniewa¿ zawiera wiêcej ni¿ jedn¹ metodê. 
 */
public interface BiggerInterfaceToImplement {
	
	double calculateSurfaceAreaOfATriangle(int a, int h);
	double calculateSurfaceAreaOfSquare(int a);
	
}

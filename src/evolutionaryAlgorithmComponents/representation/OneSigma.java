package evolutionaryAlgorithmComponents.representation;		

import java.util.Random;

public class OneSigma extends RealValueRepresentation {
	
	private final static String title = " with a single σ for the whole population";
	
	public OneSigma(double low, double high, double sigma, int dim){
		super(title, low, high, sigma, dim);
	}

}

package evolutionaryAlgorithmComponents.representation;		

public class OneSigma extends RealValueRepresentation {
	
	public final static String title = "(x1, ., xn)";
	
	public OneSigma(double low, double high, double sigma, int dim){
		super(low, high, sigma, dim);
	}

}

package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import evolutionaryAlgorithmComponents.Individual;

public class UniformCrossover extends AbstractDiscreteRecombination {

	private static String title = "Uniform Crossover";
	private double alpha = 0.5;
	
	public UniformCrossover(double alpha) {
		super(title);
		this.alpha = alpha;
	}
	
	public UniformCrossover() {
		super(title);
	}

	@Override
	public Individual[] perform(Individual mom, Individual dad) {

		Individual[] children = super.initializeChildrenForRecombination(mom.getRepresentation());

		for (int i=0; i<mom.getRepresentation().getDimensions(); i++){
			children[0].getChromosome()[i] = dad.getChromosome()[i];
			children[1].getChromosome()[i] = mom.getChromosome()[i];
			if (random.nextDouble() < alpha){
				children[0].getChromosome()[i] = mom.getChromosome()[i];
				children[1].getChromosome()[i] = dad.getChromosome()[i];
			}
		}
		return children;
	}
	
	public double getAlpha(){
		return alpha;
	}

}

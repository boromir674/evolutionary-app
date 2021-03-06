package evolutionaryAlgorithmComponents.variationOperators.recombination.realValue;

import evolutionaryAlgorithmComponents.Individual;

public class WholeArithmeticRecombination extends AbstractRealValueRecombination {
	
	private final static String title = "Whole Arithmetic";
	
	public WholeArithmeticRecombination() {
		super(title);
	}

	@Override
	public Individual[] perform(Individual mom, Individual dad) {
		
		Individual[] children = super.initializeTwoChildrenForRecombination(mom.getRepresentation());
		for (int i=0; i<mom.getRepresentation().getDimensions(); i++){
			children[0].getChromosome()[i] = a * (double) mom.getChromosome()[i] + (1-a) * (double) dad.getChromosome()[i];
			children[1].getChromosome()[i] = a * (double) dad.getChromosome()[i] + (1-a) * (double) mom.getChromosome()[i];
		}
		for (int i=mom.getRepresentation().getDimensions(); i<mom.getChromosome().length; i++){
			children[0].getChromosome()[i] = mom.getChromosome()[i];
			children[1].getChromosome()[i] = dad.getChromosome()[i];
		}
		return children;
	}

}

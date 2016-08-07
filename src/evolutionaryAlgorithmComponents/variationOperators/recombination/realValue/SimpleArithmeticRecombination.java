package evolutionaryAlgorithmComponents.variationOperators.recombination.realValue;

import evolutionaryAlgorithmComponents.Individual;

public class SimpleArithmeticRecombination extends AbstractRealValueRecombination {
	
	private static final String title = "Simple Arithmetic Recombination";
	
	public SimpleArithmeticRecombination() {
		super(title);
	}

	@Override
	public Individual[] perform(Individual mom, Individual dad) {
		
		Individual[] children = super.initializeChildrenForRecombination(mom.getRepresentation());
		int length = mom.getChromosome().length;
		int point = random.nextInt(mom.getRepresentation().getDimensions());
		
		for (int i=0; i<point; i++){
			children[0].getChromosome()[i] = mom.getChromosome()[i];
			children[1].getChromosome()[i] = dad.getChromosome()[i];
		}
		for(int i=point; i<mom.getRepresentation().getDimensions(); i++){
			children[0].getChromosome()[i] = (1-a)*(double)mom.getChromosome()[i] + a*(double)dad.getChromosome()[i]; 
			children[1].getChromosome()[i] = (1-a)*(double)dad.getChromosome()[i] + a*(double)mom.getChromosome()[i];
		}
		for (int i=point+1; i<length; i++){
			children[0].getChromosome()[i] = mom.getChromosome()[i];
			children[1].getChromosome()[i] = dad.getChromosome()[i];
		}
		return children;
	}

}

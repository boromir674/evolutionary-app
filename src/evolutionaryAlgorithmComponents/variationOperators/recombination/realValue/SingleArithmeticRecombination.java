package evolutionaryAlgorithmComponents.variationOperators.recombination.realValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.AbstractRepresentation;
import evolutionaryAlgorithmComponents.Individual;

public class SingleArithmeticRecombination extends AbstractRealValueRecombination {
	
	private static final String title = "Single Arithmetic Recombination";
	private Random aRandom;
	
	public SingleArithmeticRecombination(Random aRandom) {
		super(title);
		this.aRandom = aRandom;
	}

	@Override
	public Individual[] perform(Individual mom, Individual dad) {
		
		Individual[] children = super.initializeChildrenForRecombination(mom.getRepresentation());
		int length = mom.getChromosome().length;
		int point = aRandom.nextInt(mom.getRepresentation().getDimensions());
		
		for (int i=0; i<length; i++){
			children[0].getChromosome()[i] = mom.getChromosome()[i];
			children[1].getChromosome()[i] = dad.getChromosome()[i];
		}
		children[0].getChromosome()[point] = (1-a)*(double)mom.getChromosome()[point] + a*(double)dad.getChromosome()[point]; 
		children[1].getChromosome()[point] = (1-a)*(double)dad.getChromosome()[point] + a*(double)mom.getChromosome()[point];
		return children;
	}

}

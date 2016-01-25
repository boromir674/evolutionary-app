package evolutionaryAlgorithmComponents.variationOperators.recombination.realValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.AbstractRepresentation;

public class SimpleArithmetic extends AbstractRealValueRecombination {
	
	private Random rand;
	private static final String title = "Simple Arithmetic Recombination";
	
	public SimpleArithmetic(Random aRandom) {
		super(title);
		this.rand = aRandom;
	}

	@Override
	public Individual[] perform(Individual mom, Individual dad) {
		
		Individual[] children = super.initializeChildrenForRecombination(mom.getRepresentation());
		int length = mom.getChromosome().length;
		int point = rand.nextInt(mom.getRepresentation().getDimensions());
		
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

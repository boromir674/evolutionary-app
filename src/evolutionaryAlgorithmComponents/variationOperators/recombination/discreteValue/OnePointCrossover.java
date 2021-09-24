package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import interfaces.Representation;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;

public class OnePointCrossover extends AbstractDiscreteRecombination {
	
	private static String title = "One-Point Crossover";
	private Random random;
	
	public OnePointCrossover(Random aRandom) {
		super(title);
		this.random = aRandom;
	}

	@Override
	public Individual[] perform(Individual mom, Individual dad) throws Exception {
		
		Individual[] children = super.initializeChildrenForRecombination(mom.getRepresentation());
		int point = random.nextInt(mom.getRepresentation().getDimensions()-1);
	
		for (int i=0; i<point+1; i++){
			children[0].getChromosome()[i] = mom.getChromosome()[i];
			children[1].getChromosome()[i] = dad.getChromosome()[i];
		}		
		for (int i=point+1; i<mom.getRepresentation().getDimensions(); i++){
			children[0].getChromosome()[i] = dad.getChromosome()[i];
			children[1].getChromosome()[i] = mom.getChromosome()[i];
		}
		if (!((PermutationRepresentation)mom.getRepresentation()).chromosomeOK(children[0].getChromosome()))
			throw new Exception("offspring 1 contains dublicates!");
		if (!((PermutationRepresentation)mom.getRepresentation()).chromosomeOK(children[1].getChromosome()))
			throw new Exception("offspring 2 contains dublicates!");
		return children;
	}

}

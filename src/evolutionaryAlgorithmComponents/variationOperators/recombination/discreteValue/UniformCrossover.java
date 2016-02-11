package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import interfaces.Representation;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;

public class UniformCrossover extends AbstractDiscreteRecombination {

	private static String title = "Uniform Crossover";
	private double alpha = 0.5;
	private Random random;
	
	public UniformCrossover(Random aRandom, double alpha) {
		super(title);
		this.random = aRandom;
		this.alpha = alpha;
	}
	
	public UniformCrossover(Random aRandom) {
		super(title);
		this.random = aRandom;
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

	@Override
	public boolean applicableToPermutation() {
		return false;
	}
}

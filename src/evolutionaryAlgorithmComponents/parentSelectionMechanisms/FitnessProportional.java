package evolutionaryAlgorithmComponents.parentSelectionMechanisms;

import interfaces.FitnessCalculator;

import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.AbstractParentSelection;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

public class FitnessProportional extends AbstractParentSelection {

	private final static String title = "Fitness Proportional";
	private FitnessCalculator fitnessCalculator;

	public FitnessProportional(){
		super(title);
		this.fitnessCalculator = new FitnessCalculator(){
			@Override
			public double computeFitness(Individual anIndividual) {
				return anIndividual.getFitness();
			}
		};
	}
	public FitnessProportional(FitnessCalculator aFitnessCalculator){
		super(title);
		this.fitnessCalculator = aFitnessCalculator;
	}
	
	@Override
	public int[] select(Population pop, Random rand) throws Exception {
		double[] cumulativeProbabilities = Util.getCumulativeDistribution(pop.getPool(), 0, pop.getMu(), fitnessCalculator);
		int[] parentPointers = Util.stochasticUniversalSampling(cumulativeProbabilities, pop.getLambda(), rand);
		Util.shuffleArray(parentPointers, rand);
		return parentPointers;
	}

}

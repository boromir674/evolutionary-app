package evolutionaryAlgorithmComponents.parentSelectionMechanisms;

import interfaces.FitnessCalculator;

import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.AbstractParentSelection;
import evolutionaryAlgorithmComponents.AbstractRepresentation;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;
import evolutionaryAlgorithmComponents.fitnessCalculators.RawFitnessReporter;

public class FitnessProportional extends AbstractParentSelection {

	private final static String title = "Fitness Proportional";
	private FitnessCalculator fitnessCalculator;

	public FitnessProportional(){
		super(title);
		this.fitnessCalculator = new RawFitnessReporter();
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

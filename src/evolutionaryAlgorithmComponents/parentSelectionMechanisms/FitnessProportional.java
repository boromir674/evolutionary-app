package evolutionaryAlgorithmComponents.parentSelectionMechanisms;

import interfaces.FitnessSharingScheme;

import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.AbstractParentSelection;
import evolutionaryAlgorithmComponents.AbstractRepresentation;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

public class FitnessProportional extends AbstractParentSelection {

	private final static String title = "Fitness Proportional";

	public FitnessProportional(){
		super(title);
	}

	@Override
	public int[] select(Population pop, Random rand) throws Exception {
		double[] cumulativeProbabilities = Util.getCumulativeDistribution(pop.getPool(), 0, pop.getMu());
		int[] parentPointers = Util.stochasticUniversalSampling(cumulativeProbabilities, pop.getLambda(), rand);
		return parentPointers;
	}

}

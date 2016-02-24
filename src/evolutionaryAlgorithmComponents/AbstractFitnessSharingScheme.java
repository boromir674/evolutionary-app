package evolutionaryAlgorithmComponents;

import java.util.Random;

import util.Util;
import interfaces.FitnessSharingScheme;

public abstract class AbstractFitnessSharingScheme implements FitnessSharingScheme {
	
	private String title;
	
	public AbstractFitnessSharingScheme(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public int[] select(Population pop, Random rand) {
		double[] cumulProbs1 = Util.getCumulativeDistribution(pop.getPool(), 0, pop.getMu(), this);
		int[] parentPointers = Util.stochasticUniversalSampling(cumulProbs1, pop.getLambda(), rand);
		return parentPointers;
	}

}

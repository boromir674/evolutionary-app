package evolutionaryAlgorithmComponents.parentSelectionMechanisms;

import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.AbstractParentSelection;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

public class RankingSelection extends AbstractParentSelection {

	private final static String title = "Ranking";
	// "Ranking Selection"
	public RankingSelection(){
		super(title);
	}

	@Override
	public int[] select(Population pop, Random rand) throws Exception {
		// stochastically selects lambda parents, sampling according to probabilities based
		// on the linear rank of each member of the population
		double[] fitArray = Util.getFitnessArray(pop.getPool(), pop.getMu());
		double[] rankProbabilities = Util.findRankingProbs(fitArray); // probabilities based on ranking
		double[] cumulProbs = Util.getCumulativeDistribution(rankProbabilities);
		// array with indices to the pool ArrayList
		int[] parentPointers = Util.stochasticUniversalSampling(cumulProbs, pop.getLambda(), rand);
		return parentPointers;
	}	

}

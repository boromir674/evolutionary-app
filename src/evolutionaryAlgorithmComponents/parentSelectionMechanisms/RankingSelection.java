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
	public Individual[] select(Population aPopulation, Random aRandom) throws Exception {
		// stochastically selects lambda parents, sampling according to probabilities based
		// on the linear rank of each member of the population
		double[] fitArray = Util.getFitnessArray(aPopulation);
		double[] rankProbabilities = Util.findRankingProbs(fitArray); // probabilities based on ranking
		double[] cumulProbs = Util.getCumulativeDistribution(rankProbabilities);
		// array with indices to the pool ArrayList
		int[] matingPool = Util.stochasticUniversalSampling(cumulProbs, aPopulation.getLambda(), aRandom);
		//matingPool = Utils.rouletteWheel(cumulProbs, lambda, poolRandom);
		Individual[] parents = new Individual[aPopulation.getLambda()];
		for (int i=0; i<parents.length; i++)
			parents[i] = aPopulation.member(matingPool[i]);
		return parents;
	}
}

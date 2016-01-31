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
		double[] fitArray = new double[pop.getMu()];
		fitArray[0] = pop.getPool()[0].getFitness();
		double minFitness = fitArray[0];
		for (int i=1; i<fitArray.length; i++){
			fitArray[i] = pop.getPool()[i].getFitness();
			if (fitArray[i] < minFitness)
				minFitness = fitArray[i];
		}
		double s = 1.5; // parameter: 1 < s <= 2
		double[] probs = new double[fitArray.length];
		probs[0] = (2.0 - s)/fitArray.length + 2*0*(s-1)/(fitArray.length*(fitArray.length-1));
		double[] cumulProbs = new double[fitArray.length];
		cumulProbs[0] = probs[0];
		for (int i=1; i<fitArray.length; i++) {
			fitArray[i] = fitArray[i] - minFitness + 1;
			probs[i] = (2.0 - s)/fitArray.length + 2*i*(s-1)/(fitArray.length*(fitArray.length-1));
			cumulProbs[i] = cumulProbs[i-1] + probs[i];
		}
		int[] parentPointers = Util.stochasticUniversalSampling(cumulProbs, pop.getLambda(), rand);
		return parentPointers;
	}	

}

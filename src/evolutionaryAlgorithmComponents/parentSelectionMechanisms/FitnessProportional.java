package evolutionaryAlgorithmComponents.parentSelectionMechanisms;

import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.AbstractParentSelection;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

public class FitnessProportional extends AbstractParentSelection {
	
	private final static String title = "Fitness Proportional";
	
	public FitnessProportional(){
		super(title);
	}

	@Override
	public int[] select(Population pop, Random rand) throws Exception {
		double[] fitArray = new double[pop.getPool().length];
		for (int i=0; i<fitArray.length; i++)
			fitArray[i] = pop.getPool()[i].getFitness();
		double minFitness = Util.findMin(fitArray);
		double fitnessSum = 0;
		for (int i=0; i<fitArray.length; i++) {
			fitArray[i] = fitArray[i] - minFitness + 1;
			fitnessSum += fitArray[i];
		}
		double[] cumulProbs = new double[fitArray.length];
		cumulProbs[0] = fitArray[0]/fitnessSum;
		for (int i=1; i<fitArray.length; i++){	
			cumulProbs[i] = cumulProbs[i-1] + fitArray[i]/fitnessSum;
		}
		// array with indices to the pool ArrayList
		int[] parentPointers = Util.stochasticUniversalSampling(cumulProbs, pop.getLambda(), rand);
		return parentPointers;
	}

}

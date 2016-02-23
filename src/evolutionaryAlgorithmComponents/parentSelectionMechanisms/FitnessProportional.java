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
		double[] fitArray = new double[pop.getMu()];
		fitArray[0] = pop.getPool()[0].getFitness();
		double minFitness = fitArray[0];
		double fitnessSum = 0;
		for (int i=1; i<fitArray.length; i++){
			fitArray[i] = pop.getPool()[i].getFitness();
			if (fitArray[i] < minFitness)
				minFitness = fitArray[i];
			//fitnessSum += fitArray[i];
		}
		//fitnessSum += fitArray.length * (1 - minFitness);
		for (int i=0; i<fitArray.length; i++)
			fitnessSum += fitArray[i] + 1 - minFitness;
		double[] cumulProbs = new double[fitArray.length];
		fitArray[0] = fitArray[0] - minFitness + 1;
		cumulProbs[0] = fitArray[0]/fitnessSum;
		for (int i=1; i<fitArray.length; i++) {
			fitArray[i] = fitArray[i] - minFitness + 1;
			cumulProbs[i] = cumulProbs[i-1] + fitArray[i]/fitnessSum;
		}
		int[] parentPointers = Util.stochasticUniversalSampling(cumulProbs, pop.getLambda(), rand);
		return parentPointers;
	}

}

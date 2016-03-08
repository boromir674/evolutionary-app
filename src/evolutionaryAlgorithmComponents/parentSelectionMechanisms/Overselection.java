package evolutionaryAlgorithmComponents.parentSelectionMechanisms;

import interfaces.FitnessCalculator;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import util.Util;
import evolutionaryAlgorithmComponents.AbstractParentSelection;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;
import evolutionaryAlgorithmComponents.fitnessCalculators.RawFitnessReporter;

public class Overselection extends AbstractParentSelection {

	private final static String title = "Overselection";
	private final static double eliteSelectionRate = 0.8;
	private final static double lesserSelectionRate = 0.2;
	private double a;
	private double b;
	
	private FitnessCalculator fitnessCalculator;

	public Overselection() {
		super(title);
		this.fitnessCalculator = new RawFitnessReporter();
	}
	public Overselection(FitnessCalculator aFitnessCalculator) {
		super(title);
		this.fitnessCalculator = aFitnessCalculator;
	}

	@Override
	public int[] select(Population pop, Random aRandom)	throws Exception {
		if (pop.getMu() < 1000)
			throw new Exception("Overselection requires a population size of at least 1000 individuals.");
		if (pop.getMu() <= 2000){
			a = - 2.0/125;
			b = 48;
		} else if (pop.getMu() <= 4000){
			a = - 1.0/250;
			b = 24;
		} else if (pop.getMu() < 8000){
			a = - 1.0/1000;
			b = 12;
		} else
			throw new Exception("Overselection not supported for a population size of more than 8000 individuals");
		double y = a*pop.getMu() + b;
		int fitterGroupIndex = (int) (pop.getMu()*y/100.0);
		int[] upperPick;
		int[] lowerPick;
		Arrays.sort(pop.getPool(), 0, pop.getMu());
		ArrayUtils.reverse(pop.getPool(), 0, pop.getMu());
		ensure(pop);

		double[] upperCumulProbs = Util.getCumulativeDistribution(pop.getPool(), 0, fitterGroupIndex, fitnessCalculator);
		double[] lowerCumulProbs = Util.getCumulativeDistribution(pop.getPool(), fitterGroupIndex, pop.getMu(), fitnessCalculator);
		//TODO debug
		upperPick = Util.stochasticUniversalSampling(upperCumulProbs, (int)(eliteSelectionRate*pop.getLambda()), aRandom);
		lowerPick = Util.stochasticUniversalSampling(lowerCumulProbs, (int)(lesserSelectionRate*pop.getLambda()), aRandom);
		
		int i;
		int[] parents = new int[pop.getLambda()];
		for (i=0; i<upperPick.length; i++){
			parents[i] = upperPick[i];
		}
		for (int j=i; j<lowerPick.length+i; j++){
			parents[j] = lowerPick[j-i] + fitterGroupIndex;
		}
		return parents;
	}

	private static void ensure(Population pop) throws Exception {
		double max = pop.getPool()[0].getFitness();
		for (int i=1; i<pop.getMu(); i++)
			if (pop.getPool()[i].getFitness() > max)
				throw new Exception("Arrays.sort did not work as expected in Overselection");

	}

}

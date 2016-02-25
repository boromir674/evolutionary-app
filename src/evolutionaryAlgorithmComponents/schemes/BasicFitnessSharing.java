package evolutionaryAlgorithmComponents.schemes;

import interfaces.FitnessCalculator;

import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.AbstractFitnessSharingScheme;
import evolutionaryAlgorithmComponents.AbstractRepresentation;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

public class BasicFitnessSharing extends AbstractFitnessSharingScheme {

	private final static String title = "Simple fitness sharing";
	private int alpha = 1; //linear recommended
	public BasicFitnessSharing() {
		super(title);
	}

	@Override
	public double computeFitness(Individual ind) {
		double denominator = 0;
		for (int i=0; i<ind.getPopulation().getMu(); i++) {
			double distance = ((AbstractRepresentation)ind.getPopulation().getPool()[0].getRepresentation()).genotypicDistance(ind, ind.getPopulation().getPool()[i]);
			if (distance <= 5)
				denominator += 1 - Math.pow(distance/5, alpha);
		}
		return ind.getFitness() / denominator;
	}

}

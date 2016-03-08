package evolutionaryAlgorithmComponents.fitnessCalculators;

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
	protected double sigmaShare = 5;
	
	public BasicFitnessSharing() {
		super(title);
	}
	public BasicFitnessSharing(double sigmaShare) {
		super(title);
		this.sigmaShare = sigmaShare;
	}

	@Override
	public double computeFitness(Individual ind) {
		double denominator = 0;
		for (int i=0; i<ind.getPopulation().getMu(); i++) {
			double distance = ((AbstractRepresentation)ind.getPopulation().getPool()[0].getRepresentation()).genotypicDistance(ind, ind.getPopulation().getPool()[i]);
			if (distance <= sigmaShare)
				denominator += 1 - Math.pow(distance/sigmaShare, alpha);
		}
		return ind.getFitness() / denominator;
	}

}

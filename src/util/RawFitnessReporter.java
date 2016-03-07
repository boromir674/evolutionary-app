package util;

import evolutionaryAlgorithmComponents.Individual;
import interfaces.FitnessCalculator;

class RawFitnessReporter implements FitnessCalculator {

	@Override
	public double computeFitness(Individual anIndividual) {
		return anIndividual.getFitness();
	}

}

package evolutionaryAlgorithmComponents.fitnessCalculators;

import evolutionaryAlgorithmComponents.Individual;
import interfaces.FitnessCalculator;

public class RawFitnessReporter implements FitnessCalculator {

	@Override
	public double computeFitness(Individual anIndividual) {
		return anIndividual.getFitness();
	}

}

package interfaces;

import evolutionaryAlgorithmComponents.Individual;

public interface FitnessCalculator {
	/**
	 * Calculates the fitness value for the specified individual's chromosome.
	 * Should be implemented such that bigger fitness values indicate fitter individuals.
	 * @param anIndividual the Individual to be evaluated.
	 * @return the fitness value.
	 */
	public double computeFitness(Individual anIndividual);
}

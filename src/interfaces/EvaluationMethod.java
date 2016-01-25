package interfaces;

import evolutionaryAlgorithmComponents.Individual;

public interface EvaluationMethod extends EvolutionaryAlgorithmComponent{
	/**
	 * Returns the maximum fitness value that has been encountered so far.
	 * @return the maximum fitness value.
	 */
	public double getBestScoreEncountered();
	/**
	 * Returns the amount of resources consumed so far.
	 * @return the number of fitness evaluations performed.
	 */
	public int getEvaluationsUsed();
	/**
	 * This method deals with properly re-initializing the necessary counters that keep track of the score
	 * and the number of fitness calculations performed. The method should be called whenever a new experiment
	 * should be conducted using the same EvaluationMethod. 
	 */
	void reInitialize();
	/**
	 * Calculates the fitness value for the specified chromosome.
	 * Should be implemented such that bigger fitness indicates fitter individual.
	 * @param chromosome An Object array representing the chromosome to be evaluated.
	 * @return the fitness value.
	 */
	public abstract double computeFitness(Individual anIndividual);
}

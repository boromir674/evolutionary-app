package interfaces;

public interface EvaluationMethod extends EvolutionaryAlgorithmComponent, FitnessCalculator {
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
	 * This method should return an array which represents the vector containing the solution to the 
	 * original problem. This vector will naturally correspond also to the best fitness value.  
	 * @return the solution vector if it known, null otherwise.
	 */
	public Object[] getSolutionVector();
}

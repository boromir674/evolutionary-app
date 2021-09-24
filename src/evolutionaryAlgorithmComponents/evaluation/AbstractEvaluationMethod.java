package evolutionaryAlgorithmComponents.evaluation;

import evolutionaryAlgorithmComponents.Individual;
import exceptions.UnknownSolutionException;
import interfaces.EvaluationMethod;

public abstract class AbstractEvaluationMethod implements EvaluationMethod {
	
	/* (non-Javadoc)
	 * @see interfaces.FitnessCalculator#computeFitness(evolutionaryAlgorithmComponents.Individual)
	 */
	@Override
	public double computeFitness(Individual anIndividual) {
		this.evaluationsUsed ++;
		double fitness = calculateFitness(anIndividual);
		if (fitness > this.bestScoreEncountered)
			this.bestScoreEncountered = fitness;
		return fitness;
	}

	protected abstract double calculateFitness(Individual anIndividual);

	private final String title;
	protected double bestScoreEncountered = Double.NEGATIVE_INFINITY;
	protected int evaluationsUsed = 0;
	
	public AbstractEvaluationMethod(String title) {
		this.title = title;
	}
	
	/**
	 * @return the fitness value of the chromosome that contains the optimum solution
	 * @throws Exception 
	 */
	public abstract double getSolutionFitness(int dimensionality) throws UnknownSolutionException;
	
	/* (non-Javadoc)
	 * @see interfaces.EvolutionaryAlgorithmComponent#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see interfaces.EvaluationMethod#getBestScoreEncountered()
	 */
	@Override
	public double getBestScoreEncountered() {
		return bestScoreEncountered;
	}

	/* (non-Javadoc)
	 * @see interfaces.EvaluationMethod#getEvaluationsUsed()
	 */
	@Override
	public int getEvaluationsUsed() {
		return evaluationsUsed;
	}
	
	/* (non-Javadoc)
	 * @see interfaces.EvaluationMethod#reInitialize()
	 */
	@Override
	public void reInitialize() {
		bestScoreEncountered = Double.NEGATIVE_INFINITY;
		evaluationsUsed = 0;
	}
	
}

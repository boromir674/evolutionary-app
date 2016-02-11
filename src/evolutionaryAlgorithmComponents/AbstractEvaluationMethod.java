package evolutionaryAlgorithmComponents;

import java.net.UnknownServiceException;

import exceptions.NoKnownSolutionException;
import interfaces.EvaluationMethod;

public abstract class AbstractEvaluationMethod implements EvaluationMethod {
	
	private final String title;
	protected double bestScoreEncountered = Double.NEGATIVE_INFINITY;
	protected int evaluationsUsed;
	
	public AbstractEvaluationMethod(String title) {
		this.title = title;
	}
	
	/**
	 * @return the fitness value of the chromosome that contains the optimum solution
	 * @throws Exception 
	 */
	public abstract double getSolutionFitness() throws Exception;
	
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

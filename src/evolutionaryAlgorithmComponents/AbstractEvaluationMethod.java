package evolutionaryAlgorithmComponents;

import interfaces.EvaluationMethod;

public abstract class AbstractEvaluationMethod implements EvaluationMethod {
	
	private final String title;
	double bestScoreEncountered = Double.MIN_VALUE;
	protected int evaluationsUsed;
	
	public AbstractEvaluationMethod(String title) {
		this.title = title;
	}
	
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
		bestScoreEncountered = Double.MIN_VALUE;
		evaluationsUsed = 0;
	}
	
}

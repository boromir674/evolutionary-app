package simulationComponents.terminationConditions;

import simulationComponents.Experiment;

public class EvaluationLimit extends AbstractTerminationCondition {

	private final static String title = "Evaluation Reach Limit";
	private int evaluationsLimit;

	public EvaluationLimit(int evaluationsLimit) {
		super(title);
		this.evaluationsLimit = evaluationsLimit;
	}
	public EvaluationLimit(String evaluationsLimit) {
		super(title);
		this.evaluationsLimit = Integer.parseInt(evaluationsLimit);
	}

	@Override
	public boolean satisfied(Experiment anExperiment) {
		if (anExperiment.getRunner().getEvaluation().getEvaluationsUsed() >= evaluationsLimit)
			return true;
		return false;
	}

	/**
	 * @return the evaluationsLimit
	 */
	public int getEvaluationsLimit() {
		return evaluationsLimit;
	}

}

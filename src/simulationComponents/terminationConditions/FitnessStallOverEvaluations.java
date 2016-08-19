package simulationComponents.terminationConditions;

import simulationComponents.Experiment;

public class FitnessStallOverEvaluations extends AbstractTerminationCondition {

	private final static String title = "Fitness stall over evaluations";
	private int evaluationsSpan;
	private int evaluationsCounter = 0;
	private double currentBest = Double.NEGATIVE_INFINITY;
	private int progress = 0;

	public FitnessStallOverEvaluations(int evaluationsSpan) {
		super(title);
		this.evaluationsSpan = evaluationsSpan;
	}
	public FitnessStallOverEvaluations(String evaluationsSpan) {
		super(title);
		this.evaluationsSpan = Integer.parseInt(evaluationsSpan);
	}
	@Override
	public boolean satisfied(Experiment anExperiment) {
		int counter = anExperiment.getRunner().getEvaluation().getEvaluationsUsed();
		evaluationsCounter = counter - progress;
		double newBest = anExperiment.getRunner().getEvaluation().getBestScoreEncountered();
		//System.out.println(newBest);
		if (newBest > currentBest) {
			currentBest = newBest;
			progress = counter;
		}
		else if (evaluationsCounter >= evaluationsSpan)
			return true;
		return false;
	}

}

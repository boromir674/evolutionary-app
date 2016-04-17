package simulationComponents.terminationConditions;

import simulationComponents.Experiment;

public class FitnessStallOverEvaluations extends AbstractTerminationCondition {

	private final static String title = "Fitness improvement over evaluations";
	private int evaluationsSpan;
	private int evaluationsCounter = 0;
	private double currentBest = Double.NEGATIVE_INFINITY;
	private int progress = 0;

	public FitnessStallOverEvaluations(int evaluationsSpan) {
		super(title);
		this.evaluationsSpan = evaluationsSpan;
	}

	@Override
	public boolean satisfied(Experiment anExperiment) {
		int counter = anExperiment.getEvolutionaryAlgorithm().getEvaluator().getEvaluationsUsed();
		evaluationsCounter = counter - progress;
		double newBest = anExperiment.getEvolutionaryAlgorithm().getEvaluator().getBestScoreEncountered();
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

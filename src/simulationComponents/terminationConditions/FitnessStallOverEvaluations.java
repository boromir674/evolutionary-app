package simulationComponents.terminationConditions;

import simulationComponents.Experiment;
@Deprecated
public class FitnessStallOverEvaluations extends AbstractTerminationCondition {
	
	private final static String title = "Fitness improvement over evaluations";
	private int evaluationsSpan;
	private int evaluationsCounter = 0;
	private double currentBest = Double.NEGATIVE_INFINITY;
	private double threshold;
	
	public FitnessStallOverEvaluations(int evaluationsSpan, double threshold) {
		super(title);
		this.evaluationsSpan = evaluationsSpan;
		this.threshold = threshold;
	}

	@Override
	public boolean satisfied(Experiment anExperiment) {
		if (evaluationsCounter == evaluationsSpan)
			return true;
		double newBest = anExperiment.getEvolutionaryAlgorithm().getPopulation().getFittestIndividual().getFitness();
		if (newBest - currentBest < threshold)
			evaluationsCounter ++;
		else {
			evaluationsCounter = 0;
			newBest = currentBest;
		}			
		return false;
	}

}

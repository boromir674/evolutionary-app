package simulationComponents.terminationConditions;

import simulationComponents.Experiment;

public class FitnessStallOverGenerations extends AbstractTerminationCondition {
	
	private final static String title = "Fitness improvement over evaluations";
	private int generationsSpan;
	private int generationsCounter = 0;
	private double currentBest = Double.NEGATIVE_INFINITY;
	private double threshold;
	
	public FitnessStallOverGenerations(int generationsSpan, double threshold) {
		super(title);
		this.generationsSpan = generationsSpan;
		this.threshold = threshold;
	}

	@Override
	public boolean satisfied(Experiment anExperiment) {
		/*if (generationsCounter == generationsSpan)
			return true;
		double newBest = anExperiment.getEvolutionaryAlgorithm().getPopulation().getFittestIndividual().getFitness();
		if (newBest - currentBest < threshold)
			generationsCounter ++;
		else {
			generationsCounter = 0;
			newBest = currentBest;
		}	*/		
		return false;
	}

}

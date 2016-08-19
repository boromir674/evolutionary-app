package simulationComponents.terminationConditions;

import simulationComponents.Experiment;

public class FitnessStallOverGenerations extends AbstractTerminationCondition {
	
	private final static String title = "Fitness stall over evaluations";
	private int generationsSpan;
	private int generationsCounter = 0;
	private double currentBest = Double.NEGATIVE_INFINITY;
	
	public FitnessStallOverGenerations(int generationsSpan) {
		super(title);
		this.generationsSpan = generationsSpan;
	}
	public FitnessStallOverGenerations(String generationsSpan) {
		super(title);
		this.generationsSpan = Integer.parseInt(generationsSpan);
	}
	@Override
	public boolean satisfied(Experiment anExperiment) {
		if (generationsCounter == generationsSpan)
			return true;
		double newBest = anExperiment.getRunner().getPopulation().getFittestIndividual().getFitness();
		if (newBest > currentBest) {
			currentBest = newBest;
			generationsCounter = 0;
		}
		else {
			generationsCounter ++;
			
		}		
		return false;
	}

}

package simulationComponents.terminationConditions;

import simulationComponents.Experiment;

public class FitnessStallOverGenerations extends AbstractTerminationCondition {
	
	private final static String title = "Fitness improvement over evaluations";
	private int generationsSpan;
	private int generationsCounter = 0;
	private double currentBest = Double.NEGATIVE_INFINITY;
	
	public FitnessStallOverGenerations(int generationsSpan) {
		super(title);
		this.generationsSpan = generationsSpan;
	}

	@Override
	public boolean satisfied(Experiment anExperiment) {
		if (generationsCounter == generationsSpan)
			return true;
		double newBest = anExperiment.getEvolutionaryAlgorithm().getPopulation().getFittestIndividual().getFitness();
		if (newBest > currentBest) {
			currentBest = newBest;
			generationsCounter = 0;
		}
		else {
			generationsCounter ++;
			
		}		
		return false;
	}

	public AbstractTerminationCondition getInstance(String parameter) {
		return new FitnessStallOverGenerations(Integer.parseInt(parameter));
	}

}

package simulationComponents.terminationConditions;

import simulationComponents.Experiment;

public class HopedFitness extends AbstractTerminationCondition {
	
	private final static String title = "Known/Hoped Fitness";
	private double hopedFitness;
	
	public HopedFitness(double hopedFitness) {
		super(title);
		this.hopedFitness = hopedFitness;
	}

	@Override
	public boolean satisfied(Experiment anExperiment) {
		if (anExperiment.getEvolutionaryAlgorithm().getPopulation().getFittestIndividual().getFitness() >= hopedFitness)
			return true;
		return false;
	}
}

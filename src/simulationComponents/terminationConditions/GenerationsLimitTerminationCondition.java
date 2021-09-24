package simulationComponents.terminationConditions;

import simulationComponents.Experiment;

public class GenerationsLimitTerminationCondition extends AbstractTerminationCondition {
	
	private final static String title = "Generations Limit";
	private int generationsLimit;
	
	public GenerationsLimitTerminationCondition(int generarionsLimit) {
		super(title);
		this.generationsLimit = generarionsLimit;
	}

	@Override
	public boolean satisfied(Experiment anExperiment) {
		if (anExperiment.getEvolutionaryAlgorithm().getPopulation().getGenerationCounter() >= generationsLimit)
			return true;
		return false;
	}

	/**
	 * @return the generationsLimit
	 */
	public int getLimit() {
		return generationsLimit;
	}

}

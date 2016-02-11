package simulationComponents.terminationConditions;

import simulationComponents.Experiment;

public class DiversityBelowThreshold extends AbstractTerminationCondition {
	
	private final static String title = "Population diversity < ";
	private double threshold;
	
	public DiversityBelowThreshold(double threshold) {
		super(title+Double.toString(threshold));
		this.threshold = threshold;
	}

	@Override
	public boolean satisfied(Experiment anExperiment) {
		if (anExperiment.getEvolutionaryAlgorithm().getPopulation().getDiversity() <= threshold)
			return true;
		return false;
	}

}
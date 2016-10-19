package interfaces;

import simulationComponents.Experiment;

public interface TerminationCondition {
	
	public boolean satisfied(Experiment anExperiment);
	
}

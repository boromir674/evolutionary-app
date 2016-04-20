package simulationComponents.terminationConditions;

import simulationComponents.Experiment;

public class TimeLimit extends AbstractTerminationCondition {

	private final static String title = "Time > Limit";
	private double timeLimit;

	public TimeLimit(double timeLimit) {
		super(title);
		this.timeLimit = timeLimit;
	}

	@Override
	public boolean satisfied(Experiment anExperiment) {
		try {
			if (System.nanoTime() - anExperiment.getStartTime() > timeLimit)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public AbstractTerminationCondition getInstance(String parameter) {
		return new TimeLimit(Double.parseDouble(parameter));
	}

}

package simulationComponents.terminationConditions;

import simulationComponents.Experiment;

public class TimeLimit extends AbstractTerminationCondition {

	private final static String title = "Time > Limit";
	private double timeLimit;

	public TimeLimit(double timeLimit) {
		super(title);
		this.timeLimit = timeLimit;
	}
	public TimeLimit(String timeLimit) {
		super(title);
		this.timeLimit = Double.parseDouble(timeLimit);
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

}

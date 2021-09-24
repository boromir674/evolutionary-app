package simulationComponents.terminationConditions;

import interfaces.TerminationCondition;

public abstract class AbstractTerminationCondition implements TerminationCondition{

	private final String title;

	public AbstractTerminationCondition(String title){
		this.title = title;
	}
		
	public String getTitle(){
		return title;
	}
	
}

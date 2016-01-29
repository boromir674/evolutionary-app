package evolutionaryAlgorithmComponents;

import interfaces.SurvivorSelection;

public abstract class AbstractSurvivorSelection implements SurvivorSelection{

	private final String title;
	protected int maxIndex;

	public AbstractSurvivorSelection(String title) {
		this.title = title;
	}

	public String getTitle(){
		return title;
	} 

}

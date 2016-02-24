package evolutionaryAlgorithmComponents;

import interfaces.SurvivorSelection;

public abstract class AbstractSurvivorSelection implements SurvivorSelection{

	private final String title;

	public AbstractSurvivorSelection(String title) {
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

}

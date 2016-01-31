package evolutionaryAlgorithmComponents;

import interfaces.SurvivorSelection;

public abstract class AbstractSurvivorSelection implements SurvivorSelection{

	private final String title;
	private int maxIndex = -1;

	public AbstractSurvivorSelection(String title) {
		this.title = title;
	}

	public String getTitle(){
		return title;
	} 
	protected void setMaxIndex(int index){
		this.maxIndex = index;
	}
	Individual informAboutMaxIndividual(Population pop){
		int temp = maxIndex;
		maxIndex = -1;
		if (temp == -1)
			return null;
		else
			return pop.getPool()[temp];
	}
	
	
}

package evolutionaryAlgorithmComponents;

import interfaces.FitnessCalculator;
import interfaces.ParentSelection;

public abstract class AbstractParentSelection implements ParentSelection {

	private final String title;
	protected EvolutionaryAlgorithm myEvolutionaryAlgorithm;
	
	public AbstractParentSelection(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

}

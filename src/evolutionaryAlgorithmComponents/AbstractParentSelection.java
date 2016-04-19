package evolutionaryAlgorithmComponents;

import interfaces.ParentSelection;

public abstract class AbstractParentSelection implements ParentSelection {

	private final String title;						// malakia

	public AbstractParentSelection(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
	
}

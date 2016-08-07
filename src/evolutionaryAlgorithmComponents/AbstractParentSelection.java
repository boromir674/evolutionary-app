package evolutionaryAlgorithmComponents;

import java.util.Random;

import interfaces.ParentSelection;

public abstract class AbstractParentSelection implements ParentSelection {

	private final String title;
	
	public AbstractParentSelection(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

}

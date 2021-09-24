package evolutionaryAlgorithmComponents;

import java.util.Random;

import interfaces.SurvivorSelection;

public abstract class AbstractSurvivorSelection implements SurvivorSelection{

	private final String title;
	protected Random random;
	
	public AbstractSurvivorSelection(String title) {
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
	
	/** 
	 * This method should return true if the answer to the question, whether the {@link EvolutionaryAlgorithm}
	 * should check and make sure that the fittest member of the population is preserved (if not) over the generations,
	 * is positive. If the implementation of the select method guarantees the "elitism" property (the preservation
	 * the fittest Individual), then return value of this method has no influence in the pipeline.  
	 * @return true if the answer is yes, false if the answer is no.
	 */
	protected abstract boolean forceElitism();
}

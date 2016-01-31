package interfaces;

import Exceptions.LambdaLessThanMuException;
import Exceptions.SortsInPlaceThePopulationException;
import evolutionaryAlgorithmComponents.Population;

public interface SurvivorSelection extends EvolutionaryAlgorithmComponent{
	
	/**
	 * Applies to a @link{Population} of size lambda+mu. Selects survivors by
	 * reducing the size of the @link{Population} to mu.
	 * @param aPopulation is an instance of @link{Population}.
	 * @param mu is an integer dictating the desired @link{Population} size.
	 * @throws Exception 
	 */
	public int[] select(Population aPopulation) throws Exception;
	
	/** 
	 * This method should return true if the answer to the question, whether the {@link EvolutionaryAlgorithm}
	 * should check and make sure that the fittest member of the population is preserved over the generations,
	 * is positive. If the implementation of the select method guarantees the "elitism" property (the preservation
	 * the fittest Individual), then return value of this method has no influence in the pipeline.  
	 * @return true if the answer is yes, false if the answer is no.
	 */
	public boolean forceElitism();
}

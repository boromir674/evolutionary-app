package interfaces;

import Exceptions.LambdaLessThanMuException;
import Exceptions.SortsInPlaceThePopulationException;
import evolutionaryAlgorithmComponents.Population;

public interface SurvivorSelection extends EvolutionaryAlgorithmComponent{
	
	/**
	 * This method picks members of the {@link Population} (parents and offsprings) that
	 * will survive the selection process. It should return a (mu) μ-sized array of linear
	 * indices that point to the elements of the Population pool.
	 * @param aPopulation the population to undergo selection
	 * @param mu is an integer dictating the desired @link{Population} size.
	 * @return the linear indices
	 * @throws Exception if the number of parents selected is not equal to μ (mu).
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

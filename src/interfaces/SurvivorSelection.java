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
	
	public boolean isElitistic();
}

package interfaces;

import evolutionaryAlgorithmComponents.Population;

public interface SurvivorSelection extends EvolutionaryAlgorithmComponent{
	
	/**
	 * Applies to a @link{Population} of size lambda+mu. Selects survivors by
	 * reducing the size of the @link{Population} to mu.
	 * @param aPopulation is an instance of @link{Population}.
	 * @param mu is an integer dictating the desired @link{Population} size.
	 */
	public int[] select(Population aPopulation) throws Exception;
}

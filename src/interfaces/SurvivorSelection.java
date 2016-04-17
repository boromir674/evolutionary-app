package interfaces;

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
	 * This method should serve as a reporter of whether the selection process guarantees
	 * or not the survival of the fittest member of the Population over the generations. In other
	 * words whether the selection process satisfies the 'elitism' property. 
	 * @return true if the selection is elitist, else false
	 */
	public boolean isElitist();
}

package interfaces;

import java.util.Random;

import evolutionaryAlgorithmComponents.Population;

/**
 * Parent Selection Interface
 */
public interface ParentSelection extends EvolutionaryAlgorithmComponent{

	/**
	 * This method should return linear indices pointing to the Population pool of
	 * Individuals. The array returned can contain duplicates.
	 * @param aPopulation the Population of mu Individuals
	 * @param aRandom an instance of class Random
	 * @return the indices pointing to the Individuals selected for undergoing variation 
	 */
	public int[] select(Population aPopulation, Random aRandom) throws Exception;
}
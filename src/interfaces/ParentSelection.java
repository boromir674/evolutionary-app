package interfaces;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

/**
 * Parent Selection Interface
 */
public interface ParentSelection extends EvolutionaryAlgorithmComponent{
	/**
	 * 
	 * @param aPopulation
	 * @param lambda
	 * @param aRandom
	 * @return indices pointing to the elements 
	 * @throws Exception 
	 */
	public Individual[] select(Population aPopulation, Random aRandom) throws Exception;
}

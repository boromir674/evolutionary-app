package interfaces;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

/**
 * Parent Selection Interface
 */
public interface ParentSelection extends EvolutionaryAlgorithmComponent{

	/**
	 * TODO write javadoc
	 * @param anArrayOfIndividuals
	 * @param aRandom
	 * @return
	 * @throws Exception
	 */
	public int[] select(Population aPopulation, Random aRandom) throws Exception;
}
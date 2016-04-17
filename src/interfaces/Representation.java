package interfaces;

import java.util.Random;

public interface Representation extends EvolutionaryAlgorithmComponent{
	/**
	 * Uniformly Randomly creates a new chromosome in the form of an Object array given an instance of class {@link Random}.
	 * @param aRandom an instance of class Random.
	 * @return the Object array representing the chromosome generated. 
	 * @throws Exception 
	 */
	public abstract Object[] generateRandomChromosome(Random aRandom) throws Exception;
	/**
	 * Initializes a new chromosome with empty space so that to receive specific Objects. Basically returns an new array of the user defined type.
	 * @return the array of Objects.
	 */
	public abstract Object[] createEmptyChromosome();
	/**
	 * This method returns the user defined dimensions that specify the length of the chromosome.
	 * @return the value that corresponds to the dimensionality of the problem.
	 */
	public abstract int getDimensions();
}

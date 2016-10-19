/**
 * A Strategy to initialize Algorithms 
 */
package interfaces;

import java.util.Random;

import evolutionaryAlgorithmComponents.Population;

/**
 * Implementing this interfaces allows a class to initialize a Population 
 */
public interface AlgorithmInitializator {
	
	public void initializeAlgorithm(Algorithm algorithm);
}

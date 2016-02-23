package interfaces;

import evolutionaryAlgorithmComponents.Individual;

/**
 * @author konstantinos
 * An object implementing this interface has the ability to calculate the
 * fitness value of an Individual according to a user specified "fitness sharing scheme".
 */
public interface FitnessSharingScheme extends EvolutionaryAlgorithmComponent {

	/**
	 * This method should calculate and return the modified fitness value of
	 * the given Individual according to the specified "fitness sharing scheme".   
	 * @param anIndividual the Individual to be evaluated
	 * @return the fitness computed by the fitness sharing scheme
	 */
	double computeSharedFitness(Individual anIndividual);
}

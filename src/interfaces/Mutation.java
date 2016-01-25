package interfaces;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;

public interface Mutation extends EvolutionaryAlgorithmComponent {
	/**
	 * Mutates the @link{Individual} according to the specific
	 * implementation of the interface.
	 * @param anIndividual the input @link{Individual} on whom the operation is performed.
	 * @param aRandom an instant of class @link{java.util.Random}.
	 * @throws Exception a java.lang.Exception printing an appropriate message.
	 */
	public void apply(Individual anIndividual, Random aRandom) throws Exception;
	/**
	 * Checks whether the mutation operator can be applied on
	 * chromosomes encoding discrete variables.
	 * @return true if the operator is applicable to discrete variables or
	 * 		   false if the operator is not applicable to discrete variables.
	 */
	public boolean applicableToDiscrete();
	/**
	 * This method should return the user defined probability of an Individual being mutated.
	 * @return the probability of mutation.
	 */
	public double getProbability();
}

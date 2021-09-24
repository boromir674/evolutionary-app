package interfaces;

import evolutionaryAlgorithmComponents.Individual;

public interface Recombination extends EvolutionaryAlgorithmComponent{
	/**
	 * Produces two new offsprings by recombining the two parents.
	 * @param mom first parent
	 * @param dad second parent
	 * @return an array of length 2 containing the new Individuals/children. 
	 */
	public Individual[] perform(Individual mom, Individual dad) throws Exception;
	/**
	 * Checks whether the recombination operator can be applied on
	 * chromosomes encoding discrete variables.
	 * @return true if the operator is applicable to discrete variables or
	 * 		   false if the operator is not applicable to discrete variables.
	 */
	public boolean applicableToDiscrete();
}

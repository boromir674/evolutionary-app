package evolutionaryAlgorithmComponents;

import interfaces.EvolutionaryAlgorithmComponent;
import interfaces.Mutation;
import interfaces.Recombination;
import interfaces.Representation;

import java.util.Random;

import evolutionaryAlgorithmComponents.variationOperators.mutation.AbstractMutation;
import evolutionaryAlgorithmComponents.variationOperators.mutation.discreteValue.AbstractPermutationMutation;
import evolutionaryAlgorithmComponents.variationOperators.recombination.AbstractRecombination;
import evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue.AbstractDiscreteRecombination;
import evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue.AbstractPermutationRecombination;

public class VarianceOperator implements EvolutionaryAlgorithmComponent{

	private Recombination recombination;
	private Mutation mutation;	
	boolean applicableToPermutation = false;
	
	public VarianceOperator(Recombination recombinationType, Mutation mutationType){
		this.recombination = recombinationType;
		this.mutation = mutationType;
		if (recombination instanceof AbstractPermutationRecombination && mutation instanceof AbstractPermutationMutation)
			applicableToPermutation = true;
	}
	public VarianceOperator(Recombination recombinationType){
		this.recombination = recombinationType;
		if (recombination instanceof AbstractPermutationRecombination)
			applicableToPermutation = true;
	}
	public VarianceOperator(Mutation mutationType){
		this.mutation = mutationType;
		if (mutation instanceof AbstractPermutationMutation)
			applicableToPermutation = true;
	}

	public Individual[] operate(Individual mom, Individual dad, Random rand) throws Exception{
		Individual[] children = null;
		if (recombination != null)
			children = recombination.perform(mom, dad);
		else
			children = new Individual[]{mom, dad};
		if (hasNull(children)){
			throw new Exception("Offspring failed to be produced properly: contains null element(s)");
		}
		if (mutation != null){
			mutation.apply(children[0], rand);
			if (children.length == 2)
				mutation.apply(children[1], rand);
		}
		return children;
	}

	private static boolean hasNull(Individual[] someChildren){
		for (int i=0; i<someChildren.length; i++)
			for (int j=0; j<someChildren[0].getChromosome().length; j++)
				if (someChildren[i].getChromosome()[j] == null)
					return true;
		return false;
	}
	@Override
	public String getTitle() {
		return null;
	}
	/**
	 * @return the recombination
	 */
	public Recombination getRecombination() {
		return recombination;
	}
	/**
	 * @return the mutation
	 */
	public Mutation getMutation() {
		return mutation;
	}
	
}

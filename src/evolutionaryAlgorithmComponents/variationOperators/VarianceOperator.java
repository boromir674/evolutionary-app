package evolutionaryAlgorithmComponents.variationOperators;

import interfaces.EvolutionaryAlgorithmComponent;
import interfaces.Mutation;
import interfaces.Recombination;
import interfaces.Representation;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;

public class VarianceOperator implements EvolutionaryAlgorithmComponent{

	private Recombination recombination;
	private Mutation mutation;	

	public VarianceOperator(Recombination recombinationType, Mutation mutationType){
		this.recombination = recombinationType;
		this.mutation = mutationType;
	}
	public VarianceOperator(Recombination recombinationType){
		this.recombination = recombinationType;
	}
	public VarianceOperator(Mutation mutationType){
		this.mutation = mutationType;
	}

	public Individual[] operate(Individual mom, Individual dad, Representation representation, Random rand) throws Exception{
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

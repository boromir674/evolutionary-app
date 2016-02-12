package evolutionaryAlgorithmComponents.representation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;

public class PermutationRepresentation extends AbstractIntegerRepresentation {

	private final static String title = "Permutation";

	public PermutationRepresentation(int dim, int low, int high) {
		super(title, dim, low, high);
	}

	@Override
	public Object[] generateRandomChromosome(Random aRandom) throws Exception {

		int[] indices = new int[super.dimensions];
		for (int i=0; i<indices.length; i++)
			indices[i] = i;
		util.Util.shuffleArray(indices, aRandom);
		Integer[] chromosome = new Integer[super.dimensions];
		for (int i=0; i< chromosome.length; i++)
			chromosome[indices[i]] = super.lowerBound + i;
		if (!chromosomeOK(chromosome))
			throw new Exception("random chromosome is invalid");
		return chromosome;
	}

	/**
	 * @param in the chromosome to being evaluated
	 * @return true if the chromosome encodes a valid permutation of integers, false otherwise
	 */
	public static boolean chromosomeOK(Object[] in) {
		boolean[] flags = new boolean[in.length];
		for (int i=0; i<in.length; i++) {
			if (flags[(int)(in[i])-1])
				return false;
			else
				flags[(int)(in[i])-1] = true;
		}
		return true;
	}

}

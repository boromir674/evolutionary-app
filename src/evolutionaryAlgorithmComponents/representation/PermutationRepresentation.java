package evolutionaryAlgorithmComponents.representation;

import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import evolutionaryAlgorithmComponents.Individual;

public class PermutationRepresentation extends AbstractIntegerRepresentation {

	private final static String title = "Permutation";

	public PermutationRepresentation(int dim, int low, int high) {
		super(title, dim, low, high);
	}

	@Override
	public Object[] generateRandomChromosome(Random aRandom) {

		int[] indices = new int[super.dimensions];
		for (int i=0; i<indices.length; i++)
			indices[i] = i;
		util.Util.shuffleArray(indices, aRandom);
		Integer[] chromosome = new Integer[super.dimensions];
		for (int i=0; i< chromosome.length; i++)
			chromosome[indices[i]] = super.lowerBound + i;
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
	
	@Override
	public double genotypicDistance(Individual ind0, Individual ind1) {
		//https://en.wikipedia.org/wiki/Kendall_tau_distance
		int d = ind0.getRepresentation().getDimensions();
		int distance = 0;
		for (int i=0; i<d; i++) {
			for (int j=i+1; j<d; j++) {
				if (ArrayUtils.indexOf(ind1.getChromosome(), ind0.getChromosome()[i]) > ArrayUtils.indexOf(ind1.getChromosome(), ind0.getChromosome()[j])) {
					distance ++;
				}
			}
		}
		return distance / (double) (d*(d-1)/2); // normalized
	}

}

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
	public Object[] generateRandomChromosome(Random aRandom) {

		ArrayList<Integer> set = new ArrayList<Integer>();

		for (int i=1; i<dimensions+1; i++)
			set.add(i);

		Collections.shuffle(set, aRandom);
		Object[] chromosome = set.toArray();

		return chromosome;
	}

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

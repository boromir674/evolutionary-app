package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import interfaces.Representation;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import evolutionaryAlgorithmComponents.Individual;

public class CycleCrossover extends AbstractDiscreteRecombination {
	// The Cycle Crossover is concerned with preserving as much information 
	// as possible about the absolute position in which elements occur.

	// I.M. Oliver, D.J. Smith, and J. Holland. A study of permutation crossover
	// operators on the traveling salesman problem. In Grefenstette [198], pages
	// 224–230.

	// J.J. Grefenstette, editor. Proceedings of the 2nd International Conference on
	// Genetic Algorithms and Their Applications. Lawrence Erlbaum, Hillsdale, New
	// Jersey, 1987.

	private final static String title = "Order Crossover";
	
	public CycleCrossover() {
		super(title);
	}

	@Override
	public Individual[] perform(Individual mom, Individual dad) {
		
		Individual[] children = super.initializeChildrenForRecombination(mom.getRepresentation());

		int i1, i2, i = 0, first;
		ArrayList<ArrayList<Integer>> cycles = new ArrayList<ArrayList<Integer>>();
		boolean[] indices = new boolean[mom.getRepresentation().getDimensions()];
		Arrays.fill(indices, true);
		
		while (ArrayUtils.contains(indices, true)){
			cycles.add(new ArrayList<Integer>());
			i1 = i;
			while (!indices[i1])
				i1 ++;
			
			first = i1;
			do {				
				i2 = (int) dad.getChromosome()[i1];
				children[i%2].getChromosome()[i1] = mom.getChromosome()[i1];
				children[(i+1)%2].getChromosome()[i1] = i2;
				i1 = ArrayUtils.indexOf(mom.getChromosome(), i2);
				cycles.get(cycles.size()-1).add(i2);
				indices[i1] = false;
			} while (i2 != (int) mom.getChromosome()[first]);
			i ++;
		}
		return children;
	}

	@Override
	public boolean applicableToPermutation() {
		return true;
	}

}

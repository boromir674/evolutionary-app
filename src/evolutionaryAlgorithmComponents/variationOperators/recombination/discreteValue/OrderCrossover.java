package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import interfaces.Representation;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;

public class OrderCrossover extends AbstractPermutationRecombination {
	// For order-based permutation problems (Davis)
	// L. Davis, editor. Handbook of Genetic Algorithms. Van Nostrand Reinhold, 1991.

	private final static String title = "Order Crossover";
	private Random random;

	public OrderCrossover(Random aRandom) {
		super(title);
		this.random = aRandom;
	}

	@Override
	public Individual[] perform(Individual mom, Individual dad) throws Exception {

		Individual[] children = super.initializeChildrenForRecombination(mom.getRepresentation());
		int dimensions = mom.getRepresentation().getDimensions();
		int point1 = random.nextInt(dimensions);
		int point2 = random.nextInt(dimensions-1);
		if (point2 >= point1)
			point2 ++;

		int[] points = new int[]{point1, point2};
		Arrays.sort(points);

		for (int i=points[0]; i<points[1]+1; i++){
			children[0].getChromosome()[i] = mom.getChromosome()[i];
			children[1].getChromosome()[i] = dad.getChromosome()[i];
		}
		int ii = (points[1] + 1) % dimensions;
		int ij = (points[1] + 1) % dimensions;
		int i = (points[1] + 1) % dimensions;
		int toBeFilled = dimensions - points[1] + points[0] - 1;
		boolean done = false;
		int counter1 = 0;
		int counter2 = 0;
		while (!done){
			if (counter1 < toBeFilled){
				if (!ArrayUtils.contains(children[0].getChromosome(), dad.getChromosome()[i])){
					children[0].getChromosome()[ii] = dad.getChromosome()[i];
					ii = (ii + 1) % dimensions;
					counter1 ++;
				}
			}
			if (counter2 < toBeFilled){
				if (!ArrayUtils.contains(children[1].getChromosome(), mom.getChromosome()[i])){
					children[1].getChromosome()[ij] = mom.getChromosome()[i];
					ij = (ij + 1) % dimensions;
					counter2 ++;
				}
			}
			if (counter1 == toBeFilled  && counter2 == toBeFilled)
				done = true;
			i = (i + 1) % dimensions;

		}
		if (mom.getRepresentation() instanceof PermutationRepresentation) {
			if (!PermutationRepresentation.chromosomeOK(children[0].getChromosome()))
				throw new Exception("offspring 1 contains dublicates!");
			if (!PermutationRepresentation.chromosomeOK(children[1].getChromosome()))
				throw new Exception("offspring 2 contains dublicates!");
		}
		return children;
	}

}

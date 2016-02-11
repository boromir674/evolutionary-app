package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import interfaces.Representation;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;

public class PartiallyMappedCrossover extends AbstractDiscreteRecombination {
	// Suitable for adjacency-type problems like TSP
	// page 70
	// D. Whitley. Permutations. In Back et al [27], chapter 33.3, pages 274-284.
	// 27. T. Back, D.B. Fogel, and Z. Michalewicz, editors. Evolutionary Computation 1:
	// Basic Algorithms and Operators. Institute of Physics Publishing, Bristol, 2000
	private final static String title = "Partially Mapped Crossover";
	private Random random;

	public PartiallyMappedCrossover(Random aRandom) {
		super(title);
		this.random = aRandom;
	}

	@Override
	public Individual[] perform(Individual mom, Individual dad) throws Exception {

		if (!PermutationRepresentation.chromosomeOK(mom.getChromosome()))
			throw new Exception("mom has duplicates");
		if (!PermutationRepresentation.chromosomeOK(dad.getChromosome()))
			throw new Exception("dad has duplicates");

		Individual[] children = this.go(mom, dad);

		if (!PermutationRepresentation.chromosomeOK(children[0].getChromosome())){
			children = this.go(mom, dad);
			throw new Exception("offspring 1 contains dublicates!");
		}
		if (!PermutationRepresentation.chromosomeOK(children[1].getChromosome()))
			throw new Exception("offspring 2 contains dublicates!");

		return children;
	}

	public Individual[] go(Individual mom, Individual dad) throws Exception {
		Individual[] children = initializeChildrenForRecombination(mom.getRepresentation());
		int dimensions = mom.getRepresentation().getDimensions();
		int point1 = random.nextInt(dimensions);
		int point2 = random.nextInt(dimensions-1);
		if (point2 >= point1)
			point2 ++;
		int[] points = new int[]{point1, point2};
		Arrays.sort(points);
		int p1, pi;
		int p2, pj;
		int[] momSegment = new int[points[1]-points[0]];
		int[] dadSegment = new int[points[1]-points[0]];

		for (int i=points[0]; i<points[1]; i++){
			children[0].getChromosome()[i] = mom.getChromosome()[i];
			momSegment[i-points[0]] = (int) mom.getChromosome()[i];
			children[1].getChromosome()[i] = dad.getChromosome()[i];
			dadSegment[i-points[0]] = (int) dad.getChromosome()[i];
		}
		for (int i=points[0]; i<points[1]; i++){
			if (!(ArrayUtils.contains(momSegment, (int) dad.getChromosome()[i]))){
				pi = (int) mom.getChromosome()[i];
				pj = ArrayUtils.indexOf(dad.getChromosome(), pi);
				while (children[0].getChromosome()[pj] != null) {
					pi = (int) mom.getChromosome()[pj];
					pj = ArrayUtils.indexOf(dad.getChromosome(), pi);

				}
				children[0].getChromosome()[pj] = dad.getChromosome()[i];
			}

			if (!(ArrayUtils.contains(dadSegment, (int) mom.getChromosome()[i]))){
				p1 = (int) dad.getChromosome()[i];
				p2 = ArrayUtils.indexOf(mom.getChromosome(), p1);
				while (children[1].getChromosome()[p2] != null) {
					p1 = (int) dad.getChromosome()[p2];
					p2 = ArrayUtils.indexOf(mom.getChromosome(), p1);
				}

				children[1].getChromosome()[p2] = mom.getChromosome()[i];
			}
		}

		for (int i = 0; i < dimensions; i++) {
			if (children[0].getChromosome()[i] == null)
				children[0].getChromosome()[i] = dad.getChromosome()[i];
			if (children[1].getChromosome()[i] == null)
				children[1].getChromosome()[i] = mom.getChromosome()[i];
		}

		return children;		
	}

	@Override
	public boolean applicableToPermutation() {
		return true;
	}

}

package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import interfaces.Representation;

import java.util.Arrays;
import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;

public class NPointCrossover extends AbstractDiscreteRecombination {
	
	private final static String title = "-Point Crossover";
	private int numberOfPoints;
	private Random random;
	
	public NPointCrossover(int n, Random aRandom) {
		super(Integer.toString(n)+title);
		numberOfPoints = n;
		this.random = aRandom;
	}

	@Override
	public Individual[] perform(Individual mom, Individual dad) {
		
		Individual[] children = super.initializeChildrenForRecombination(mom.getRepresentation());
		
		int[] original = new int[numberOfPoints];
		for (int i=0; i<original.length; i++)
			original[i] = i;
		int[] points = new int[numberOfPoints];
		int offset = 0;
		for (int i=0; i<points.length; i++){
			points[i] = original[random.nextInt(mom.getRepresentation().getDimensions()-offset)];
			for (int j=points[i]; j<original.length; j++)
				original[j] ++;
			offset ++;
		}
		Arrays.sort(points);
		offset = 0;
		for (int i=0; i<points.length; i++){
			for (int j=0+offset; j<points[i]; j++){
				children[(0+i)%2].getChromosome()[j] = mom.getChromosome()[j];
				children[(1+i)%2].getChromosome()[j] = dad.getChromosome()[j];
			}
			offset = points[i]; 
		}
		return children;
	}

}

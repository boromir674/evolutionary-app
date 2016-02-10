package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import interfaces.Representation;

import java.util.Arrays;
import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;

public class NPointCrossover extends AbstractDiscreteRecombination {
	
	private final static String title = "-Point Crossover";
	private int numberOfPoints;
	private Random random;
	//TODO debug!!!
	public NPointCrossover(int n, Random aRandom) {
		super(Integer.toString(n)+title);
		numberOfPoints = n;
		this.random = aRandom;
	}

	@Override
	public Individual[] perform(Individual mom, Individual dad) {
		
		Individual[] children = super.initializeChildrenForRecombination(mom.getRepresentation());
		int d = mom.getRepresentation().getDimensions();
		int[] indices = new int[d];
		for (int i=0; i<indices.length; i++)
			indices[i] = i;
		int[] points = new int[numberOfPoints + 1];
		int offset = 0;
		int index;
		for (int i=0; i<points.length - 1; i++){
			index =  random.nextInt(d-offset);
			points[i] = indices[index];
			for (int j=points[i]; j<indices.length; j++)
				indices[j] ++;
			offset ++;
		}
		points[points.length-1] = d;
		Arrays.sort(points);
		offset = 0;
		for (int i=0; i<points.length; i++){
			for (int j=offset; j<points[i]; j++){
				children[(0+i)%2].getChromosome()[j] = mom.getChromosome()[j];
				children[(1+i)%2].getChromosome()[j] = dad.getChromosome()[j];
			}
			offset = points[i]; 
		}
		return children;
	}

}

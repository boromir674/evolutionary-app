package evolutionaryAlgorithmComponents;

import interfaces.Representation;
import interfaces.EvaluationMethod;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import exceptions.NoKnownSolutionException;

public class Individual implements Comparable<Individual>{

	private double fitness; // the fitness value of the Individual's chromosome
	private Object[] chromosome; // the chromosome represented as an Object array
	private int age = 0;
	private Representation representation;
	Population pop;
	
	public Individual() {

	}

	@Override
	public int compareTo(Individual ind1) {
		double fit1 = this.getFitness();
		double fit2 = ind1.getFitness();
		Double d1 = fit1;
		Double d2 = fit2;
		@SuppressWarnings("unused")
		int r = d1.compareTo(d2);
		int r2 = Double.compare(fit1, fit2);
		return r2;
	}

	public void computeMyFitness(EvaluationMethod evaluator) throws Exception{
		this.fitness = evaluator.computeFitness(this);
	}

	public Object[] getChromosome() {
		return chromosome;
	}

	public double getFitness() {
		return fitness;
	}

	public void initializeEmpty(Representation representation){
		chromosome = representation.createEmptyChromosome();
		this.representation = representation;
	}

	public void initializeRandomly(Representation representation, Random aRandom) throws Exception{
		chromosome = representation.generateRandomChromosome(aRandom);
		this.representation = representation;
	}
	//TODO generalize..
	public void printChromosome(){
		int offset;
		for (int i=0; i< chromosome.length; i++) 
			if (chromosome instanceof Double[])
				System.out.format("%.2f ", chromosome[i]);
			else //if (chromosome instanceof Integer[])
				{
				try {
					Integer[] vector = (Integer[])(this.pop.evo.getEvaluator()).getSolutionVector();
					offset = ArrayUtils.indexOf(chromosome, vector[0]);
				} catch (NoKnownSolutionException e) {
					offset = ArrayUtils.indexOf(chromosome, 1);
				}
				System.out.format("%d ", chromosome[(i+offset) % chromosome.length]);
			}

		System.out.println();
	}

	public void incrementAge(){
		age ++;
	}
	public int getAge(){
		return age;
	}

	/**
	 * @return the representation
	 */
	public Representation getRepresentation() {
		return representation;
	}
}

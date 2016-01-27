package evolutionaryAlgorithmComponents;

import interfaces.Representation;
import interfaces.EvaluationMethod;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

public class Individual implements Comparable<Individual>{

	public double fitness; // the fitness value of the Individual's chromosome
	private Object[] chromosome; // the chromosome represented as an Object array
	private int age = 0;
	private Representation representation;

	public Individual() {

	}	

	@Override
	public int compareTo(Individual ind1) {
		double fit1 = this.getFitness();
		double fit2 = ind1.getFitness();
		return Double.compare(fit2, fit1); // descending order
	}

	public void computeMyFitness(EvaluationMethod evaluator){
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

	public void printChromosome(){
		for (int i=0; i< chromosome.length; i++) 
			if (chromosome instanceof Double[])
				System.out.format("%.2f ", chromosome[i]);
			else if (chromosome instanceof Integer[]){
				int offset = ArrayUtils.indexOf(chromosome, 1);
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

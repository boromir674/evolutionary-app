package evolutionaryAlgorithmComponents;

import interfaces.Representation;
import interfaces.EvaluationMethod;

import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

public class Individual implements Comparable<Individual>{

	protected double fitness; // the fitness value of the Individual's chromosome
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
		return Double.compare(fit1, fit2);
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
				} catch (NullPointerException e) {
					offset = ArrayUtils.indexOf(chromosome, 1);
				}
				System.out.format("%d ", chromosome[(i+offset) % chromosome.length]);
			}
		System.out.println();
	}

	void incrementAge(){
		age ++;
	}
	public int getAge(){
		return age;
	}
	public Population getPopulation(){
		return this.pop;
	}
	public Representation getRepresentation() {
		return representation;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		Individual a = new Individual();
		a.age = this.age;
		a.fitness = this.fitness;
		a.pop = this.pop;
		a.initializeEmpty(representation);
		for (int i=0; i<a.chromosome.length; i++)
			a.chromosome[i] = this.chromosome[i];
		return a;
	}
	
}

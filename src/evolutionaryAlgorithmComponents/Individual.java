package evolutionaryAlgorithmComponents;

import interfaces.Representation;
import interfaces.EvaluationMethod;

import java.util.Random;

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

	public void computeMyFitness(EvaluationMethod evaluator) {
		this.fitness = evaluator.computeFitness(this);
	}

	public Object[] getChromosome() {
		return chromosome;
	}

	public double getFitness() {
		return fitness;
	}

	public void initializeRandomly(Representation representation, Random aRandom){
		chromosome = representation.generateRandomChromosome(aRandom);
		this.representation = representation;
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
		a.setChromosome(representation.createEmptyChromosome());
		for (int i=0; i<a.chromosome.length; i++)
			a.chromosome[i] = this.chromosome[i];
		return a;
	}

	private void setChromosome(Object[] aChromosome) {
		this.chromosome = aChromosome;
	}

	private void setRepresentation(Representation aRepresentation) {
		this.representation = aRepresentation;
	}

	/**
	 * Factory method that returns an Individual with a yet empty chromosome 
	 * @param aRepresentation
	 * @return 
	 */
	public static Individual getEmptyIndividual(Representation aRepresentation) {
		Individual i = new Individual();
		i.representation = aRepresentation;
		i.setChromosome(aRepresentation.createEmptyChromosome());
		return i;
	}

}

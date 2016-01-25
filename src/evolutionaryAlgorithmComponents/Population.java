package evolutionaryAlgorithmComponents;

import interfaces.Representation;
import interfaces.EvaluationMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.representation.RealValueRepresentation;

public class Population implements Iterable<Individual>{

	private int mu; // population size
	private int lambda; // number of offsprings to create on every generation
	private ArrayList<Individual> pool = new ArrayList<Individual>(); // population
	int generationCount = 1;

	public Population(int mu, int lambda){
		this.mu = mu;
		this.lambda = lambda;
		//this.representation = aRepresentation;
	}

	public void initializeRandom(Representation representation, Random aRandom, EvaluationMethod evaluator) throws Exception{
		pool.clear();
		generationCount = 1;
		Individual member;
		for (int i=0; i<this.mu; i++){
			member = new Individual();
			member.initializeRandomly(representation, aRandom);
			addIndividual(member, evaluator);
		}
	}
	public int getGenerationCounter(){
		return generationCount;
	}

	public void addIndividual(Individual someone, EvaluationMethod evaluator){
		someone.computeFitness(evaluator);
		pool.add(someone);
	}

	@Override
	public Iterator<Individual> iterator() {
		Iterator<Individual> iPop = pool.iterator();
		return iPop; 
	}

	public Individual getFittestIndividual(){
		return Collections.max(pool);
	}

	public Individual member(int i){
		return pool.get(i);
	}

	public void set(int i, Individual anIndividual){
		pool.set(i, anIndividual);
	}

	/**
	 * @return the mu
	 */
	public int getMu() {
		return mu;
	}

	/**
	 * @return the lambda
	 */
	public int getLambda() {
		return lambda;
	}

	public ArrayList<Individual> getPool() {
		return pool;
	}
	
	public void printDiversity() throws Exception {
		if (pool.size() != mu)
			throw new Exception("diversity should be computed only after the survivor selection has been applied");
		double[] fitArray = Util.getFitnessArray(this);
		double[] meanAndStd = Util.recursiveMeanAndStd(fitArray);
		System.out.format("%.2f ", meanAndStd[1]);
	}
}

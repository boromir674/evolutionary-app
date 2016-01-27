package evolutionaryAlgorithmComponents;

import interfaces.Representation;
import interfaces.EvaluationMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.representation.RealValueRepresentation;

public class Population {

	private int mu; // population size
	private int lambda; // number of offsprings to create on every generation
	public Individual[] pool; // parents and children (offsprings)
	int generationCount = 1;
	
	private int offspringStoreIndex = 0;
	
	public Population(int mu, int lambda){
		this.mu = mu;
		this.lambda = lambda;
		pool = new Individual[mu+lambda];
		//this.representation = aRepresentation;
	}

	public void initializeRandom(Representation representation, Random aRandom, EvaluationMethod evaluator) throws Exception{
		pool = new Individual[mu+lambda];
		generationCount = 1;
		Individual member;
		for (int i=0; i<this.mu; i++){
			member = new Individual();
			member.initializeRandomly(representation, aRandom);
			member.computeMyFitness(evaluator);
			pool[i] = member;
		}
	}
	public int getGenerationCounter(){
		return generationCount;
	}

	void addOffspring(Individual someone, EvaluationMethod evaluator){
		someone.computeMyFitness(evaluator);
		pool[offspringStoreIndex+mu] = someone;
		offspringStoreIndex = (offspringStoreIndex + 1) % lambda; 
	}
	
	/** This method finds and returns the Individual, among the Population (of size mu), that
	 * has the highest/maximum fitness value. 
	 * @return the fittest Individual
	 */
	public Individual getFittestIndividual(){
		Individual[] pop = new Individual[mu];
		for (int i=0; i<mu; i++)
			pop[i] = pool[i];
		return Collections.min(Arrays.asList(pool));
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

	/**
	 * @return the pool
	 */
	public Individual[] getPool() {
		return pool;
	}

	public void printDiversity() throws Exception {
		double[] fitArray = Util.getFitnessArray(pool, mu);
		double[] meanAndStd = Util.recursiveMeanAndStd(fitArray);
		System.out.format("%.2f ", meanAndStd[1]);
	}

}

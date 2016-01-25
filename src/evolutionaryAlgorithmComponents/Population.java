package evolutionaryAlgorithmComponents;

import interfaces.Representation;
import interfaces.EvaluationMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.representation.RealValueRepresentation;

public class Population {

	private int mu; // population size
	private int lambda; // number of offsprings to create on every generation
	private Individual[] pool; // parents and children (offsprings)
	int generationCount = 1;
	
	private int readIndex = 0;
	private int poolIndex;
	private int offIndex;
	private Individual[] parents;
	private Individual[] survivors;

	public Population(int mu, int lambda){
		this.mu = mu;
		this.lambda = lambda;
		pool = new Individual[mu+lambda];
		//this.representation = aRepresentation;
	}

	public void initializeRandom(Representation representation, Random aRandom, EvaluationMethod evaluator) throws Exception{
		poolIndex = 0;
		offIndex = this.mu;
		pool = new Individual[mu+lambda];
		generationCount = 1;
		Individual member;
		for (int i=0; i<this.mu; i++){
			member = new Individual();
			member.initializeRandomly(representation, aRandom);
			addIndividualToPool(member, evaluator);
		}
	}
	public int getGenerationCounter(){
		return generationCount;
	}

	void addIndividualToPool(Individual someone, EvaluationMethod evaluator){
		someone.computeFitness(evaluator);
		pool[poolIndex] = someone;
		poolIndex = (poolIndex + 1) % 
	}
	public void setParents(Individual[] parents){
		this.parents = parents;
	}
	public Individual getFittestIndividual(){
		double[] fitnessArray = util.Util.getFitnessArray(this);
		int index = util.Util.findMaxIndex(fitnessArray);
		return pool[index];
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

	public Individual[] getPool() {
		/*Individual[][] parentsAndChildren = new Individual[2][];
		parentsAndChildren[0] = this.mainPopulation;
		parentsAndChildren[1] = children;*/
		//return parentsAndChildren;
		return pool;
	}

	public void printDiversity() throws Exception {
		double[] fitArray = Util.getFitnessArray(this);
		double[] meanAndStd = Util.recursiveMeanAndStd(fitArray);
		System.out.format("%.2f ", meanAndStd[1]);
	}

}

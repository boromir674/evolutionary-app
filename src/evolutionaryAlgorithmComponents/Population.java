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
	private Individual[] mainPopulation; // population
	int generationCount = 1;
	
	private Individual[] children;
	private int popIndex;

	public Population(int mu, int lambda){
		this.mu = mu;
		this.lambda = lambda;
		//this.representation = aRepresentation;
	}

	public void initializeRandom(Representation representation, Random aRandom, EvaluationMethod evaluator) throws Exception{
		popIndex = 0;
		mainPopulation = new Individual[mu];
		children = new Individual[lambda];
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
		mainPopulation[popIndex] = someone;
	}

	public Individual getFittestIndividual(){
		double[] fitnessArray = util.Util.getFitnessArray(this);
		int index = util.Util.findMaxIndex(fitnessArray);
		return mainPopulation[index];
	}

	public Individual member(int i){
		return mainPopulation[i];
	}

	public void set(int i, Individual anIndividual){
		mainPopulation[i] = anIndividual;
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

	public Individual[][] getParentsAndChildren() {
		Individual[][] parentsAndChildren = new Individual[2][];
		parentsAndChildren[0] = this.mainPopulation;
		parentsAndChildren[1] = children;
		return parentsAndChildren;
	}
	
	public void printDiversity() throws Exception {
		double[] fitArray = Util.getFitnessArray(this);
		double[] meanAndStd = Util.recursiveMeanAndStd(fitArray);
		System.out.format("%.2f ", meanAndStd[1]);
	}
}

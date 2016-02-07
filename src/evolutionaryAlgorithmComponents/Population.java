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

public class Population implements Cloneable{

	// temp method
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	private int mu; // population size
	private int lambda; // number of offsprings to create on every generation
	private Individual[] pool; // parents and children (offsprings)
	int generationCount = 1;

	private int offspringStoreIndex;
	private int parentStoreIndex;
	Individual fitterTillMu;
	Individual fitterTillEnd;
	private Random cheatRandom;
	
	public Population(int mu, int lambda){
		this.mu = mu;
		this.lambda = lambda;
	}

	public void initializeRandom(Representation representation, Random aRandom, EvaluationMethod evaluator) throws Exception{
		cheatRandom = aRandom;
		pool = new Individual[mu+lambda];
		generationCount = 1;
		offspringStoreIndex = 0;
		parentStoreIndex = 0;
		Individual member = new Individual();
		member.initializeRandomly(representation, aRandom);
		fitterTillMu = member;
		addParent(member, evaluator);
		for (int i=1; i<this.mu; i++) {
			member = new Individual();
			member.initializeRandomly(representation, aRandom);
			addParent(member, evaluator);
		}
		fitterTillEnd = fitterTillMu;
	}
	public int getGenerationCounter(){
		return generationCount;
	}
	void forceFitter(){
		pool[cheatRandom.nextInt(mu)] = fitterTillEnd;
	}
	void addOffspring(Individual someone, EvaluationMethod evaluator) throws Exception{
		someone.computeMyFitness(evaluator);
		pool[offspringStoreIndex+mu] = someone;
		offspringStoreIndex = (offspringStoreIndex + 1) % lambda;
		if (someone.getFitness() > fitterTillEnd.getFitness())
			fitterTillEnd = someone;
	}
	void updatePoolWithNewGeneration(Individual[] newPool){
		this.pool = newPool;
	}
	private void addParent(Individual in, EvaluationMethod eval) throws Exception{
		in.computeMyFitness(eval);
		pool[parentStoreIndex] = in;
		parentStoreIndex = (parentStoreIndex + 1) % mu;
		if (in.getFitness() > fitterTillMu.getFitness())
			fitterTillMu = in;
	}

	/** This method finds and returns the Individual, among the Population (of size mu), that
	 * has the highest/maximum fitness value. 
	 * @return the fittest Individual
	 */
	public Individual getFittestIndividual(){
		return this.fitterTillMu;
	}
	public Individual getFittestIndividualFromTheWholePool(){
		return fitterTillEnd;

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

	public void visualize() throws Exception {
		double[] fitArray = new double[mu];
		for (int i=0; i<mu; i++)
			fitArray[i] = pool[i].getFitness();
		double[] meanAndStd = Util.sampleMeanAndVariance(fitArray);
		System.out.format("%.2f %.2f ", meanAndStd[0], meanAndStd[1]);
	}

}

package evolutionaryAlgorithmComponents;

import interfaces.Representation;
import interfaces.EvaluationMethod;

import java.util.Random;
import util.Util;

public class Population implements Cloneable{

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	private int mu; // population size
	private int lambda; // number of offsprings to create on every generation
	private Individual[] pool; // parents and children (offsprings)
	int generationCount;
	EvolutionaryAlgorithm evo;
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
		generationCount = 0;
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
	
	/** This method finds and returns the Individual, among the Population (of size mu), that
	 * has the highest/maximum fitness value. 
	 * @return the fittest Individual
	 */
	public Individual getFittestIndividual(){
		return this.fitterTillMu;
	}
	public double getDiversity() {
		double[] fitArray = new double[mu];
		for (int i=0; i<mu; i++)
			fitArray[i] = pool[i].getFitness();
		double[] meanAndStd = Util.sampleMeanAndVariance(fitArray);
		return meanAndStd[1];
	}
	public int getGenerationCounter(){
		return generationCount;
	}
	public EvolutionaryAlgorithm getEvolutionaryAlgorithm(){
		return evo;
	}
	public int getMu() {
		return mu;
	}
	public int getLambda() {
		return lambda;
	}
	public Individual[] getPool() {
		return pool;
	}
	void forceFitter(){
		pool[cheatRandom.nextInt(mu)] = fitterTillEnd;
		fitterTillMu = fitterTillEnd;
	}
	void addOffspring(Individual someone, EvaluationMethod evaluator) throws Exception{
		someone.computeMyFitness(evaluator);
		pool[offspringStoreIndex+mu] = someone;
		someone.pop = this;
		offspringStoreIndex = (offspringStoreIndex + 1) % lambda;
		if (someone.getFitness() > fitterTillEnd.getFitness())
			fitterTillEnd = someone;
	}
	void updatePoolWithNewGeneration(int[] survivors){
		Individual[] newGeneration = new Individual[survivors.length + lambda];
		fitterTillMu = pool[survivors[0]];
		newGeneration[0] = pool[survivors[0]];
		newGeneration[0].incrementAge();
		for (int i=1; i<survivors.length; i++){
			newGeneration[i] = pool[survivors[i]];
			newGeneration[i].incrementAge();
			if (newGeneration[i].getFitness() > fitterTillMu.getFitness())
				fitterTillMu = newGeneration[i];
		}
		this.pool = newGeneration;
	}
	private void addParent(Individual in, EvaluationMethod eval) throws Exception{
		in.computeMyFitness(eval);
		pool[parentStoreIndex] = in;
		in.pop = this;
		parentStoreIndex = (parentStoreIndex + 1) % mu;
		if (in.getFitness() > fitterTillMu.getFitness())
			fitterTillMu = in;
	}
	
}

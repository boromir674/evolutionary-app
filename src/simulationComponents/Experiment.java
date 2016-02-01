package simulationComponents;

import java.util.Random;

import simulationComponents.terminationConditions.EvaluationLimit;
import simulationComponents.terminationConditions.GenerationsLimitTerminationCondition;
import interfaces.SurvivorSelection;
import interfaces.TerminationCondition;
import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

public class Experiment {

	private EvolutionaryAlgorithm evolutionaryAlgorithm;
	private TerminationCondition terminationCondition;
	private long startingTime = 0;
	private Random random;

	public Experiment(EvolutionaryAlgorithm EA, TerminationCondition aTerminationCondition, Random aRandom){
		evolutionaryAlgorithm = EA;
		terminationCondition = aTerminationCondition;
		random = aRandom;
	}

	public Individual performOptimizationTask() throws Exception {
		int i = 0;
		Individual temp;
		this.startingTime = System.nanoTime();
		evolutionaryAlgorithm.randomInitialization(random);
		Population previousPopulation;
		while (!terminationCondition.satisfied(this)){
			previousPopulation = (Population) this.evolutionaryAlgorithm.getPopulation().clone();

			evolutionaryAlgorithm.parentSelection(random);
			evolutionaryAlgorithm.applyOperator(random);
			evolutionaryAlgorithm.survivorSelection();
			
			// debug
			double newBest = findMax(evolutionaryAlgorithm.getPopulation()).getFitness();
			double oldBest = findMax(previousPopulation).getFitness();
			if (evolutionaryAlgorithm.getSurvivorSelectionMethod().forceElitism()){
				if (newBest < oldBest){
					throw new Exception("next gen is worse then previous");
				}
			}
			if (i%25 == 0) {
				this.showPercentage(i);
				temp = findMax(evolutionaryAlgorithm.getPopulation());
				evolutionaryAlgorithm.getPopulation().printStats();
				System.out.format("%.1f%n", temp.getFitness());
			}
			i++;
		}
		return evolutionaryAlgorithm.getPopulation().getFittestIndividual();
	}
	// debug
	private static Individual findMax(Population pop){
		Individual max = pop.getPool()[0];
		for (int i=1; i<pop.getMu(); i++){
			if (pop.getPool()[i].getFitness() > max.getFitness())
				max = pop.getPool()[i];
		}
		return max;
	}
	// needs fixing.....
	public double[] runBatches(int replicates) throws Exception {
		if (replicates < 100)
			throw new Exception("Should produce at least 100 data points.");
		int i = 1;
		random.setSeed(i-1);
		while (!terminationCondition.satisfied(this)){
			evolutionaryAlgorithm.randomInitialization(random);
			evolutionaryAlgorithm.parentSelection(random);
			evolutionaryAlgorithm.applyOperator(random);
			evolutionaryAlgorithm.survivorSelection();
		}
		double x = evolutionaryAlgorithm.getPopulation().getFittestIndividual().getFitness();
		// 1 <= j <= replicates
		// xmean_0 = 0 , var_1 = 0
		// xmean_{j+1} = xmean_j + (x_{j+1} - xmean_j) / (j+1)
		// var_{j+1} = (1-1/j)*var_j - (j+1) * (xmean_{j+1} - xmean_j)^2
		double[] result = new double[]{x, 0};
		while (i<replicates){
			System.out.println(String.format("Batch %d", i));
			random.setSeed(i);
			while (!terminationCondition.satisfied(this)){
				evolutionaryAlgorithm.randomInitialization(random);
				evolutionaryAlgorithm.parentSelection(random);
				evolutionaryAlgorithm.applyOperator(random);
				evolutionaryAlgorithm.survivorSelection();
			}
			x = evolutionaryAlgorithm.getPopulation().getFittestIndividual().getFitness();
			i++;
			// calculations
			result[0] += (x - result[0])/(i+1);
			result[1] = (1-1/i)*result[1] - (i+1)*(result[0]-x);
			x = result[0];
			// setting certain values

		}
		return result;
	}
	/**
	 * Returns the clock time at which the optimization process has started
	 * @return the value of the clock
	 */
	public long getStartTime() throws Exception{
		if (this.startingTime == 0)
			throw new Exception("Optimization hasn't started yet");
		return this.startingTime;
	}
	/**
	 * @return the evolutionaryAlgorithm
	 */
	public EvolutionaryAlgorithm getEvolutionaryAlgorithm() {
		return evolutionaryAlgorithm;
	}

	/**
	 * @param evolutionaryAlgorithm the evolutionaryAlgorithm to set
	 */
	public void setEvolutionaryAlgorithm(EvolutionaryAlgorithm evolutionaryAlgorithm) {
		this.evolutionaryAlgorithm = evolutionaryAlgorithm;
	}

	/**
	 * @return the terminationCondition
	 */
	public TerminationCondition getTerminationCondition() {
		return terminationCondition;
	}

	/**
	 * @param terminationCondition the terminationCondition to set
	 */
	public void setTerminationCondition(TerminationCondition terminationCondition) {
		this.terminationCondition = terminationCondition;
	}

	private void showPercentage(int i){
		int max = (((GenerationsLimitTerminationCondition)terminationCondition)).getLimit();
		double res = i*100/max;
		System.out.format("%.2f  ", res);
	}

}

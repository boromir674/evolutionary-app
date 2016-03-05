package simulationComponents;

import java.text.DecimalFormat;
import java.util.Random;

import simulationComponents.terminationConditions.EvaluationLimit;
import simulationComponents.terminationConditions.GenerationsLimitTerminationCondition;
import util.Util;
import interfaces.SurvivorSelection;
import interfaces.TerminationCondition;
import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;
import exceptions.NoKnownSolutionException;

public class Experiment {

	private EvolutionaryAlgorithm evolutionaryAlgorithm;
	private TerminationCondition terminationCondition;
	private long startingTime = 0;
	private Random random;
	public boolean debug;
	public int visuals;
	public int precision = 2;

	public Experiment(EvolutionaryAlgorithm EA, TerminationCondition aTerminationCondition, Random aRandom){
		evolutionaryAlgorithm = EA;
		terminationCondition = aTerminationCondition;
		random = aRandom;
	}

	public Individual performOptimizationTask() throws Exception {
		int i = 0; startingTime = System.nanoTime();
		evolutionaryAlgorithm.randomInitialization(random);
		Population previousPopulation;
		while (!terminationCondition.satisfied(this)){
			previousPopulation = (Population) this.evolutionaryAlgorithm.getPopulation().clone();
			evolutionaryAlgorithm.parentSelection(random);
			evolutionaryAlgorithm.applyOperator(random);
			evolutionaryAlgorithm.survivorSelection();
			i++;
			if (debug)
				this.compareToPreviousPopulation(previousPopulation);
			if (visuals != 0 && i%visuals == 0)
				this.visualize(precision, evolutionaryAlgorithm.getPopulation().getMu());
		}
		this.visualize(precision, evolutionaryAlgorithm.getPopulation().getMu());
		this.showDuration();
		return evolutionaryAlgorithm.getPopulation().getFittestIndividual();
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
	public EvolutionaryAlgorithm getEvolutionaryAlgorithm() {
		return evolutionaryAlgorithm;
	}
	public void setEvolutionaryAlgorithm(EvolutionaryAlgorithm evolutionaryAlgorithm) {
		this.evolutionaryAlgorithm = evolutionaryAlgorithm;
	}
	public TerminationCondition getTerminationCondition() {
		return terminationCondition;
	}
	public void setTerminationCondition(TerminationCondition terminationCondition) {
		this.terminationCondition = terminationCondition;
	}
	private void compareToPreviousPopulation(Population previousPopulationInstance) throws Exception {
		Individual best = this.evolutionaryAlgorithm.getPopulation().getFittestIndividual();
		Individual newBest = findMax(evolutionaryAlgorithm.getPopulation());
		if (best.getFitness() != newBest.getFitness())
			throw new Exception("error in getFittestIndvidual");
		double oldBest = findMax(previousPopulationInstance).getFitness();
		if (evolutionaryAlgorithm.getSurvivorSelectionMethod().isElitist())
			if (newBest.getFitness() < oldBest)
				throw new Exception("next gen is worse then previous");
	}
	private void showDuration(){
		double duration = ((double)(System.nanoTime() - this.startingTime)) / 1000000000;
		System.out.println("Elapsed Time : " + new DecimalFormat("#.#").format(duration) + " Seconds");
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
	private void visualize(int precision, int limit) {
		Population pop = this.evolutionaryAlgorithm.getPopulation();
		double[] fitArray = new double[limit];
		for (int i=0; i<limit; i++)
			fitArray[i] = pop.getPool()[i].getFitness();
		double[] meanAndStd = Util.sampleMeanAndVariance(fitArray);
		try {
			this.evolutionaryAlgorithm.printPerformance();}
		catch (Exception e) {
		}		
		String visual = "%d %."+Integer.toString(precision)+"f %."+Integer.toString(precision)+"f %."+Integer.toString(precision)+"f%n";
		System.out.format(visual, pop.getGenerationCounter(), pop.getFittestIndividual().getFitness(), meanAndStd[0], meanAndStd[1]);
	}

}

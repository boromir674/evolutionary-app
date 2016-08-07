package simulationComponents;

import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JTextArea;

import util.Util;
import interfaces.TerminationCondition;
import evolutionaryAlgorithmComponents.EARunner;
import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import evolutionaryAlgorithmComponents.Individual;
import gui.EADesignWindow;

public class Experiment {
	
	public boolean debug;
	public int visuals = 5;
	public int precision = 2;
	
	private EvolutionaryAlgorithm evolutionaryAlgorithm;
	private TerminationCondition terminationCondition;
	private Random random = new Random();
	private EARunner runner = new EARunner(random);

	private long startingTime = 0;
	private JTextArea textOutput;

	public Experiment(EvolutionaryAlgorithm EA, TerminationCondition aTerminationCondition){
		evolutionaryAlgorithm = EA;
		terminationCondition = aTerminationCondition;
		this.runner.setEA(EA);
	}

	public Experiment() {
	}
	
	public void designEA(){
		@SuppressWarnings("unused")
		EADesignWindow eaWindow = new EADesignWindow();
	}
	
	public Individual optimize() throws Exception {
		int i = 0;
		startingTime = System.nanoTime();
		runner.randomInitialization();
		while (!terminationCondition.satisfied(this)){
			performCycle();
			i++;
			if (visuals != 0 && i%visuals == 0)
				this.visualize(precision, evolutionaryAlgorithm.getPop().getMu());
		}
		this.visualize(precision, evolutionaryAlgorithm.getPop().getMu());
		this.showDuration();
		return evolutionaryAlgorithm.getPop().getFittestIndividual();
	}

	private void performCycle() throws Exception {
		runner.parentSelection();
		runner.applyOperator();
		runner.survivorSelection();
	}
	
	/*
	// needs fixing.....
	public double[] runBatches(int replicates) throws Exception {
		if (replicates < 100)
			throw new Exception("Should produce at least 100 data points.");
		int i = 1;
		random.setSeed(i-1);
		while (!terminationCondition.satisfied(this))
			evolutionaryAlgorithm.randomInitialization(random);
		double x = evolutionaryAlgorithm.getPopulation().getFittestIndividual().getFitness();
		double[] result = new double[]{x, 0};
		while (i<replicates){
			System.out.println(String.format("Batch %d", i));
			random.setSeed(i);
			while (!terminationCondition.satisfied(this)){
				evolutionaryAlgorithm.randomInitialization(random);
				performCycle();
			}
			x = evolutionaryAlgorithm.getPopulation().getFittestIndividual().getFitness();
			i++;
			result[0] += (x - result[0])/(i+1);
			result[1] = (1-1/i)*result[1] - (i+1)*(result[0]-x);
			x = result[0];
			// setting certain values

		}
		return result;
	}
	*/
	
	public void setRandom(Random random) {
		this.random = random;
		this.runner.setRandom(random);
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
		this.runner.setEA(evolutionaryAlgorithm);
	}
	public TerminationCondition getTerminationCondition() {
		return terminationCondition;
	}
	public void setTerminationCondition(TerminationCondition terminationCondition) {
		this.terminationCondition = terminationCondition;
	}
	private void showDuration(){
		double duration = ((double)(System.nanoTime() - this.startingTime)) / 1000000000;
		System.out.println("Elapsed Time : " + new DecimalFormat("#.#").format(duration) + " Seconds");
	}
	private void visualize(int precision, int limit) {
		double[] fitArray = new double[limit];
		for (int i=0; i<limit; i++)
			fitArray[i] = this.evolutionaryAlgorithm.getPop().getPool()[i].getFitness();
		double[] meanAndStd = Util.sampleMeanAndVariance(fitArray);	
		String visual = "%d %."+Integer.toString(precision)+"f %."+Integer.toString(precision)+"f %."+Integer.toString(precision)+"f%n";
		visual = String.format(visual, this.evolutionaryAlgorithm.getPop().getGenerationCounter(), this.evolutionaryAlgorithm.getPop().getFittestIndividual().getFitness(), meanAndStd[0], meanAndStd[1]);
		textOutput.append(visual);
	}

	public void directOutput(JTextArea jTextArea) {
		this.textOutput = jTextArea;
	}

}

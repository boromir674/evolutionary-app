package simulationComponents;

import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JTextArea;

import util.Util;
import interfaces.TerminationCondition;
import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import evolutionaryAlgorithmComponents.Individual;
import gui.EADesignWindow;

public class Experiment {
	
	public boolean debug;
	public int visuals = 5;
	public int precision = 2;
	
	private EvolutionaryAlgorithm evolutionaryAlgorithm;
	private TerminationCondition terminationCondition;
	private Random random;
	//private EARunner runner = new EARunner(random);

	private long startingTime = 0;
	private JTextArea textOutput;
	
	public Experiment(EvolutionaryAlgorithm EA, TerminationCondition aTerminationCondition){
		evolutionaryAlgorithm = EA;
		terminationCondition = aTerminationCondition;
		this.random = new Random();
	}
	
	public void designEA() {
		EADesignWindow eaWindow = new EADesignWindow();
		@SuppressWarnings("unused")
		EADesignWindowListener listener = new EADesignWindowListener(eaWindow, this);
		eaWindow.initialize();
		eaWindow.getFrame().setVisible(true);
		this.directOutput(eaWindow.getOutputTextArea());
	}
	
	public Individual optimize() throws Exception {
		this.evolutionaryAlgorithm.checkComponentsCompatibility();
		int i = 0;
		startingTime = System.nanoTime();
		evolutionaryAlgorithm.randomInitialization();
		while (!terminationCondition.satisfied(this)){
			performCycle();
			i++;
			if (visuals != 0 && i%visuals == 0)
				this.visualize(precision, this.evolutionaryAlgorithm.getPopulation().getMu());
		}
		this.visualize(precision, evolutionaryAlgorithm.getPopulation().getMu());
		this.showDuration();
		return this.evolutionaryAlgorithm.getPopulation().getFittestIndividual();
	}

	private void performCycle() throws Exception {
		evolutionaryAlgorithm.parentSelection();
		evolutionaryAlgorithm.applyOperator();
		evolutionaryAlgorithm.survivorSelection();
	}
	
		//int i = 1;
			//i++;
			//result[0] += (x - result[0])/(i+1);
			//result[1] = (1-1/i)*result[1] - (i+1)*(result[0]-x);
			//x = result[0];
			// setting certain values
	
	public void setRandom(Random random) {
		this.random = random;
		this.evolutionaryAlgorithm.setRandom(random);
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
	private void showDuration(){
		double duration = ((double)(System.nanoTime() - this.startingTime)) / 1000000000;
		System.out.println("Elapsed Time : " + new DecimalFormat("#.#").format(duration) + " Seconds");
	}
	private void visualize(int precision, int limit) {
		double[] fitArray = new double[limit];
		for (int i=0; i<limit; i++)
			fitArray[i] = this.evolutionaryAlgorithm.getPopulation().getPool()[i].getFitness();
		double[] meanAndStd = Util.sampleMeanAndVariance(fitArray);	
		String visual = "%d %."+Integer.toString(precision)+"f %."+Integer.toString(precision)+"f %."+Integer.toString(precision)+"f%n";
		visual = String.format(visual, evolutionaryAlgorithm.getPopulation().getGenerationCounter(), evolutionaryAlgorithm.getPopulation().getFittestIndividual().getFitness(), meanAndStd[0], meanAndStd[1]);
		textOutput.append(visual);
	}

	private void directOutput(JTextArea jTextArea) {
		this.textOutput = jTextArea;
	}

}

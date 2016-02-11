package simulationComponents.terminationConditions;

import evolutionaryAlgorithmComponents.AbstractEvaluationMethod;
import exceptions.NoKnownSolutionException;
import simulationComponents.Experiment;

public class HopedFitness extends AbstractTerminationCondition {

	private final static String title = "Known/Hoped Fitness";
	private double hopedFitness;
	private boolean flag;

	public HopedFitness(double hopedFitness) {
		super(title);
		this.hopedFitness = hopedFitness;
	}
	public HopedFitness() {
		super(title);
		flag = true;
	}

	@Override
	public boolean satisfied(Experiment anExperiment) {
		if (flag)
			try {
				hopedFitness = ((AbstractEvaluationMethod) anExperiment.getEvolutionaryAlgorithm().getEvaluator()).getSolutionFitness();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		if (anExperiment.getEvolutionaryAlgorithm().getPopulation().getFittestIndividual().
				getFitness() >= hopedFitness){
			return true;
		}
		return false;
	}
}

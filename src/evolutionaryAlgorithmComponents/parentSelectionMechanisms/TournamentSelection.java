package evolutionaryAlgorithmComponents.parentSelectionMechanisms;

import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.AbstractParentSelection;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

public class TournamentSelection extends AbstractParentSelection {
	
	private final static String title = "Tournament";
	
	public TournamentSelection() {
		super(title);
	}

	@Override
	public Individual[] select(Population aPopulation, Random aRandom) {
		double[] fitArray = Util.getFitnessArray(aPopulation);
		int[] matingPool = Util.tournamentSelection(aPopulation.getLambda(), fitArray, aRandom);
		Individual[] parents = new Individual[aPopulation.getLambda()];
		for (int i=0; i<parents.length; i++)
			parents[i] = aPopulation.member(matingPool[i]);
		return parents;
	}

}

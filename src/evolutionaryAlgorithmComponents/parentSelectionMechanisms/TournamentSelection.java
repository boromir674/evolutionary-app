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
	public int[] select(Population pop, Random aRandom) {
		double[] fitArray = Util.getFitnessArray(pop.getPool(), pop.getMu());
		int[] parentPointers = Util.tournamentSelection(pop.getLambda(), fitArray, aRandom);
		return parentPointers;
	}

}

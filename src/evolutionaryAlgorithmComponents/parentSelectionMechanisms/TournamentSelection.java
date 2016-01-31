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
		double[] fitArray = new double[pop.getPool().length];
		for (int i=0; i<fitArray.length; i++)
			fitArray[i] = pop.getPool()[i].getFitness();
		int[] parentPointers = Util.tournamentSelection(pop.getLambda(), fitArray, aRandom);
		return parentPointers;
	}

}

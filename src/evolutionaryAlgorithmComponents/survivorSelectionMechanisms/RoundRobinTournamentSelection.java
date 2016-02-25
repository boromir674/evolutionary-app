package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Random;

import evolutionaryAlgorithmComponents.AbstractSurvivorSelection;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;
import util.Util;

public class RoundRobinTournamentSelection extends AbstractSurvivorSelection {

	private final int q = 10;
	private final static String title = "Round-robin tournament";
	private Random random;

	public RoundRobinTournamentSelection(Random aRandom) {
		super(title);
		random = aRandom;
	}

	@Override
	public int[] select(Population pop) {
		Double[] fitArray = new Double[pop.getPool().length];
		for (int i=0; i<fitArray.length; i++)
			fitArray[i] = pop.getPool()[i].getFitness();
		// Round-Robin tournament
		int[] survivors = Util.roundRobinTournament(pop.getMu(), q, fitArray, random);

		return survivors;
	}

	@Override
	public boolean forceElitism() {
		return false;
	}

	@Override
	public boolean isElitist() {
		return false;
	}

}

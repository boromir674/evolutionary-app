package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Random;

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
	public void select(Population aPopulation) throws Exception{
		double[] fitArray = Util.getFitnessArray(aPopulation);
		// Round-Robin tournament
		int[] survivors = Util.roundRobinTournament(aPopulation.getMu(), q, fitArray, random);

		// store members picked by the round-Robin tournament at the top μ positions
		for (int i=0; i<aPopulation.getMu(); i++)
			aPopulation.set(i, aPopulation.member(survivors[i]));
		aPopulation.getPool().subList(aPopulation.getMu(), aPopulation.getPool().size()).clear(); // discard all after top μ
		super.select(aPopulation);
	}

}

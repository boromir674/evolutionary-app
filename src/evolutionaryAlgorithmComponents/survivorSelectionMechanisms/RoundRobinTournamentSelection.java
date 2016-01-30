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
	public int[] select(Population pop) throws Exception{
		double[] fitArray = Util.getFitnessArray(pop.getPool(), pop.getPool().length);
		// Round-Robin tournament
		int[] survivors = Util.roundRobinTournament(pop.getMu(), q, fitArray, random);
		
		// store members picked by the round-Robin tournament at the top μ positions
		Individual temp = pop.getPool()[survivors[0]];
		//super.maxIndex = 0;
		for (int i=1; i<pop.getMu(); i++){
			temp = pop.getPool()[survivors[i]];
			if (temp.getFitness() > pop.getPool()[1].getFitness())
				//maxIndex = i;
			pop.getPool()[i] = temp;
		}
		return null;
	}

}

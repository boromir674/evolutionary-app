package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import evolutionaryAlgorithmComponents.AbstractSurvivorSelection;
import evolutionaryAlgorithmComponents.Population;
import util.Util;

public class RoundRobinTournamentSelection extends AbstractSurvivorSelection {

	private int q = 10;
	private final static String title = "Round-robin tournament";

	public RoundRobinTournamentSelection() {
		super(title);
	}

	@Override
	public int[] select(Population pop) {
		double[] fitArray = new double[pop.getPool().length];
		for (int i=0; i<fitArray.length; i++)
			fitArray[i] = pop.getPool()[i].getFitness();
		// Round-Robin tournament
		int[] survivors = Util.tournamentSelection(pop.getMu(), q, fitArray, random);
		return survivors;
	}
	public int getTournamentSize(){
		return q;
	}
	public void setTournamentSize(int newSize){
		this.q = newSize;
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

package evolutionaryAlgorithmComponents.Schemes;

import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.AbstractRepresentation;
import evolutionaryAlgorithmComponents.AbstractSurvivorSelection;
import evolutionaryAlgorithmComponents.Population;

public class DeterministicCrowding extends AbstractSurvivorSelection {
	
	// S.W. Mahfoud. Crowding and preselection revisited. In Manner and Manderick, pages 27-36
	// R. Manner and B. Manderick, editors. Proceedings of the 2nd Conference on
	// Parallel Problem Solving from Nature. North-Holland, Amsterdam, 1992.
	
	private final static String title = "Deterministic Crowding";
	private Random rand;
	
	public DeterministicCrowding(Random aRandom) {
		super(title);
		this.rand = aRandom;
	}

	@Override
	public int[] select(Population pop) throws Exception {
		int[] parents = pop.getEvolutionaryAlgorithm().getParents();
		Util.shuffleArray(parents, rand);
		int[] survivors = new int[pop.getMu()];
		for (int i=0; i<survivors.length; i++)
			survivors[i] = parents[i];
		for (int i=0; i<pop.getLambda(); i=i+2) {
			double distanceOf11Plus22 = ((AbstractRepresentation)pop.getEvolutionaryAlgorithm().getRepresentation()).genotypicDistance(pop.getPool()[i+pop.getMu()], pop.getPool()[parents[i]]) +
					((AbstractRepresentation)pop.getEvolutionaryAlgorithm().getRepresentation()).genotypicDistance(pop.getPool()[i+1+pop.getMu()], pop.getPool()[parents[i+1]]);
			double distanceOf12Plus21 = ((AbstractRepresentation)pop.getEvolutionaryAlgorithm().getRepresentation()).genotypicDistance(pop.getPool()[i+pop.getMu()], pop.getPool()[parents[i+1]]) +
					((AbstractRepresentation)pop.getEvolutionaryAlgorithm().getRepresentation()).genotypicDistance(pop.getPool()[i+1+pop.getMu()], pop.getPool()[parents[i]]);
			if (distanceOf11Plus22 < distanceOf12Plus21){
				if (pop.getPool()[i+pop.getMu()].getFitness() > pop.getPool()[parents[i]].getFitness())
					survivors[parents[i]] = i+pop.getMu();
				if (pop.getPool()[i+1+pop.getMu()].getFitness() > pop.getPool()[parents[i+1]].getFitness())
					survivors[parents[i+1]] = i+1+pop.getMu();
			} else {
				if (pop.getPool()[i+pop.getMu()].getFitness() > pop.getPool()[parents[i+1]].getFitness())
					survivors[parents[i+1]] = i+pop.getMu();
				if (pop.getPool()[i+1+pop.getMu()].getFitness() > pop.getPool()[parents[i]].getFitness())
					survivors[parents[i]] = i+1+pop.getMu();
			}
		}
		return survivors;
	}

	@Override
	public boolean forceElitism() {
		return true;
	}

}

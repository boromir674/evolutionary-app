package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import util.MinHeap;
import evolutionaryAlgorithmComponents.AbstractSurvivorSelection;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;
import exceptions.LambdaLessThanMuException;
import exceptions.SortsInPlaceThePopulationException;

public class MuCommaLambda extends AbstractSurvivorSelection {
	
	private final static String title = "(μ,λ)";
	private static final MinHeap heap = new MinHeap();
	
	public MuCommaLambda() {
		super(title);
	}

	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.survivorSelectionMechanisms.AbstractSurvivorSelection#select(evolutionaryAlgorithmComponents.population.Population, int)
	 */
	@Override
	public int[] select(Population pop) throws LambdaLessThanMuException, SortsInPlaceThePopulationException {
		if (pop.getLambda() < pop.getMu())
			throw new LambdaLessThanMuException();
		Individual[] newGeneration = new Individual[pop.getLambda()];
		for (int i=0; i<pop.getLambda(); i++)
			newGeneration[i] = pop.getPool()[pop.getMu()+i];
		heap.heapsort(newGeneration, pop.getMu());
		for (int i=0; i<pop.getMu(); i++)
			pop.getPool()[i] = newGeneration[newGeneration.length-1-i];
		throw new SortsInPlaceThePopulationException();
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

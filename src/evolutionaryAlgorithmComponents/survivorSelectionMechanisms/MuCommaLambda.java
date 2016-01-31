package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Arrays;
import java.util.Collections;

import util.MinHeap;
import Exceptions.LambdaLessThanMuException;
import Exceptions.SortsInPlaceThePopulationException;
import evolutionaryAlgorithmComponents.AbstractSurvivorSelection;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

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
		Individual[] children = new Individual[pop.getLambda()];
		for (int i=0; i<children.length; i++)
			children[i] = pop.getPool()[pop.getMu()+i];
		heap.heapsort(children);
		for (int i=0; i<children.length; i++)
			pop.getPool()[i] = children[children.length-1-i];
		throw new SortsInPlaceThePopulationException();
	}

	@Override
	public boolean isElitistic() {
		return false;
	}
}

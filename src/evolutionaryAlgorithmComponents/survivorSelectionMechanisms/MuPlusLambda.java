package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Exceptions.SortsInPlaceThePopulationException;
import util.MinHeap;
import evolutionaryAlgorithmComponents.AbstractSurvivorSelection;
import evolutionaryAlgorithmComponents.Population;

public class MuPlusLambda extends AbstractSurvivorSelection {
	
	private final static String title = "(μ+λ)";
	private MinHeap heap = new MinHeap();
	public MuPlusLambda() {
		super(title);
	}

	@Override
	public int[] select(Population pop) throws SortsInPlaceThePopulationException {
		heap.heapsort(pop.getPool(), pop.getMu());
		throw new SortsInPlaceThePopulationException();
	}
}
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
	
	public MuPlusLambda() {
		super(title);
	}

	@Override
	public int[] select(Population pop) throws SortsInPlaceThePopulationException {
		MinHeap.heapsort(pop.getPool());
		throw new SortsInPlaceThePopulationException();
	}
}
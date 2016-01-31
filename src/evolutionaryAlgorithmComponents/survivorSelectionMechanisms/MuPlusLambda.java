package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

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
		MinHeap.heapsort(pop.getPool(), pop.getMu());
		ArrayUtils.reverse(pop.getPool());
		throw new SortsInPlaceThePopulationException();
	}
}
package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import org.apache.commons.lang3.ArrayUtils;

import util.MinHeap;
import evolutionaryAlgorithmComponents.AbstractSurvivorSelection;
import evolutionaryAlgorithmComponents.Population;
import exceptions.SortsInPlaceThePopulationException;

public class MuPlusLambda extends AbstractSurvivorSelection {

	private final static String title = "(μ+λ)";
	private final static MinHeap heap = new MinHeap();

	public MuPlusLambda() {
		super(title);
	}

	@Override
	public int[] select(Population pop) throws SortsInPlaceThePopulationException {
		heap.heapsort(pop.getPool(), pop.getMu());
		if (pop.getMu() <= pop.getLambda())
			for (int i=0; i<pop.getMu(); i++)
				pop.getPool()[i] = pop.getPool()[pop.getPool().length-1-i];
		else			
			ArrayUtils.reverse(pop.getPool());
		throw new SortsInPlaceThePopulationException();
	}

	@Override
	public boolean forceElitism() {
		return false;
	}

	@Override
	public boolean isElitist() {
		return true;
	}
	
	
}
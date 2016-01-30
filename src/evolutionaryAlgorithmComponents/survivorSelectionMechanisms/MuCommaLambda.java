package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Arrays;
import java.util.Collections;

import Exceptions.LambdaLessThanMuException;
import Exceptions.SortsInPlaceThePopulationException;
import evolutionaryAlgorithmComponents.AbstractSurvivorSelection;
import evolutionaryAlgorithmComponents.Population;

public class MuCommaLambda extends AbstractSurvivorSelection {
	
	private final static String title = "(μ,λ)";
	
	public MuCommaLambda() {
		super(title);
	}

	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.survivorSelectionMechanisms.AbstractSurvivorSelection#select(evolutionaryAlgorithmComponents.population.Population, int)
	 */
	@Override
	public int[] select(Population pop) throws LambdaLessThanMuException {
		if (pop.getMu()>pop.getLambda()) 
			throw new LambdaLessThanMuException();
		
		Arrays.sort(pop.getPool());
		return null;
	}
}

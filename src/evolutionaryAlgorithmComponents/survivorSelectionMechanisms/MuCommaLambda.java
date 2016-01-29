package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Arrays;
import java.util.Collections;

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
	public void select(Population pop) throws Exception {
		if (pop.getMu()>pop.getLambda()) {
			throw new Exception(String.format("(μ,λ) survivor selection requires μ<=λ%n"
					+ "instead: μ=%d and λ=%d",pop.getMu(), pop.getLambda()));
		}//TODO implement
		Arrays.sort(pop.getPool());
	}
}

package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Collections;

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
		if (pop.getMu()<pop.getLambda()) {
			throw new Exception(String.format("(μ,λ) survivor selection requires μ<=λ%n"
					+ "instead: μ=%d and λ=%d",pop.getMu(), pop.getLambda()));
		}
		// "Fitness-Based Replacement" (μ,λ); preferred over (μ+λ)
		// applies (μ,λ) survivor selection and updates the population
		pop.getPool().subList(0, pop.getMu()).clear(); // discard parents (top μ members) 
		Collections.sort(pop.getPool()); // sorts population according to fitness value
		int temp = pop.getPool().size();
		pop.getPool().subList(pop.getMu(), temp).clear(); // keeps top μ members
		super.select(pop);
	}
}

package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Collections;

import evolutionaryAlgorithmComponents.Population;

public class MuPlusLambda extends AbstractSurvivorSelection {
	
	private final static String title = "(μ+λ)";
	
	public MuPlusLambda() {
		super(title);
	}

	@Override
	public void select(Population aPopulation) throws Exception {
		// "Fitness-Based Replacement" (μ+λ)
		// applies (μ+λ) survivor selection and updates the population
		Collections.sort(aPopulation.getPool()); // sorts population according to fitness value
		aPopulation.getPool().subList(aPopulation.getMu(), aPopulation.getPool().size()).clear(); // discards after μ/keeps top μ members
		super.select(aPopulation);
	}
}
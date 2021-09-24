package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import interfaces.SurvivorSelection;
import evolutionaryAlgorithmComponents.Population;

public abstract class AbstractSurvivorSelection implements SurvivorSelection{

	private final String title;

	public AbstractSurvivorSelection(String title) {
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	/* (non-Javadoc)
	 * @see interfaces.SurvivorSelection#select(evolutionaryAlgorithmComponents.population.Population, int)
	 */
	@Override
	public void select(Population aPopulation) throws Exception {
		if (aPopulation.getPool().size() != aPopulation.getMu())
			throw new Exception(String.format("The pool size of the population (%d) after the replacement (selecting survivors)"
					+ "does not macth the user defined mu (%d)", aPopulation.getPool().size(), aPopulation.getMu()));
	}

}

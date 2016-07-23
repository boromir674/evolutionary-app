package evolutionaryAlgorithmComponents;

import interfaces.Representation;

public abstract class AbstractRepresentation implements Representation{
	
	protected int dimensions;
	private final String title;
	
	public AbstractRepresentation(String title, int dimensions){
		this.dimensions = dimensions;
		this.title = title;
	}

	@Override
	public int getDimensions() {
		return dimensions;
	}
	
	/* (non-Javadoc)
	 * @see interfaces.EvolutionaryAlgorithmComponent#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	public abstract double genotypicDistance(Individual anIndividual0, Individual anIndividual1);
	
}

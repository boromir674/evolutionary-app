package evolutionaryAlgorithmComponents.representation;

import evolutionaryAlgorithmComponents.AbstractRepresentation;

public abstract class AbstractIntegerRepresentation extends AbstractRepresentation {

	protected int lowerBound;
	protected int upperBound;

	public AbstractIntegerRepresentation(String title, int dim) {
		super(title, dim);
		//lowerBound = low;
		//upperBound = up;
	}

	@Override
	public Object[] createEmptyChromosome() {
		return new Integer[dimensions];
	}

	public int[] getLowAndHigh(){
		return new int[]{lowerBound, upperBound};
	}

}

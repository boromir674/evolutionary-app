package evolutionaryAlgorithmComponents.representation;

import java.util.Random;

public abstract class BinaryRepresentation extends AbstractRepresentation {
	
	private final static String title = "Binary";
	
	public BinaryRepresentation(int dim) {
		super(title, dim);
	}

	@Override
	public Object[] generateRandomChromosome(Random aRandom) {
		Object[] chromosome = new Byte[dimensions];
		for (int i=0; i<dimensions; i++)
			chromosome[i] = Math.round(aRandom.nextDouble());
		return chromosome;
	}

	@Override
	public Object[] createEmptyChromosome() {
		return new Byte[dimensions];
	}

}

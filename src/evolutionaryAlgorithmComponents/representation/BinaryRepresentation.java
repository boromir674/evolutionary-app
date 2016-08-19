package evolutionaryAlgorithmComponents.representation;

import java.util.Random;

public abstract class BinaryRepresentation extends AbstractRepresentation {
	
	private final static String title = "Bit-string";
	
	public BinaryRepresentation(int dim) {
		super(title, dim);
	}

	@Override
	public Object[] generateRandomChromosome(Random aRandom) {
		Object[] chromosome = new Boolean[dimensions];
		for (int i=0; i<dimensions; i++)
			chromosome[i] = Math.round(aRandom.nextDouble());
		return chromosome;
	}

	@Override
	public Object[] createEmptyChromosome() {
		return new Boolean[dimensions];
	}

}

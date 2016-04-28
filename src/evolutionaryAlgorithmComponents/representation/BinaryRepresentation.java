package evolutionaryAlgorithmComponents.representation;

import java.util.Random;

import evolutionaryAlgorithmComponents.AbstractRepresentation;

public abstract class BinaryRepresentation extends AbstractRepresentation {
	
	public final static String title = "Bit-string";
	
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

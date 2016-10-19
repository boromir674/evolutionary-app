package evolutionaryAlgorithmComponents.variationOperators.mutation.realValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.AbstractRealValueRepresentation;
/**
 * Implemented with bitwise mutation probability
 * @author kostas
 *
 */
public class UniformMutation extends AbstractRealValueMutation {

	private static final String title = "Uniform"; // Uniform Mutation

	// Implemented with bitwise mutation probability
	public UniformMutation(double probabilityOfMutation) {
		super(title, probabilityOfMutation);
	}

	@Override
	public void apply(Individual anIndividual, Random aRandom) {
		int d = anIndividual.getRepresentation().getDimensions();
		for (int i=0; i<d; i++)
			if (aRandom.nextDouble() < super.getProbability())
				anIndividual.getChromosome()[i] = ((AbstractRealValueRepresentation) anIndividual.getRepresentation()).uniformSample(aRandom);
	}

}

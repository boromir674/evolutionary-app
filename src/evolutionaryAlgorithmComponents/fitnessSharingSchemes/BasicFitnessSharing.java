package evolutionaryAlgorithmComponents.fitnessSharingSchemes;

import evolutionaryAlgorithmComponents.AbstractFitnessSharingScheme;
import evolutionaryAlgorithmComponents.Individual;

public class BasicFitnessSharing extends AbstractFitnessSharingScheme {

	private final static String title = "Simple fitness sharing";

	public BasicFitnessSharing() {
		super(title);
	}

	@Override
	public double computeSharedFitness(Individual anIndividual) {

		int alpha = 1;
		for (int i=0; i<population.getMu()+population.getLambda(); i++) {
			double denominator = 0;
			for (int j=0; j<population.getMu()+population.getLambda(); j++) {
				double distance = ((AbstractRepresentation)representation).genotypicDistance(population.getPool()[i].getChromosome(), population.getPool()[i].getChromosome());
				if (distance <= 5)
					denominator += 1 - Math.pow(distance/5, alpha);
			}
			population.getPool()[i].fitness /= denominator;

			return 0;
		}

	}

}

package evolutionaryAlgorithmComponents.representation;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;

public class IntegerRepresentation extends AbstractIntegerRepresentation {
	
	private final static String title = "Integer";
	
	public IntegerRepresentation(int dim, int low, int up) {
		super(title, dim, low, up);
	}

	@Override
	public Object[] generateRandomChromosome(Random aRandom) {
		Object[] chromosome = new Integer[dimensions];
			for (int i=0; i<dimensions; i++)
				chromosome[i] = aRandom.nextInt(upperBound-lowerBound+1)+upperBound;
		return chromosome;
	}

	@Override
	public double genotypicDistance(Individual anIndividual0,
			Individual anIndividual1) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}

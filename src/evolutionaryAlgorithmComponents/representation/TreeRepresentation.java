package evolutionaryAlgorithmComponents.representation;

import java.util.Random;

import evolutionaryAlgorithmComponents.AbstractRepresentation;

public class TreeRepresentation extends AbstractRepresentation {
	
	private final static String title ="Tree";
	public TreeRepresentation(int dim) {
		super(title, dim);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object[] generateRandomChromosome(Random aRandom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] createEmptyChromosome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double genotypicDistance(Object[] chromosome, Object[] chromosome2) {
		// TODO Auto-generated method stub
		return 0;
	}

}

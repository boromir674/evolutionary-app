package evolutionaryAlgorithmComponents;

import java.util.Random;

import interfaces.FitnessSharingScheme;

public abstract class AbstractFitnessSharingScheme implements FitnessSharingScheme {
	
	private String title;
	
	public AbstractFitnessSharingScheme(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}
	//TODO check if abstract
	@Override
	public int[] select(Population aPopulation, Random aRandom)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

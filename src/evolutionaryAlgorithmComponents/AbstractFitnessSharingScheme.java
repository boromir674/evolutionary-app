package evolutionaryAlgorithmComponents;

import java.util.Random;

import util.Util;
import interfaces.EvolutionaryAlgorithmComponent;
import interfaces.FitnessCalculator;

public abstract class AbstractFitnessSharingScheme implements FitnessCalculator, EvolutionaryAlgorithmComponent {
	
	private String title;
	
	public AbstractFitnessSharingScheme(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}

}

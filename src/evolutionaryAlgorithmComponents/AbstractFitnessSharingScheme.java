package evolutionaryAlgorithmComponents;

import interfaces.EvolutionaryAlgorithmComponent;
import interfaces.FitnessCalculator;

public abstract class AbstractFitnessSharingScheme implements FitnessCalculator, EvolutionaryAlgorithmComponent {
	
	private String title;
	
	public AbstractFitnessSharingScheme(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}

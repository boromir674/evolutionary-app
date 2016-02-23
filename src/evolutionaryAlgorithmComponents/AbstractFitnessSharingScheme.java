package evolutionaryAlgorithmComponents;

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
}

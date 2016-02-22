package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import evolutionaryAlgorithmComponents.AbstractSurvivorSelection;
import evolutionaryAlgorithmComponents.Population;

public class MuCommaLambdaWithElitism extends MuCommaLambda {

	public MuCommaLambdaWithElitism() {
		super();
	}

	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.AbstractSurvivorSelection#getTitle()
	 */
	@Override
	public String getTitle() {
		return super.getTitle() + " with elitism";
	}

	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.survivorSelectionMechanisms.MuCommaLambda#forceElitism()
	 */
	@Override
	public boolean forceElitism() {
		return true;
	}

}

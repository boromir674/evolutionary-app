package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

public class MuCommaLambdaWithElitism extends MuCommaLambda {

	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.survivorSelectionMechanisms.MuCommaLambda#isElitist()
	 */
	@Override
	public boolean isElitist() {
		return true;
	}

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

package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Random;

public class RoundRobinTournamentSelectionWithElitism extends RoundRobinTournamentSelection {
	
	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.survivorSelectionMechanisms.RoundRobinTournamentSelection#isElitist()
	 */
	@Override
	public boolean isElitist() {
		return true;
	}

	public RoundRobinTournamentSelectionWithElitism() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.survivorSelectionMechanisms.RoundRobinTournamentSelection#forceElitism()
	 */
	@Override
	public boolean forceElitism() {
		return true;
	}

	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.AbstractSurvivorSelection#getTitle()
	 */
	@Override
	public String getTitle() {
		return super.getTitle() + " with elitism";
	}

}

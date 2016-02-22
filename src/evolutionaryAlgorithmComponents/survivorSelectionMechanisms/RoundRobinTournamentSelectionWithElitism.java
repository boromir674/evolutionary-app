package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Random;

public class RoundRobinTournamentSelectionWithElitism extends RoundRobinTournamentSelection {
	
	public RoundRobinTournamentSelectionWithElitism(Random aRandom) {
		super(aRandom);
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

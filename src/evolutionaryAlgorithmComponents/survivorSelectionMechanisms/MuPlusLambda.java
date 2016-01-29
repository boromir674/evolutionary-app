package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import evolutionaryAlgorithmComponents.AbstractSurvivorSelection;
import evolutionaryAlgorithmComponents.Population;

public class MuPlusLambda extends AbstractSurvivorSelection {
	
	private final static String title = "(μ+λ)";
	
	public MuPlusLambda() {
		super(title);
	}

	@Override
	public void select(Population pop) throws Exception {
		Arrays.sort(pop.getPool());
		super.maxIndex = 0;
	}
}
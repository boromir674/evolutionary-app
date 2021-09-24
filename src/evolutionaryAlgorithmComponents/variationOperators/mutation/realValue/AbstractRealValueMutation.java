package evolutionaryAlgorithmComponents.variationOperators.mutation.realValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.variationOperators.mutation.AbstractMutation;

public abstract class AbstractRealValueMutation extends AbstractMutation {
	
	protected final static double epsilon = 0.001;
	
	public AbstractRealValueMutation(String title, double probabilityOfMutation) {
		super(title, probabilityOfMutation);
	}

	@Override
	public boolean applicableToDiscrete() {
		return false;
	}

}

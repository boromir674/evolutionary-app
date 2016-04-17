package evolutionaryAlgorithmComponents.parentSelectionMechanisms;

import java.util.Random;

import evolutionaryAlgorithmComponents.AbstractParentSelection;
import evolutionaryAlgorithmComponents.Population;

public class BestNFromRandomM extends AbstractParentSelection {
	
	private final static String title = "Best n from Random m";
	
	public BestNFromRandomM(int M) {
		super(title);
		//this.M = M;
	}
	//TODO
	@Override
	public int[] select(Population aPopulation, Random aRandom) {
		// TODO Auto-generated method stub
		return null;
	}

}

package evolutionaryAlgorithmComponents.variationOperators.mutation.permutation;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;

public class InsertMutation extends AbstractPermutationMutation {

	private final static String title = "Insert Mutation";

	public InsertMutation(double probability) {
		super(title, probability);
	}

	@Override
	public void apply(Individual in, Random aRandom) {
		if (aRandom.nextDouble() < super.getProbability()){
			int who;
			int where;
			int d = in.getRepresentation().getDimensions();

			who = aRandom.nextInt(d);
			where = aRandom.nextInt(d-1);
			if (where>=who)
				where ++;
			//util.Util.insert(( (in.getChromosome())), who, where);
			if (who != where+1)
				if (where < who){
					int temp = (int) in.getChromosome()[where+1];
					in.getChromosome()[where+1] = in.getChromosome()[who];
					for (int i=where+2; i<who+1; i++){	
						int temp2 = (int) in.getChromosome()[i];
						in.getChromosome()[i] = temp;
						temp = temp2;
					}
				}
				else {
					int temp = (int) in.getChromosome()[who];
					for (int i=who; i<where; i++)
						in.getChromosome()[i] = in.getChromosome()[i+1];
					in.getChromosome()[where] = temp;
				}
		}	
	}
}

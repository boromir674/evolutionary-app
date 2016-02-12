package evolutionaryAlgorithmComponents.evaluation.permutation.TSP;

import util.TSPReader;
import evolutionaryAlgorithmComponents.Individual;
import interfaces.DistanceCalculator;
import interfaces.TSP;

/**
 * Symmetric traveling salesman problem (TSP)
 */
public class TSPEvaluation extends AbstractTSPLIBEvaluation implements TSP{

	private final static String title = "Symmetric traveling salesman problem";

	public TSPEvaluation(TSPReader aTSPReader, DistanceCalculator aDistanceCalculator) {
		super(aTSPReader, aDistanceCalculator, title);
	}

	@Override
	public double computeFitness(Individual in) throws Exception {
		int d = in.getRepresentation().getDimensions();
		double fitness = 0;
		for (int i=0; i<d; i++)
			fitness -= this.distance((int)in.getChromosome()[i], (int)in.getChromosome()[(i+1)%d]);
		super.evaluationsUsed ++;
		if (super.bestScoreEncountered < fitness)
			super.bestScoreEncountered = fitness;
		return fitness;
	}

	@Override
	public int distance(int node1, int node2){
		//super.checkNodes(node1, node2);
		if (super.myDistanceCalculator == null) {
			if (super.fullMatrixFlag)
				return super.fullMatrixDistance(node1, node2);
			else
				return (int) super.vector[this.linearIndex(node1-1, node2-1)];
		}
		else 
			return super.myDistanceCalculator.calculateDistance(matrix[node1-1], matrix[node2-1]);
	}

	@Override
	public double getSolutionFitness() {
		Integer[] vector = (Integer[])super.getSolutionVector();
		double fitness = 0;
		for (int i=0; i<vector.length; i++)
			fitness += this.distance(vector[i], vector[(i+1)%vector.length]);
		return -fitness;
	}

}

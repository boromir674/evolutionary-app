package evolutionaryAlgorithmComponents.evaluation.permutation;

import util.TSPReader;
import interfaces.DistanceCalculator;
import interfaces.TSP;
import evolutionaryAlgorithmComponents.AbstractEvaluationMethod;
import evolutionaryAlgorithmComponents.Individual;

/**
 * Symmetric traveling salesman problem (TSP)
 */
public class TSPEvaluation extends AbstractTSPLIBEvaluation implements TSP{

	private final static String title = "Symmetric traveling salesman problem";

	public TSPEvaluation(TSPReader aTSPReader, DistanceCalculator aDistanceCalculator) {
		super(aTSPReader, aDistanceCalculator, title);
	}

	/* (non-Javadoc)
	 * @see interfaces.EvaluationMethod#computeFitness(evolutionaryAlgorithmComponents.population.Individual)
	 */
	@Override
	public double computeFitness(Individual in) {
		int d = in.getRepresentation().getDimensions();
		double fitness = Double.MIN_VALUE;
		int i = 0;
		while (i<d){
			try {
				fitness += this.distance((int)in.getChromosome()[i], (int)in.getChromosome()[(i+1)%d]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
		super.evaluationsUsed ++;
		return -fitness;
	}

	@Override
	public int distance(int node1, int node2) throws Exception {
		super.checkNodes(node1, node2);
		if (super.c == null) {
			if (super.fullMatrixFlag)
				return super.fullMatrixDistance(node1, node2);
			else
				return (int) super.vector[this.linearIndex(node1-1, node2-1)];
		}
		else 
			return super.c.calculateDistance(matrix[node1-1], matrix[node2-1]);
	}

}

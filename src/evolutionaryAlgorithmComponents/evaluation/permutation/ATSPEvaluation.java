package evolutionaryAlgorithmComponents.evaluation.permutation;

import util.TSPReader;
import interfaces.ATSP;
import interfaces.DistanceCalculator;
import evolutionaryAlgorithmComponents.Individual;

/**
 * Asymmetric traveling salesman problem (TSP)
 */
public class ATSPEvaluation extends AbstractTSPLIBEvaluation implements ATSP{

	private final static String title = "Asymmetric traveling salesman problem";

	ATSPEvaluation(TSPReader aTSPReader,	DistanceCalculator aDistanceCalculator) {
		super(aTSPReader, aDistanceCalculator, title);
	}

	@Override
	public double computeFitness(Individual in) {
		int d = in.getRepresentation().getDimensions();
		double fitness = 0;
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

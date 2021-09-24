package evolutionaryAlgorithmComponents.evaluation.permutation.TSP;

import util.TSPReader;
import interfaces.DistanceCalculator;
import interfaces.HCP;
import evolutionaryAlgorithmComponents.Individual;

/**
 * Hamiltonian cycle problem (HCP).
 */
public class HCPEvaluation extends AbstractTSPLIBEvaluation implements HCP{

	private final static String title = "Hamiltonian cycle problem";
	private double[][] edgeList;
	HCPEvaluation(TSPReader aTSPReader, DistanceCalculator aDistanceCalculator) {
		super(aTSPReader, aDistanceCalculator, title);
		edgeList = aTSPReader.get2DDataArray();
	}

	@Override
	public boolean connected(int node1, int node2) throws Exception {
		super.checkNodes(node1, node2);		
		for (int i=0; i<edgeList.length; i++) {
			if (edgeList[i][0] == node1){
				if (edgeList[i][1] == node2)
					return true;
			} else if (edgeList[i][0] == node2){
				if (edgeList[i][1] == node1)
					return true;
			}
		}
		return false;
	}

	@Override
	public double getSolutionFitness() {
		Integer[] vector = (Integer[])super.getSolutionVector();
		double fitness = 0;
		for (int i=0; i<vector.length; i++){
			try {
				if (!connected(vector[i], vector[(i+1)%vector.length]))
					fitness += 1; // penalty for non-connectivity
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		return -fitness;
	}

	@Override
	protected double calculateFitness(Individual anIndividual) {
		int d = anIndividual.getRepresentation().getDimensions();
		double fitness = 0;
		for (int i=0; i<d; i++){
			try {
				if (!connected((int)anIndividual.getChromosome()[i], (int)anIndividual.getChromosome()[(i+1)%d]))
					fitness -= 1; // penalty for non-connectivity
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		return fitness;
	}

}


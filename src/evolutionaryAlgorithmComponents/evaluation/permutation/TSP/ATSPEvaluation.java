package evolutionaryAlgorithmComponents.evaluation.permutation.TSP;

import interfaces.DistanceCalculator;
import evolutionaryAlgorithmComponents.Individual;
import exceptions.UnknownSolutionException;

/**
 * Asymmetric traveling salesman problem (TSP)
 */
public class ATSPEvaluation extends AbstractTSPLIBEvaluation {

	private final static String title = "Asymmetric traveling salesman problem";

	public ATSPEvaluation(TSPReader aTSPReader,	DistanceCalculator aDistanceCalculator) {
		super(aTSPReader, aDistanceCalculator, title);
	}

	public int distance(int node1, int node2){
/*		if (super.myDistanceCalculator == null) {
			if (super.fullMatrixFlag)
				return super.fullMatrixDistance(node1, node2);
			else		// TODO Auto-generated method stub
				return (int) super.vector[this.linearIndex(node1-1, node2-1)];
		}
		else 
			return super.myDistanceCalculator.calculateDistance(matrix[node1-1], matrix[node2-1]);*/
		return super.fullMatrixDistance(node1, node2);
	}

	@Override
	public double getSolutionFitness(int dimensionality) throws UnknownSolutionException {
		Integer[] vector = (Integer[])super.getSolutionVector(dimensionality);
		double fitness = 0;
		for (int i=0; i<vector.length; i++)
			fitness += this.distance(vector[i], vector[(i+1)%vector.length]);
		return -fitness;
	}

	@Override
	protected double calculateFitness(Individual anIndividual) {
		int d = anIndividual.getRepresentation().getDimensions();
		double fitness = 0;
		for (int i=0; i<d; i++)
			fitness -= this.distance((int)anIndividual.getChromosome()[i], (int)anIndividual.getChromosome()[(i+1)%d]);
		return fitness;
	}


}

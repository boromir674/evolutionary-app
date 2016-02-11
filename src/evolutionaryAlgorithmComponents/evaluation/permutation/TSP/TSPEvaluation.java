package evolutionaryAlgorithmComponents.evaluation.permutation.TSP;

import util.TSPReader;
import interfaces.DistanceCalculator;
import interfaces.TSP;
import evolutionaryAlgorithmComponents.AbstractEvaluationMethod;
import evolutionaryAlgorithmComponents.Individual;
import exceptions.NoKnownSolutionException;

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
	public double computeFitness(Individual in) throws Exception {
		super.evaluationsUsed ++;
		double fitness = this.calculateFitness((Integer[]) in.getChromosome());
		return fitness;
	}

	@Override
	public int distance(int node1, int node2) throws Exception {
		super.checkNodes(node1, node2);
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
	public double getSolutionFitness() throws Exception {
		Integer[] chromosome = (Integer[]) super.getSolutionVector();
		return calculateFitness(chromosome);
	}
	
	private double calculateFitness(Integer[] chromosome) throws Exception{
		double fitness = 0;
		for (int i=0; i<chromosome.length; i++)
			fitness -= this.distance(chromosome[i], chromosome[(i+1)%chromosome.length]);
		return fitness;
	}

}

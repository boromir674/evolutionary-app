package evolutionaryAlgorithmComponents.variationOperators.mutation.realValue;

import java.util.Random;

import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.linear.SingularMatrixException;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.RealValueRepresentation;

public class CorrelatedMutations extends AbstractRealValueMutation {

	private final static String title = "Correlated Mutation";
	private static double tPrime;
	private static double t;
	private static double b = 5;

	public CorrelatedMutations(double probabilityOfMutation) {
		super(title, probabilityOfMutation);
	}

	@Override
	public void apply(Individual in, Random aRandom) throws Exception {
		int d = in.getRepresentation().getDimensions();
		if (2*d+(d*(d-1))/2 != in.getChromosome().length){
			System.out.println("dimensions:"+d);
			System.out.println("length:"+in.getChromosome().length);
			System.out.println("dimensions+1 should equal to length");
			System.exit(0);
		}
		if (aRandom.nextDouble() < super.getProbability()){
			initializeConstants(d);
			double N = aRandom.nextGaussian(); // N(0,1)

			double newSigmaI;
			double newXI;	

			// mutate sigma values
			for (int i=d; i<2*d; i++){
				double N_i = aRandom.nextGaussian();
				newSigmaI = (double) in.getChromosome()[i] * Math.exp(tPrime*N + t*N_i);
				if (newSigmaI < epsilon) // check if sigma'<epsilon
					newSigmaI = epsilon;
				in.getChromosome()[i] = newSigmaI;
			}

			// mutate alpha values
			for (int j=2*d; j<in.getChromosome().length; j++)
				in.getChromosome()[j] = (double) in.getChromosome()[j] + b * aRandom.nextGaussian();

			// build covariance matrix S
			double[][] covariances = buildCovarianceMatrix
					(getAlphas(d, (Double[])(in.getChromosome())), getSigmas(d, (Double[])in.getChromosome()), d);

			// mutate x values checking if covariance matrix is singular (usually first instant of it is singular)
			try {
				MultivariateNormalDistribution g = new MultivariateNormalDistribution(new double[d], covariances);
				// mutate x values
				double[] sample = g.sample();
				for (int i=0; i<d; i++){
					newXI = (double) in.getChromosome()[i] + sample[i];
					in.getChromosome()[i] = ((RealValueRepresentation)in.getRepresentation()).ensureValueRange(newXI);
				}
			} catch (SingularMatrixException e) {
				for (int i=0; i<d; i++){
					double sum = 0;
					for (int j=0; j<d; j++)
						sum += covariances[i][j]*(double)in.getChromosome()[j];
					in.getChromosome()[i] = ((RealValueRepresentation)in.getRepresentation()).ensureValueRange(sum);
				}
			}
		}

		double low = ((RealValueRepresentation) in.getRepresentation()).getLowestValue();
		double high = ((RealValueRepresentation) in.getRepresentation()).getHighestValue();
		for (int i=0; i<in.getRepresentation().getDimensions(); i++){
			if ((Double)in.getChromosome()[i] < low || (Double)in.getChromosome()[i] > high){
				throw new Exception("Value out of range");
			}
		}
	}

	private static double[] getAlphas(int dim, Double[] chromosome){
		double[] a = new double[dim*(dim-1)/2];
		for (int i=0; i<a.length; i++)
			a[i] = chromosome[2*dim+i];
		return a;
	}

	private static double[] getSigmas(int dim, Double[] chromosome){
		double[] s = new double[dim];
		for (int i=0; i<s.length; i++)
			s[i] = chromosome[dim+i];
		return s;
	}

	private static void initializeConstants(int dimensions) {
		if (t == 0){
			tPrime = 1/Math.sqrt(2*dimensions);
			t = 1/Math.sqrt(2*Math.sqrt(dimensions));
		}
	}

	private static double[][] buildCovarianceMatrix(double[] alphas, double[] sigmas, int dim){
		double[][] covMatrix = new double[dim][dim];
		// diagonal
		for (int i=0; i<dim; i++)
			covMatrix[i][i] = sigmas[i]*sigmas[i];
		int ai = 0;
		for (int i=0; i<covMatrix.length; i++){
			for (int j=i+1; j<covMatrix[0].length; j++){
				covMatrix[i][j] = (1/2)*(sigmas[i]*sigmas[i]-sigmas[j]*sigmas[j]) * Math.tan(alphas[ai]);// above diagonal
				ai ++;
				covMatrix[j][i] = covMatrix[i][j]; // below diagonal
			}
		}
		return covMatrix;
	}

}

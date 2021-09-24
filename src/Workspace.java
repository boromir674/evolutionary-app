import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;
import interfaces.TSPLIBProblem;
import interfaces.TerminationCondition;
import interfaces.EvaluationMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation;

import simulationComponents.*;
import simulationComponents.terminationConditions.*;
import util.TSPReader;
import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;
import evolutionaryAlgorithmComponents.evaluation.permutation.*;
import evolutionaryAlgorithmComponents.representation.*;
import evolutionaryAlgorithmComponents.survivorSelectionMechanisms.*;
import evolutionaryAlgorithmComponents.variationOperators.VarianceOperator;
import evolutionaryAlgorithmComponents.variationOperators.mutation.permutation.*;
import evolutionaryAlgorithmComponents.variationOperators.mutation.realValue.*;
import evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue.*;
import evolutionaryAlgorithmComponents.parentSelectionMechanisms.*;

public class Workspace {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {

		// SETTINGS
		Random rand = new Random();
		rand.setSeed(4);
		int dim = 5;
		double low = -10;
		double high = 10;
		double sigma = 4;
		int mu = 40;
		int lambda = 20;
		double mutationRate = 0.99;
		int evalLimit = 10000000;
		int genLimit = 50000;
		double alpha = 5;
		// --------------------------------------------
		
		//String cf = System.getProperty("user.dir") + "/TSP_samples/HCP";
		//File f = new File(cf);
		//ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));

		//TSPReader ar = new TSPReader();
		//ar.parseInfo("swiss42.tsp");
		/*for (String s: names) {
			String temp = s.substring(s.length()-4, s.length());
			if (!(temp.equals("tour") || temp.charAt(temp.length()-1) == 'c')) {
				try{
					ar.parseFile(s);
					System.out.println("File: " + s + " processed successfuly");
				} catch (Exception e){
					e.printStackTrace();
					System.out.println("PROBLEM AT: " + s);
				}
			}
		}*/

		// DESIGN
		TSPProblemFactory factory = new TSPProblemFactory();
		//Representation myRep = new OneSigma(low, high, sigma, dim);
		//Representation myRep = new OneSigmaPerIndividual(low, high, sigma, dim);
		//Representation myRep = new MultipleSigmasRepresentation(low, high, sigma, dim);
		//Representation myRep = new MultipleSigmasWithAlphasRepresentation(low, high, sigma, alpha, dim);
		EvaluationMethod myEval = factory.produceTSPProblem("bayg29.tsp");
		Representation myRep = new PermutationRepresentation(((TSPLIBProblem) myEval).getNumberOfNodes(), 1, ((TSPLIBProblem) myEval).getNumberOfNodes());
		Population myPopulation = new Population(mu, lambda);
		ParentSelection parentSelector = new evolutionaryAlgorithmComponents.parentSelectionMechanisms.FitnessProportional();
		Recombination rec = new OrderCrossover(rand);
		
		//Mutation mut = new UncorrelatedWithOneStepSize(mutationRate);
		//Mutation mut = new UncorrelatedWithNStepSizes(mutationRate);
		Mutation mut = new evolutionaryAlgorithmComponents.variationOperators.mutation.permutation.InsertMutation(mutationRate);

		VarianceOperator myVar = new VarianceOperator(rec, mut);
		SurvivorSelection surv = new evolutionaryAlgorithmComponents.survivorSelectionMechanisms.MuCommaLambda();
		EvolutionaryAlgorithm myEvo = new EvolutionaryAlgorithm(myRep, myEval, myPopulation, parentSelector, myVar, surv);

		TerminationCondition term = new GenerationsLimitTerminationCondition(genLimit);
		Experiment exp1 = new Experiment(myEvo, term, rand);
		
		Individual solution = exp1.performOptimizationTask();
		
		System.out.format("%.4f%n", solution.getFitness());
		solution.printChromosome();
		
		
		int[] sol = new int[]{1,28,6,12,9,26,3,29,5,21,2,20,10,4,15,18,14,17,22,11,19,25,7,23,8,27,16,13,24};
		Individual min = new Individual();
		min.initializeEmpty(myRep);
		for (int i=0;i<sol.length;i++)
			min.getChromosome()[i] = sol[i];
		min.computeFitness(myEval);
		min.printChromosome();
		System.out.format("min dist: %.4f%n", min.getFitness());
		
		//System.out.println(solution.getChromosome().length);
		//System.out.println(solution.getFitness());
		//double[] res = exp1.runBatches(100);
		//System.out.println(res[0]);
		//System.out.println(res[1]);
	}
}

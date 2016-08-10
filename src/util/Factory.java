/**
 * A class that contains a variety of static factory methods
 */
package util;

import evolutionaryAlgorithmComponents.evaluation.permutation.TSP.ATSPEvaluation;
import evolutionaryAlgorithmComponents.evaluation.permutation.TSP.HCPEvaluation;
import evolutionaryAlgorithmComponents.evaluation.permutation.TSP.TSPEvaluation;
import evolutionaryAlgorithmComponents.evaluation.permutation.TSP.TSPReader;
import evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators.CeilingEuclidean;
import evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators.Euclidean;
import evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators.GeographicalDistance;
import evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators.ManhattanDistance;
import evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators.Maximum;
import evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators.PseudoEuclidean;
import evolutionaryAlgorithmComponents.representation.MultipleSigmasRepresentation;
import evolutionaryAlgorithmComponents.representation.MultipleSigmasWithAlphasRepresentation;
import evolutionaryAlgorithmComponents.representation.OneSigmaPerIndividual;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;
import exceptions.FailedToParseException;
import interfaces.DistanceCalculator;
import interfaces.EvaluationMethod;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;
import interfaces.TerminationCondition;

/**
 * @author Konstantinos
 *
 */
public final class Factory {

	public final static EvaluationMethod getEvaluationMethod(String anEvaluationName) throws FailedToParseException{
		EvaluationMethod evalMethod = null;
		int offset = 0;
		String s = "evolutionaryAlgorithmComponents.evaluation.mathFunctions.";
		if (anEvaluationName.endsWith(".java")) {
			offset = 5;
			try {
				evalMethod = (EvaluationMethod) Class.forName(s+anEvaluationName.substring(0, anEvaluationName.length()-offset)).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} else {
			try {
				evalMethod = produceTSPProblem(anEvaluationName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return evalMethod;
	}

	public final static ParentSelection getParentSelection(String aParentSelectionName) throws Exception {
		//No fitness sharing scheme supported yet
		int offset = 0;
		if (aParentSelectionName.endsWith(".java"))
			offset = 5;
		String s = "evolutionaryAlgorithmComponents.parentSelectionMechanisms.";
		return (ParentSelection) Class.forName(s+aParentSelectionName.substring(0, aParentSelectionName.length()-offset)).getConstructor().newInstance();
	}

	public final static SurvivorSelection getSurvivorSelection(String aSurvivorSelectionName) throws Exception {
		int offset = 0;
		if (aSurvivorSelectionName.endsWith(".java"))
			offset = 5;
		String s = "evolutionaryAlgorithmComponents.survivorSelectionMechanisms.";
		return (SurvivorSelection) Class.forName(s+aSurvivorSelectionName.substring(0, aSurvivorSelectionName.length()-offset)).getConstructor().newInstance();
	}

	// ------------ CROSSOVER FACTORY --------------------------
	public final static Recombination getCrossoverOperator(String aRecombinationName) throws Exception{
		int offset = 0;
		if (aRecombinationName.endsWith(".java"))
			offset = 5;
		String s1 = "evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue.";
		if (aRecombinationName.startsWith("Simple") || aRecombinationName.startsWith("Single") || aRecombinationName.startsWith("Whole"))
			s1 = "evolutionaryAlgorithmComponents.variationOperators.recombination.realValue.";
		return (Recombination) Class.forName(s1+aRecombinationName.substring(0, aRecombinationName.length()-offset)).getConstructor().newInstance();
	}
	public final static Recombination getCrossoverOperator(String aRecombinationName, int n) throws Exception{
		int offset = 0;
		if (aRecombinationName.endsWith(".java"))
			offset = 5;
		String s1 = "evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue.";
		if (aRecombinationName.startsWith("Simple") || aRecombinationName.startsWith("Single") || aRecombinationName.startsWith("Whole"))
			s1 = "evolutionaryAlgorithmComponents.variationOperators.recombination.realValue.";
		return (Recombination) Class.forName(s1+aRecombinationName.substring(0, aRecombinationName.length()-offset)).getConstructor(int.class).newInstance(n);
	}

	// ------------ MUTATION FACTORY --------------------------	
	public final static Mutation getMutationOperator(String aMutationName, double prob) throws Exception{
		int offset = 0;
		if (aMutationName.endsWith(".java"))
			offset = 5;
		String s1 = "evolutionaryAlgorithmComponents.variationOperators.mutation.discreteValue.";
		if (aMutationName.startsWith("Bitwise"))
			s1 = "evolutionaryAlgorithmComponents.variationOperators.mutation.";
		else if (aMutationName.startsWith("CorrelatedM") || aMutationName.startsWith("NonuniformM") || aMutationName.startsWith("UncorrelatedW") || aMutationName.startsWith("UniformM"))
			s1 = "evolutionaryAlgorithmComponents.variationOperators.mutation.realValue.";	
		return (Mutation) Class.forName(s1+aMutationName.substring(0, aMutationName.length()-offset)).getConstructor(double.class).newInstance(prob);
	}

	// SUPPORTED : RealValue (1σ, n-σ,..) and Permutation
	public final static Representation getRepresentation(String aTSPSampleProblem){
		//String s = "evolutionaryAlgorithmComponents.representation.";
		String s = util.Util.parseDimensions(aTSPSampleProblem);
		int dimensionality = Integer.parseInt(s);
		return new PermutationRepresentation(dimensionality, 1, dimensionality);
	}
	public final static Representation getRepresentation(String aMutation, String dimensions){
		//TODO enable more user input through the interface
		//String s = "evolutionaruAlgorithmComponents.representation.";
		Representation representationInstance = null;
		if (aMutation.startsWith("UncorrelatedWithNStepSizes"))
			representationInstance = new MultipleSigmasRepresentation(0, 10, 2, Integer.parseInt(dimensions));
		else if (aMutation.startsWith("UncorrelatedWithOneStepSize"))
			representationInstance = new OneSigmaPerIndividual(0, 10, 2, Integer.parseInt(dimensions));
		else if (aMutation.startsWith("Correlated"))
			representationInstance = new MultipleSigmasWithAlphasRepresentation(0, 10, 2, 0.5, Integer.parseInt(dimensions));
		//else
			//representationInstance = new AbstractRealValueRepresentation(0, 10, 2, Integer.parseInt(dimensions));
		return representationInstance;
	}

	public final static TerminationCondition getTerminationCondition(String aTerminationCondition, String numericalParameter) throws Exception{
		int offset = 0;
		if (aTerminationCondition.endsWith(".java"))
			offset = 5;
		String s = "simulationComponents.terminationConditions.";
		return (TerminationCondition) Class.forName(s+aTerminationCondition.substring(0, aTerminationCondition.length()-offset)).getConstructor(String.class).newInstance(numericalParameter);
	}
	
	private static EvaluationMethod produceTSPProblem(String path) throws Exception {
		TSPReader reader = new TSPReader();
		reader.parseFile(path);
		EvaluationMethod problem = null;
		DistanceCalculator calculator = null;

		if (reader.getEdgeWeightType().equals("EUC_2D") || reader.getEdgeWeightType().equals("EUC_3D"))
			calculator = new Euclidean();
		else if (reader.getEdgeWeightType().equals("MAX_2D") || reader.getEdgeWeightType().equals("MAX_3D"))
			calculator = new Maximum();
		else if (reader.getEdgeWeightType().equals("MAN_2D") || reader.getEdgeWeightType().equals("MAN_3D"))
			calculator = new ManhattanDistance();
		else if (reader.getEdgeWeightType().equals("GEO"))
			calculator = new GeographicalDistance();
		else if (reader.getEdgeWeightType().equals("ATT"))
			calculator = new PseudoEuclidean();
		else if (reader.getEdgeWeightType().equals("CEIL_2D"))
			calculator = new CeilingEuclidean();

		if (reader.getType().equals("TSP"))
			problem = new TSPEvaluation(reader, calculator);
		else if (reader.getType().equals("HCP"))
			problem = new HCPEvaluation(reader, calculator);
		else if (reader.getType().equals("ATSP"))
			problem = new ATSPEvaluation(reader, calculator);
		else if (reader.getEdgeWeightType().equals("SOP")){
			//TODO problem = new SOPEvaluation(reader, calculator);
		}
		else if (reader.getEdgeWeightType().equals("CVRP")) {
			//TODO problem = new CVRPEvaluation(reader, calculator);
		}
		return problem;
	}

}

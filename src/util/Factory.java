/**
 * A class that contains a variety of static factory methods
 */
package util;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import evolutionaryAlgorithmComponents.evaluation.permutation.TSP.TSPProblemFactory;
import evolutionaryAlgorithmComponents.representation.MultipleSigmasRepresentation;
import evolutionaryAlgorithmComponents.representation.MultipleSigmasWithAlphasRepresentation;
import evolutionaryAlgorithmComponents.representation.OneSigmaPerIndividual;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;
import evolutionaryAlgorithmComponents.representation.RealValueRepresentation;
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

	public final static EvaluationMethod getEvaluationMethod(String anEvaluationName) throws Exception{
		int offset = 0;
		if (anEvaluationName.endsWith(".java"))
			offset = 5;
		String s = "evolutionaryAlgorithmComponents.evaluation.realValueFunction.";
		if (anEvaluationName.endsWith(".java"))
			return (EvaluationMethod) Class.forName(s+anEvaluationName.substring(0, anEvaluationName.length()-offset)).newInstance();
		else {
			TSPProblemFactory fac = new TSPProblemFactory();
			return fac.produceTSPProblem(anEvaluationName);
		}
	}

	public final static ParentSelection getParentSelection(String aParentSelectionName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		//No fitness sharing scheme supported yet
		int offset = 0;
		if (aParentSelectionName.endsWith(".java"))
			offset = 5;
		String s = "evolutionaryAlgorithmComponents.parentSelectionMechanisms.";
		return (ParentSelection) Class.forName(s+aParentSelectionName.substring(0, aParentSelectionName.length()-offset)).getConstructor().newInstance();
	}

	public final static SurvivorSelection getSurvivorSelection(String aSurvivorSelectionName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		int offset = 0;
		if (aSurvivorSelectionName.endsWith(".java"))
			offset = 5;
		String s = "evolutionaryAlgorithmComponents.survivorSelectionMechanisms.";
		return (SurvivorSelection) Class.forName(s+aSurvivorSelectionName.substring(0, aSurvivorSelectionName.length()-offset)).getConstructor().newInstance();
	}
	
	// ------------ CROSSOVER FACTORY --------------------------
	public final static Recombination getCrossoverOperator(String aRecombinationName) throws ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException{
		int offset = 0;
		if (aRecombinationName.endsWith(".java"))
			offset = 5;
		String s1 = "evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue.";
		if (aRecombinationName.startsWith("Simple") || aRecombinationName.startsWith("Single") || aRecombinationName.startsWith("Whole"))
			s1 = "evolutionaryAlgorithmComponents.variationOperators.recombination.realValue.";
		return (Recombination) Class.forName(s1+aRecombinationName.substring(0, aRecombinationName.length()-offset)).getConstructor().newInstance();
	}
	public final static Recombination getCrossoverOperator(String aRecombinationName, int n) throws ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException{
		int offset = 0;
		if (aRecombinationName.endsWith(".java"))
			offset = 5;
		String s1 = "evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue.";
		if (aRecombinationName.startsWith("Simple") || aRecombinationName.startsWith("Single") || aRecombinationName.startsWith("Whole"))
			s1 = "evolutionaryAlgorithmComponents.variationOperators.recombination.realValue.";
		return (Recombination) Class.forName(s1+aRecombinationName.substring(0, aRecombinationName.length()-offset)).getConstructor(int.class).newInstance(n);
	}
	
	// ------------ MUTATION FACTORY --------------------------	
	public final static Mutation getMutationOperator(String aMutationName, double prob) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		int offset = 0;
		if (aMutationName.endsWith(".java"))
			offset = 5;
		String s1 = "evolutionaryAlgorithmComponents.variationOperators.mutation.discreteValue.";
		if (aMutationName.startsWith("Bitwise"))
			s1 = "evolutionaryAlgorithmComponents.variationOperators.";
		else if (aMutationName.startsWith("CorrelatedM") || aMutationName.startsWith("NonuniformM") || aMutationName.startsWith("UncorrelatedW") || aMutationName.startsWith("UniformM"))
			s1 = "evolutionaryAlgorithmComponents.variationOperators.mutation.realValue.";	
		return (Mutation) Class.forName(s1+aMutationName.substring(0, aMutationName.length()-offset)).getConstructor(double.class).newInstance(prob);
	}
	
	// SUPPORTED : RealValue and children + Permutation
	public final static Representation getRepresentation(String aTSPSampleProblem){
		//String s = "evolutionaryAlgorithmComponents.representation.";
		int dimensionality = Integer.parseInt(util.Util.parseDimensions(aTSPSampleProblem));
		return new PermutationRepresentation(dimensionality, 1, dimensionality);
	}
	public final static Representation getRepresentation(String aMutation, String dimensions){
		//TODO enable more user input through the interface
		//String s = "evolutionaruAlgorithmComponents.representation.";
		Representation representationInstance = null;
		if (aMutation.startsWith("UncorrelatedWithNStep"))
			representationInstance = new MultipleSigmasRepresentation(0, 10, 2, Integer.parseInt(dimensions));
		else if (aMutation.startsWith("UncorrelatedWithOneStep"))
			representationInstance = new OneSigmaPerIndividual(0, 10, 2, Integer.parseInt(dimensions));
		else if (aMutation.startsWith("Correlated"))
			representationInstance = new MultipleSigmasWithAlphasRepresentation(0, 10, 2, 0.5, Integer.parseInt(dimensions));
		else
			representationInstance = new RealValueRepresentation(0, 10, 2, Integer.parseInt(dimensions));
		return representationInstance;
	}

	public final static TerminationCondition getTerminationCondition(String aTerminationCondition, String numericalParameter) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		int offset = 0;
		if (aTerminationCondition.endsWith(".java"))
			offset = 5;
		String s = "simulationComponents.terminationConditions.";
		return (TerminationCondition) Class.forName(s+aTerminationCondition.substring(0, aTerminationCondition.length()-offset)).getConstructor(String.class).newInstance(numericalParameter);
	}

}

/**
 * A class that contains a variety of static factory methods
 */
package util;

import evolutionaryAlgorithmComponents.evaluation.permutation.TSP.TSPProblemFactory;
import interfaces.EvaluationMethod;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;

/**
 * @author Konstantinos
 *
 */
public final class HyperFactory {

	public final static EvaluationMethod getEvaluationMethod(String fileName) throws Exception{
		String s = "evolutionaryAlgorithmComponents.evaluation.realValueFunction.";
		if (fileName.endsWith(".java"))
			return (EvaluationMethod) Class.forName(s+fileName.substring(0, fileName.length()-5)).newInstance();
		else {
			TSPProblemFactory fac = new TSPProblemFactory();
			return fac.produceTSPProblem(fileName);
		}
	}

	public final static ParentSelection getParentSelection(String fileName) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String s = "evolutionaryAlgorithmComponents.parentSelectionMechanisms.";
		return (ParentSelection) Class.forName(s+fileName.substring(0, fileName.length()-5)).newInstance();
	}

	public final static SurvivorSelection getSurvivorSelection(String fileName) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String s = "evolutionaryAlgorithmComponents.survivorSelectionMechanisms.";
		return (SurvivorSelection) Class.forName(s+fileName.substring(0, fileName.length()-5)).newInstance();
	}

	public final static Recombination getCrossoverOperator(String fileName, double prob) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String s1 = "evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue.";
		String s2 = "evolutionaryAlgorithmComponents.variationOperators.recombination.realValue.";
		Recombination rec;
		try {
			rec = (Recombination) Class.forName(s1+fileName.substring(0, fileName.length()-5)).newInstance();
		} catch (Exception e) {
			rec = (Recombination) Class.forName(s2+fileName.substring(0, fileName.length()-5)).newInstance();
		}
		return rec;
	}
	public final static Mutation getMutationOperator(String fileName, double prob) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String s1 = "evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue.";
		String s2 = "evolutionaryAlgorithmComponents.variationOperators.recombination.realValue.";
		Mutation mutation;
		try {
			mutation = (Mutation) Class.forName(s1+fileName.substring(0, fileName.length()-5)).newInstance();
		} catch (Exception e) {
			mutation = (Mutation) Class.forName(s2+fileName.substring(0, fileName.length()-5)).newInstance();
		}
		return mutation;
	}

	public final static Representation getRepresentation(EvaluationMethod anEvaluationMethod){
		return null;

	}
	public final static Representation getPermutationRepresentation(int numberOfNodes){
		return null;

	}

}

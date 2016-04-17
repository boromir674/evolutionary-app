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

	public final static Recombination getCrossoverOperator(String filename, double prob){
		return null;

	}
	public final static Mutation getMutationOperator(String filename, double prob){
		return null;

	}

	public final static Representation getRepresentation(EvaluationMethod anEvaluationMethod){
		return null;

	}
	public final static Representation getPermutationRepresentation(int numberOfNodes){
		return null;

	}

}

/**
 * A class that contains a variety of static factory methods
 */
package util;

import interfaces.EvaluationMethod;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.SurvivorSelection;

/**
 * @author Konstantinos
 *
 */
public final class HyperFactory {


	public final static EvaluationMethod getEvaluationMethod(String filename){

	}

	public final static ParentSelection getParentSelection(String fileName){
		String s = "evolutionaryAlgorithmComponents.evaluation.realValueFunction.";
		Class.forName(s+fileName.substring(0, fileName.length()-5)).newInstance();
	}

	public final static SurvivorSelection getSurvivorSelection(String filename){

	}

	public final static Recombination getCrossoverOperator(String filename){

	}
	public final static Mutation getMutationOperator(String filename){

	}
	
	public final static Representation getRepresentation(String filename){
		
	}
	
	
}

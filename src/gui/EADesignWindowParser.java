package gui;

import java.lang.reflect.InvocationTargetException;

import util.HyperFactory;
import interfaces.EvaluationMethod;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;
import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import evolutionaryAlgorithmComponents.Population;
import evolutionaryAlgorithmComponents.VarianceOperator;
import evolutionaryAlgorithmComponents.evaluation.permutation.TSP.TSPProblemFactory;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;

public class EADesignWindowParser {
	
	private EADesignWindow window;
	
	public EADesignWindowParser(){
		
	}
	public EADesignWindowParser(EADesignWindow anEDesignWindow){
		window = anEDesignWindow;
	}
	
	@SuppressWarnings("unused")
	public EvolutionaryAlgorithm parse() throws Exception{ 
		EvaluationMethod evaluation = HyperFactory.getEvaluationMethod(window.lblProblemInstance.getText());
		Population population = new Population(Integer.parseInt(window.muJTextField.getText()), Integer.parseInt(window.lambdaJTextField.getText()));
		Recombination recombination = HyperFactory.getCrossoverOperator(window.recombinationJComboBox.getSelectedItem().toString(), Integer.parseInt(window.recombinationRateJTextField.getText()));  
		Mutation mutation = HyperFactory.getMutationOperator(window.mutationJComboBox.getSelectedItem().toString(), Integer.parseInt(window.recombinationRateJTextField.getText()));
		ParentSelection parentSelection = HyperFactory.getParentSelection(window.parentSelectionList.getSelectedItem().toString());
		SurvivorSelection survivorSelection = HyperFactory.getSurvivorSelection(window.survivorSelectionList.getSelectedItem().toString());
		Representation representation;
		if (window.lblProblemInstance.getText().contains(".java"))
			representation = HyperFactory.getRepresentation(window.lblProblemInstance.getText());
		else 
			representation = HyperFactory.getRepresentation(window.dJTextField.getText(), window.mutationJComboBox.getSelectedItem().toString());
		return new EvolutionaryAlgorithm(representation, evaluation, population, parentSelection, new VarianceOperator(recombination, mutation), survivorSelection);
		
	}
	
	private EvaluationMethod parseEvaluation() throws Exception {
		String s = "evolutionaryAlgorithmComponents.evaluation.realValueFunction.";
		TSPProblemFactory fac = new TSPProblemFactory();
		if (window.lblProblemInstance.getText().endsWith(".java")) {
			int l = window.lblProblemInstance.getText().length();
			return (EvaluationMethod) Class.forName(s+(window.lblProblemInstance.getText().substring(0, l-5))).newInstance();
		}
		else //(window.lblProblemInstance.getText().endsWith(".tsp")||
			//window.lblProblemInstance.getText().endsWith(".atsp")||window.lblProblemInstance.getText().endsWith(".hcp"))
			return fac.produceTSPProblem(window.lblProblemInstance.getText());
	}
	
	//private Representation

}

package gui;

import java.lang.reflect.InvocationTargetException;

import interfaces.EvaluationMethod;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.SurvivorSelection;
import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import evolutionaryAlgorithmComponents.Population;
import evolutionaryAlgorithmComponents.evaluation.permutation.TSP.TSPProblemFactory;

public class EADesignWindowParser {
	
	private EADesignWindow window;
	
	public EADesignWindowParser(){
		
	}
	public EADesignWindowParser(EADesignWindow anEDesignWindow){
		window = anEDesignWindow;
	}
	
	@SuppressWarnings("unused")
	public EvolutionaryAlgorithm parse() throws Exception{ 
		EvaluationMethod evaluation = this.parseEvaluation();
		Population population = new Population(Integer.parseInt(window.muJTextField.getText()), Integer.parseInt(window.lambdaJTextField.getText()));
		Recombination recombination = (Recombination) Class.forName(window.recombinationJComboBox.getSelectedItem().toString()).getConstructor(null).newInstance(null); 
		Mutation mutation = (Mutation) Class.forName(window.mutationJComboBox.getSelectedItem().toString()).getConstructor(double.class).newInstance(Integer.parseInt(window.recombinationRateJTextField.getText()));
		ParentSelection parentSelection = (ParentSelection) Class.forName(window.parentSelectionList.getSelectedItem().toString()).newInstance();
		SurvivorSelection survivorSelection = (SurvivorSelection) Class.forName(window.survivorSelectionList.getSelectedItem().toString()).newInstance();
		//Representation representation = 
		return null;
		
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

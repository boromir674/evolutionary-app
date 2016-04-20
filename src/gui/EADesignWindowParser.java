package gui;

import java.lang.reflect.InvocationTargetException;

import util.Factory;
import interfaces.EvaluationMethod;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;
import interfaces.TerminationCondition;
import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import evolutionaryAlgorithmComponents.Population;
import evolutionaryAlgorithmComponents.VarianceOperator;

public class EADesignWindowParser {
	// Singleton class
	private static EADesignWindowParser eaDesignWindowParserInstance = null;

	private EADesignWindowParser() {
	}
	// Singleton pattern
	public static EADesignWindowParser getInstance() {
		if (eaDesignWindowParserInstance == null)
			eaDesignWindowParserInstance = new EADesignWindowParser();
		return eaDesignWindowParserInstance;
	}

	public static EvolutionaryAlgorithm parse(EADesignWindow window) throws Exception{ 
		EvaluationMethod evaluation = Factory.getEvaluationMethod(window.lblProblemInstance.getText());
		Population population = new Population(Integer.parseInt(window.muJTextField.getText()), Integer.parseInt(window.lambdaJTextField.getText()));
		Recombination recombination = Factory.getCrossoverOperator(window.recombinationJComboBox.getSelectedItem().toString());  
		Mutation mutation = Factory.getMutationOperator(window.mutationJComboBox.getSelectedItem().toString(), Double.parseDouble(window.recombinationRateJTextField.getText()));
		ParentSelection parentSelection = Factory.getParentSelection(window.parentSelectionList.getSelectedItem().toString());
		SurvivorSelection survivorSelection = Factory.getSurvivorSelection(window.survivorSelectionList.getSelectedItem().toString());
		Representation representation;
		if (window.lblProblemInstance.getText().contains(".java"))
			representation = Factory.getRepresentation(window.lblProblemInstance.getText());
		else 
			representation = Factory.getRepresentation(window.dJTextField.getText(), window.mutationJComboBox.getSelectedItem().toString());
		return new EvolutionaryAlgorithm(representation, evaluation, population, parentSelection, new VarianceOperator(recombination, mutation), survivorSelection);		
	}
	public static TerminationCondition getTerminationCondition(EADesignWindow window) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return Factory.getTerminationCondition(window.terminationConditionJComboBox.getSelectedItem().toString(), window.terminationParameterJTextField.getText());
	}

}

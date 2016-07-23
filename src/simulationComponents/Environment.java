package simulationComponents;

import gui.EADesignWindow;
import interfaces.EvaluationMethod;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;
import interfaces.TerminationCondition;

import java.lang.reflect.InvocationTargetException;
import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import evolutionaryAlgorithmComponents.Population;
import evolutionaryAlgorithmComponents.VarianceOperator;
import exceptions.FailedToParseException;
import exceptions.IncompatibleComponentsException;
import util.Factory;
import util.LibraryModel;

public final class Environment extends Thread{

	final static LibraryModel model = new LibraryModel();
	final static Experiment exp1 = new Experiment();
	static EADesignWindow eaWindow = new EADesignWindow();

	@Override
	public void run() {
		eaWindow.getFrame().setVisible(true);
		exp1.directOutput(eaWindow.getOutputTextArea());
	}

	public static void evolvePopulation() throws Exception {
		TerminationCondition cond = Factory.getTerminationCondition(eaWindow.getTerminationConditionJComboBox().getSelectedItem().toString(), eaWindow.getTerminationParameterJTextField().getText());
		exp1.setEvolutionaryAlgorithm(parse());
		exp1.setTerminationCondition(cond);
		try {exp1.performOptimizationTask();}
		catch (Exception e) {e.printStackTrace();}
	}

	private static EvolutionaryAlgorithm parse() throws FailedToParseException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IncompatibleComponentsException{
		EvaluationMethod evaluation = Factory.getEvaluationMethod(eaWindow.getLblProblemInstance().getText());
		Population population = new Population(Integer.parseInt(eaWindow.getMuJTextField().getText()), Integer.parseInt(eaWindow.getLambdaJTextField().getText()));
		Recombination recombination = Factory.getCrossoverOperator(eaWindow.getRecombinationJComboBox().getSelectedItem().toString());  
		Mutation mutation = Factory.getMutationOperator(eaWindow.getMutationJComboBox().getSelectedItem().toString(), Double.parseDouble(eaWindow.getMutationRateJTextField().getText()));
		ParentSelection parentSelection = Factory.getParentSelection(eaWindow.getParentSelectionList().getSelectedItem().toString());
		SurvivorSelection survivorSelection = Factory.getSurvivorSelection(eaWindow.getSurvivorSelectionList().getSelectedItem().toString());
		Representation representation;
		if (eaWindow.getLblProblemInstance().getText().contains(".java"))
			representation = Factory.getRepresentation(eaWindow.getLblProblemInstance().getText(), eaWindow.getdJTextField().getText());
		else 
			representation = Factory.getRepresentation(eaWindow.getLblProblemInstance().getText());
		return new EvolutionaryAlgorithm(representation, evaluation, population, parentSelection, new VarianceOperator(recombination, mutation), survivorSelection);		
	}

}

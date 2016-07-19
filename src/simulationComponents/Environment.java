package simulationComponents;

import interfaces.EvaluationMethod;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;
import interfaces.TerminationCondition;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import evolutionaryAlgorithmComponents.Population;
import evolutionaryAlgorithmComponents.VarianceOperator;
import exceptions.FailedToParseException;
import exceptions.IncompatibleComponentsException;
import util.Factory;
import util.LibraryModel;
import gui.EADesignWindow;

public final class Environment implements Runnable{

	final static LibraryModel model = new LibraryModel();
	//final static Random rand1 = new Random(); 
	final static Experiment exp1 = new Experiment();
	static EADesignWindow eaWindow = new EADesignWindow();
	
	public static void main(String[] args){
		Environment field = new Environment();
		field.run();
	}
	
	@Override
	public void run() {
		eaWindow.getFrame().setVisible(true);
		exp1.directeOutput(eaWindow.getTextArea_1());
	}

	public static void runEA() throws Exception {
		exp1.setEvolutionaryAlgorithm(parse(eaWindow));
		exp1.setTerminationCondition(getTerminationCondition(eaWindow));
		try {
			exp1.performOptimizationTask();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public static EvolutionaryAlgorithm parse(EADesignWindow window) throws FailedToParseException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IncompatibleComponentsException{
		System.out.println(window.getLblProblemInstance().getText());
		EvaluationMethod evaluation = Factory.getEvaluationMethod(window.getLblProblemInstance().getText());
		Population population = new Population(Integer.parseInt(window.getMuJTextField().getText()), Integer.parseInt(window.getLambdaJTextField().getText()));
		Recombination recombination = Factory.getCrossoverOperator(window.getRecombinationJComboBox().getSelectedItem().toString());  
		Mutation mutation = Factory.getMutationOperator(window.getMutationJComboBox().getSelectedItem().toString(), Double.parseDouble(window.getMutationRateJTextField().getText()));
		ParentSelection parentSelection = Factory.getParentSelection(window.getParentSelectionList().getSelectedItem().toString());
		SurvivorSelection survivorSelection = Factory.getSurvivorSelection(window.getSurvivorSelectionList().getSelectedItem().toString());
		Representation representation;
		if (window.getLblProblemInstance().getText().contains(".java"))
			representation = Factory.getRepresentation(window.getLblProblemInstance().getText(), window.getdJTextField().getText());
		else 
			representation = Factory.getRepresentation(window.getLblProblemInstance().getText());
		return new EvolutionaryAlgorithm(representation, evaluation, population, parentSelection, new VarianceOperator(recombination, mutation), survivorSelection);		
	}
	public static TerminationCondition getTerminationCondition(EADesignWindow window) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return Factory.getTerminationCondition(window.getTerminationConditionJComboBox().getSelectedItem().toString(), window.getTerminationParameterJTextField().getText());
	}

}

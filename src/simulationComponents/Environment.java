package simulationComponents;

import interfaces.EvaluationMethod;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import evolutionaryAlgorithmComponents.Population;
import evolutionaryAlgorithmComponents.VarianceOperator;
import util.Factory;
import util.LibraryModel;
import gui.EADesignWindow;

public class Environment implements Runnable, ActionListener{

	@SuppressWarnings("unused")
	private final static LibraryModel model = new LibraryModel();
	private Random rand1 = new Random();
	private Experiment exp1 = new Experiment(rand1);
	private final EADesignWindow eaWindow = new EADesignWindow();
	
	@Override
	public void run() {
		eaWindow.getFrame().setVisible(true);
	}

	public void runEA() {
		
		try {
			exp1.setEvolutionaryAlgorithm(parse(eaWindow));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			exp1.setTerminationCondition(Factory.getTerminationCondition(eaWindow.getTerminationConditionJComboBox().getSelectedItem().toString(), eaWindow.getTerminationParameterJTextField().getText()));
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			exp1.performOptimizationTask();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public EvolutionaryAlgorithm parse(EADesignWindow window) throws Exception{ 
		EvaluationMethod evaluation = Factory.getEvaluationMethod(window.getLblProblemInstance().getText());
		Population population = new Population(Integer.parseInt(window.getMuJTextField().getText()), Integer.parseInt(window.getLambdaJTextField().getText()));
		Recombination recombination = Factory.getCrossoverOperator(window.getRecombinationJComboBox().getSelectedItem().toString());  
		Mutation mutation = Factory.getMutationOperator(window.getMutationJComboBox().getSelectedItem().toString(), Double.parseDouble(window.getMutationRateJTextField().getText()));
		ParentSelection parentSelection = Factory.getParentSelection(window.getParentSelectionList().getSelectedItem().toString());
		SurvivorSelection survivorSelection = Factory.getSurvivorSelection(window.getSurvivorSelectionList().getSelectedItem().toString());
		Representation representation;
		if (window.getLblProblemInstance().getText().contains(".java"))
			representation = Factory.getRepresentation(window.getMutationJComboBox().getSelectedItem().toString(), window.getdJTextField().getText());
		else 
			representation = Factory.getRepresentation(window.getLblProblemInstance().getText());
		return new EvolutionaryAlgorithm(representation, evaluation, population, parentSelection, new VarianceOperator(recombination, mutation), survivorSelection);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.eaWindow.getRunButton()) {
			this.runEA();
		}	
	}

}

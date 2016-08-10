package simulationComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JMenu;

import org.apache.commons.lang3.ArrayUtils;

import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import evolutionaryAlgorithmComponents.Population;
import evolutionaryAlgorithmComponents.representation.AbstractRealValueRepresentation;
import gui.EADesignWindow;
import interfaces.EvaluationMethod;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;
import util.Factory;

public class EADesignWindowListener implements ActionListener{

	private final static String[] elitistSelectionSet = new String[]{"MuPlusLambda", "DeterministicCrowding"};

	EADesignWindow win;
	Experiment exp;

	public EADesignWindowListener(EADesignWindow eaWindow, Experiment experiment) {
		this.win = eaWindow;
		this.exp = experiment;
		this.win.setListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == win.getSurvivorSelectionList()) {
			this.updateElitismSwitch(e);
			try {exp.getEvolutionaryAlgorithm().setSurvivorSelectionMethod(Factory.getSurvivorSelection(e.getActionCommand()));}
			catch (Exception e1) {e1.printStackTrace();}
		} else if (e.getSource() == win.getParentSelectionList()) {
			try {exp.getEvolutionaryAlgorithm().setParentSelectionMethod(Factory.getParentSelection(e.getActionCommand()));}
			catch (Exception e1) {e1.printStackTrace();}
		} else if (e.getSource() == win.getRecombinationJComboBox()) {
			try {exp.getEvolutionaryAlgorithm().setRecombination(Factory.getCrossoverOperator(e.getActionCommand()));}
			catch (Exception e1) {e1.printStackTrace();}
		} else if (e.getSource() == win.getMutationJComboBox()) {
			try {exp.getEvolutionaryAlgorithm().setMutation(Factory.getMutationOperator(e.getActionCommand(), Double.parseDouble(win.getMutationRateJTextField().getText())));}
			catch (Exception e1) {e1.printStackTrace();}


		} else if (e.getSource() instanceof JMenu) {
			this.readEvaluationChoice(e);
			try {
				exp.getEvolutionaryAlgorithm().setEval(Factory.getEvaluationMethod(e.getActionCommand()));}
			catch (Exception e1) {
				e1.printStackTrace();}
		} else if (e.getSource() == win.getdJTextField()) {
			if (win.getdJTextField().isEnabled()) {
				((AbstractRealValueRepresentation) this.exp.getEvolutionaryAlgorithm().getRepresentation()).setDimensions(Integer.parseInt(e.getActionCommand()));
			}
			try {exp.getEvolutionaryAlgorithm().setSurvivorSelectionMethod(Factory.getSurvivorSelection(e.getActionCommand()));}
			catch (Exception e1) {
				e1.printStackTrace();}
		} else if (e.getSource() == win.getRunButton()) {
			try {
				exp.setEvolutionaryAlgorithm(this.parse());
				exp.optimize();
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
		}
		//else if (e.getSource() == win.getMuJTextField()) {
		//exp.getEvolutionaryAlgorithm().setPop(new Population());
		//}
	}
	private void updateElitismSwitch(ActionEvent e) {
		String selection = ((String)((JComboBox<?>)e.getSource()).getSelectedItem());
		if (ArrayUtils.contains(elitistSelectionSet, selection)) {
			win.getRdbtnElitismOn().setSelected(true);
			win.getRdbtnElitismOn().setEnabled(false);
			win.getRdbtnElitismOff().setEnabled(false);
		}
		else {
			win.getRdbtnElitismOn().setEnabled(true);
			win.getRdbtnElitismOff().setEnabled(true);
		}
	}
	private void readEvaluationChoice(ActionEvent e) {
		try {
			win.parseEvaLuationMenuChoice(e.getActionCommand());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();}
		win.getProblemInstanceLabel().setText(e.getActionCommand());
		win.getLblRepresentation().setText(e.getActionCommand());
		String dim = util.Util.parseDimensions(e.getActionCommand());
		win.getdJTextField().setText(dim);
		if (!dim.isEmpty())
			win.getdJTextField().setEnabled(false);
		else
			win.getdJTextField().setEnabled(true);
	}
	private EvolutionaryAlgorithm parse() throws Exception {
		EvaluationMethod evaluation = Factory.getEvaluationMethod(this.win.getLblProblemInstance().getText());
		Population population = new Population(Integer.parseInt(this.win.getMuJTextField().getText()), Integer.parseInt(this.win.getLambdaJTextField().getText()));
		Recombination recombination = Factory.getCrossoverOperator(this.win.getRecombinationJComboBox().getSelectedItem().toString());  
		Mutation mutation = Factory.getMutationOperator(this.win.getMutationJComboBox().getSelectedItem().toString(), Double.parseDouble(this.win.getMutationRateJTextField().getText()));
		ParentSelection parentSelection = Factory.getParentSelection(this.win.getParentSelectionList().getSelectedItem().toString());
		SurvivorSelection survivorSelection = Factory.getSurvivorSelection(this.win.getSurvivorSelectionList().getSelectedItem().toString());
		Representation representation;
		if (this.win.getLblProblemInstance().getText().contains(".java"))
			representation = Factory.getRepresentation(this.win.getMutationJComboBox().getSelectedItem().toString(), this.win.getdJTextField().getText());
		else 
			representation = Factory.getRepresentation(this.win.getLblProblemInstance().getText());
		return new EvolutionaryAlgorithm(evaluation, representation, recombination, mutation, population, parentSelection, survivorSelection);
	}

}
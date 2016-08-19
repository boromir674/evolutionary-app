package simulationComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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

	private EADesignWindow win;
	private Experiment exp;

	public EADesignWindowListener(EADesignWindow eaWindow, Experiment experiment) {
		this.win = eaWindow;
		this.win.setListener(this);
		this.exp = experiment;
	}
	// TODO fix this shit
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == win.getSurvivorSelectionList()) {
			try {exp.getEvolutionaryAlgorithm().setSurvivorSeletion(Factory.getSurvivorSelection(win.getSurvivorSelectionList().getSelectedItem().toString()));}
			catch (Exception e1) {e1.printStackTrace();}
		} else if (e.getSource() == win.getParentSelectionList()) {
			try {exp.getEvolutionaryAlgorithm().getParameters().setParentSelection(Factory.getParentSelection(e.getActionCommand()));}
			catch (Exception e1) {e1.printStackTrace();}
		} else if (e.getSource() == win.getRecombinationJComboBox()) {
			try {exp.getEvolutionaryAlgorithm().getParameters().setRecombination(Factory.getCrossoverOperator(e.getActionCommand()));}
			catch (Exception e1) {e1.printStackTrace();}
		} else if (e.getSource() == win.getMutationJComboBox()) {
			try {exp.getEvolutionaryAlgorithm().getParameters().setMutation(Factory.getMutationOperator(e.getActionCommand(), Double.parseDouble(win.getMutationRateJTextField().getText())));}
			catch (Exception e1) {e1.printStackTrace();}


		} else if (e.getSource() instanceof JMenuItem) {
			this.readEvaluationChoice(e);
			try {
				exp.getEvolutionaryAlgorithm().setEvaluation(Factory.getEvaluationMethod(e.getActionCommand()));}
			catch (Exception e1) {
				e1.printStackTrace();}
		} else if (e.getSource() == win.getdJTextField()) {
			if (win.getdJTextField().isEnabled()) {
				((AbstractRealValueRepresentation) this.exp.getEvolutionaryAlgorithm().getParameters().getRepresentation()).setDimensions(Integer.parseInt(e.getActionCommand()));
			}
			try {exp.getEvolutionaryAlgorithm().getParameters().setSurvivorSelection(Factory.getSurvivorSelection(e.getActionCommand()));}
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
		else {
			System.out.println(e.getSource().toString());
		}

		//else if (e.getSource() == win.getMuJTextField()) {
		//exp.getEvolutionaryAlgorithm().setPop(new Population());
		//}
	}

	private void readEvaluationChoice(ActionEvent e) {
		String description = "";
		System.out.println("here");
		try {
			description = win.parseEvaLuationMenuChoice(e.getActionCommand());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();}
		win.getlblRepresentationDescription().setText(description);
		win.getLblProblemInstance().setText(e.getActionCommand());
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
		return new EvolutionaryAlgorithm(representation, recombination, mutation, parentSelection, survivorSelection);
	}

}
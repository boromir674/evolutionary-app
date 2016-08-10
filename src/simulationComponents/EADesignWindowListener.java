package simulationComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JMenu;

import org.apache.commons.lang3.ArrayUtils;

import gui.EADesignWindow;
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
	
	public void updateEAComponent() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == win.getSurvivorSelectionList()) {
			this.updateElistismSwitch(e);
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
			try {exp.getEvolutionaryAlgorithm().setEval(Factory.getEvaluationMethod(e.getActionCommand()));}
			catch (Exception e1) {e1.printStackTrace();}
		} else if (e.getSource() == win.getParentSelectionList()) {
			try {exp.getEvolutionaryAlgorithm().setSurvivorSelectionMethod(Factory.getSurvivorSelection(e.getActionCommand()));}
			catch (Exception e1) {e1.printStackTrace();}
		}
		
	}
	
	private void updateElistismSwitch(ActionEvent e) {
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
		//String text = this.
		//bestFitnessJLabel.setText(text);
	}

}


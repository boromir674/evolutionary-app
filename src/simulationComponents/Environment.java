package simulationComponents;

import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import util.EAPrinter;
import util.LibraryModel;

public final class Environment extends Thread{

	final static LibraryModel model = new LibraryModel();
	final static Experiment exp1 = new Experiment();
	
	Thread printer = new EAPrinter();
	
	@Override
	public void run() {
		EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm();
		//exp1.setEvolutionaryAlgorithm(ea);
		exp1.designEA();
	}
/*
	public static void evolvePopulation() throws Exception {
		TerminationCondition cond = Factory.getTerminationCondition(eaWindow.getTerminationConditionJComboBox().getSelectedItem().toString(), eaWindow.getTerminationParameterJTextField().getText());
		exp1.setEvolutionaryAlgorithm(parse());
		exp1.setTerminationCondition(cond);
		try {exp1.optimize();}
		catch (Exception e) {e.printStackTrace();}
	}
*/

}

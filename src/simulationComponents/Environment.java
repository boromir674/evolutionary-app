package simulationComponents;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import gui.EADesignWindow;
import gui.EADesignWindowParser;

public final class Environment{
	
	private final static Random rand1 = new Random(); 
	private static Experiment exp1 = new Experiment(rand1);
	private static EADesignWindow eaWindow = new EADesignWindow();
	
	public Environment() {
		eaWindow.getFrame().setVisible(true);
	}
	
	public static void runEA() {
		try {
			exp1.setEvolutionaryAlgorithm(EADesignWindowParser.parse(eaWindow));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			exp1.setTerminationCondition(EADesignWindowParser.getTerminationCondition(eaWindow));
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			exp1.performOptimizationTask();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}

package gui;

import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.SurvivorSelection;

@SuppressWarnings("unused")
public class EAParameterVector {
	
	private int lambda;
	private int mu;
	private int dimensions;
	private double recombinationRate;
	private double mutationRate;
	private Recombination recombination;
	private Mutation mutation;
	private ParentSelection parentSelectionMethod;
	private SurvivorSelection survivorSelectionMethod;
	
}

package simulationComponents;

import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;

public class EAParameterVector {

	public  Representation representation;
	private Recombination recombination;
	private Mutation mutation;
	private ParentSelection parentSelectionMethod;
	private SurvivorSelection survivorSelectionMethod;

	public EAParameterVector(ParentSelection parSel, Recombination rec, Mutation mut, SurvivorSelection survSel) {
		parentSelectionMethod = parSel;
		recombination = rec;
		mutation = mut;
		survivorSelectionMethod = survSel;
	}
	
	public ParentSelection parSel() {
		return parentSelectionMethod;
	}
	public SurvivorSelection survSel() {
		return survivorSelectionMethod;
	}
	public Representation rep(){
		return representation;
	}
	public Recombination rec(){
		return recombination;
	}
	public Mutation mut(){
		return mutation;
	}
}


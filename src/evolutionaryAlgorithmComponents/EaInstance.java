package evolutionaryAlgorithmComponents;

import java.util.Random;

import evolutionaryAlgorithmComponents.variationOperators.recombination.AbstractRecombination;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;
import optimizationComponents.Parameter;
import optimizationComponents.ParameterVector;

public class EaInstance extends ParameterVector{
	
	public EaInstance(Parameter[] parameters) {
		super(parameters);
		this.representation = (Representation) parameters[0];
		this.recombination = (Recombination) parameters[1];
		this.mutation = (Mutation) parameters[2];
		this.parentSelection = (ParentSelection) parameters[3];
		this.survivorSelection = (SurvivorSelection) parameters[4];
	}
	// fundamental components
	private Representation representation;
	private Recombination recombination = null;
	private Mutation mutation = null;
	private ParentSelection parentSelection;
	private SurvivorSelection survivorSelection;
	
	Random random;

	
	public Representation getRepresentation() {
		return representation;
	}
	public ParentSelection getParentSelection() {
		return parentSelection;
	}
	public SurvivorSelection getSurvivorSelection() {
		return survivorSelection;
	}
	public Recombination getRecombination() {
		return recombination;
	}
	public Mutation getMutation() {
		return mutation;
	}
	
	public void setRepresentation(Representation representation) {
		this.representation = representation;
	}
	public void setParentSelection(ParentSelection parentSelectionMethod) {
		this.parentSelection = parentSelectionMethod;
	}
	public void setSurvivorSelection(SurvivorSelection survivorSelectionMethod) {
		this.survivorSelection = survivorSelectionMethod;
		((AbstractSurvivorSelection) this.survivorSelection).setRandom(this.random);
	}
	public void setRecombination(Recombination recombination) {
		this.recombination = recombination;
		((AbstractRecombination) this.recombination).setRandom(this.random);
	}
	public void setMutation(Mutation mutation) {
		this.mutation = mutation;
	}	
}

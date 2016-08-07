package evolutionaryAlgorithmComponents;

import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import evolutionaryAlgorithmComponents.fitnessCalculators.DynamicNiching;
import evolutionaryAlgorithmComponents.representation.AbstractIntegerRepresentation;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;
import evolutionaryAlgorithmComponents.representation.AbstractRealValueRepresentation;
import evolutionaryAlgorithmComponents.survivorSelectionMechanisms.DeterministicCrowding;
import evolutionaryAlgorithmComponents.survivorSelectionMechanisms.MuCommaLambda;
import exceptions.IncompatibleComponentsException;
import exceptions.SortsInPlaceThePopulationException;
import exceptions.UnknownSolutionException;
import interfaces.EvaluationMethod;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;
import simulationComponents.EAParameterVector;

public class EvolutionaryAlgorithm {
	// fundamental components
	private Representation representation;
	private Recombination recombination;
	private Mutation mutation;
	private ParentSelection parentSelectionMethod;
	private SurvivorSelection survivorSelectionMethod;
	
	private EAParameterVector ea;
	private Population pop;
	private EvaluationMethod eval;
	private VarianceOperator varOp;

	protected int[] parents;
	boolean maxInFirstPosition;
	//private double lowerValue;
	//private AbstractFitnessSharingScheme fitnessSharingScheme = new DynamicNiching(5);
	Random random;

	public void setEvolutionaryAlgorithm(EAParameterVector paramVector){
		this.ea = paramVector;
		varOp = new VarianceOperator(ea.rec(), ea.mut());
	}
	public EvolutionaryAlgorithm(EAParameterVector paramVector){
		this.ea = paramVector;
		varOp = new VarianceOperator(ea.rec(), ea.mut());
	}
	public EvolutionaryAlgorithm(){
		
	}
	/*
	public EARunner(Representation aRepresentation, EvaluationMethod anEvaluationMethod, pop apop, ParentSelection aParentSelection, 
			VarianceOperator aVarianceOperator, SurvivorSelection aSurvivorSelection) throws IncompatibleComponentsException{
		representation = aRepresentation;
		eval = anEvaluationMethod;
		pop = apop;
		pop.evo = this;
		variationOperator = aVarianceOperator;
		//variationOperator.feedRandom();
		variationOperator.evo = this;
		parentSelectionMethod = aParentSelection;
		survivorSelectionMethod = aSurvivorSelection;
		this.checkComponentsCompatibility();
		printInfo();
	}*/
/*
	//TODO generalize..
	public void printChromosome(Individual anIndividual){
		if (chromosome instanceof Double[])
			for (int i=0; i< chromosome.length; i++) 
				System.out.format("%.2f ", chromosome[i]);
		else {  //if (chromosome instanceof Integer[])
			int offset;
			try {
				Integer[] vector = (Integer[])(this.pop.evo.getEvaluator()).getSolutionVector(representation.getDimensions());
				offset = ArrayUtils.indexOf(chromosome, vector[0]);
			} catch (UnknownSolutionException e) {
				offset = ArrayUtils.indexOf(chromosome, 1);
			}
			System.out.format("%d ", chromosome[(i+offset) % chromosome.length]);
		}
		System.out.println();
	}*/

	private void checkComponentsCompatibility() throws IncompatibleComponentsException {
		if (pop.getLambda() < pop.getMu() && ea.survSel() instanceof MuCommaLambda)
			throw new IncompatibleComponentsException("children less than parents");
		if (ea.rep() instanceof PermutationRepresentation && !varOp.applicableToPermutation)
			throw new IncompatibleComponentsException("operator is incompatible with permutation problems");
		if (ea.rep() instanceof AbstractIntegerRepresentation && !varOp.applicableToDiscrete)
			throw new IncompatibleComponentsException("operator is only compatible with continuous values");
		if (ea.rep() instanceof AbstractRealValueRepresentation && varOp.applicableToDiscrete)
			throw new IncompatibleComponentsException("real value representation, but discrete operator");
		if (ea.survSel() instanceof DeterministicCrowding && pop.getMu() != pop.getLambda())
			throw new IncompatibleComponentsException("Deterministic Crowding scheme demands: μ=λ");
	}
	
	public Representation getRepresentation() {
		return representation;
	}
	public void setRepresentation(Representation representation) {
		this.representation = representation;
	}
	public Mutation getMutation() {
		return mutation;
	}
	public void setMutation(Mutation mutation) {
		this.mutation = mutation;
	}
	public ParentSelection getParentSelectionMethod() {
		return parentSelectionMethod;
	}
	public void setParentSelectionMethod(ParentSelection parentSelectionMethod) {
		this.parentSelectionMethod = parentSelectionMethod;
	}
	public SurvivorSelection getSurvivorSelectionMethod() {
		return survivorSelectionMethod;
	}
	public void setSurvivorSelectionMethod(SurvivorSelection survivorSelectionMethod) {
		this.survivorSelectionMethod = survivorSelectionMethod;
	}
	public Population getPop() {
		return pop;
	}
	public void setPop(Population pop) {
		this.pop = pop;
	}
	public EvaluationMethod getEval() {
		return eval;
	}
	public void setEval(EvaluationMethod eval) {
		this.eval = eval;
	}
	public Recombination getRecombination() {
		return recombination;
	}
	public void setRecombination(Recombination recombination) {
		this.recombination = recombination;
	}

}

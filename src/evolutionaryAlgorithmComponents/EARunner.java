package evolutionaryAlgorithmComponents;

import java.util.Random;

import evolutionaryAlgorithmComponents.representation.AbstractIntegerRepresentation;
import evolutionaryAlgorithmComponents.representation.AbstractRealValueRepresentation;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;
import evolutionaryAlgorithmComponents.survivorSelectionMechanisms.DeterministicCrowding;
import evolutionaryAlgorithmComponents.survivorSelectionMechanisms.MuCommaLambda;
import exceptions.IncompatibleComponentsException;
import exceptions.SortsInPlaceThePopulationException;
import interfaces.EvaluationMethod;

public class EARunner {
	
	private Random random;
	private EvolutionaryAlgorithm ea;
	private EvaluationMethod eval;
	private Population pop;

	private int[] _parents;
	private VarianceOperator varOp;
	private int[] _survivors;
	
	public EARunner(Random random) {
		this.random = random;
	}

	public void setRandom(Random aRandom) {
		this.random = aRandom;
		if (ea != null) {
			ea.random = aRandom;
			ea.updateRandomReferences();
		}
	}
	public void setEvaluation(EvaluationMethod eval){
		this.eval = eval;
	}
	public void setPopulation(Population pop){
		this.pop = pop;
		this.pop.eaRunnerRef = this;
	}
	public void setEA(EvolutionaryAlgorithm ea) {
			this.ea = ea;
			ea.random = this.random;
			ea.updateRandomReferences();
	}
// -------- TYPICAL STEPS OF AN EVOLUTIONARY PROCEDURE -------------------
	public void randomInitialization() throws Exception{
		eval.reInitialize();
		pop.initializeRandom(ea.getRepresentation(), random, eval);
	}
	//if diakoptis at Dynamic Niching then this.scheme = new DynamicNiching;
	public void parentSelection() throws Exception{
		//if (this.fitnessSharingScheme instanceof DynamicNiching)
		//((DynamicNiching)fitnessSharingScheme).greedyDynamicPeakIdentification(pop, 10);
		_parents = ea.getParentSelectionMethod().select(pop, random);
	}
	public void applyOperator() throws Exception { //each pair gives two children
		for (int i=0; i<pop.getLambda(); i=i+2){
			Individual[] children = varOp.operate(pop.getPool()[_parents[i]], pop.getPool()[_parents[i+1]], random);
			pop.addOffspring(children[0], eval);
			if (children.length == 2)
				pop.addOffspring(children[1], eval);
			else
				pop.addOffspring((Individual) children[0].clone(), eval);
		}
	}
	public void survivorSelection() throws Exception {
		this.pop.generationCount ++;
		try {
			_survivors = ea.getSurvivorSelectionMethod().select(pop);
			pop.updatePoolWithNewGeneration(_survivors);
		} catch (SortsInPlaceThePopulationException e) {
			pop.fitterTillMu = pop.getPool()[0];
		}
		if (((AbstractSurvivorSelection) ea.getSurvivorSelectionMethod()).forceElitism())
			if (pop.fitterTillMu.getFitness() < pop.fitterTillEnd.getFitness())
				pop.forceFitter(this.random);
	}
// --------------------------------------------------------------------------
	public void printInfo(){
		System.out.println("\nEvolutionary Algorithm deployed with components:");
		System.out.println("Evaluation: " + eval.getTitle());
		System.out.format("pop size: μ=%d%n", pop.getMu());
		System.out.format("Offsprings: λ=%d%n", pop.getLambda());
		System.out.println("Representation: " + ea.getRepresentation().getTitle());
		System.out.println("Parent Selection: " + ea.getParentSelectionMethod().getTitle());
		System.out.println("Recombination: " + ea.getRecombination().getTitle());
		System.out.format("Mutation (p=%.2f): " + ea.getMutation().getTitle()+"%n", ea.getMutation().getProbability());
		System.out.println("Survivor Selection: " + ea.getSurvivorSelectionMethod().getTitle());
	}
	
	public int[] getParents(){
		return this._parents;
	}
	public EvolutionaryAlgorithm getEA() {
		return this.ea;
	}
	// serves debugging purposes, to be deleted...
	public void checkComponentsCompatibility() throws IncompatibleComponentsException {
		if (pop.getLambda() < pop.getMu() && ea.getSurvivorSelectionMethod() instanceof MuCommaLambda)
			throw new IncompatibleComponentsException("children less than parents");
		if (ea.getRepresentation() instanceof PermutationRepresentation && !varOp.applicableToPermutation)
			throw new IncompatibleComponentsException("operator is incompatible with permutation problems");
		if (ea.getRepresentation() instanceof AbstractIntegerRepresentation && !varOp.applicableToDiscrete)
			throw new IncompatibleComponentsException("operator is only compatible with continuous values");
		if (ea.getRepresentation() instanceof AbstractRealValueRepresentation && varOp.applicableToDiscrete)
			throw new IncompatibleComponentsException("real value representation, but discrete operator");
		if (ea.getSurvivorSelectionMethod() instanceof DeterministicCrowding && pop.getMu() != pop.getLambda())
			throw new IncompatibleComponentsException("Deterministic Crowding scheme demands: μ=λ");
	}

	public Population getPopulation() {
		return this.pop;
	}

	public EvaluationMethod getEvaluation() {
		return this.eval;
	}
}

package evolutionaryAlgorithmComponents;

import java.util.Random;

import algorithms.AbstractAlgorithm;
import algorithms.AlgorithmOutput;
import evolutionaryAlgorithmComponents.representation.AbstractIntegerRepresentation;
import evolutionaryAlgorithmComponents.representation.AbstractRealValueRepresentation;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;
import evolutionaryAlgorithmComponents.survivorSelectionMechanisms.DeterministicCrowding;
import evolutionaryAlgorithmComponents.survivorSelectionMechanisms.MuCommaLambda;
import evolutionaryAlgorithmComponents.variationOperators.recombination.AbstractRecombination;
import exceptions.IncompatibleComponentsException;
import exceptions.SortsInPlaceThePopulationException;
import interfaces.EvaluationMethod;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;
import optimizationComponents.ParameterVector;
import optimizationComponents.ProblemSpecifications;
import simulationComponents.EAParameterVector;

public class EvolutionaryAlgorithm extends AbstractAlgorithm{

	@Override
	public AlgorithmOutput run(ParameterVector vec, ProblemSpecifications specs) {
		return null;
		// TODO Auto-generated method stub
	}

	private Random random;
	private EAParameterVector params;
	private EvaluationMethod eval;
	private Population pop;

	private int[] _parents;
	private VarianceOperator varOp;
	private int[] _survivors;

	public EvolutionaryAlgorithm(Random random) {
		this.random = random;
	}

	public EvolutionaryAlgorithm(Representation representation, Recombination recombination, Mutation mutation,
			ParentSelection parentSelection, SurvivorSelection survivorSelection) {
	}
	
	public void setRandom(Random aRandom) {
		this.random = aRandom;
		if (params != null)
			this.updateRandomReferences();
	}
	public void setEvaluation(EvaluationMethod eval){
		this.eval = eval;
	}
	public void setPopulation(Population pop){
		this.pop = pop;
		this.pop.ea = this;
	}
	public void setEA(ParameterVector ea) {
		this.params = ea;
		this.updateRandomReferences();
	}
	// -------- TYPICAL STEPS OF AN EVOLUTIONARY PROCEDURE -----------------------
	public void randomInitialization(){
		eval.reInitialize();
		try {
			pop.initializeRandom(params.getRepresentation(), random, eval);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//if diakoptis at Dynamic Niching then this.scheme = new DynamicNiching;
	public void parentSelection() throws Exception{
		//if (this.fitnessSharingScheme instanceof DynamicNiching)
		//((DynamicNiching)fitnessSharingScheme).greedyDynamicPeakIdentification(pop, 10);
		_parents = params.getParentSelection().select(pop, random);
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
			_survivors = params.getSurvivorSelection().select(pop);
			pop.updatePoolWithNewGeneration(_survivors);
		} catch (SortsInPlaceThePopulationException e) {
			pop.fitterTillMu = pop.getPool()[0];
		}
		if (((AbstractSurvivorSelection) params.getSurvivorSelection()).forceElitism())
			if (pop.fitterTillMu.getFitness() < pop.fitterTillEnd.getFitness())
				pop.forceFitter(this.random);
	}
	// -------------------------------------------------------------------------------
	public void printInfo(){
		System.out.println("\nEvolutionary Algorithm deployed with components:");
		System.out.println("Evaluation: " + eval.getTitle());
		System.out.format("pop size: μ=%d%n", pop.getMu());
		System.out.format("Offsprings: λ=%d%n", pop.getLambda());
		System.out.println("Representation: " + params.rep());
		System.out.println("Parent Selection: " + params.parSel());
		System.out.println("Recombination: " + params.rec());
		System.out.format("Mutation (p=%.2f): " + params.mut()+"%n", params.mut().getProbability());
		System.out.println("Survivor Selection: " + params.survSel().getTitle());
	}

	public int[] getParents(){
		return this._parents;
	}

	public Population getPopulation() {
		return this.pop;
	}

	public EvaluationMethod getEvaluation() {
		return this.eval;
	}

	//TODO potentially/optimally eliminate the need for such function
	void updateRandomReferences() {
		((AbstractSurvivorSelection) this.params.survivorSelection()).setRandom(this.random);
		((AbstractRecombination) this.params.getRecombination()).setRandom(this.random);
	}

	@Override
	public void setNumericalParameters() {
		// TODO Auto-generated method stub
		
	}
}

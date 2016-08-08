package evolutionaryAlgorithmComponents;

import java.util.Random;

import evolutionaryAlgorithmComponents.representation.AbstractIntegerRepresentation;
import evolutionaryAlgorithmComponents.representation.AbstractRealValueRepresentation;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;
import evolutionaryAlgorithmComponents.survivorSelectionMechanisms.DeterministicCrowding;
import evolutionaryAlgorithmComponents.survivorSelectionMechanisms.MuCommaLambda;
import exceptions.IncompatibleComponentsException;
import exceptions.SortsInPlaceThePopulationException;

public class EARunner {
	
	private Random random;
	private EvolutionaryAlgorithm ea;

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

	public void setEA(EvolutionaryAlgorithm ea) {
		try {
			this.ea = ea;
			checkComponentsCompatibility();
			ea.getPop().eaRunnerRef = this;
			ea.random = this.random;
			ea.updateRandomReferences();
		} catch (IncompatibleComponentsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
// -------- TYPICAL STEPS OF AN EVOLUTIONARY PROCEDURE -------------------
	public void randomInitialization() throws Exception{
		ea.getEval().reInitialize();
		ea.getPop().initializeRandom(ea.getRepresentation(), random, ea.getEval());
	}
	//if diakoptis at Dynamic Niching then this.scheme = new DynamicNiching;
	public void parentSelection() throws Exception{
		//if (this.fitnessSharingScheme instanceof DynamicNiching)
		//((DynamicNiching)fitnessSharingScheme).greedyDynamicPeakIdentification(pop, 10);
		_parents = ea.getParentSelectionMethod().select(ea.getPop(), random);
	}
	public void applyOperator() throws Exception { //each pair gives two children
		for (int i=0; i<ea.getPop().getLambda(); i=i+2){
			Individual[] children = varOp.operate(ea.getPop().getPool()[_parents[i]], ea.getPop().getPool()[_parents[i+1]], random);
			ea.getPop().addOffspring(children[0], ea.getEval());
			if (children.length == 2)
				ea.getPop().addOffspring(children[1], ea.getEval());
			else
				ea.getPop().addOffspring((Individual) children[0].clone(), ea.getEval());
		}
	}
	public void survivorSelection() throws Exception {
		this.ea.getPop().generationCount ++;
		try {
			_survivors = ea.getSurvivorSelectionMethod().select(ea.getPop());
			ea.getPop().updatePoolWithNewGeneration(_survivors);
		} catch (SortsInPlaceThePopulationException e) {
			ea.getPop().fitterTillMu = ea.getPop().getPool()[0];
		}
		if (((AbstractSurvivorSelection) ea.getSurvivorSelectionMethod()).forceElitism())
			if (ea.getPop().fitterTillMu.getFitness() < ea.getPop().fitterTillEnd.getFitness())
				ea.getPop().forceFitter();
	}
// --------------------------------------------------------------------------
	public void printInfo(){
		System.out.println("\nEvolutionary Algorithm deployed with components:");
		System.out.println("Evaluation: " + ea.getEval().getTitle());
		System.out.format("pop size: μ=%d%n", ea.getPop().getMu());
		System.out.format("Offsprings: λ=%d%n", ea.getPop().getLambda());
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
	
	private void checkComponentsCompatibility() throws IncompatibleComponentsException {
		if (ea.getPop().getLambda() < ea.getPop().getMu() && ea.getSurvivorSelectionMethod() instanceof MuCommaLambda)
			throw new IncompatibleComponentsException("children less than parents");
		if (ea.getRepresentation() instanceof PermutationRepresentation && !varOp.applicableToPermutation)
			throw new IncompatibleComponentsException("operator is incompatible with permutation problems");
		if (ea.getRepresentation() instanceof AbstractIntegerRepresentation && !varOp.applicableToDiscrete)
			throw new IncompatibleComponentsException("operator is only compatible with continuous values");
		if (ea.getRepresentation() instanceof AbstractRealValueRepresentation && varOp.applicableToDiscrete)
			throw new IncompatibleComponentsException("real value representation, but discrete operator");
		if (ea.getSurvivorSelectionMethod() instanceof DeterministicCrowding && ea.getPop().getMu() != ea.getPop().getLambda())
			throw new IncompatibleComponentsException("Deterministic Crowding scheme demands: μ=λ");
	}
}

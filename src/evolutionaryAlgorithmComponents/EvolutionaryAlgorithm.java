package evolutionaryAlgorithmComponents;

import java.util.Random;

import exceptions.IncompatibleComponentsException;
import interfaces.EvaluationMethod;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.Recombination;
import interfaces.Representation;
import interfaces.SurvivorSelection;

public class EvolutionaryAlgorithm {
	// fundamental components
	private EvaluationMethod evaluation;
	private Representation representation;
	private Recombination recombination = null;
	private Mutation mutation = null;
	private ParentSelection parentSelection;
	private SurvivorSelection survivorSelection;
	private Population population;
	
	Random random;

	public EvolutionaryAlgorithm(EvaluationMethod eval, Representation rep, Recombination rec, Mutation mut, Population pop, ParentSelection parSel, SurvivorSelection survSel) throws IncompatibleComponentsException {
		this.evaluation = eval;
		this.representation = rep;
		this.recombination = rec;
		this.mutation = mut;
		this.parentSelection = parSel;
		this.survivorSelection = survSel;
		this.population = pop;
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


	public EvolutionaryAlgorithm() {
		
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
		return parentSelection;
	}
	public void setParentSelectionMethod(ParentSelection parentSelectionMethod) {
		this.parentSelection = parentSelectionMethod;
	}
	public SurvivorSelection getSurvivorSelectionMethod() {
		return survivorSelection;
	}
	public void setSurvivorSelectionMethod(SurvivorSelection survivorSelectionMethod) {
		this.survivorSelection = survivorSelectionMethod;
		((AbstractSurvivorSelection) this.survivorSelection).setRandom(this.random);
	}
	public Population getPop() {
		return population;
	}
	public void setPop(Population pop) {
		this.population = pop;
	}
	public EvaluationMethod getEval() {
		return evaluation;
	}
	public void setEval(EvaluationMethod eval) {
		this.evaluation = eval;
	}
	public Recombination getRecombination() {
		return recombination;
	}
	public void setRecombination(Recombination recombination) {
		this.recombination = recombination;
		((AbstractRecombination) this.recombination).setRandom(this.random);
	}
	//TODO potentially/optimally eliminate the need for such function
	void updateRandomReferences() {
		((AbstractSurvivorSelection) this.survivorSelection).setRandom(this.random);
		((AbstractRecombination) this.recombination).setRandom(this.random);
	}
}

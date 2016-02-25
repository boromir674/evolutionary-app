package evolutionaryAlgorithmComponents;

import java.util.Collections;
import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.Schemes.BasicFitnessSharing;
import evolutionaryAlgorithmComponents.Schemes.DeterministicCrowding;
import evolutionaryAlgorithmComponents.representation.AbstractIntegerRepresentation;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;
import evolutionaryAlgorithmComponents.representation.RealValueRepresentation;
import evolutionaryAlgorithmComponents.survivorSelectionMechanisms.MuCommaLambda;
import exceptions.IncompatibleComponentsException;
import exceptions.NoKnownSolutionException;
import exceptions.SortsInPlaceThePopulationException;
import interfaces.EvaluationMethod;
import interfaces.FitnessSharingScheme;
import interfaces.ParentSelection;
import interfaces.Representation;
import interfaces.SurvivorSelection;

public class EvolutionaryAlgorithm {
	// Fundamental components
	private Representation representation;
	private EvaluationMethod evaluator;
	private Population population;
	private VarianceOperator variationOperator;
	private ParentSelection parentSelectionMethod;
	private SurvivorSelection survivorSelectionMethod;

	private FitnessSharingScheme sharingScheme = null;
	protected int[] parents;
	boolean maxInFirstPosition;
	private int[] survivors;
	private double lowerValue;

	public EvolutionaryAlgorithm(Representation aRepresentation, EvaluationMethod anEvaluationMethod, Population aPopulation, ParentSelection aParentSelection, 
			VarianceOperator aVarianceOperator, SurvivorSelection aSurvivorSelection) throws IncompatibleComponentsException{
		representation = aRepresentation;
		evaluator = anEvaluationMethod;
		population = aPopulation;
		population.evo = this;
		variationOperator = aVarianceOperator;
		variationOperator.evo = this;
		parentSelectionMethod = aParentSelection;
		survivorSelectionMethod = aSurvivorSelection;
		this.checkComponentsCompatibility();
		printInfo();
	}

	public void randomInitialization(Random aRandom) throws Exception{
		evaluator.reInitialize();
		population.initializeRandom(representation, aRandom, evaluator);
		this.lowerValue = this.population.getFittestIndividual().getFitness();
	}
	public void parentSelection(Random aRandom) throws Exception{
		if (sharingScheme == null)
			parents = parentSelectionMethod.select(population, aRandom);
		else
			parents = sharingScheme.select(population, aRandom);
	}

	public void applyOperator(Random aRandom) throws Exception { //each pair gives two children
		population.fitterTillEnd = population.fitterTillMu;
		for (int i=0; i<population.getLambda(); i=i+2){
			Individual[] children = variationOperator.operate(population.getPool()[parents[i]], population.getPool()[parents[i+1]], aRandom);
			population.addOffspring(children[0], evaluator);
			if (children[0].getFitness() > population.fitterTillEnd.getFitness())
				population.fitterTillEnd = children[0];
			if (children.length == 2) {
				population.addOffspring(children[1], evaluator);
				if (children[1].getFitness() > population.fitterTillEnd.getFitness())
					population.fitterTillEnd = children[1];
			}
			else
				population.addOffspring(children[0], evaluator);
		}
	}

	public void survivorSelection() throws Exception {
		this.population.generationCount ++;
		try {
			survivors = survivorSelectionMethod.select(population);
			Individual[] newGeneration = new Individual[survivors.length + population.getLambda()];
			population.fitterTillMu = population.getPool()[survivors[0]];
			newGeneration[0] = population.getPool()[survivors[0]];
			newGeneration[0].incrementAge();
			for (int i=1; i<survivors.length; i++){
				newGeneration[i] = population.getPool()[survivors[i]];
				newGeneration[i].incrementAge();
				if (newGeneration[i].getFitness() > population.fitterTillMu.getFitness())
					population.fitterTillMu = newGeneration[i];
			}
			population.updatePoolWithNewGeneration(newGeneration);
		} catch (SortsInPlaceThePopulationException e) {
			population.fitterTillMu = population.getPool()[0];
		}
		if (survivorSelectionMethod.forceElitism())
			if (population.fitterTillMu.getFitness() < population.fitterTillEnd.getFitness())
				population.forceFitter();
	}
	
	public void printInfo(){
		System.out.println("\nEvolutionary Algorithm deployed with components:");
		System.out.println("Evaluation: " + evaluator.getTitle());
		System.out.format("Population size: μ=%d%n", population.getMu());
		System.out.format("Offsprings: λ=%d%n", population.getLambda());
		System.out.println("Representation: " + representation.getTitle());
		System.out.println("Parent Selection: " + parentSelectionMethod.getTitle());
		System.out.println("Recombination: " + variationOperator.getRecombination().getTitle());
		System.out.format("Mutation (p=%.2f): " + variationOperator.getMutation().getTitle()+"%n", variationOperator.getMutation().getProbability());
		System.out.println("Survivor Selection: " + survivorSelectionMethod.getTitle());
	}
	public void printPerformance() throws Exception {
		double percentage = (this.population.getFittestIndividual().getFitness()-this.lowerValue)/(((AbstractEvaluationMethod) this.evaluator).getSolutionFitness() - this.lowerValue) * 100;	
		System.out.format("%.2f ", percentage);		
	}

	private void checkComponentsCompatibility() throws IncompatibleComponentsException {
		if (population.getLambda() < population.getMu() && survivorSelectionMethod instanceof MuCommaLambda)
			throw new IncompatibleComponentsException("children less than parents");
		if (representation instanceof PermutationRepresentation && !variationOperator.applicableToPermutation)
			throw new IncompatibleComponentsException("operator is incompatible with permutation problems");
		if (representation instanceof AbstractIntegerRepresentation && !variationOperator.applicableToDiscrete)
			throw new IncompatibleComponentsException("operator is only compatible with continuous values");
		if (representation instanceof RealValueRepresentation && variationOperator.applicableToDiscrete)
			throw new IncompatibleComponentsException("real value representation, but discrete operator");
	}
	public int[] getParents(){
		return this.parents;
	}
	/**
	 * @return the representation
	 */
	public Representation getRepresentation() {
		return representation;
	}
	/**
	 * @param representation the representation to set
	 */
	public void setRepresentation(Representation representation) {
		this.representation = representation;
	}
	/**
	 * @return the evaluator
	 */
	public EvaluationMethod getEvaluator() {
		return evaluator;
	}
	/**
	 * @param evaluator the evaluator to set
	 */
	public void setEvaluator(EvaluationMethod evaluator) {
		this.evaluator = evaluator;
	}
	/**
	 * @return the population
	 */
	public Population getPopulation() {
		return population;
	}
	/**
	 * @param population the population to set
	 */
	public void setPopulation(Population population) {
		this.population = population;
	}
	/**
	 * @return the variationOperator
	 */
	public VarianceOperator getVariationOperator() {
		return variationOperator;
	}
	/**
	 * @param variationOperator the variationOperator to set
	 */
	public void setVariationOperator(VarianceOperator variationOperator) {
		this.variationOperator = variationOperator;
	}
	/**
	 * @return the parentSelectionMethod
	 */
	public ParentSelection getParentSelectionMethod() {
		return parentSelectionMethod;
	}
	/**
	 * @param parentSelectionMethod the parentSelectionMethod to set
	 */
	public void setParentSelectionMethod(ParentSelection parentSelectionMethod) {
		this.parentSelectionMethod = parentSelectionMethod;
	}
	/**
	 * @return the survivorSelectionMethod
	 */
	public SurvivorSelection getSurvivorSelectionMethod() {
		return survivorSelectionMethod;
	}
	/**
	 * @param survivorSelectionMethod the survivorSelectionMethod to set
	 */
	public void setSurvivorSelectionMethod(SurvivorSelection survivorSelectionMethod) {
		this.survivorSelectionMethod = survivorSelectionMethod;
	}
	public FitnessSharingScheme getFitnessSharingScheme(){
		return sharingScheme;
	}
	public void deterministicCrowdingON(Random aRandom) {
		this.survivorSelectionMethod = new DeterministicCrowding(aRandom);
	}
	public void fitnessSharingON() {
		this.sharingScheme = new BasicFitnessSharing();
	}
	public void fitnessSharingOFF() {
		this.sharingScheme = null;
	}

}

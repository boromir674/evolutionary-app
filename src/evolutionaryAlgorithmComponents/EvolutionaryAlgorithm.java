package evolutionaryAlgorithmComponents;

import java.util.Random;

import evolutionaryAlgorithmComponents.variationOperators.VarianceOperator;
import interfaces.EvaluationMethod;
import interfaces.ParentSelection;
import interfaces.Representation;
import interfaces.SurvivorSelection;

public class EvolutionaryAlgorithm {

	private Representation representation;
	private EvaluationMethod evaluator;
	private Population population;
	private VarianceOperator variationOperator;
	private ParentSelection parentSelectionMethod;
	private SurvivorSelection survivorSelectionMethod;

	private Individual[] parents;

	public EvolutionaryAlgorithm(Representation aRepresentation, EvaluationMethod anEvaluationMethod, Population aPopulation, ParentSelection aParentSelection, 
			VarianceOperator aVarianceOperator, SurvivorSelection aSurvivorSelection) {
		representation = aRepresentation;
		evaluator = anEvaluationMethod;
		population = aPopulation;
		variationOperator = aVarianceOperator;
		parentSelectionMethod = aParentSelection;
		survivorSelectionMethod = aSurvivorSelection;
		printInfo();
	}

	public void randomInitialization(Random aRandom) throws Exception{
		evaluator.reInitialize();
		population.initializeRandom(representation, aRandom, evaluator);		
	}
	public void parentSelection(Random aRandom) throws Exception{
		parents = parentSelectionMethod.select(population, aRandom);
	}
	public void applyOperator(Random aRandom) throws Exception { //each pair gives two children
		for (int i=0; i<population.getLambda(); i=i+2){
			Individual[] children = variationOperator.operate(parents[i], parents[i+1], representation, aRandom);
			population.addIndividual(children[0], evaluator);
			if (children.length == 2)
				population.addIndividual(children[1], evaluator);
			else
				population.addIndividual(children[0], evaluator);
		}
		if (population.getParentsAndChildren().size() - population.getMu() != population.getLambda()){
			System.out.println("Did not produce lambda children");
			System.exit(0);
		}
	}

	public void survivorSelection() throws Exception {
		survivorSelectionMethod.select(population);
		this.population.generationCount ++;
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

	public void printInfo(){
		System.out.println("Evolutionary Algorithm deployed with components:\n");
		System.out.format("Population size: μ=%d%n", population.getMu());
		System.out.format("Offsprings: λ=%d%n", population.getLambda());
		System.out.println("Representation: " + representation.getTitle());
		System.out.println("Parent Selection: " + parentSelectionMethod.getTitle());
		System.out.println("Recombination: " + variationOperator.getRecombination().getTitle());
		System.out.println("Mutation: " + variationOperator.getMutation().getTitle());
		System.out.println("Mutation probability: " + variationOperator.getMutation().getProbability());
		System.out.println("Survivor Selection: " + survivorSelectionMethod.getTitle());
	}
}

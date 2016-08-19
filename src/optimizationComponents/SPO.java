package optimizationComponents;

import interfaces.Constraints;
import interfaces.InitializationMethod;
import interfaces.TerminationMethod;

import algorithms.AbstractAlgorithm;
/**
 * An implementation of "Paper, C., & Sciences, A. (2004). Analysis of Particle Swarm Optimization Using Computational Statistics, (JANUARY). http://doi.org/10.1002/anac.200410007"
 */
public class SPO {
	
	private SpoPlan plan;
	private Constraints constraints;
	private InitializationMethod initializer;
	private TerminationMethod terminationCondition;
	private AbstractAlgorithm algorithm;
	
	// local variables
	private ParameterVector[] initialVectors;
	private OptimizationProblem optimizationProblem;
	
	public Constraints getConstraints() {
		return constraints;
	}
	public void setConstraints(Constraints constraints) {
		this.constraints = constraints;
	}
	public SPO(){
	}
	public SPO(SpoPlan plan, Constraints constraints, InitializationMethod init, TerminationMethod term, AbstractAlgorithm algorithm, OptimizationProblem op){
		this.plan = plan;
		this.constraints = constraints;
		this.optimizationProblem = op;
		this.initializer = init;
		this.terminationCondition = term;
		this.algorithm = algorithm;
	}
	
	//public SPOOutput
	private void pickInitialVectors() {
		this.initializer.initialize(this.algorithm, constraints, optimizationProblem);
		
	}

	private void evaluateVectors(UtilityMeasure measure){
		
	}

	private double F(double[] b, double[] x) {
		double sum = 0;
		for (int i=0; i<b.length; i++)
			continue;
			//sum += b[i]*f[i][x];
		return sum;
	}

	public AbstractAlgorithm getAlgorithm() {
		// TODO Auto-generated method stub
		return null;
	}
	public ProblemSpecifications getProblemSpecifications() {
		// TODO Auto-generated method stub
		return null;
	}
}

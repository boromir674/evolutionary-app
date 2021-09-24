package interfaces;

import optimizationComponents.OptimizationProblem;

public interface InitializationMethod {

	public void initialize(Algorithm algorithm, Constraints constraints, OptimizationProblem optimizationProblem);
}

package interfaces;

import algorithms.AlgorithmOutput;
import optimizationComponents.ParameterVector;
import optimizationComponents.ProblemSpecifications;
import optimizationComponents.UtilityMeasure;

public interface Algorithm {

	public AlgorithmOutput run(ParameterVector vec, ProblemSpecifications specs);

	public void randomInitialization();

}

package interfaces;

import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import simulationComponents.TunerOutput;

public interface T1Tuner extends Tuner {
	
	public TunerOutput tuneForPerformance(EvolutionaryAlgorithm ea);
}

package interfaces;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

/**
 * @author konstantinos
 * An object implementing this interface has the ability to calculate the
 * fitness value of an Individual according to a user specified "fitness sharing scheme".
 */
public interface FitnessSharingScheme extends EvolutionaryAlgorithmComponent, ParentSelection, FitnessCalculator{
	
}

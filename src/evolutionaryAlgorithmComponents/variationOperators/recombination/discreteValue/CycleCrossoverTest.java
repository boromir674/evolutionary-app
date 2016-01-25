package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import org.junit.Test;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;
import evolutionaryAlgorithmComponents.representation.AbstractRepresentation;

public class CycleCrossoverTest {

	@Test
	public void testPerform() {
		AbstractRepresentation arep = new PermutationRepresentation(9, 1, 9);
		//Recombination tester = new CycleCrossover(arep);
		//CycleCrossover tester = new CycleCrossover(arep);
		Individual i1 = new Individual();
		Individual i2 = new Individual();
		
		i1.initializeEmpty(arep);
		i2.initializeEmpty(arep);
		int[] t1 = new int[]{9, 3, 7, 8, 2, 6, 5, 1, 4};
		for (int i =0; i<t1.length; i++)
			i1.getChromosome()[i] = i+1;
		for (int i =0; i<t1.length; i++)
			i2.getChromosome()[i] = t1[i];
		Individual c1 = new Individual();
		Individual c2 = new Individual();
		c1.initializeEmpty(arep);
		c2.initializeEmpty(arep);
		t1 = new int[]{1,3,7,4,2,6,5,8,9};
		for (int i=0;i<t1.length;i++)
			c1.getChromosome()[i] = t1[i];
		t1 = new int[]{9,2,3,8,5,6,7,1,4};
		for (int i=0;i<t1.length;i++)
			c2.getChromosome()[i] = t1[i];
		c1.printChromosome();
		//Individual[] exp = tester.perform(i1, i2);
		//exp[0].printChromosome();
		c2.printChromosome();
		//exp[1].printChromosome();
		//assertArrayEquals(c1.getChromosome(), exp[0].getChromosome());
		//assertArrayEquals(c2.getChromosome(), exp[1].getChromosome());
	}

}

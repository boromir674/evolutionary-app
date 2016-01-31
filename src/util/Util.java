package util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

public abstract class Util {	

	public static int[] rouletteWheel(double[] cumulativeProbabilities, int numberOfSamples, Random aRandom){
		double rand;
		int[] indicesSampled = new int[numberOfSamples];
		int j;
		// roulette wheel algorithm
		for (int i=0; i<numberOfSamples; i++){
			rand = aRandom.nextDouble();
			j = 0;
			while (cumulativeProbabilities[j] < rand)
				j++;
			indicesSampled[i] = j;
		}
		return indicesSampled;
	}

	public static int[] stochasticUniversalSampling(double[] cumulProbs, int numberOfSamples, Random aRandom){
		double rand = aRandom.nextDouble() / numberOfSamples;
		int[] indicesSampled = new int[numberOfSamples];
		int currentMember = 1;
		int i = 1;
		// stochastic universal sampling algorithm
		while (currentMember <= numberOfSamples){
			while (rand <= cumulProbs[i-1]){
				indicesSampled[currentMember-1] = i-1;
				rand += 1.0/numberOfSamples;
				currentMember ++;
			}
			i ++;
		}
		return indicesSampled;
	}

	public static int[] tournamentSelection(int numberOfSamples, double[] values, Random aRandom){
		// tournament selection algorithm
		int k = 10;
		int tempIndex = 0;
		int winner = 0;
		double max = Double.MIN_VALUE;
		int[] indicesSampled = new int[numberOfSamples];

		for (int i=0; i<numberOfSamples; i++){
			for (int j=0; j<k; j++){// with replacement
				tempIndex = aRandom.nextInt(values.length);
				if (values[tempIndex] > max){
					max = values[tempIndex];
					winner = tempIndex;
				}
			}
			indicesSampled[i] = winner;
			max = Double.MIN_VALUE;
		}
		return indicesSampled;
	}

	public static int[] roundRobinTournament(int numberOfBest, int q, Object[] values, Random aRandom){
		int[] topCompetitors = new int[numberOfBest];
		Integer[][] wins = new Integer[values.length][2];
		for (int j=0; j<wins.length; j++){
			wins[j][0] = 0;
			wins[j][1] = j;
		}
		int randomIndex;

		for (int i=0; i<values.length; i++){
			for (int j=0; j<q; j++){// with replacement
				randomIndex = aRandom.nextInt(values.length);
				if (((Double)values[randomIndex]).compareTo((Double)values[i]) < 0){
					wins[i][0] ++;
				}
			}
		}
		Arrays.sort(wins, new Comparator<Integer[]>() {
			@Override
			public int compare(final Integer[] entry1, Integer[] entry2) {
				final Integer w1 = entry1[0];
				final Integer w2 = entry2[0];
				return w2.compareTo(w1); // Descending order
			}
		});
		for (int i=0; i<topCompetitors.length; i++)
			topCompetitors[i] = wins[i][1];
		return topCompetitors;
	}

	public static Double[] getFitnessArray(Individual[] anArrayOfIndividuals, int limit) {
		Double[] fitArray = new Double[limit];
		for (int i=0; i<limit; i++)
			fitArray[i] = anArrayOfIndividuals[i].getFitness();
		return fitArray;
	}
	public static double[] getFitnessArray(Individual[] anArrayOfIndividuals, int start, int limit) {
		double[] fitArray = new double[limit-start];
		for (int i=start; i<limit; i++)
			fitArray[i-start] = anArrayOfIndividuals[i].getFitness();
		return fitArray;
	}

	public static double[] findFitnessBasedProbabilities(Double[] fitarray){
		// scale all values
		double minFitness = findMin(fitarray);
		double fitnessSum = 0;
		for (int i=0; i<fitarray.length; i++) {
			fitarray[i] = fitarray[i] - minFitness + 1;
			fitnessSum += fitarray[i];
		}
		double [] probs = new double[fitarray.length];
		for (int i=0; i<fitarray.length; i++){
			probs[i] = fitarray[i]/fitnessSum;
		}
		return probs;
	}

	public static double findMin(double[] fitarray){
		//int minIndex = 0;
		double min = Double.POSITIVE_INFINITY;
		for (int i=0; i<fitarray.length; i++)
			if (fitarray[i] < min)
				min = fitarray[i];
		return min;
	}
	public static double[] getCumulativeDistribution(double[] probabilities){
		double[] cumulativeProbs = new double[probabilities.length];
		cumulativeProbs[0] = probabilities[0];
		for (int i=1; i<probabilities.length; i++) 
			cumulativeProbs[i] = cumulativeProbs[i-1] + probabilities[i];
		return cumulativeProbs;
	}

	public static double[] findRankingProbs(Double[] fitArray){
		double s = 1.5; // parameter: 1 < s <= 2
		double[] probs = new double[fitArray.length];
		for (int i=0; i<probs.length; i++) // linear formula
			probs[i] = (2.0 - s)/fitArray.length + 2*i*(s-1)/(fitArray.length*(fitArray.length-1));
		return probs;
	}

	public static int findMaxIndex(double[] ar){
		int maxIndex = 0;
		double max = Double.NEGATIVE_INFINITY;
		for (int i=0; i<ar.length; i++)
			if (ar[i] > max){
				maxIndex = i;
				max = ar[i];
			}
		return maxIndex;
	}

	public static double[] sampleMeanAndVariance(Double[] fitArray){
		double meanj = fitArray[0]; // mean_1
		double varj = 0; // var_1
		double newMean;
		for (int j=2; j<fitArray.length+1; j++){
			newMean = meanj + (fitArray[j-1]-meanj)/(j);
			varj = (1-1.0/(j-1))*varj + (j)*Math.pow(newMean-meanj,2);
			meanj = newMean;
		}
		return new double[]{meanj, varj};
	}

	// Implementing Fisher–Yates shuffle
	// https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
	public static void shuffleArray(int[] anArray, Random aRandom) {
		for (int i = anArray.length - 1; i > 0; i--)
		{
			int index = aRandom.nextInt(i + 1);
			// Simple swap
			int a = anArray[index];
			anArray[index] = anArray[i];
			anArray[i] = a;
		}
	}

	public static double findMin(Double[] fitArray) {
		double min = Double.POSITIVE_INFINITY;
		for (int i=0; i<fitArray.length; i++)
			if (fitArray[i] < min)
				min = fitArray[i];
		return min;
	}
}
package util;

import interfaces.FitnessCalculator;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;

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

	public static int[] tournamentSelection(int numberOfSamples, int tournamentSize, double[] values, Random aRandom){
		// tournament size: if larger then weaker inds have smaller chance of being selected
		int contestant;
		int winner = 0;
		double max = Double.NEGATIVE_INFINITY;
		int[] indicesSampled = new int[numberOfSamples];

		for (int i=0; i<numberOfSamples; i++){
			for (int j=0; j<tournamentSize; j++){// with replacement
				contestant = aRandom.nextInt(values.length);
				if (values[contestant] > max){
					max = values[contestant];
					winner = contestant;
				}
			}
			indicesSampled[i] = winner;
			max = Double.NEGATIVE_INFINITY;
		}
		return indicesSampled;
	}

	public static double[] getCumulativeDistribution(Individual[] anArrayOfIndividuals, int start, int limit, FitnessCalculator aFitnessCalculator){
		double[] fitArray = new double[limit-start];
		double minFitness = Double.POSITIVE_INFINITY;
		double fitnessSum = 0;
		for (int i=0; i<fitArray.length; i++){
			fitArray[i] = aFitnessCalculator.computeFitness(anArrayOfIndividuals[start+i]);
			if (fitArray[i] < minFitness)
				minFitness = fitArray[i];
			fitnessSum += fitArray[i];
		}
		fitnessSum += fitArray.length * (1 - minFitness);
		double[] cumulProbs = new double[fitArray.length];
		fitArray[0] = fitArray[0] - minFitness + 1;
		cumulProbs[0] = fitArray[0]/fitnessSum;
		for (int i=1; i<fitArray.length; i++) {
			fitArray[i] = fitArray[i] - minFitness + 1;
			cumulProbs[i] = cumulProbs[i-1] + fitArray[i]/fitnessSum;
		}

		return cumulProbs;
	}
	/*public static double[] getCumulativeDistribution(Individual[] anArrayOfIndividuals, int start, int limit){

		double[] fitArray = new double[limit-start];
		double minFitness = Double.POSITIVE_INFINITY;
		double fitnessSum = 0;
		for (int i=0; i<fitArray.length; i++){
			fitArray[i] = anArrayOfIndividuals[start+i].getFitness();
			if (fitArray[i] < minFitness)
				minFitness = fitArray[i];
			fitnessSum += fitArray[i];
		}
		fitnessSum += fitArray.length * (1 - minFitness);
		double[] cumulProbs = new double[fitArray.length];
		fitArray[0] = fitArray[0] - minFitness + 1;
		cumulProbs[0] = fitArray[0]/fitnessSum;
		for (int i=1; i<fitArray.length; i++) {
			fitArray[i] = fitArray[i] - minFitness + 1;
			cumulProbs[i] = cumulProbs[i-1] + fitArray[i]/fitnessSum;
		}

		return cumulProbs;
	}*/

	public static double findMin(double[] fitarray){
		//int minIndex = 0;
		double min = Double.POSITIVE_INFINITY;
		for (int i=0; i<fitarray.length; i++)
			if (fitarray[i] < min)
				min = fitarray[i];
		return min;
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

	public static double[] sampleMeanAndVariance(double[] fitArray){
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
	public static void shuffleArray(int[] array, Random aRandom) {
		int temp;
		for (int i = array.length - 1; i > 0; i--)
		{
			int index = aRandom.nextInt(i + 1);
			// Simple swap
			temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}
	
	public static String parseDimensions(String aFileName){
		int i = 1;
		int start = 0;
		int end = 0;
		while ((start == 0 || end == 0) && i<aFileName.length()){
			char ch0 = aFileName.charAt(i-1);
			char ch1 = aFileName.charAt(i);
			if (!Character.isDigit(ch0) && Character.isDigit(ch1))
				start = i;
			if (Character.isDigit(ch0) && !Character.isDigit(ch1))
				end = i;
			i++;
		}
		if (end == 0)
			return ""; // fileName contains no 'dimension'
		return aFileName.substring(start, end);
	}

}
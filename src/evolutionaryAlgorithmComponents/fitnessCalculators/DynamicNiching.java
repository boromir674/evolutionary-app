package evolutionaryAlgorithmComponents.fitnessCalculators;

import java.util.ArrayList;
import java.util.Arrays;

import util.MinHeap;
import evolutionaryAlgorithmComponents.AbstractFitnessSharingScheme;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;
import evolutionaryAlgorithmComponents.representation.AbstractRepresentation;

public class DynamicNiching extends AbstractFitnessSharingScheme {

	private final static String title = "Dynamic Niching";
	
	private int alpha = 1; //linear recommended
	private double sigmaShare = 5;
	
	private Individual[] currentPeakSet;
	private int[] nichesSizes;
	private static final MinHeap heap = new MinHeap();

	public DynamicNiching() {
		super(title);
	}

	public DynamicNiching(double sigmaShare) {
		super(title);
		this.sigmaShare = sigmaShare;
	}

	@Override
	public double computeFitness(Individual ind) {
		double denominator;
		int peak = belongsToPeak(ind, this.currentPeakSet);
		if (peak != -1)
			denominator = nichesSizes[peak];
		else {
			denominator = 0;
			for (int i=0; i<ind.getPopulation().getMu(); i++) {
				double distance = ((AbstractRepresentation)ind.getPopulation().getPool()[0].getRepresentation()).genotypicDistance(ind, ind.getPopulation().getPool()[i]);
				if (distance <= sigmaShare)
					denominator += 1 - Math.pow(distance/sigmaShare, alpha);
			}
		}
		return ind.getFitness() / denominator;
	}

	private int belongsToPeak(Individual anIndividual, Individual[] peakSet){
		for (int i=0; i<peakSet.length; i++)
			if (((AbstractRepresentation) anIndividual.getRepresentation()).genotypicDistance(anIndividual, peakSet[i]) < sigmaShare)
				return i;
		return -1;
	}

	public void greedyDynamicPeakIdentification(Population pop, int numberOfPeaks) {
		nichesSizes = new int[numberOfPeaks];
		Arrays.fill(nichesSizes, 0);
		int i = pop.getMu()-1;
		ArrayList<Individual> DPS = new ArrayList<Individual>(); // Dynamic Peak Set
		DPS.add(pop.getPool()[i]);
		int q = 1;
		heap.heapsort(pop.getPool(), i+1);
		i--;
		while (q < numberOfPeaks && i!=-1) {
			int peak = belongsToPeak(pop.getPool()[i], (Individual[]) DPS.toArray());
			if (peak != -1){
				DPS.add(pop.getPool()[i]);
				q++;
			}
			else
				this.nichesSizes[peak]++;
			i--;
		}
		this.currentPeakSet = (Individual[]) DPS.toArray();
	}

}

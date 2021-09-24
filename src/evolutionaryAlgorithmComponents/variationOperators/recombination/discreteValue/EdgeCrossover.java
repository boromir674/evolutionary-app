package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import evolutionaryAlgorithmComponents.Individual;

public class EdgeCrossover extends AbstractPermutationRecombination {

	private final static String title = "Edge Crossover";
	
	public EdgeCrossover() {
		super(title);
	}

	@Override
	public Individual[] perform(Individual mom, Individual dad) {
		
		Individual[] children = super.initializeChildrenForRecombination(mom.getRepresentation());
		Individual[] child = new Individual[]{children[0]};
		int dimensions = mom.getRepresentation().getDimensions();
		
		ArrayList<Integer> availableNodes = new ArrayList<>();
		for (int i=0; i<dimensions; i++)
			availableNodes.add(i+1);

		// algorithm
		ArrayList<ArrayList<String>> edgeTable = constructEdgeTable(mom, dad); // 1. construct edge table
		//for (int i=0; i<edgeTable.size(); i++)
		//System.out.println(edgeTable.get(i));
		//System.out.println();
		int r = random.nextInt(dimensions) + 1; // 2. pick initial element at random
		ArrayList<Integer> offspring = new ArrayList<Integer>();
		ArrayList<String> choices;

		while (offspring.size()<dimensions){
			offspring.add(r); // and put it in the offspring

			availableNodes.remove((Integer) r);
			removeEntryFromTable(Integer.toString(r), edgeTable);
			choices = edgeTable.get(r-1);
			//System.out.print(choices+" ");
			//System.out.print(r+" ");
			//System.out.println(offspring);
			if (!choices.isEmpty()){
				int common = findCommon(choices);
				if (common > 0)// if common edge then pick that element 
					r = common;
				else{// pick entry with shortest list (fewer neighbors)
					// break ties randomly
					int shortest = findShortest(choices, edgeTable);
					r = shortest;
				}
			}
			else{// list is empty; randomly choose node that is not in offspring
				if (availableNodes.size() > 0){
					int ind = random.nextInt(availableNodes.size());
					r = availableNodes.get(ind);
				}
			}
		}

		for (int i=0;i<dimensions;i++){
			child[0].getChromosome()[i] = offspring.get(i);
		}
		return child;
	}

	private static ArrayList<ArrayList<String>> constructEdgeTable(Individual mom, Individual dad){
		ArrayList<ArrayList<String>> edgeTable = new ArrayList<ArrayList<String>>();
		int ind;
		int dimensions = mom.getRepresentation().getDimensions();
		for (int i=0; i<dimensions; i++){
			edgeTable.add(new ArrayList<String>());
			String[] edges = new String[4];
			int i1 = ArrayUtils.indexOf(mom.getChromosome(), i+1);
			int i2 = ArrayUtils.indexOf(dad.getChromosome(), i+1);
			edges[0] = Integer.toString((int) mom.getChromosome()[(i1-1+dimensions)%dimensions]);
			edges[1] = Integer.toString((int) dad.getChromosome()[(i2-1+dimensions)%dimensions]);
			edges[2] = Integer.toString((int) mom.getChromosome()[(i1+1+dimensions)%dimensions]);
			edges[3] = Integer.toString((int) dad.getChromosome()[(i2+1+dimensions)%dimensions]);
			edgeTable.get(i).add(edges[0]);
			for (int j=1; j<edges.length; j++){
				if (edgeTable.get(i).contains(edges[j])){
					ind = edgeTable.get(i).indexOf(edges[j]);
					edgeTable.get(i).set(ind, edges[j]+"+");
				}
				else
					edgeTable.get(i).add(edges[j]);
			}
		}
		return edgeTable;
	}

	private static void removeEntryFromTable(String entry, ArrayList<ArrayList<String>> eT){
		for (int i=0; i<eT.size(); i++){
			int j = 0;
			int removed = 0;
			while (removed<2 && j<eT.get(i).size()){
				String s = eT.get(i).get(j);
				if (s.charAt(s.length()-1) == '+')
					s = eT.get(i).get(j).substring(0, s.length()-1);
				if (s.equals(entry)){
					eT.get(i).remove(j);
					removed ++;
				}
				j ++;
			}				
		}
	}

	private static int findCommon(ArrayList<String> list){
		int common = -1;
		String s;
		for (int i=0; i<list.size(); i++){
			s = list.get(i);
			if (s.charAt(s.length()-1) == '+') {
				common = Integer.parseInt(s.substring(0, s.length()-1));
				break;
			}
		}
		return common;
	}

	private int findShortest(ArrayList<String> list, ArrayList<ArrayList<String>> eT){
		String s = list.get(0);
		if (list.size() == 1){
			if (s.charAt(s.length()-1) == '+')
				return Integer.parseInt(s.substring(0, s.length()-1));
			return Integer.parseInt(s.substring(0, s.length()));
		}
		int shortest, index, min = 4;
		ArrayList<Integer> pool = new ArrayList<>();
		for (int i=0; i<list.size(); i++){
			s = list.get(i);
			if (s.charAt(s.length()-1) != '+')
				index = Integer.parseInt(s);
			else
				index = Integer.parseInt(s.substring(0, s.length()-1));
			if (eT.get(index-1).size() < min){
				pool.clear();
				pool.add(index);
				min = eT.get(index-1).size();
			}
			else if (eT.get(index-1).size() == min)
				pool.add(index);
		}
		int ind = random.nextInt(pool.size());
		shortest = pool.get(ind);
		return shortest;
	}

}

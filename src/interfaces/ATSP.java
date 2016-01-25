package interfaces;

import interfaces.TSPLIBProblem;
/**
 * Implementing this interface allows an object to behave as an instance of a
 * Asymmetric Traveling Salesman Problem. The instance facilitates a certain amount of nodes and can
 * return the distance between any two nodes.
 */
public interface ATSP extends TSPLIBProblem {
	/**
	 * This method should calculate the distance
	 * between nodes i and j by any metric. Counting of nodes starts from 1.
	 * @param node1 the first node i.
	 * @param node2 the second node j.
	 * @return the distance between i and j.
	 * @throws Exception
	 */
	public int distance(int node1, int node2) throws Exception;
}

/**
 * 
 */
package interfaces;

/**
 * Implementing this interface allows an object to behave as an instance of a
 * Hamiltonian Cycle Problem. The instance facilitates a certain amount of nodes and can
 * determine if two nodes are connected.
 */
public interface HCP extends TSPLIBProblem{
	/**
	 * This method should serve as a boolean function which can answer whether the
	 * given nodes i and j are connected or not. Counting of nodes starts from 1.  
	 * @param node1 the first node i.
	 * @param node2 the second node j.
	 * @return true if i and j are connected, false otherwise.
	 * @throws Exception
	 */
	public boolean connected(int node1, int node2) throws Exception;
}

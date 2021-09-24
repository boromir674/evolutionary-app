package interfaces;

public abstract interface TSPLIBProblem {
	/**
	 * This method should return the dimensions of the problem instance.
	 * Usually the number of nodes represent the dimensionality of the problem.
	 * @return the problem's dimensions.
	 * @throws Exception
	 */
	public int getNumberOfNodes() throws Exception;
}

package evolutionaryAlgorithmComponents.evaluation.permutation.TSP;

import interfaces.DistanceCalculator;
import interfaces.TSPLIBProblem;
import evolutionaryAlgorithmComponents.evaluation.AbstractEvaluationMethod;
import exceptions.UnknownSolutionException;

abstract class AbstractTSPLIBEvaluation extends AbstractEvaluationMethod implements TSPLIBProblem {
	
	/* (non-Javadoc)
	 * @see interfaces.EvaluationMethod#getSolutionVector(int)
	 */
	@Override
	public Object[] getSolutionVector(int dimensionality) throws UnknownSolutionException {
		if (solution == null)
			throw new UnknownSolutionException();
		return this.solution;
	}
	private String name;
	protected int dimension;
	protected DistanceCalculator myDistanceCalculator;
	protected double[][] matrix;
	protected double[] vector;
	protected boolean fullMatrixFlag = false;
	protected String triangular;
	private Integer[] solution;

	public AbstractTSPLIBEvaluation(TSPReader aTSPReader, DistanceCalculator aDistanceCalculator, String title) {
		super(title);
		this.myDistanceCalculator = aDistanceCalculator;
		name = aTSPReader.getName();
		dimension = aTSPReader.getDimension();
		if (aTSPReader.getEdgeWeightFormat().equals("FULL_MATRIX"))
			this.fullMatrixFlag = true;
		else
			triangular = aTSPReader.getEdgeWeightFormat();
		matrix = aTSPReader.get2DDataArray();
		vector = aTSPReader.getVector();
		try {
			solution = aTSPReader.getSolutionTour();
		} catch (UnknownSolutionException e) {
			System.out.println(aTSPReader.getPath());
		}
	}

	@Override
	public int getNumberOfNodes() throws Exception{
		return dimension;
	}

	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.AbstractEvaluationMethod#getTitle()
	 */
	@Override
	public String getTitle() {
		return super.getTitle() + " " + name;
	}
	
	protected int fullMatrixDistance(int n1, int n2) {
		return (int) matrix[n1-1][n2-1];
	}

	protected void checkNodes(int node1, int node2) throws Exception{
		if (!(1<=node1 && node1<=getNumberOfNodes() && 1<=node2 && node2<=getNumberOfNodes()))
			throw new Exception(String.format("at least one of the nodes is out of range. Expected:\n"
					+ "1<=%d<=N and 1<=%d<=N\n where N=%d :total number of nodes.", node1, node2, dimension));
	}

	protected int linearIndex(int i, int j){
		//if (triangular.equals(""))
			//throw new Exception("EDGE_WEIGHT_FORMAT field is empty!");
		int index, zeros=0;
		if (triangular.substring(triangular.length()-3, triangular.length()).equals("ROW")){
			if (triangular.substring(0, 5).equals("LOWER"))
				index = lowerRow(i, j);
			else
				index = this.upperRow(i, j);
			
		}
		else {
			if (triangular.substring(0, 5).equals("LOWER"))
				index = this.lowerCol(i, j);
			else
				index = upperCol(i, j);
			if (triangular.charAt(6) == 'D')
				zeros = i + 1; 
		}
		return index + zeros;
	}

	protected int lowerRow(int i, int j){
		if (i<j)
			return lowerRow(j, i);
		int offset = 0;
		for (int k=1; k<i; k++)
			offset += k;
		if (triangular.charAt(6) == 'D')
			return offset + i + j; 
		return offset + j;
	}
	protected int upperCol(int i, int j){
		return lowerRow(j, i);
	}

	protected int lowerCol(int i, int j){
		if (i<j) {
			return lowerCol(j, i);
		}
		int offset = 0;
		for (int k=0; k<j; k++)
			offset += (dimension - k);
		return offset + i-j;
	}
	protected int upperRow(int i, int j){
		return lowerCol(j, i);
	}

}

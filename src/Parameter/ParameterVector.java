package Parameter;

public abstract class ParameterVector {

	private UtilityMeasure utility;
	private int numericalIndex = -1;
	protected Parameter[] parameters;
	
	/**
	 * The assumption of the constructor is that all String type parameters are placed on the first positions
	 * in the list of parameters, followed by the numerical parameters (double and/or int).
	 * It can be the case that the array does not contain Strings at all.  
	 * @param parameters array of Object of type Parameter. Basically an array of String, double and int
	 */
	public ParameterVector(Parameter[] parameters) {
		this.parameters = parameters;
		int i = 0;
		while (numericalIndex == -1) { // finds the index of the first numerical parameter
			if (!(parameters[i].getValue() instanceof String)) {
				this.numericalIndex = i;
			}
			i ++;
		}
	}

	/**
	 * @return an array of objects of type Parameter
	 */
	public Parameter[] getArrayOfParameters() {
		return parameters;
	}

	public void setUtility(UtilityMeasure utility) {
		this.utility = utility;
	}

	/**
	 * @return the symbolic parameters as an array of Strings 
	 */
	public String[] getSymbolicParameters(){
		String[] params = new String[this.numericalIndex];
		for (int i=0; i<this.numericalIndex; i++)
			params[i] = (String) this.parameters[i].getValue();
		return params;
	}

	public Object[] getNumericalParameters() {
		Object[] params = new Object[this.parameters.length-this.numericalIndex];
		for (int i=numericalIndex; i<params.length; i++)
			params[i-numericalIndex] = this.parameters[i].getValue();
		return params;
	}

}

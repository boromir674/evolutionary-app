package Parameter;

public abstract class Parameter {

	/**
	 * @return a representation of an algorithm's parameter. Supported:
	 * <p>
	 * <li>String: for symbolic parameters, eg mutation-operator, parent-selection-method
	 * <li>double or int for numerical parameter: eg mutation-rate, population-size
	 * <p>
	 */
	public abstract Object getValue();
}

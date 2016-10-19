package Parameter;

public class SymbolicParameter extends Parameter {
	
	private String value;
	
	public SymbolicParameter(String variable) {
		this.value = variable;
	}
	@Override
	public Object getValue() {
		return value;
	}

}

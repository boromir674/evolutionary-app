package Parameter;

public class EAParameterVector extends ParameterVector{

	public EAParameterVector(Parameter[] parameters) {
		super(parameters);
	}
	public String rep(){
		return (String) super.parameters[0].getValue();
	}
	public String rec(){
		return (String) super.parameters[1].getValue();
	}
	public String mut(){
		return (String) super.parameters[2].getValue();
	}
	public String parSel() {
		return (String) super.parameters[3].getValue();
	}
	public String survSel() {
		return (String) super.parameters[4].getValue();
	}
}


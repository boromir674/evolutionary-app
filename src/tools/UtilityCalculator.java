package tools;

import interfaces.UtilityEstablishmentStrategy;
import optimizationComponents.SPO;

public abstract class UtilityCalculator implements UtilityEstablishmentStrategy{

	private SPO spo;

	public UtilityCalculator(SPO spo) {
		this.spo = spo;
	}

}

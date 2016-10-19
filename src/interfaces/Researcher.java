/**
 * Implementing this interfaces allows a class/agent to obtain research capabilities
 */

package interfaces;

import simulationComponents.ResearchOutput;

/**
 *
 */
public interface Researcher {
	
	public ResearchOutput performResearch(ExperimentalDesign design);
}

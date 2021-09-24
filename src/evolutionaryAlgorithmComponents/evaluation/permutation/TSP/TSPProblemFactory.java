package evolutionaryAlgorithmComponents.evaluation.permutation.TSP;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators.CeilingEuclidean;
import evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators.Euclidean;
import evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators.GeographicalDistance;
import evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators.ManhattanDistance;
import evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators.Maximum;
import evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators.PseudoEuclidean;
import exceptions.FileFormatNotSupportedException;
import interfaces.EvaluationMethod;
import interfaces.DistanceCalculator;
import util.TSPReader;

public class TSPProblemFactory {

	private TSPReader reader = new TSPReader();

	public TSPProblemFactory(){

	}

	public EvaluationMethod produceTSPProblem(String path) throws Exception{

		reader.parseFile(path);

		EvaluationMethod problem = null;
		DistanceCalculator calculator = null;

		if (reader.getEdgeWeightType().equals("EUC_2D") || reader.getEdgeWeightType().equals("EUC_3D"))
			calculator = new Euclidean();
		else if (reader.getEdgeWeightType().equals("MAX_2D") || reader.getEdgeWeightType().equals("MAX_3D"))
			calculator = new Maximum();
		else if (reader.getEdgeWeightType().equals("MAN_2D") || reader.getEdgeWeightType().equals("MAN_3D"))
			calculator = new ManhattanDistance();
		else if (reader.getEdgeWeightType().equals("GEO"))
			calculator = new GeographicalDistance();
		else if (reader.getEdgeWeightType().equals("ATT"))
			calculator = new PseudoEuclidean();
		else if (reader.getEdgeWeightType().equals("CEIL_2D"))
			calculator = new CeilingEuclidean();

		if (reader.getType().equals("TSP"))
			problem = new TSPEvaluation(reader, calculator);
		else if (reader.getType().equals("HCP"))
			problem = new HCPEvaluation(reader, calculator);
		else if (reader.getType().equals("ATSP"))
			problem = new ATSPEvaluation(reader, calculator);
		else if (reader.getEdgeWeightType().equals("SOP")){
			//TODO problem = new SOPEvaluation(reader, calculator);
		}
		else if (reader.getEdgeWeightType().equals("CVRP")) {
			//TODO problem = new CVRPEvaluation(reader, calculator);
		}
		return problem;
	}

	public boolean testFactoryOnFolder(String folderPath) throws Exception{
		boolean pass = true;
		File f = new File(folderPath);
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
		for (int i=0; i<names.size(); i++){
			System.out.println(":"+names.get(i));
			try {
				EvaluationMethod ev = this.produceTSPProblem(names.get(i));
				try {
					Object[] sol = ev.getSolutionVector();
					for (int j=0; j<sol.length; j++)
						System.out.print(sol[j] + " ");
					System.out.println();
				} catch (NullPointerException e) {

				}
			} catch (FileFormatNotSupportedException e) {
				
			}

		}
		return pass;
	}
}

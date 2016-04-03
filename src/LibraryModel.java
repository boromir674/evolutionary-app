import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public final class LibraryModel {
	
	/*private final ArrayList<String> recombinationModels = new ArrayList<String>();
	private final ArrayList<String> mutationModels = new ArrayList<String>();
	private final ArrayList<String> parentSelectionModels = new ArrayList<String>();
	private final ArrayList<String> survivorSelectionModels = new ArrayList<String>();*/
	
	private final String[] folders = new String[]{"evaluation/realValueEvaluations/",
			"variationOperators/recombination","variationOperators/mutation","parentSelectionMechanisms/","survivorSelectionMechanisms"};

	private final ArrayList[] models = new ArrayList[folders.length];
	
	public LibraryModel(){
		
		for (int i=0; i<folders.length; i++) {
			String path = System.getProperty("user.dir")+"/evolutionaryAlgorithmComponents/" + folders[i];
			File file = new File(path);
			models[i] = new ArrayList<String>(Arrays.asList(file.list()));
		}
		
	}
	
}

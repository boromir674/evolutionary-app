package util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

@SuppressWarnings("unchecked")
public final class LibraryModel extends SimpleFileVisitor<Path> {
	
	//private final static String EA_COMPONENTS_CLASS_ROOT = "evolutionaryAlgorithmComponents.";
	public final static String EA_COMPONENTS_ROOT = System.getProperty("user.dir")+"/src/evolutionaryAlgorithmComponents/";
	public final static String SIMULATION_COMPONENTS_ROOT = System.getProperty("user.dir")+"/src/simulationComponents/";
	// Components parent folders
	private final static String realValueEvaluationRoot = "evaluation/mathFunctions/";
	private final static String recombinationSelectionRoot = "variationOperators/recombination/";
	private final static String mutationSelectionRoot = "variationOperators/mutation/";
	private final static String parentSelectionRoot = "parentSelectionMechanisms/";
	private final static String survivorSelectionRoot = "survivorSelectionMechanisms/";
	private final static String terminationConditionRoot = "terminationConditions/";

	private final static String[] folders = new String[]{EA_COMPONENTS_ROOT+realValueEvaluationRoot,
														EA_COMPONENTS_ROOT+recombinationSelectionRoot,
														EA_COMPONENTS_ROOT+mutationSelectionRoot,
														EA_COMPONENTS_ROOT+parentSelectionRoot,
														EA_COMPONENTS_ROOT+survivorSelectionRoot,
														SIMULATION_COMPONENTS_ROOT+terminationConditionRoot};

	private final static ArrayList<Path>[] models = new ArrayList[folders.length]; // contains Classes implemented
	private static ArrayList<Path> buffer = new ArrayList<Path>();

	private final static LibraryModel INSTANCE = new LibraryModel();

	private LibraryModel(){
		if (INSTANCE != null) {
			throw new IllegalStateException("Already instantiated");
		}
	}
	public final static String PARENT_SELECTION_FOLDER = "evolutionaryAlgorithmComponents.parentSelectionMechanisms.";
	public final static String SURVIVOR_SELECTION_FOLDER = "evolutionaryAlgorithmComponents.survivorSelectionMechanisms";
	//public final static String RECOMBINATION_FOLDER = "evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue.";
	//public final static String MUTATION_FOLDER = "evolutionaryAlgorithmComponents." + mutationSelectionRoot;
	//public final static String REPRESENTATION_ROOT_FOLDER = "evolutionaryAlgorithmComponents/" + representationSelectionRoot;
	//public final static String PARENT_SELECTION_ROOT_FOLDER = "evolutionaryAlgorithmComponents/" + parentSelectionRoot;
	
	
	public static LibraryModel getInstance() {
		return INSTANCE;
	}
	
	public void readLibrary() {
		//models = new ArrayList[folders.length];
		for (int i=0; i<folders.length; i++) {
			models[i] = new ArrayList<Path>();
			Path ppath = FileSystems.getDefault().getPath(folders[i]);
			try {
				Files.walkFileTree(ppath, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//TODO change return types
	public final static ArrayList<Path> getRealValueFunctions(){
		return models[0];
	}
	public final static ArrayList<Path> getCrossoverOperators(){
		return models[1];
	}
	public final static ArrayList<Path> getMutationOperators(){
		return models[2];
	}
	public final static ArrayList<Path> getParentSelectionMethods(){
		return models[3];
	}
	public final static ArrayList<Path> getSurvivorSelectionMethods(){
		return models[4];
	}
	public final static ArrayList<Path> getTerminationConditions(){
		return models[5];
	}

	public final static ArrayList<Path> getRealValueApplicableCrossoverOperators() throws ClassNotFoundException{
		buffer.clear();
		for (int i=0; i<models[1].size(); i++) {
			int l = models[1].get(i).toString().length();
			int s = models[1].get(i).toString().indexOf("/src");
			if (Class.forName(models[1].get(i).toString().substring(s+5, l-5).replace('/', '.')).getSuperclass().getName().contains("RealValueRecombination"))
				buffer.add(models[1].get(i));
		}
		return (ArrayList<Path>) buffer.clone();
	}
	public final static ArrayList<Path> getPermutationApplicableCrossoverOperators() throws ClassNotFoundException{
		buffer.clear();
		for (int i=0; i<models[1].size(); i++) {
			int l = models[1].get(i).toString().length();
			int s = models[1].get(i).toString().indexOf("/src");
			if (Class.forName(models[1].get(i).toString().substring(s+5, l-5).replace('/', '.')).getSuperclass().getName().contains("PermutationRecombination"))
				buffer.add(models[1].get(i));
		}
		return (ArrayList<Path>) buffer.clone();
	}
	public final static ArrayList<Path> getRealValueApplicableMutationOperators() throws ClassNotFoundException{
		buffer.clear();
		for (int i=0; i<models[2].size(); i++) {
			int l = models[2].get(i).toString().length();
			int s = models[2].get(i).toString().indexOf("/src");
			if (Class.forName(models[2].get(i).toString().substring(s+5, l-5).replace('/', '.')).getSuperclass().getName().contains("RealValueMutation"))
				buffer.add(models[2].get(i));
		}
		return (ArrayList<Path>) buffer.clone();
	}
	public final static ArrayList<Path> getPermutationApplicableMutationOperators() throws ClassNotFoundException{
		buffer.clear();
		for (int i=0; i<models[2].size(); i++) {
			int l = models[2].get(i).toString().length();
			int s = models[2].get(i).toString().indexOf("/src");
			if (Class.forName(models[2].get(i).toString().substring(s+5, l-5).replace('/', '.')).getSuperclass().getName().contains("PermutationMutation"))
				buffer.add(models[2].get(i));
		}
		return (ArrayList<Path>) buffer.clone();
	}
	private static void updateModel(Path file) {
		if (accept(null, file.toString()))
			for (int i=0; i<folders.length; i++)
				if (file.toString().contains(folders[i])) {
					//models[i].add(file.toFile().getName().substring(0, file.toFile().getName().length()-5));
					models[i].add(file);
					break;
				}
	}
	private static boolean accept(File dir, String name) {
		if (name.contains("Abstract")|| !name.contains(".java") || name.contains("Test"))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFile(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	 */
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {
		updateModel(file);
		return super.visitFile(file, attrs);
	}

}

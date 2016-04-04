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

public final class LibraryModel extends SimpleFileVisitor<Path> {

	private final static String EA_COMPONENTS_ROOT = System.getProperty("user.dir")+"/src/evolutionaryAlgorithmComponents/"; 
	// Components parent folders
	private final static String realValueEvaluationRoot = "evaluation/mathFunctions/";
	private final static String recombinationSelectionRoot = "variationOperators/recombination/";
	private final static String mutationSelectionRoot = "variationOperators/mutation/";
	private final static String parentSelectionRoot = "parentSelectionMechanisms/";
	private final static String survivorSelectionRoot = "survivorSelectionMechanisms/";

	private final static String[] folders = new String[]{realValueEvaluationRoot,recombinationSelectionRoot,
			mutationSelectionRoot,parentSelectionRoot,survivorSelectionRoot};
	
	@SuppressWarnings("unchecked")
	private final static ArrayList<Path>[] models = new ArrayList[folders.length]; // contains Classes implemented
	
	public LibraryModel(){
		this.readLibrary();
	}

	private void readLibrary() {
		//models = new ArrayList[folders.length];
		for (int i=0; i<folders.length; i++) {
			models[i] = new ArrayList<Path>();
			String path = EA_COMPONENTS_ROOT + folders[i];
			Path ppath = FileSystems.getDefault().getPath(path);
			try {
				Files.walkFileTree(ppath, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//TODO change return types
	public final static Path[] getRealValueFunctions(){
		return (Path[]) models[0].toArray();
	}
	public final static Path[] getCrossoverOperators(){
		return (Path[]) models[1].toArray();
	}
	public final static Path[] getMutationOperators(){
		return (Path[]) models[2].toArray();
	}
	public final static Path[] getParentSelectionMethods(){
		return (Path[]) models[3].toArray();
	}
	public final static Path[] getSurvivorSelectionMethods(){
		return (Path[]) models[4].toArray();
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

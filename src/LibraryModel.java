import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public final class LibraryModel extends SimpleFileVisitor<Path> implements FilenameFilter {
	
	private final static String EA_COMPONENTS_ROOT = System.getProperty("user.dir")+"/src/evolutionaryAlgorithmComponents/"; 
	// Components parent folders
	private final static String realValueEvaluationRoot = "evaluation/realValueEvaluations/";
	private final static String recombinationSelectionRoot = "variationOperators/recombination/";
	private final static String mutationSelectionRoot = "variationOperators/mutation/";
	private final static String parentSelectionRoot = "parentSelectionMechanisms/";
	private final static String survivorSelectionRoot = "survivorSelectionMechanisms/";
	
	private final String[] folders = new String[]{realValueEvaluationRoot,recombinationSelectionRoot,
			mutationSelectionRoot,parentSelectionRoot,survivorSelectionRoot};

	@SuppressWarnings("unchecked")
	private final ArrayList<String>[] models = new ArrayList[folders.length];
	
	public LibraryModel(){

		for (int i=0; i<folders.length; i++) {
			models[i] = new ArrayList<String>();
			String path = EA_COMPONENTS_ROOT + folders[i];
			Path ppath = FileSystems.getDefault().getPath(path);
			File file = new File(path);
			file.listFiles(this);
			try {
				Files.walkFileTree(ppath, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateModel(Path file) {
		if (accept(null, file.toString())){
			if (file.startsWith(EA_COMPONENTS_ROOT+realValueEvaluationRoot)){
				models[0].add(file.toFile().getName().substring(0, file.toFile().getName().length()-5));
			} else if (file.startsWith(EA_COMPONENTS_ROOT+recombinationSelectionRoot)){
				models[1].add(file.toFile().getName().substring(0, file.toFile().getName().length()-5));
			} else if (file.startsWith(EA_COMPONENTS_ROOT+mutationSelectionRoot)){
				models[2].add(file.toFile().getName().substring(0, file.toFile().getName().length()-5));
			} else if (file.startsWith(EA_COMPONENTS_ROOT+parentSelectionRoot)){
				models[3].add(file.toFile().getName().substring(0, file.toFile().getName().length()-5));
			} else if (file.startsWith(EA_COMPONENTS_ROOT+survivorSelectionRoot)){
				models[4].add(file.toFile().getName().substring(0, file.toFile().getName().length()-5));
			}
		}
	}

	@Override
	public boolean accept(File dir, String name) {
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

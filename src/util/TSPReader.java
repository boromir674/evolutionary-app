package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.commons.lang3.ArrayUtils;

import exceptions.FileFormatNotSupportedException;

/**
 * An utility class that is used to parse .tsp files.
 */
public class TSPReader {
	/**
	 * TSP  : xray.problems not supported yet.
	 * ATSP : all supported.
	 * HCP  : all supported.
	 */
	private final static String[] allTypes = new String[]{"TSP", "ATSP", "SOP", "HCP", "CVRP", "TOUR"};
	private final static String[] allEdgeWeightTypes = new String[]{"EXPLICIT", "EUC_2D", "EUC_3D", "MAX_2D", 
		"MAX_3D", "MAN_2D", "MAN_3D", "CEIL_2D", "GEO", "ATT", "XRAY1", "XRAY2", "SPECIAL"};
	private final static String[] allEdgeWeightFormats = new String[]{"FUNCTION", "FULL_MATRIX", "UPPER_ROW",
		"LOWER_ROW", "UPPER_DIAG_ROW", "LOWER_DIAG_ROW", "UPPER_COL", "LOWER_COL", "UPPER_DIAG_COL", "LOWER_DIAG_COL"};
	private final static String[] allEdgaDataFormats = new String[]{"EDGE_LIST", "ADJ_LIST"};
	private final static String[] allNodeCoordTypes = new String[]{"TWOD_COORDS", "THREED_COORDS", "NO_COORDS"};
	private final static String[] allDisplayDataTypes = new String[]{"COORD_DISPLAY", "TWOD_DISPLAY", "NO_DISPLAY"};
	private final static String[] allSections = new String[]{"NODE_COORD_SECTION", "DEPOT_SECTION", "DEMAND_SECTION",
		"EDGE_DATA_SECTION", "FIXED_EDGES_SECTION", "DISPLAY_DATA_SECTION", "TOUR_SECTION", "EDGE_WEIGHT_SECTION"};

	private final static HashSet<String> dataTypes = new HashSet<String>(6);
	private final static HashSet<String> edgeWeightTypes = new HashSet<String>(13);
	private final static HashSet<String> edgeWeightFormats = new HashSet<String>(10);
	private final static HashSet<String> edgeDataFormats = new HashSet<String>(2);
	private final static HashSet<String> nodeCoordTypes = new HashSet<String>(3);
	private final static HashSet<String> displayDataTypes = new HashSet<String>(3);
	private final static HashSet<String> sections = new HashSet<String>(8);
	
	// private members with getters
	private String path;
	private String name;
	private String type;
	private String edgeWeightType;
	private String edgeWeightFormat;
	private String edgeDataFormat;
	private String nodeCoordType;
	private String displayDataType;
	private int dimension;
	private int capacity;
	private String section;
	private double[][] matrix;
	private double[] vector;
	private int vCounter;
	private int[][] fixedEdges;
	
	// local variables
	private FileReader reader;
	private BufferedReader bf;
	// TODO convert ArrayList to array of pointers to various-length arrays.
	private ArrayList<int[]> _fixedEdges;
	private ArrayList<int[]> _list;
	private int[] _matrixIndices;
	private Integer[] solutionTour;

	public TSPReader() {
		for (int i=0; i<allTypes.length; i++)
			dataTypes.add(allTypes[i]);
		for (int i=0; i<allEdgeWeightTypes.length; i++)
			edgeWeightTypes.add(allEdgeWeightTypes[i]);
		for (int i=0; i<allEdgeWeightFormats.length; i++)
			edgeWeightFormats.add(allEdgeWeightFormats[i]);
		for (int i=0; i<allEdgaDataFormats.length; i++)
			edgeDataFormats.add(allEdgaDataFormats[i]);
		for (int i=0; i<allNodeCoordTypes.length; i++)
			nodeCoordTypes.add(allNodeCoordTypes[i]);
		for (int i=0; i<allDisplayDataTypes.length; i++)
			displayDataTypes.add(allDisplayDataTypes[i]);
		for (int i=0; i<allSections.length; i++)
			sections.add(allSections[i]);
	}

	public void parseFile(String file) throws Exception{
		String folder = decideOnFolder(file);
		if (folder.equals(""))
			throw new FileFormatNotSupportedException();
		this.initialize();
		this.path = System.getProperty("user.dir") + "/TSP_samples"+folder;
		reader = new FileReader(path);
		bf = new BufferedReader(reader);
		this.parseInfoUntilSection();
		this.complete();
		if (section.equals("FIXED_EDGES_SECTION")){
			_fixedEdges = new ArrayList<int[]>();
			this.parseFixedEdgeSection();
			section = "";
			this.parseInfoUntilSection();}
		this.parseAllData();
		bf.close();
		try {
			parseOptimumTour();
		} catch (IOException e){
			System.out.println(this.name + " has no opt");
		}
	}

	private void parseOptimumTour() throws Exception{
		String path1 = this.path.substring(0, this.path.indexOf('.')) + ".opt.tour";
		reader = new FileReader(path1);
		bf = new BufferedReader(reader);
		solutionTour = new Integer[dimension];
		String line = bf.readLine();
		while (!line.equals("TOUR_SECTION")) {
			line = bf.readLine();
		}
		line = bf.readLine().trim();
		String[] pLine;
		int i = 0;
		while (!(line.equals("-1") || line == null || line.equals("EOF") || line.trim().equals(""))) {;
			pLine = prepareForParsing(line);
			int j;
			for (j=0; j<pLine.length; j++)
				solutionTour[i+j] = Integer.parseInt(pLine[j].trim());
			i += j;
			line = bf.readLine().trim();
		}
		bf.close();
	}

	private void parseFixedEdgeSection() throws Exception {
		String line = null;
		while (true) {
			line = bf.readLine();
			if (line.trim().equals("-1"))
				break;
			this.readLineFixedEdges(line);
		}
	}

	private void parseInfoUntilSection() throws Exception{

		String line;
		do {
			line = bf.readLine().trim();
			if (line.substring(0, 4).equals("NAME"))
				this.name = (line.substring(line.indexOf(":")+1, line.length())).trim();
			else if (line.substring(0, 4).equals("TYPE")){
				this.type = decideOnType(line);
				if (!dataTypes.contains(type))
					throw new Exception("Unknown data type: "+type);}
			else if (line.substring(0, 9).equals("DIMENSION"))
				this.dimension = Integer.parseInt((line.substring(line.indexOf(":")+1, line.length())).trim());
			else if (line.substring(0, 8).equals("CAPACITY"))
				this.capacity = Integer.parseInt((line.substring(line.indexOf(":")+1, line.length())).trim());
			else if (line.substring(line.length()-7, line.length()).equals("SECTION")){
				this.section = line.trim();
				if (!sections.contains(section))
					throw new Exception("Unknown data section");}
			else if (line.substring(0, 15).equals("NODE_COORD_TYPE")){
				this.nodeCoordType = (line.substring(line.indexOf(":")+1, line.length())).trim();
				if (!nodeCoordTypes.contains(nodeCoordType))
					throw new Exception("Unknown node coord type");}
			else if (line.substring(0, 16).equals("EDGE_WEIGHT_TYPE")){
				this.edgeWeightType = (line.substring(line.indexOf(":")+1, line.length())).trim();
				if (!edgeWeightTypes.contains(edgeWeightType))
					throw new Exception("Unknown edge weight type");}
			else if (line.substring(0, 17).equals("DISPLAY_DATA_TYPE")){
				this.displayDataType = (line.substring(line.indexOf(":")+1, line.length())).trim();
				if (!displayDataTypes.contains(displayDataType))
					throw new Exception("Unknown display data type");}
			else if (line.substring(0, 18).equals("EDGE_WEIGHT_FORMAT")){
				this.edgeWeightFormat = (line.substring(line.indexOf(":")+1, line.length())).trim();
				if (!edgeWeightFormats.contains(edgeWeightFormat))
					throw new Exception("Unknown edge weight format");}
			else if (line.substring(0, 16).equals("EDGE_DATA_FORMAT")){
				this.edgeDataFormat = (line.substring(line.indexOf(":")+1, line.length())).trim();
				if (!edgeDataFormats.contains(edgeDataFormat))
					throw new Exception("Unknown edge data format");}
		} while (section.equals(""));
	}

	private void parseAllData() throws Exception {
		this.initializeDataArray();
		String line = bf.readLine().trim();
		do {
			if (line.equals("EOF") || line.equals("-1") || line.equals("") || line.equals("DISPLAY_DATA_SECTION"))
				break;
			this.parseDataLine(line);
			try {
				line = bf.readLine().trim();
			} catch (Exception e) {
				line = null;
			}
		} while (line != null);
	}
	private void complete(){
		if (!nodeCoordType.equals("NO_COORDS"))
			displayDataType = "COORD_DISPLAY";
	}

	private void initializeDataArray() throws Exception{
		if (!edgeWeightType.equals("EXPLICIT")){
			if (edgeWeightType.equals("EUC_3D") || edgeWeightType.equals("MAN_3D") ||edgeWeightType.equals("MAX_3D"))
				matrix = new double[dimension][3];
			else
				matrix = new double[dimension][2];
		}
		else if (edgeWeightFormat.equals("FULL_MATRIX"))
			matrix = new double[dimension][dimension];
		else if (!edgeDataFormat.equals("")) {
			_list = new ArrayList<int[]>();
		}
		else if (edgeWeightFormat.equals("UPPER_ROW")|| edgeWeightFormat.equals("LOWER_ROW")
				|| edgeWeightFormat.equals("UPPER_COL") || edgeWeightFormat.equals("LOWER_COL"))
			vector = new double[dimension*(dimension-1)/2]; // # elements = n(n-1)/2
		else if (edgeWeightFormat.equals("UPPER_DIAG_ROW")|| edgeWeightFormat.equals("LOWER_DIAG_ROW")
				|| edgeWeightFormat.equals("UPPER_DIAG_COL") || edgeWeightFormat.equals("LOWER_DIAG_COL"))
			vector = new double[dimension + dimension*(dimension-1)/2]; // # elements = n + n(n-1)/2

		if (_fixedEdges != null)
			fixedEdges = new int[_fixedEdges.size()][2];
		if (matrix==null && vector==null && _list == null)
			throw new Exception("no data structure inititialized");
	}

	private void parseDataLine(String line) throws Exception{
		if (line.length() == 0)
			throw new Exception("empty string came as input: blank line");
		if (!edgeWeightType.equals("EXPLICIT"))
			this.readCoordsDataLine(line);
		else {
			if (edgeWeightFormat.equals("FULL_MATRIX"))
				this.readFullMatrix(line);
			else {
				if (edgeWeightFormat.equals(""))
					this.readIntoArrayList(line);
				else
					this.readAnyTriangular(line);
			}
		}
	}

	private void readLineFixedEdges(String line) throws Exception {
		String[] pLine = prepareForParsing(line);
		_fixedEdges.add(new int[2]);
		_fixedEdges.get(_fixedEdges.size()-1)[0] = Integer.parseInt(pLine[0]);
		_fixedEdges.get(_fixedEdges.size()-1)[1] = Integer.parseInt(pLine[1]);
	}
	private void readCoordsDataLine(String line) throws Exception{
		String[] pLine = prepareForParsing(line);
		for (int i=1; i<pLine.length; i++)
			matrix[Integer.parseInt(pLine[0])-1][i-1] = Double.parseDouble(pLine[i]);
	}
	private void readFullMatrix(String line) throws Exception{
		String[] pLine = prepareForParsing(line);
		for (int i=0; i<pLine.length; i++){
			matrix[_matrixIndices[0]][_matrixIndices[1]] = Double.parseDouble(pLine[i]);
			_matrixIndices[1] ++;
			_matrixIndices[0] += (int) (_matrixIndices[1] / matrix[0].length);  
			_matrixIndices[1] %= matrix[0].length;
		}
	}
	private void readIntoArrayList(String line) throws Exception{
		String[] pLine = prepareForParsing(line);
		this._list.add(new int[pLine.length]);
		for (int i=0; i<pLine.length; i++)
			this._list.get(_list.size()-1)[i] = Integer.parseInt(pLine[i]);
	}
	private void readAnyTriangular(String line) throws Exception{
		String[] pLine = prepareForParsing(line);
		int i;
		for (i=0; i<pLine.length; i++)
			vector[i+vCounter] = Double.parseDouble(pLine[i]);
		vCounter += i;
	}

	private static String[] prepareForParsing(String aString) throws Exception{
		char[] s = aString.toCharArray();
		int l = s.length;
		int i = -1;
		boolean flag = false;
		while (i<l){
			while (!flag){
				i ++;
				if (i == l)
					break;
				if (s[i] == ' '){
					flag = true;
					s[i] = 'w';}}
			flag = false;
			while (!flag){
				if (i == l)
					break;
				i ++;
				if (s[i] != ' ')
					flag = true;}
			flag = false;}

		String temp = new String(s);
		temp = temp.trim();
		String[] pLine = temp.split("w");
		if (pLine.length == 0)
			throw new Exception("Zero length after splitting");
		return pLine;
	}

	private void initialize() {
		path = "";
		name = "";
		type = "";
		edgeWeightType = "";
		edgeWeightFormat = "";
		edgeDataFormat = "";
		nodeCoordType = "NO_COORDS";
		displayDataType = "NO_DISPLAY";
		dimension = -1;
		capacity = -1;
		section = "";
		matrix = null;
		_matrixIndices = new int[2];
		vector = null;
		vCounter = 0;
		solutionTour = null;
		fixedEdges = null;
		_fixedEdges = null;
		solutionTour = null;
	}
	private static String decideOnType(String line) {
		String temp = line.substring(line.indexOf(":")+1, line.length());
		if (temp.contains("TSP"))
			return "TSP";
		else if (temp.contains("ATSP"))
			return "ATSP";
		else if (temp.contains("HCP"))
			return "HCP";
		else if (temp.contains("SOP"))
			return "SOP";
		else return "CVRP";
	}

	private static String decideOnFolder(String name){
		if (name.substring(name.length()-4, name.length()).equals(".tsp"))
			return "/TSP/"+name;
		else if (name.substring(name.length()-4, name.length()).equals(".hcp"))
			return "/HCP/"+name;
		else if (name.substring(name.length()-5, name.length()).equals(".atsp"))
			return "/ATSP/"+name;
		else if (name.substring(name.length()-4, name.length()).equals(".sop"))
			return "/SOP/"+name;
		else if (name.substring(name.length()-4, name.length()).equals(".cvrp"))
			return "/CVRP/"+name;
		else
			return "";
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the edgeWeightType
	 */
	public String getEdgeWeightType() {
		return edgeWeightType;
	}
	/**
	 * @return the edgeWeightFormat
	 */
	public String getEdgeWeightFormat() {
		return edgeWeightFormat;
	}
	/**
	 * @return the edgeDataFormat
	 */
	public String getEdgeDataFormat() {
		return edgeDataFormat;
	}
	/**
	 * @return the nodeCoordType
	 */
	public String getNodeCoordType() {
		return nodeCoordType;
	}
	/**
	 * @return the displayDataType
	 */
	public String getDisplayDataType() {
		return displayDataType;
	}
	/**
	 * @return the dimension
	 */
	public int getDimension() {
		return dimension;
	}
	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * @return a double[n][]
	 */
	public double[][] get2DDataArray() {
		if (_list != null){
			matrix = new double[_list.size()][];
			for (int i=0; i<_list.size(); i++){
				for (int j=0; j<_list.get(i).length; j++){
					matrix[i][j] =  _list.get(i)[j];
				}
			}
		}
		return matrix;
	}
	/**
	 * @return the fixedEdges
	 */
	public int[][] getFixedEdges() {
		return fixedEdges;
	}

	/**
	 * @return the vector
	 */
	public double[] getVector() {
		return vector;
	}
	
	/**
	 * @return the solutionTour
	 */
	public Integer[] getSolutionTour() {
		return solutionTour;
	}
}

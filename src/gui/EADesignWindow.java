package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;

import java.awt.CardLayout;

import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.List;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.GridLayout;
import java.awt.TextField;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.Label;
import java.awt.Font;

import javax.swing.UIManager;


public class EADesignWindow {

	private JFrame frame;
	private File file;
	private String path;
	ArrayList<String> names;
	private final String[] parentSelectionSet = new String[]{"Fitness Proportional", "Tournament", "Ranking", "Random"};
	private final String[] survivorSelectionSet = new String[]{"Fitness Proportional","Tournament","Deterministic (μ+λ)","Deterministic (μ,λ)","Deterministic Crowding"};
	private final static String[] allRecombinationSet = new String[]{"Simple Arithmetic", "Single Arithmetic", "Whole Arithmetic","Cycle Crossover",
			"Edge Crossover", "Order Crossover", "PMX", "N-point Crossover", "Uniform Crossover"};
	private final String[] realValueRecombinationSet = new String[]{"Simple Arithmetic", "Single Arithmetic", "Whole Arithmetic"};
	private final String[] permutationRecombinationSet = new String[]{"Cycle Crossover", "Edge Crossover", "Order Crossover", "PMX"};
	private final String[] genericRecombinationSet = new String[]{"N-point Crossover", "Uniform Crossover"};
	private final static String[] allMutationSet = new String[]{"Bitwise","Creep","Insert","Invert","Random Reseting", "Scramble",
		"Swap", "Correlated", "Ucorrelated with n sigmas", "Ucorrelated with 1 sigma", "Uniform"};
	private final JTextField dimensionalityJTextField = new JTextField();
	private final JLabel lblProblemInstance = new JLabel("");
	private final JLabel lblDimensionality = new JLabel("dimensionality");
	private JComboBox<JLabel> mutationJComboBox = new JComboBox<JLabel>();
	private JComboBox<JLabel> recombinationJComboBox = new JComboBox<JLabel>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EADesignWindow window = new EADesignWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EADesignWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		recombinationJComboBox = new JComboBox(allRecombinationSet);
		mutationJComboBox = new JComboBox(allMutationSet);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 707, 579);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(32, 192, 129, 21);
		frame.getContentPane().add(menuBar_1);

		JMenu mnEvaluation = new JMenu("evaluation");
		menuBar_1.add(mnEvaluation);

		JMenu mnRealValue = new JMenu("Real Value");

		mnEvaluation.add(mnRealValue);
		names = getContents("/src/evolutionaryAlgorithmComponents/evaluation/realValueEvaluations");
		JMenuItem[] menuContents = new JMenuItem[names.size()];
		this.populateMenu(mnRealValue, menuContents);

		JMenu mnPermutation = new JMenu("Permutation");
		mnEvaluation.add(mnPermutation);

		JMenu mnTsp = new JMenu("TSP");
		mnTsp.setToolTipText("Traveling Salesman Problem");
		mnTsp.setLayout(new GridLayout(30, 3));
		mnPermutation.add(mnTsp);
		names = getContents("/TSP_samples/TSP/");
		menuContents = new JMenuItem[names.size()];
		//tspMenuList.setLayout(new GridLayout(30, 3));
		this.populateMenu(mnTsp, menuContents);

		JMenu mnAtsp = new JMenu("ATSP");
		mnAtsp.setToolTipText("Asymmetric Traveling Salesman Problem");
		mnPermutation.add(mnAtsp);
		names = getContents("/TSP_samples/ATSP/");
		menuContents = new JMenuItem[names.size()];
		this.populateMenu(mnAtsp, menuContents);

		JMenu mnHcp = new JMenu("HCP");
		mnHcp.setToolTipText("Hamiltonian Cycle Problem");
		mnPermutation.add(mnHcp);
		names = getContents("/TSP_samples/HCP/");
		menuContents = new JMenuItem[names.size()];
		this.populateMenu(mnHcp, menuContents);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(132, 252, 273, 122);
		frame.getContentPane().add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new GridLayout(5, 1));
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new GridLayout(5, 1));
		
		JLabel label_4 = new JLabel("Evaluation");
		panel.add(label_4);
		panel_1.add(lblProblemInstance);

		JLabel lblParentSelection = new JLabel("Parent Selection");
		panel.add(lblParentSelection);

		JComboBox parentSelectionList = new JComboBox(parentSelectionSet);
		panel_1.add(parentSelectionList);

		JLabel lblSurvivorSelection = new JLabel("Survivor Selection");
		panel.add(lblSurvivorSelection);

		JComboBox survivorSelectionList = new JComboBox(survivorSelectionSet);
		panel_1.add(survivorSelectionList);
		
		JLabel label_2 = new JLabel("Recombination");
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Mutation");
		panel.add(label_3);
		
		panel_1.add(recombinationJComboBox);
		
		panel_1.add(mutationJComboBox);

		JSplitPane splitPane_6 = new JSplitPane();
		splitPane_6.setBounds(32, 116, 252, 32);
		frame.getContentPane().add(splitPane_6);
		splitPane_6.setResizeWeight(0.5);

		splitPane_6.setLeftComponent(lblDimensionality);
		dimensionalityJTextField.setFont(UIManager.getFont("Button.font"));

		splitPane_6.setRightComponent(dimensionalityJTextField);
		dimensionalityJTextField.setColumns(10);

		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setBounds(32, 33, 115, 32);
		frame.getContentPane().add(splitPane_4);
		splitPane_4.setResizeWeight(0.2);

		JLabel label_1 = new JLabel("λ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setToolTipText("number of children to produce per generation (should be even number)");
		splitPane_4.setLeftComponent(label_1);

		TextField textField_1 = new TextField();
		textField_1.setFont(UIManager.getFont("Button.font"));
		splitPane_4.setRightComponent(textField_1);

		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setBounds(32, 77, 115, 27);
		frame.getContentPane().add(splitPane_2);
		splitPane_2.setToolTipText("population size");
		splitPane_2.setResizeWeight(0.2);

		JLabel label = new JLabel("μ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane_2.setLeftComponent(label);

		TextField textField = new TextField();
		textField.setFont(UIManager.getFont("Button.font"));
		splitPane_2.setRightComponent(textField);
		
		JLabel lblRepresentation = new JLabel("Representation");
		lblRepresentation.setBounds(32, 148, 121, 32);
		frame.getContentPane().add(lblRepresentation);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(157, 148, 129, 32);
		frame.getContentPane().add(label_5);

	}

	/*private void populateMenu(JPopupMenu anEvaluationSubmenu, JMenuItem[] folderContents) {
		for (int i=0; i<folderContents.length; i++){
			folderContents[i] = new JMenuItem(names.get(i));
			anEvaluationSubmenu.add(folderContents[i]);
			folderContents[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					lblProblemInstance.setText(arg0.getActionCommand());
					String dim = parseDimensions(arg0.getActionCommand());
					dimensionalityJTextField.setText(dim);
					if (!dim.isEmpty())
						dimensionalityJTextField.setEnabled(false);

					else
						dimensionalityJTextField.setEnabled(true);						
				}
			});
		}		
	}*/

	private ArrayList<String> getContents(String aRelativePath){
		path = System.getProperty("user.dir") + aRelativePath;
		file = new File(path);
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(file.list()));
		return names;
	}

	private void populateMenu(JMenu anEvaluationSubmenu, JMenuItem[] folderContents){
		// anEvaluationSubmenu: parent of a leaf node in the evaluation tree
		// it also stores the references of the menu's items 
		for (int i=0; i<folderContents.length; i++){
			folderContents[i] = new JMenuItem(names.get(i));
			anEvaluationSubmenu.add(folderContents[i]);
			folderContents[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					lblProblemInstance.setText(arg0.getActionCommand());
					String dim = parseDimensions(arg0.getActionCommand());
					dimensionalityJTextField.setText(dim);
					if (!dim.isEmpty())
						dimensionalityJTextField.setEnabled(false);

					else
						dimensionalityJTextField.setEnabled(true);						
				}
			});
		}
	}
	private static String parseDimensions(String aFileName){
		int i = 1;
		int start = 0;
		int end = 0;
		while ((start == 0 || end == 0) && i<aFileName.length()){
			char ch0 = aFileName.charAt(i-1);
			char ch1 = aFileName.charAt(i);
			if (!Character.isDigit(ch0) && Character.isDigit(ch1))
				start = i;
			if (Character.isDigit(ch0) && !Character.isDigit(ch1))
				end = i;
			i++;
		}
		if (end == 0)
			return "";
		return aFileName.substring(start, end);
	}
	/*private String[] decideOnOperatorSet(JMenu menu){
		if (menu.getText().equals("Real Value"))
			return realValueRecombinationSet;
		else //(menu.getText().equals("Permutation"))
			return permutationRecombinationSet;
	}*/

}

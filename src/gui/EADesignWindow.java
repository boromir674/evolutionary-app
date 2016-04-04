package gui;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.apache.commons.lang3.ArrayUtils;

import evolutionaryAlgorithmComponents.EvolutionaryAlgorithm;
import util.LibraryModel;

import java.awt.Font;

import javax.swing.JButton;

public class EADesignWindow {

	private JFrame frmEaRunner;
	private File file;
	private String path;
	ArrayList<String> names;
	
	private final static String[] elitistSelectionSet = new String[]{"MuPlusLambda", "DeterministicCrowding"};
	private final static String[] genericRecombinationSet = new String[]{"N-point Crossover", "Uniform Crossover"};
	private final static String[] realValueRecombinationSet = new String[]{"Simple Arithmetic", "Single Arithmetic", "Whole Arithmetic"};
	private final static String[] permutationRecombinationSet = new String[]{"Cycle Crossover", "Edge Crossover", "Order Crossover", "PMX"};
	private final static String[] realValueMutationSet = new String[]{"Correlated", "Non uniform", "Ucorrelated with n sigmas", "Ucorrelated with 1 sigma", "Uniform"};
	private final static String[] permutationMutationSet = new String[]{"Creep","Insert","Invert","Scramble","Swap"};
	
    final JLabel lblProblemInstance = new JLabel("");
	private final JLabel lblDimensionality = new JLabel("d: ");
		
	private JMenu mnRealValue = new JMenu("Real Value");
	private JMenu mnPermutation = new JMenu("Permutation");
	private JLabel lblRepresentation = new JLabel("Representation");
	private JLabel lblRepresentationDescription = new JLabel("                                 "
			+ "                                  ");
	// package visibility
	JTextField lambdaJTextField = new JTextField();
	JTextField muJTextField = new JTextField();
	final JTextField dJTextField = new JTextField();
	JTextField recombinationRateJTextField = new JTextField();
	JTextField mutationRateJTextField = new JTextField();
	JComboBox<JLabel> recombinationJComboBox = new JComboBox<JLabel>();
	JComboBox<JLabel> mutationJComboBox = new JComboBox<JLabel>();	
	JComboBox<JLabel> parentSelectionList = new JComboBox<JLabel>();
	JComboBox<JLabel> survivorSelectionList = new JComboBox<JLabel>();
	final JRadioButton rdbtnElitismOn = new JRadioButton("on");
	private EADesignWindowParser parser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryModel model = new LibraryModel();
					EADesignWindow window = new EADesignWindow();
					window.frmEaRunner.setVisible(true);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		parser = new EADesignWindowParser(this);
		setDefaultValues();
		frmEaRunner = new JFrame();
		frmEaRunner.setResizable(false);
		frmEaRunner.setTitle("EA Runner");
		frmEaRunner.setBounds(100, 100, 554, 562);
		frmEaRunner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmEaRunner.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		frmEaRunner.getContentPane().setLayout(null);

		JMenuBar flowtingMenuBar = new JMenuBar();
		flowtingMenuBar.setToolTipText("Select instance ");

		flowtingMenuBar.setBounds(166, 135, 88, 21);
		frmEaRunner.getContentPane().add(flowtingMenuBar);

		final JMenu mnEvaluation = new JMenu("evaluation");
		mnEvaluation.setHorizontalAlignment(SwingConstants.CENTER);

		flowtingMenuBar.add(mnEvaluation);
		mnRealValue.setName("Vector of real numbers");
		mnEvaluation.add(mnRealValue);
		names = getContents("/src/evolutionaryAlgorithmComponents/evaluation/realValueEvaluations");
		JMenuItem[] menuContents = new JMenuItem[names.size()];
		this.populateMenu(mnRealValue, menuContents);

		mnPermutation.setName("Permutation of integers");
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
		splitPane.setBounds(166, 12, 373, 122);
		frmEaRunner.getContentPane().add(splitPane);

		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("OptionPane.messageAreaBorder"));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new GridLayout(5, 1));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(UIManager.getBorder("OptionPane.buttonAreaBorder"));
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new GridLayout(5, 1));
		panel.add(lblRepresentation);
		
				JLabel label_2 = new JLabel("Recombination");
				panel.add(label_2);
		
				JLabel label_3 = new JLabel("Mutation");
				panel.add(label_3);

		JLabel lblParentSelection = new JLabel("Parent Selection");
		panel.add(lblParentSelection);
		lblRepresentationDescription.setVerticalAlignment(SwingConstants.TOP);
		panel_1.add(lblRepresentationDescription);
		lblRepresentationDescription.setText("");
		recombinationJComboBox.setFont(UIManager.getFont("ComboBox.font"));

		recombinationJComboBox.setModel(new DefaultComboBoxModel(LibraryModel.getCrossOverOperators().toArray()));
		panel_1.add(recombinationJComboBox);
		
				panel_1.add(mutationJComboBox);
				mutationJComboBox.setModel(new DefaultComboBoxModel(LibraryModel.getMutationOperators().toArray()));
		
		panel_1.add(parentSelectionList);
		parentSelectionList.setModel(new DefaultComboBoxModel(LibraryModel.getParentSelectionMethods().toArray()));

		JLabel lblSurvivorSelection = new JLabel("Survivor Selection");
		panel.add(lblSurvivorSelection);
		
		survivorSelectionList.setModel(new DefaultComboBoxModel(LibraryModel.getSurvivorSelectionMethods().toArray()));
		panel_1.add(survivorSelectionList);
														lblProblemInstance.setBounds(272, 136, 267, 20);
														frmEaRunner.getContentPane().add(lblProblemInstance);
		final ButtonGroup group = new ButtonGroup();
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(12, 12, 147, 70);
		frmEaRunner.getContentPane().add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 1));
								
								JPanel panel_6 = new JPanel();
								panel_6.setBounds(83, 12, 52, 61);
								panel_6.setLayout(new BorderLayout(0, 0));
								
								JLabel elitismJLabel = new JLabel("Elitism");
								elitismJLabel.setHorizontalAlignment(SwingConstants.LEFT);
								elitismJLabel.setVerticalAlignment(SwingConstants.BOTTOM);
								panel_6.add(elitismJLabel, BorderLayout.NORTH);
								elitismJLabel.setToolTipText("Population's property of preserving the fittest individual through the generations");
								
								JPanel panel_4 = new JPanel();
								panel_4.setBorder(UIManager.getBorder("RadioButton.border"));
								panel_6.add(panel_4, BorderLayout.SOUTH);
								panel_5.add(panel_6, BorderLayout.EAST);
								
								panel_4.setLayout(new GridLayout(2, 1, 0, 0));
								
								
								rdbtnElitismOn.setFont(UIManager.getFont("RadioButton.font"));
								rdbtnElitismOn.setToolTipText("Satisfied either by natural selection or by force");
								rdbtnElitismOn.setHorizontalAlignment(SwingConstants.CENTER);
								rdbtnElitismOn.setSelected(true);
								panel_4.add(rdbtnElitismOn);
								group.add(rdbtnElitismOn);
								
								final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("off");
								rdbtnNewRadioButton_1.setFont(UIManager.getFont("RadioButton.font"));
								rdbtnNewRadioButton_1.setToolTipText("Survival of the fittest is left on the selection process to decide on");
								rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
								panel_4.add(rdbtnNewRadioButton_1);
								group.add(rdbtnNewRadioButton_1);
								
								JPanel panel_7 = new JPanel();
								panel_5.add(panel_7, BorderLayout.WEST);
								panel_7.setLayout(new BorderLayout(0, 0));
								
								JPanel panel_3 = new JPanel();
								panel_7.add(panel_3, BorderLayout.WEST);
								panel_3.setLayout(new GridLayout(3, 1, 1, 0));
								
								JLabel lblNewLabel = new JLabel("μ: ");
								panel_3.add(lblNewLabel);
								lblNewLabel.setToolTipText("Population size to maintain (should be even number)");
								lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
								
										JLabel label_1 = new JLabel("λ: ");
										panel_3.add(label_1);
										label_1.setHorizontalAlignment(SwingConstants.CENTER);
										label_1.setToolTipText("number of children to produce per generation (should be even number)");
										panel_3.add(lblDimensionality);
										lblDimensionality.setToolTipText("Dimensionality");
										lblDimensionality.setHorizontalAlignment(SwingConstants.CENTER);
														
														JPanel panel_8 = new JPanel();
														panel_8.setBorder(UIManager.getBorder("TextArea.border"));
														panel_5.add(panel_8, BorderLayout.CENTER);
														panel_8.setLayout(new GridLayout(3, 1, 0, 0));

														panel_8.add(muJTextField);
														muJTextField.setHorizontalAlignment(SwingConstants.CENTER);
														muJTextField.setFont(UIManager.getFont("Button.font"));
														
																panel_8.add(lambdaJTextField);
																lambdaJTextField.setHorizontalAlignment(SwingConstants.CENTER);
																lambdaJTextField.setFont(UIManager.getFont("Button.font"));
																panel_8.add(dJTextField);
																dJTextField.setHorizontalAlignment(SwingConstants.CENTER);
																dJTextField.setFont(UIManager.getFont("Button.font"));
																dJTextField.setColumns(8);
																
																JScrollPane scrollPane = new JScrollPane();
																scrollPane.setViewportBorder(UIManager.getBorder("TextArea.border"));
																scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
																scrollPane.setBounds(9, 168, 530, 147);
																frmEaRunner.getContentPane().add(scrollPane);
																
																JTextArea textArea_1 = new JTextArea();
																scrollPane.setViewportView(textArea_1);
																textArea_1.setEditable(false);
																
																JPanel panel_2 = new JPanel();
																panel_2.setBorder(UIManager.getBorder("EditorPane.border"));
																panel_2.setBounds(12, 88, 147, 68);
																frmEaRunner.getContentPane().add(panel_2);
																panel_2.setLayout(new BorderLayout(0, 0));
																
																JLabel lblRates = new JLabel("Rates");
																lblRates.setFont(new Font("Courier 10 Pitch", Font.BOLD, 13));
																lblRates.setHorizontalAlignment(SwingConstants.CENTER);
																panel_2.add(lblRates, BorderLayout.NORTH);
																
																JPanel panel_11 = new JPanel();
																panel_2.add(panel_11, BorderLayout.WEST);
																panel_11.setLayout(new GridLayout(2, 1, 0, 0));
																
																JLabel lblRecombination = new JLabel("Recombination:");
																lblRecombination.setHorizontalAlignment(SwingConstants.LEFT);
																lblRecombination.setFont(new Font("Courier 10 Pitch", Font.BOLD, 12));
																panel_11.add(lblRecombination);
																
																JLabel lblMutation = new JLabel("Mutation:");
																lblMutation.setFont(new Font("Courier 10 Pitch", Font.BOLD, 12));
																panel_11.add(lblMutation);
																
																JPanel panel_10 = new JPanel();
																panel_2.add(panel_10, BorderLayout.EAST);
																panel_10.setLayout(new GridLayout(2, 1, 5, 0));
																
																mutationRateJTextField.setHorizontalAlignment(SwingConstants.CENTER);
																mutationRateJTextField.setColumns(4);
																panel_10.add(mutationRateJTextField);
																
																recombinationRateJTextField.setHorizontalAlignment(SwingConstants.CENTER);
																panel_10.add(recombinationRateJTextField);
																
																JButton runButton = new JButton("Run");
																runButton.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent arg0) {
																		try {
																			EvolutionaryAlgorithm ea = parser.parse();
																		} catch (
																				Exception e) {
																			// TODO Auto-generated catch block
																			e.printStackTrace();
																		}
																		//ea.
																	}
																});
																runButton.setBounds(198, 452, 117, 25);
																frmEaRunner.getContentPane().add(runButton);
																
																JTextArea textArea = new JTextArea();
																DefaultCaret caret = (DefaultCaret)textArea.getCaret();
																caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
																
																survivorSelectionList.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {
																		String selection = ((String)((JComboBox<?>)e.getSource()).getSelectedItem());
																		if (ArrayUtils.contains(elitistSelectionSet, selection)) {
																			rdbtnElitismOn.setSelected(true);
																			rdbtnElitismOn.setEnabled(false);
																			rdbtnNewRadioButton_1.setEnabled(false);
																		}
																		else {
																			rdbtnElitismOn.setEnabled(true);
																			rdbtnNewRadioButton_1.setEnabled(true);
																		}
																	}
																});
	}

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
					lblRepresentationDescription.setText(parseEvaLuationMenuChoice(arg0.getActionCommand()));
					String dim = parseDimensions(arg0.getActionCommand());
					dJTextField.setText(dim);
					if (!dim.isEmpty())
						dJTextField.setEnabled(false);

					else
						dJTextField.setEnabled(true);						
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String parseEvaLuationMenuChoice(String evaluationFileName){
		if (evaluationFileName.contains("Function")) {
			this.recombinationJComboBox.setModel(new DefaultComboBoxModel(realValueRecombinationSet));
			this.mutationJComboBox.setModel(new DefaultComboBoxModel(realValueMutationSet));
			return "Vector of real numbers";
		}
		else if (evaluationFileName.contains(".tsp") || evaluationFileName.contains(".atsp")
				|| evaluationFileName.contains(".hcp")) {
			this.recombinationJComboBox.setModel(new DefaultComboBoxModel(permutationRecombinationSet));
			this.mutationJComboBox.setModel(new DefaultComboBoxModel(permutationMutationSet));
			return "Permutation of integers";
		}
		return "";
	}
	
	private void setDefaultValues(){
		lambdaJTextField.setText("70");
		muJTextField.setText("10");
		dJTextField.setText("10");
		recombinationRateJTextField.setText("1.0");
		mutationRateJTextField.setText("0.9");
		/*recombinationJComboBox.setSelectedItem(arg0);
		JComboBox<JLabel> mutationJComboBox = new JComboBox<JLabel>();	
		JComboBox<JLabel> parentSelectionList = new JComboBox<JLabel>();
		JComboBox<JLabel> survivorSelectionList = new JComboBox<JLabel>();*/
	}
}

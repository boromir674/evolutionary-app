package gui;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;
import java.awt.BorderLayout;
import com.jgoodies.forms.factories.DefaultComponentFactory;


public class EADesignWindow {

	private JFrame frmEaRunner;
	private File file;
	private String path;
	ArrayList<String> names;
	private final static String[] parentSelectionSet = new String[]{"Fitness Proportional", "Tournament", "Ranking", "Random"};
	private final static String[] survivorSelectionSet = new String[]{"Fitness Proportional","Tournament","Deterministic (μ+λ)","Deterministic (μ,λ)","Deterministic Crowding"};
	private final static String[] allRecombinationSet = new String[]{"Simple Arithmetic", "Single Arithmetic", "Whole Arithmetic","Cycle Crossover",
		"Edge Crossover", "Order Crossover", "PMX", "N-point Crossover", "Uniform Crossover"};
	private final static String[] realValueRecombinationSet = new String[]{"Simple Arithmetic", "Single Arithmetic", "Whole Arithmetic"};
	private final static String[] permutationRecombinationSet = new String[]{"Cycle Crossover", "Edge Crossover", "Order Crossover", "PMX"};
	@SuppressWarnings("unused")
	private final static String[] genericRecombinationSet = new String[]{"N-point Crossover", "Uniform Crossover"};
	private final static String[] allMutationSet = new String[]{"Bitwise","Creep","Insert","Invert","Random Reseting", "Scramble",
		"Swap", "Correlated", "Non uniform", "Ucorrelated with n sigmas", "Ucorrelated with 1 sigma", "Uniform"};
	private final static String[] realValueMutationSet = new String[]{"Correlated", "Non uniform", "Ucorrelated with n sigmas", "Ucorrelated with 1 sigma", "Uniform"};
	private final static String[] permutationMutationSet = new String[]{"Creep","Insert","Invert","Scramble","Swap"};
	private final JTextField dimensionalityJTextField = new JTextField();
	private final JLabel lblProblemInstance = new JLabel("");
	private final JLabel lblDimensionality = new JLabel("d: ");
	private JComboBox<JLabel> mutationJComboBox = new JComboBox<JLabel>();
	private JComboBox<JLabel> recombinationJComboBox = new JComboBox<JLabel>();	
	private JMenu mnRealValue = new JMenu("Real Value");
	private JMenu mnPermutation = new JMenu("Permutation");
	private JLabel lblRepresentation = new JLabel("Representation");
	private JLabel lblRepresentationDescription = new JLabel("                                 "
			+ "                                  ");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		frmEaRunner = new JFrame();
		frmEaRunner.setResizable(false);
		frmEaRunner.setTitle("EA Runner");
		frmEaRunner.setBounds(100, 100, 419, 354);
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

		JMenuBar menuBar_1 = new JMenuBar();

		menuBar_1.setBounds(12, 265, 129, 21);
		frmEaRunner.getContentPane().add(menuBar_1);

		final JMenu mnEvaluation = new JMenu("evaluation");
		mnEvaluation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
					mnEvaluation.doClick();
			}
		});
		menuBar_1.add(mnEvaluation);
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
		splitPane.setBounds(12, 127, 373, 122);
		frmEaRunner.getContentPane().add(splitPane);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new GridLayout(5, 1));

		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new GridLayout(5, 1));
		panel.add(lblRepresentation);
		
				JLabel label_2 = new JLabel("Recombination");
				panel.add(label_2);
		
				JLabel label_3 = new JLabel("Mutation");
				panel.add(label_3);

		JLabel lblParentSelection = new JLabel("Parent Selection");
		panel.add(lblParentSelection);
		panel_1.add(lblRepresentationDescription);
		lblRepresentationDescription.setText("");
		recombinationJComboBox.setModel(new DefaultComboBoxModel(allRecombinationSet));
		panel_1.add(recombinationJComboBox);
		
				panel_1.add(mutationJComboBox);
				mutationJComboBox.setModel(new DefaultComboBoxModel(allMutationSet));

		JComboBox parentSelectionList = new JComboBox(parentSelectionSet);
		panel_1.add(parentSelectionList);

		JLabel lblSurvivorSelection = new JLabel("Survivor Selection");
		panel.add(lblSurvivorSelection);

		JComboBox survivorSelectionList = new JComboBox(survivorSelectionSet);
		panel_1.add(survivorSelectionList);
												
														JLabel label_4 = new JLabel("Evaluation");
														label_4.setHorizontalAlignment(SwingConstants.CENTER);
														label_4.setBounds(273, 74, 75, 20);
														frmEaRunner.getContentPane().add(label_4);
														lblProblemInstance.setBounds(12, 102, 234, 20);
														frmEaRunner.getContentPane().add(lblProblemInstance);
		ButtonGroup group = new ButtonGroup();
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(12, 12, 142, 70);
		frmEaRunner.getContentPane().add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 1));
								
								JPanel panel_6 = new JPanel();
								panel_6.setBounds(83, 12, 52, 61);
								panel_6.setLayout(new BorderLayout(0, 0));
								
								JLabel elitismJLabel = new JLabel(" Elitism");
								elitismJLabel.setVerticalAlignment(SwingConstants.BOTTOM);
								panel_6.add(elitismJLabel, BorderLayout.NORTH);
								elitismJLabel.setToolTipText("Population's property of preserving the fittest individual through the generations");
								
								JPanel panel_4 = new JPanel();
								panel_6.add(panel_4, BorderLayout.SOUTH);
								panel_5.add(panel_6, BorderLayout.EAST);
								
								panel_4.setLayout(new GridLayout(2, 1, 0, 0));
								
								JRadioButton rdbtnNewRadioButton = new JRadioButton("on");
								rdbtnNewRadioButton.setToolTipText("Satisfied either by natural selection or by force");
								rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
								panel_4.add(rdbtnNewRadioButton);
								group.add(rdbtnNewRadioButton);
								
								JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("off");
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
														panel_5.add(panel_8, BorderLayout.CENTER);
														panel_8.setLayout(new GridLayout(3, 1, 0, 0));
														JTextField jTextField = new JTextField();
														panel_8.add(jTextField);
														jTextField.setHorizontalAlignment(SwingConstants.CENTER);
														jTextField.setFont(UIManager.getFont("Button.font"));
														
																JTextField jTextField_1 = new JTextField();
																panel_8.add(jTextField_1);
																jTextField_1.setHorizontalAlignment(SwingConstants.CENTER);
																jTextField_1.setFont(UIManager.getFont("Button.font"));
																panel_8.add(dimensionalityJTextField);
																dimensionalityJTextField.setHorizontalAlignment(SwingConstants.CENTER);
																dimensionalityJTextField.setFont(UIManager.getFont("Button.font"));
																dimensionalityJTextField.setColumns(10);

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String parseEvaLuationMenuChoice(String evaluationFileName){
		if (evaluationFileName.contains("Function")) {
			this.recombinationJComboBox.setModel(new DefaultComboBoxModel(realValueRecombinationSet));
			this.mutationJComboBox.setModel(new DefaultComboBoxModel(realValueMutationSet));
			return "Vector of real numbers";
		}
		else if (evaluationFileName.contains(".atsp") || evaluationFileName.contains(".atsp")
				|| evaluationFileName.contains(".hcp")) {
			this.recombinationJComboBox.setModel(new DefaultComboBoxModel(permutationRecombinationSet));
			this.mutationJComboBox.setModel(new DefaultComboBoxModel(permutationMutationSet));
			return "Permutation of integers";
		}
		return "";
	}
}

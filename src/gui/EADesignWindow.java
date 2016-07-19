package gui;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
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

import simulationComponents.Environment;
import util.LibraryModel;

import java.awt.Font;

import javax.swing.JButton;

public class EADesignWindow implements ActionListener{

	/**
	 * @return the textArea_1
	 */
	public JTextArea getTextArea_1() {
		return textArea_1;
	}

	/**
	 * @return the dJTextField
	 */
	public JTextField getdJTextField() {
		return dJTextField;
	}

	/**
	 * @return the lblProblemInstance
	 */
	public JLabel getLblProblemInstance() {
		return lblProblemInstance;
	}

	/**
	 * @return the lblDimensionality
	 */
	public JLabel getLblDimensionality() {
		return lblDimensionality;
	}

	/**
	 * @return the lblRepresentation
	 */
	public JLabel getLblRepresentation() {
		return lblRepresentation;
	}

	/**
	 * @return the lambdaJTextField
	 */
	public JTextField getLambdaJTextField() {
		return lambdaJTextField;
	}

	/**
	 * @return the muJTextField
	 */
	public JTextField getMuJTextField() {
		return muJTextField;
	}

	/**
	 * @return the recombinationRateJTextField
	 */
	public JTextField getRecombinationRateJTextField() {
		return recombinationRateJTextField;
	}

	/**
	 * @return the mutationRateJTextField
	 */
	public JTextField getMutationRateJTextField() {
		return mutationRateJTextField;
	}

	/**
	 * @return the recombinationJComboBox
	 */
	public JComboBox<JLabel> getRecombinationJComboBox() {
		return recombinationJComboBox;
	}

	/**
	 * @return the mutationJComboBox
	 */
	public JComboBox<JLabel> getMutationJComboBox() {
		return mutationJComboBox;
	}

	/**
	 * @return the parentSelectionList
	 */
	public JComboBox<JLabel> getParentSelectionList() {
		return parentSelectionList;
	}

	/**
	 * @return the survivorSelectionList
	 */
	public JComboBox<JLabel> getSurvivorSelectionList() {
		return survivorSelectionList;
	}

	/**
	 * @return the terminationConditionJComboBox
	 */
	public JComboBox<JLabel> getTerminationConditionJComboBox() {
		return terminationConditionJComboBox;
	}

	/**
	 * @return the terminationParameterJTextField
	 */
	public JTextField getTerminationParameterJTextField() {
		return terminationParameterJTextField;
	}

	private JFrame myFrame;
	/**
	 * @return the frmEaRunner
	 */
	public JFrame getFrame() {
		return myFrame;
	}

	private File file;
	private String path;
	ArrayList<String> names1;
	ArrayList<Path> names;

	private final static String[] elitistSelectionSet = new String[]{"MuPlusLambda", "DeterministicCrowding"};

	final JLabel lblProblemInstance = new JLabel("");
	private final JLabel lblDimensionality = new JLabel("d: ");

	private JMenu mnRealValue = new JMenu("Real Value");
	private JMenu mnPermutation = new JMenu("Permutation");
	private JLabel lblRepresentation = new JLabel("Representation");
	private JLabel lblRepresentationDescription = new JLabel("                                 "
			+ "                                  ");
	// package visibility
	private JTextField lambdaJTextField = new JTextField();
	private JTextField muJTextField = new JTextField();
	private JTextField dJTextField = new JTextField();
	private JTextField recombinationRateJTextField = new JTextField();
	private JTextField mutationRateJTextField = new JTextField();
	private JComboBox<JLabel> recombinationJComboBox = new JComboBox<JLabel>();
	private JComboBox<JLabel> mutationJComboBox = new JComboBox<JLabel>();	
	private JComboBox<JLabel> parentSelectionList = new JComboBox<JLabel>();
	private JComboBox<JLabel> survivorSelectionList = new JComboBox<JLabel>();
	private final JRadioButton rdbtnElitismOn = new JRadioButton("on");
	private JTextArea textArea_1;
	
	private JButton runButton = new JButton("Run");
	private JComboBox<JLabel> terminationConditionJComboBox;
	private JTextField terminationParameterJTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryModel ld = new LibraryModel();
					EADesignWindow window = new EADesignWindow();
					window.myFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == runButton) {
			try {
				System.out.println(this.lblProblemInstance.getText());
				Environment.runEA();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
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
		setDefaultValues();
		myFrame = new JFrame();
		myFrame.setResizable(false);
		myFrame.setTitle("EA Runner");
		myFrame.setBounds(100, 100, 554, 562);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		myFrame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		myFrame.getContentPane().setLayout(null);

		JMenuBar flowtingMenuBar = new JMenuBar();
		flowtingMenuBar.setToolTipText("Select instance ");

		flowtingMenuBar.setBounds(166, 135, 88, 21);
		myFrame.getContentPane().add(flowtingMenuBar);

		final JMenu mnEvaluation = new JMenu("evaluation");
		mnEvaluation.setHorizontalAlignment(SwingConstants.CENTER);

		flowtingMenuBar.add(mnEvaluation);
		mnRealValue.setName("Vector of real numbers");
		mnEvaluation.add(mnRealValue);
		this.populateMenu(mnRealValue, LibraryModel.getRealValueFunctions());

		mnPermutation.setName("Permutation of integers");
		mnEvaluation.add(mnPermutation);

		JMenu mnTsp = new JMenu("TSP");
		mnTsp.setToolTipText("Traveling Salesman Problem");
		mnTsp.setLayout(new GridLayout(30, 3));
		mnPermutation.add(mnTsp);
		this.populateMenu2(mnTsp, getContents("/TSP_samples/TSP/"));

		JMenu mnAtsp = new JMenu("ATSP");
		mnAtsp.setToolTipText("Asymmetric Traveling Salesman Problem");
		mnPermutation.add(mnAtsp);
		this.populateMenu2(mnAtsp, getContents("/TSP_samples/ATSP/"));

		JMenu mnHcp = new JMenu("HCP");
		mnHcp.setToolTipText("Hamiltonian Cycle Problem");
		mnPermutation.add(mnHcp);
		this.populateMenu2(mnHcp, getContents("/TSP_samples/HCP/"));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(166, 12, 373, 122);
		myFrame.getContentPane().add(splitPane);

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

		recombinationJComboBox.setModel(new DefaultComboBoxModel(pathsToStrings(LibraryModel.getCrossoverOperators())));
		panel_1.add(recombinationJComboBox);

		panel_1.add(mutationJComboBox);
		mutationJComboBox.setModel(new DefaultComboBoxModel(pathsToStrings(LibraryModel.getMutationOperators())));

		panel_1.add(parentSelectionList);
		parentSelectionList.setModel(new DefaultComboBoxModel(pathsToStrings(LibraryModel.getParentSelectionMethods())));

		JLabel lblSurvivorSelection = new JLabel("Survivor Selection");
		panel.add(lblSurvivorSelection);

		survivorSelectionList.setModel(new DefaultComboBoxModel(pathsToStrings(LibraryModel.getSurvivorSelectionMethods())));
		panel_1.add(survivorSelectionList);
		lblProblemInstance.setBounds(272, 136, 267, 20);
		myFrame.getContentPane().add(lblProblemInstance);
		final ButtonGroup group = new ButtonGroup();

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(12, 12, 147, 70);
		myFrame.getContentPane().add(panel_5);
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
		myFrame.getContentPane().add(scrollPane);

		textArea_1 = new JTextArea();
		scrollPane.setViewportView(textArea_1);
		textArea_1.setEditable(false);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(UIManager.getBorder("EditorPane.border"));
		panel_2.setBounds(12, 88, 147, 68);
		myFrame.getContentPane().add(panel_2);
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
		
		runButton.setBounds(12, 362, 117, 25);
		runButton.addActionListener(this);
		myFrame.getContentPane().add(runButton);
		
		terminationConditionJComboBox = new JComboBox<JLabel>();
		terminationConditionJComboBox.setBounds(12, 326, 303, 24);
		terminationConditionJComboBox.setModel(new DefaultComboBoxModel(pathsToStrings(LibraryModel.getTerminationConditions())));
		terminationConditionJComboBox.setSelectedIndex(5);
		myFrame.getContentPane().add(terminationConditionJComboBox);
		
		terminationParameterJTextField = new JTextField();
		terminationParameterJTextField.setText("1000");
		terminationParameterJTextField.setHorizontalAlignment(SwingConstants.CENTER);
		terminationParameterJTextField.setBounds(141, 362, 88, 25);
		myFrame.getContentPane().add(terminationParameterJTextField);
		terminationParameterJTextField.setColumns(10);

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

	private void populateMenu(JMenu anEvaluationSubmenu, ArrayList<Path> models){
		// anEvaluationSubmenu: parent of a leaf node in the evaluation tree
		// it also stores the references of the menu's items
		for (int i=0; i<models.size(); i++){
			JMenuItem temp = new JMenuItem(models.get(i).toFile().getName());
			temp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						parseEvaLuationMenuChoice(e.getActionCommand());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lblProblemInstance.setText(e.getActionCommand());
					lblRepresentationDescription.setText(e.getActionCommand());
					String dim = util.Util.parseDimensions(e.getActionCommand());
					dJTextField.setText(dim);
					if (!dim.isEmpty())
						dJTextField.setEnabled(false);
					else
						dJTextField.setEnabled(true);						
				}
			});
			anEvaluationSubmenu.add(temp);
		}
	}
	//TODO unify methods
	private void populateMenu2(JMenu anEvaluationSubmenu, ArrayList<String> contents){
		// anEvaluationSubmenu: parent of a leaf node in the evaluation tree
		// it also stores the references of the menu's items
		for (int i=0; i<contents.size(); i++){
			JMenuItem temp = new JMenuItem(contents.get(i));
			temp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						parseEvaLuationMenuChoice(e.getActionCommand());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lblProblemInstance.setText(e.getActionCommand());
					lblRepresentationDescription.setText(e.getActionCommand());
					String dim = util.Util.parseDimensions(e.getActionCommand());
					dJTextField.setText(dim);
					if (!dim.isEmpty())
						dJTextField.setEnabled(false);
					else
						dJTextField.setEnabled(true);						
				}
			});
			anEvaluationSubmenu.add(temp);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String parseEvaLuationMenuChoice(String evaluationFileName) throws ClassNotFoundException{
		if (evaluationFileName.contains("Function")) {
			this.recombinationJComboBox.setModel(new DefaultComboBoxModel(pathsToStrings(LibraryModel.getRealValueApplicableCrossoverOperators())));
			this.mutationJComboBox.setModel(new DefaultComboBoxModel(pathsToStrings(LibraryModel.getRealValueApplicableMutationOperators())));
			return "Vector of real numbers";
		}
		else if (evaluationFileName.contains(".tsp") || evaluationFileName.contains(".atsp")
				|| evaluationFileName.contains(".hcp")) {
			this.recombinationJComboBox.setModel(new DefaultComboBoxModel(pathsToStrings(LibraryModel.getPermutationApplicableCrossoverOperators())));
			this.mutationJComboBox.setModel(new DefaultComboBoxModel(pathsToStrings(LibraryModel.getPermutationApplicableMutationOperators())));
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
	
	private static String[] pathsToStrings(ArrayList<Path> aListOfPaths){
		String[] result = new String[aListOfPaths.size()];
		for (int i=0; i<result.length; i++) {
			int l = aListOfPaths.get(i).toFile().getName().length(); //exclude extension (.java)
			result[i] = aListOfPaths.get(i).toFile().getName().substring(0, l-5);
		}
		return result;
	}

	public Object getRunButton() {
		return this.runButton;
	}

}

package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
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


public class EADesignWindow {

	private JFrame frame;
	private File file;
	private String path;
	ArrayList<String> names;
	private final String[] parentSelectionSet = new String[]{"Fitness Proportional", "Tournament", "Ranking", "Random"};
	private final String[] survivorSelectionSet = new String[]{"Fitness Proportional", "Tournament", "(μ+λ)", "(μ,λ)", "Deterministic Crowding"};
	private final String[] realValueRecombinationSet = new String[]{"Simple Arithmetic", "Single Arithmetic", "Whole Arithmetic"};
	private final String[] permutationRecombinationSet = new String[]{"Cycle Crossover", "Edge Crossover", "Order Crossover", "PMX"};
	private final String[] genericRecombinationSet = new String[]{"N-point Crossover", "Uniform Crossover"};
	private JTextField textField_2;
	private final JLabel lblProblemInstance = new JLabel("Problem Instance");
	private final JLabel lblDimensionality = new JLabel("dimensionality");
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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 619, 534);
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

		JPanel panel = new JPanel();
		panel.setBounds(235, 61, 366, 161);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(5, 1, 0, 0));

		JSplitPane splitPane_1 = new JSplitPane();

		JLabel lblSurvivorSelection = new JLabel("Survivor Selection");
		splitPane_1.setLeftComponent(lblSurvivorSelection);

		JComboBox survivorSelectionList = new JComboBox(survivorSelectionSet);
		splitPane_1.setRightComponent(survivorSelectionList);


		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);
		panel.add(splitPane_1);

		JComboBox parentSelectionList = new JComboBox(parentSelectionSet);
		splitPane.setRightComponent(parentSelectionList);

		JLabel lblParentSelection = new JLabel("Parent Selection");
		splitPane.setLeftComponent(lblParentSelection);

		JSplitPane splitPane_5 = new JSplitPane();
		panel.add(splitPane_5);
		splitPane_5.setResizeWeight(0.5);

		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_5.setRightComponent(splitPane_4);
		splitPane_4.setResizeWeight(0.2);

		JLabel label_1 = new JLabel("λ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setToolTipText("number of children to produce per generation (should be even number)");
		splitPane_4.setLeftComponent(label_1);

		TextField textField_1 = new TextField();
		splitPane_4.setRightComponent(textField_1);

		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_5.setLeftComponent(splitPane_2);
		splitPane_2.setToolTipText("population size");
		splitPane_2.setResizeWeight(0.2);

		JLabel label = new JLabel("μ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane_2.setLeftComponent(label);

		TextField textField = new TextField();
		splitPane_2.setRightComponent(textField);
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setResizeWeight(0.5);
		splitPane_3.setBounds(235, 12, 352, 27);
		frame.getContentPane().add(splitPane_3);

		splitPane_3.setLeftComponent(lblProblemInstance);

		JSplitPane splitPane_6 = new JSplitPane();
		splitPane_3.setRightComponent(splitPane_6);
		splitPane_6.setResizeWeight(0.5);

		splitPane_6.setLeftComponent(lblDimensionality);

		textField_2 = new JTextField();
		splitPane_6.setRightComponent(textField_2);
		textField_2.setColumns(10);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(22, 87, 129, 21);
		frame.getContentPane().add(menuBar_1);

		JMenu mnEvaluation = new JMenu("evaluation");
		menuBar_1.add(mnEvaluation);

		JMenu mnRealValue = new JMenu("Real Value");

		mnEvaluation.add(mnRealValue);
		names = getContents("/src/evolutionaryAlgorithmComponents/evaluation/realValueEvaluations");
		JMenuItem[] menuContents = new JMenuItem[names.size()];
		this.populateMenu(mnRealValue, menuContents);
		
		JMenuItem mntmTesttsp = new JMenuItem("test10.tsp");
		mntmTesttsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblProblemInstance.setText(arg0.getActionCommand());
			}
		});
		
		mnRealValue.add(mntmTesttsp);
		JMenu mnPermutation = new JMenu("Permutation");
		mnEvaluation.add(mnPermutation);

		JMenu mnTsp = new JMenu("TSP");
		mnPermutation.add(mnTsp);
		names = getContents("/TSP_samples/TSP/");
		menuContents = new JMenuItem[names.size()];
		this.populateMenu(mnTsp, menuContents);
		
		JMenu mnAtsp = new JMenu("ATSP");
		mnPermutation.add(mnAtsp);
		names = getContents("/TSP_samples/ATSP/");
		menuContents = new JMenuItem[names.size()];
		this.populateMenu(mnAtsp, menuContents);
		
		JMenu mnHcp = new JMenu("HCP");
		mnPermutation.add(mnHcp);
		names = getContents("/TSP_samples/HCP/");
		menuContents = new JMenuItem[names.size()];
		this.populateMenu(mnHcp, menuContents);

	}

	private ArrayList<String> getContents(String aRelativePath){
		String path = System.getProperty("user.dir") + aRelativePath;
		file = new File(path);
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(file.list()));
		return names;
	}
	private void populateMenu(JMenu anEvaluationMenu, JMenuItem[] folderContents){
		for (int i=0; i<folderContents.length; i++){
			folderContents[i] = new JMenuItem(names.get(i));
			anEvaluationMenu.add(folderContents[i]);
			folderContents[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					lblProblemInstance.setText(arg0.getActionCommand());
				}
			});
		}
	}
	private static String parseDimensions(String aFileName){
		int i = 1;
		int start = 0;
		int end = 0;
		while (start == 0 && end == 0){
			char ch0 = aFileName.charAt(i-1);
			char ch1 = aFileName.charAt(i);
			if (!Character.isDigit(ch0) && Character.isDigit(ch1))
				start = ch1;
			if (Character.isDigit(ch0) && !Character.isDigit(ch1))
				end = ch1;
		}
		return aFileName.substring(start, end);
	}
	class EvaluationPickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//((JMenuItem)(arg0.getSource())).get

		}

	}
}

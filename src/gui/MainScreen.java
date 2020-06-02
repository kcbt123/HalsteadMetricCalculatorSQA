package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import calculator.HalsteadCalculator;

public class MainScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
//	private JTextField textField;
//	private JTextField textField_1;
//	private JTextField textField_2;
//	private JTextField textField_3;
//	private JTextField textField_4;
	
	private JLabel headerLabel;

	private JLabel basicParamLabel;	
	private JLabel param_n1_Label;
	private JLabel param_n2_Label;
	private JLabel param_N1_Label;
	private JLabel param_N2_Label;
	
	private JTextField param_n1_TextField;
	private JTextField param_n2_TextField;
	private JTextField param_N1_TextField;
	private JTextField param_N2_TextField;
	
	private JLabel chooseFileLabel;
	private JTextField chooseFileTextField;
	
	private JButton chooseButton;
	private JButton calculateButton;
	
	private JLabel metricLabel;
	private JTextArea metricTextArea;
	
	private JLabel recommendationLabel;
	private JTextField recommendationTextField;
	
	public int value_n1 = 8;
	public int value_n2 = 16;
	public int value_N1 = 50;
	public int value_N2 = 70;
	
	private float result_N;
	private float result_n;
	private float result_H;
	private float result_V;
	private float result_D;
	private float result_L;
	private float result_E;
	private float result_T;
	private float result_B;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainScreen() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		headerLabel = new JLabel("HALSTEAD CALCULATOR");
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		basicParamLabel = new JLabel("Basic Parameters");
		basicParamLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		param_n1_Label = new JLabel("n1 (seperate operators)");
		param_n2_Label = new JLabel("n2 (seperate operands)");
		param_N1_Label = new JLabel("N1 (total amount of operators)");
		param_N2_Label = new JLabel("N2 (total amount of operands)");
		
		param_n1_TextField = new JTextField();
		param_n1_TextField.setHorizontalAlignment(SwingConstants.RIGHT);
		param_n1_TextField.setEditable(false);
		param_n1_TextField.setColumns(10);
		
		param_n2_TextField = new JTextField();
		param_n2_TextField.setHorizontalAlignment(SwingConstants.RIGHT);
		param_n2_TextField.setEditable(false);
		param_n2_TextField.setColumns(10);
		
		param_N1_TextField = new JTextField();
		param_N1_TextField.setHorizontalAlignment(SwingConstants.RIGHT);
		param_N1_TextField.setEditable(false);
		param_N1_TextField.setColumns(10);
		
		param_N2_TextField = new JTextField();
		param_N2_TextField.setHorizontalAlignment(SwingConstants.RIGHT);
		param_N2_TextField.setEditable(false);
		param_N2_TextField.setColumns(10);
		
		chooseFileLabel = new JLabel("Choose a Java file");
		chooseFileLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		chooseFileTextField = new JTextField();
		chooseFileTextField.setColumns(10);
		
		chooseButton = new JButton("Choose");
		chooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}
		});
		
		metricLabel = new JLabel("Metrics");
		metricLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		recommendationLabel = new JLabel("RECOMMENDATION =>");
		recommendationLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		recommendationTextField = new JTextField();
		recommendationTextField.setEditable(false);
		recommendationTextField.setColumns(10);
		
		metricTextArea = new JTextArea();
		metricTextArea.setEditable(false);
		
		calculateButton = new JButton("CALCULATE HALSTEAD");
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculateHalstead();
				displayResult();
			}
		});
		calculateButton.setBackground(UIManager.getColor("Button.shadow"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(60)
					.addComponent(headerLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addGap(60))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(metricTextArea, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(recommendationLabel)
							.addGap(60)
							.addComponent(recommendationTextField, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))
						.addComponent(metricLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(basicParamLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(chooseFileLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
							.addGap(60)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(param_n1_Label)
										.addComponent(param_N1_Label))
									.addGap(25)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(param_n1_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(param_N1_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(37)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(param_n2_Label)
										.addComponent(param_N2_Label))
									.addGap(36)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(param_N2_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(param_n2_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(chooseFileTextField, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE)
									.addGap(20)
									.addComponent(chooseButton)))))
					.addGap(72))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(370)
					.addComponent(calculateButton)
					.addContainerGap(448, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(headerLabel)
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(chooseFileLabel)
						.addComponent(chooseFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chooseButton))
					.addGap(21)
					.addComponent(calculateButton)
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(basicParamLabel)
						.addComponent(param_n1_Label)
						.addComponent(param_n2_Label)
						.addComponent(param_n1_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(param_n2_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(param_N1_Label)
						.addComponent(param_N2_Label)
						.addComponent(param_N1_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(param_N2_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(metricLabel)
					.addGap(18)
					.addComponent(metricTextArea, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(recommendationLabel)
						.addComponent(recommendationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	// Handle events
	
	private void chooseFile() {
		
	}
	
	private void calculateHalstead() {
		HalsteadCalculator cal = new HalsteadCalculator();
		
		result_N = cal.calculate_N(value_N1, value_N2);
		result_n = cal.calculate_n(value_n1, value_n2);
		result_H = cal.calculate_H(value_n1, value_n2);
		result_V = cal.calculate_V(result_N, result_n);
		result_D = cal.calculate_D(value_n1, value_n2, value_N2);
		result_L = cal.calculate_L(result_D);
		result_E = cal.calculate_E(result_D, result_V);
		result_T = cal.calculate_T(result_E);
		result_B = cal.calculate_B_SecondWay(result_V);
	}
	
	private void displayResult() {
		param_n1_TextField.setText(String.valueOf(value_n1));
		param_n2_TextField.setText(String.valueOf(value_n2));
		param_N1_TextField.setText(String.valueOf(value_N1));
		param_N2_TextField.setText(String.valueOf(value_N2));
		
		String metricString = String.format("N (Program Length) = %f", result_N) + " \r\n"
							+ String.format("n (Program Vocabulary) = %f", result_n) + " \r\n"
							+ String.format("H (Halstead Program Length) = %f", result_H) + " \r\n"
							+ String.format("V (Program Volume) = %f", result_V) + " \r\n"
							+ String.format("D (Program Difficulty) = %f", result_D) + " \r\n"
							+ String.format("L (Program Level) = %f", result_L) + " \r\n"
							+ String.format("E (Effort) = %f", result_E) + " \r\n"
							+ String.format("T (Time required to program) = %f", result_T) + " \r\n"
							+ String.format("B (Number of delivered bugs) = %f", result_B) + " \r\n";
		//System.out.print(metricString);
		metricTextArea.setText(metricString);
		
		recommendationTextField.setText("Code như shit");
		
				
	}

}

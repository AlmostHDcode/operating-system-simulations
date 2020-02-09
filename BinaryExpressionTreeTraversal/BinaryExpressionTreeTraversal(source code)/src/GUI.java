import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**GUI class that builds the user interface that accepts user input
 * @author Matt Hunt 
 * @version V1-2/6/2019
 */

public class GUI {
	
	//window size
	final int WIDTH = 800;
	final int HEIGHT = 650;
	
	//frame
	JFrame frame = new JFrame();
	
	//panel
	JPanel panel = new JPanel();
	
	//buttons
	JButton btnCalc = new JButton("Calculate Traversals");
	JButton btnClear = new JButton("CLEAR");
	
	//labels
	JLabel inputLabel = new JLabel("Please enter an infix expression:");
	JLabel inLabel = new JLabel("In-order traversal:");
	JLabel preLabel = new JLabel("Pre-order traversal:");
	JLabel postLabel = new JLabel("Post-order traversal:");
	JLabel title = new JLabel("Binary Expression Tree Traversal");
	
	
	//text fields
	JTextField userInput = new JTextField(20);
	JTextField inorder = new JTextField(20);
	JTextField preorder = new JTextField(20);
	JTextField postorder = new JTextField(20);
	
	
	/**
	 * constructor
	 * creates GUI that runs all required methods and sets it to visible
	 */
	public GUI() {
		
		this.initializeFrame();
		this.populateFrame();
		this.addActionListeners();
		
		frame.setVisible(true);
	}
	
	/**
	 * create frame and set its basic properties
	 */
	private void initializeFrame() {
		frame.setTitle("Binary ExpressionTree GUI");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		//panel is added into frame
		frame.add(panel);
		panel.setBorder(BorderFactory.createBevelBorder(0, Color.BLUE, Color.DARK_GRAY));
	}
	
	/**
	 * manipulated the compoenents and adds them to the panel which is in the frame
	 */
	private void populateFrame() {
		
		//title of program
		Font titleFont = new Font("Serif", Font.BOLD, 36);
		title.setFont(titleFont);
		title.setBounds(130, 100, 550,50);
		panel.add(title);
		
		//label manipulation
		inputLabel.setLabelFor(userInput);
		inputLabel.setBounds(160, HEIGHT/3, 300,30);
		userInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		userInput.setBounds(360, HEIGHT/3, 250, 30);
		Font font = new Font("Comic Sans MS", Font.PLAIN, 18);
		userInput.setFont(font);
		panel.add(userInput);
		panel.add(inputLabel);
		
		//button manipulation
		btnCalc.setBackground(Color.LIGHT_GRAY);
		btnCalc.setBounds(230,300,150,30);
		panel.add(btnCalc);
		btnClear.setBackground(Color.RED);
		btnClear.setBounds(400, 300, 150, 30);
		panel.add(btnClear);
		
		//text box manipulation
		inorder.setBounds(300, 380, 250, 30);
		preorder.setBounds(300, 430, 250, 30);
		postorder.setBounds(300, 480, 250, 30);
		inorder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		preorder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		postorder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		inorder.setEditable(false);
		preorder.setEditable(false);
		postorder.setEditable(false);
		inorder.setFont(font);
		preorder.setFont(font);
		postorder.setFont(font);
		
		panel.add(inorder);
		panel.add(preorder);
		panel.add(postorder);
		
		//labels for the answer text boxes
		inLabel.setLabelFor(inorder);
		inLabel.setBounds(190, 380, 500, 30);
		preLabel.setLabelFor(preorder);
		preLabel.setBounds(182, 430, 200, 30);
		postLabel.setLabelFor(postorder);
		postLabel.setBounds(178, 480, 200, 30);
		
		panel.add(inLabel);
		panel.add(preLabel);
		panel.add(postLabel);
	}
	
	/**
	 * Created action listeners are added to the component
	 */
	private void addActionListeners() {
		btnCalc.addActionListener(btnCalcListener);
		btnClear.addActionListener(clearInfo);
	}
	
	/**
	 * action listener that takes in the user input from text box
	 * converts input to postfix
	 * creates binary tree using postfix of user input
	 * the traversal output is set to the three traversal text boxes and displayed
	 */
	ActionListener btnCalcListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent ea) {
			
			String expression = "";
			expression = userInput.getText();
			
			BinaryTree<String> tree = new BinaryTree<String>();
			tree.makeExpressionTree(expression);
			
			String in = tree.inOrderTraverse();
			String pre = tree.preOrderTraverse();
			String post = tree.postOrderTraverse();
			
			inorder.setText(in);
			preorder.setText(pre);
			postorder.setText(post);
		}

	};
	
	/**
	 * all user information in the user text box and the output traversal boxes is deleted
	 */
	ActionListener clearInfo = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent ea) {
			userInput.setText("");
			inorder.setText("");
			preorder.setText("");
			postorder.setText("");
		}

	};
	
}
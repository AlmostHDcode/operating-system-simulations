package io.github.hmatt33.memorysimulator.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/** GUI class that draws results onto the screen
 * @author Matt Hunt
 * @version 1.0
 */
public class GUI {
	//window size
	final int WIDTH = 1000;
	final int HEIGHT = 800;

	int yHeight = 0;

	//frame
	JFrame frame = new JFrame();

	//panel
	JPanel panel = new JPanel();

	//text panes sim 1
	JTextPane sim1F = new JTextPane();
	JTextPane sim1F2 = new JTextPane();
	JTextPane sim1B = new JTextPane();
	JTextPane sim1W = new JTextPane();
	JTextPane sim1N = new JTextPane();

	//text panes sim 2
	JTextPane sim2F = new JTextPane();
	JTextPane sim2B = new JTextPane();
	JTextPane sim2W = new JTextPane();
	JTextPane sim2N = new JTextPane();

	//attribute set used to center text in the text panes
	SimpleAttributeSet attribs = new SimpleAttributeSet();

	//scroll properties
	int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
	int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
	JScrollPane scrollPane = new JScrollPane(panel, v, h);

	//layout manager of gui
	GridBagLayout layout = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();

	/**
	 * Constructor
	 * initializes values and sets frame to visible
	 */
	public GUI() {
		this.initializeFrame();
		frame.setVisible(true);
	}

	/**
	 * sets initial values for frame and panel
	 */
	private void initializeFrame() {
		frame.setTitle("Binary ExpressionTree GUI");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		panel.setLayout(layout);
		//scroll bar added into frame which contains the panel
		frame.getContentPane().add(scrollPane);
		panel.setBorder(BorderFactory.createBevelBorder(0, Color.BLUE, Color.DARK_GRAY));
	}

	
	/**
	 * @param s, string to draw
	 * draws sim1 first fit onto the scroll pane using grid bag layout
	 */
	public void drawSim1F(String s) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		sim1F.setParagraphAttributes(attribs,true);
		panel.add(sim1F, c);
		sim1F.setText(s);
	}

	/**
	 * @param s, string to draw
	 * draws sim1 first fit 2 onto the scroll pane using grid bag layout
	 */
	public void drawSim1F2(String s) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		sim1F2.setParagraphAttributes(attribs,true);
		panel.add(sim1F2, c);
		sim1F2.setText(s);
	}

	/**
	 * @param s, string to draw
	 * draws sim1 best fit onto the scroll pane using grid bag layout
	 */
	public void drawSim1B(String s) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		sim1B.setParagraphAttributes(attribs,true);
		panel.add(sim1B, c);
		sim1B.setText(s);
	}

	/**
	 * @param s, string to draw
	 * draws sim1 worst fit onto the scroll pane using grid bag layout
	 */
	public void drawSim1W(String s) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		sim1W.setParagraphAttributes(attribs,true);
		panel.add(sim1W, c);
		sim1W.setText(s);
	}

	/**
	 * @param s, string to draw
	 * draws sim1 next fit onto the scroll pane using grid bag layout
	 */
	public void drawSim1N(String s) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		sim1N.setParagraphAttributes(attribs,true);
		panel.add(sim1N, c);
		sim1N.setText(s);
	}

	/**
	 * @param s, string to draw
	 * draws sim2 first fit onto the scroll pane using grid bag layout
	 */
	public void drawSim2F(String s) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		sim2F.setParagraphAttributes(attribs,true);
		panel.add(sim2F, c);
		sim2F.setText(s);
	}

	/**
	 * @param s, string to draw
	 * draws sim2 best fit onto the scroll pane using grid bag layout
	 */
	public void drawSim2B(String s) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		sim2B.setParagraphAttributes(attribs,true);
		panel.add(sim2B, c);
		sim2B.setText(s);
	}

	/**
	 * @param s, string to draw
	 * draws sim2 worst fit onto the scroll pane using grid bag layout
	 */
	public void drawSim2W(String s) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 7;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		sim2W.setParagraphAttributes(attribs,true);
		panel.add(sim2W, c);
		sim2W.setText(s);
	}

	/**
	 * @param s, string to draw
	 * draws sim2 next fit onto the scroll pane using grid bag layout
	 */
	public void drawSim2N(String s) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 8;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		sim2N.setParagraphAttributes(attribs,true);
		panel.add(sim2N, c);
		sim2N.setText(s);
	}

}

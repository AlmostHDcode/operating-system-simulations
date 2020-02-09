package io.github.hmatt33.PageRemoval;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**GUI that draws all necessary elements for user input and displaying the results
 * @author Matt Hunt
 * @version 1.0
 */
public class GUI {
	//window size
	final int WIDTH = 1000;
	final int HEIGHT = 800;

	//user input
	int frameNum = 0;
	String name;
	
	//counters
	int count = 1;
	int replaceNum = 1;
	int replaceNumLru = 5;
	
	//results
	int pageFault = 0;
	double total = 0;
	double rate = 0.0;
	
	//number formatter
	NumberFormat formatter = new DecimalFormat("#0.00");

	//frame
	JFrame frame = new JFrame();

	//panel
	JPanel panel = new JPanel();

	//text fields
	JTextField numOfFrames = new JTextField();
	JTextField addNewJob = new JTextField();

	//labels
	JLabel numOfFramesLabel = new JLabel();
	JLabel newJobLabel = new JLabel();

	//text panes
	JTextPane pageFrame1 = new JTextPane();
	JTextPane pageFrame2 = new JTextPane();
	JTextPane pageFrame3 = new JTextPane();
	JTextPane pageFrame4 = new JTextPane();
	JTextPane pageFrame5 = new JTextPane();
	JTextPane faults = new JTextPane();
	JTextPane totalNum = new JTextPane();
	JTextPane rateDisplay = new JTextPane();

	//buttons
	JButton btnFifo = new JButton();
	JButton btnLru = new JButton();
	JButton btnLruHelper = new JButton();

	//attribute set used to center text in the text panes
	SimpleAttributeSet attribs = new SimpleAttributeSet();

	//scroll properties
	int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
	int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
	JScrollPane scrollPane = new JScrollPane(panel, v, h);

	//layout manager of gui
	GridBagLayout layout = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	
	//removal sim object that adds jobs and removes and adds to stack and queue to do fifo and lfu
	RemovalSim sim = new RemovalSim();

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
	 * sets initial values for frame and panel
	 */
	private void initializeFrame() {
		frame.setTitle("Page Removal Simulator");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		panel.setLayout(layout);
		//scroll bar added into frame which contains the panel
		frame.getContentPane().add(scrollPane);
		panel.setBorder(BorderFactory.createBevelBorder(0, Color.BLUE, Color.DARK_GRAY));
	}

	/**
	 * runs all methods needed to populate the frame with all elements
	 */
	private void populateFrame() {
		drawFrameLabel();
		drawNumOfFrames();
		drawFrame1();
		drawFrame2();
		drawFrame3();
		drawFrame4();
		drawFrame5();
		drawNewJobLabel();
		drawAddNewJob();
		drawBtnFifo();
		drawBtnLru();
		drawBtnLruHelper();
		drawFaults();
		drawTotal();
		drawRate();
	}

	/**
	 * sets grid bag constraints of page frame number label
	 * adds to panel
	 */
	private void drawFrameLabel() {
		c.fill = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 25;
		c.ipadx = 50;
		panel.add(numOfFramesLabel, c);
		numOfFramesLabel.setText("Enter the number of Memory Page Frames Wanted (2-5 only)");
	}

	/**
	 * sets grid bag constraints of text field of inputting frame numbers
	 * adds to panel
	 */
	private void drawNumOfFrames() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.ipady = 25;
		c.ipadx = 50;
		c.gridwidth = 3;
		panel.add(numOfFrames, c);
		numOfFrames.setText("");
	}

	/**
	 * sets grid bag constraints of pageFrame1
	 * adds to panel
	 */
	private void drawFrame1() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		pageFrame1.setParagraphAttributes(attribs,true);
		pageFrame1.setBorder(BorderFactory.createBevelBorder(0, Color.BLUE, Color.DARK_GRAY));
		panel.add(pageFrame1, c);
		pageFrame1.setText("");
	}

	/**
	 * sets grid bag constraints of pageFrame2
	 * adds to panel
	 */
	private void drawFrame2() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		pageFrame2.setParagraphAttributes(attribs,true);
		pageFrame2.setBorder(BorderFactory.createBevelBorder(0, Color.BLUE, Color.DARK_GRAY));
		panel.add(pageFrame2, c);
		pageFrame2.setText("");
	}

	/**
	 * sets grid bag constraints of pageFrame3
	 * adds to panel
	 */
	private void drawFrame3() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		pageFrame3.setParagraphAttributes(attribs,true);
		pageFrame3.setBorder(BorderFactory.createBevelBorder(0, Color.BLUE, Color.DARK_GRAY));
		panel.add(pageFrame3, c);
		pageFrame3.setText("");
	}

	/**
	 * sets grid bag constraints of pageFrame4
	 * adds to panel
	 */
	private void drawFrame4() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		pageFrame4.setParagraphAttributes(attribs,true);
		pageFrame4.setBorder(BorderFactory.createBevelBorder(0, Color.BLUE, Color.DARK_GRAY));
		panel.add(pageFrame4, c);
		pageFrame4.setText("");
	}

	/**
	 * sets grid bag constraints of pageFrame5
	 * adds to panel
	 */
	private void drawFrame5() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.ipady = 25;
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		pageFrame5.setParagraphAttributes(attribs,true);
		pageFrame5.setBorder(BorderFactory.createBevelBorder(0, Color.BLUE, Color.DARK_GRAY));
		panel.add(pageFrame5, c);
		pageFrame5.setText("");
	}

	/**
	 * sets grid bag constraints of new job label
	 * adds to panel
	 */
	private void drawNewJobLabel() {
		c.fill = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 6;
		c.ipady = 25;
		panel.add(newJobLabel, c);
		newJobLabel.setText("Enter a job to add into memory");
	}

	/**
	 * sets grid bag constraints of new job text field
	 * adds to panel
	 */
	private void drawAddNewJob() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 6;
		c.ipady = 25;
		panel.add(addNewJob, c);
		addNewJob.setText("");
	}

	/**
	 * sets grid bag constraints of fifo button
	 * adds to panel
	 */
	private void drawBtnFifo() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 7;
		c.ipady = 25;
		panel.add(btnFifo, c);
		btnFifo.setText("FIFO");
	}

	/**
	 * sets grid bag constraints of lru button
	 * adds to panel
	 */
	private void drawBtnLru() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 8;
		c.ipady = 25;
		panel.add(btnLru, c);
		btnLru.setText("LRU");
	}
	
	/**
	 * sets grid bag constraints of lru helper button
	 * adds to panel
	 */
	private void drawBtnLruHelper() {
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 8;
		c.ipady = 25;
		panel.add(btnLruHelper, c);
		btnLruHelper.setText("Switch to LRU");
	}
	
	/**
	 * sets grid bag constraints of page faults
	 * adds to panel
	 */
	private void drawFaults() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 9;
		c.ipadx = 50;
		c.ipady = 50;
		panel.add(faults, c);
		faults.setText("");
	}
	
	/**
	 * sets grid bag constraints of total requested pages
	 * adds to panel
	 */
	private void drawTotal() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 10;
		c.ipadx = 50;
		c.ipady = 50;
		panel.add(totalNum, c);
		totalNum.setText("");
	}
	
	/**
	 * sets grid bag constraints of failure rate
	 * adds to panel
	 */
	private void drawRate() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 11;
		c.ipady = 25;
		panel.add(rateDisplay, c);
		rateDisplay.setText("");
	}

	/**
	 * adds action listeners to all buttons
	 */
	private void addActionListeners() {
		btnFifo.addActionListener(btnFifoListener);
		btnLru.addActionListener(btnLruListener);
		btnLruHelper.addActionListener(lruHelper);
	}

	/**
	 * FIFO page removal method
	 */
	private void fifo() {
		//amount of avaliable page frames in memory, user generated
		frameNum = Integer.parseInt(numOfFrames.getText());
		//user types in new job to add
		name = addNewJob.getText();
		
		//this part covers the initial additions (frame empty, jobs go right in)
		//if the job to add isn't already in memory
		if(sim.checkJob(name) != 1) {
			//if the count is less than the frame number
			if(count <= frameNum) {
				//add the job
				sim.addJob(name);
				//if the count is 1 add to frame 1
				if(count == 1) {
					//display the text in frame 1
					//update count
					//update total page requests
					pageFrame1.setText(name);
					count++;
					total++;
					totalNum.setText("Total: " + total);
				} else if(count == 2) {
					pageFrame2.setText(name);
					count++;
					total++;
					totalNum.setText("Total: " + total);
				} else if(count == 3){
					pageFrame3.setText(name);
					count++;
					total++;
					totalNum.setText("Total: " + total);
				} else if(count == 4) {
					pageFrame4.setText(name);
					count++;
					total++;
					totalNum.setText("Total: " + total);
				} else if(count == 5) {
					pageFrame5.setText(name);
					count++;
					total++;
					totalNum.setText("Total: " + total);
				}
			//part that covers when jobs already in memory and available frames full
			//if the count is equal to number of frames (initial additions over)
			//and replaceNum (new counter) is less than the frames
			//do FIFO method
			} else if(count == frameNum + 1 && replaceNum <= frameNum){
				if(sim.checkJob(name) == -1 || sim.checkJob(name) == 0) {
					sim.fifo(name);
					sim.addJob(name);
					//replaceNum corresponds to what frame to print to
					if(replaceNum == 1) {
						if(!name.equals(pageFrame1.getText())) {
							pageFrame1.setText(name);
							replaceNum++;
							pageFault++;
							total++;
							rate = pageFault / total;
							faults.setText("Faults: " + pageFault);
							totalNum.setText("Total: " + total);
							rateDisplay.setText("Failure Rate: " + (formatter.format(rate * 100)) + "%" );
						}
					} else if(replaceNum == 2) {
						if(!name.equals(pageFrame2.getText())) {
							pageFrame2.setText(name);
							replaceNum++;
							pageFault++;
							total++;
							rate = pageFault / total;
							faults.setText("Faults: " + pageFault);
							totalNum.setText("Total: " + total);
							rateDisplay.setText("Failure Rate: " + (formatter.format(rate * 100)) + "%" );
						}
					} else if(replaceNum == 3) {
						if(!name.equals(pageFrame3.getText())) {
							pageFrame3.setText(name);
							replaceNum++;
							pageFault++;
							total++;
							rate = pageFault / total;
							faults.setText("Faults: " + pageFault);
							totalNum.setText("Total: " + total);
							rateDisplay.setText("Failure Rate: " + (formatter.format(rate * 100)) + "%" );
						}
					} else if(replaceNum == 4) {
						if(!name.equals(pageFrame4.getText())) {
							pageFrame4.setText(name);
							replaceNum++;
							pageFault++;
							total++;
							rate = pageFault / total;
							faults.setText("Faults: " + pageFault);
							totalNum.setText("Total: " + total);
							rateDisplay.setText("Failure Rate: " + (formatter.format(rate * 100)) + "%" );
						}
					} else if(replaceNum == 5) {
						if(!name.equals(pageFrame5.getText())) {
							pageFrame5.setText(name);
							replaceNum = 1;
							pageFault++;
							total++;
							rate = pageFault / total;
							faults.setText("Faults: " + pageFault);
							totalNum.setText("Total: " + total);
							rateDisplay.setText("Failure Rate: " + (formatter.format(rate * 100)) + "%" );
						}
					}
				}
			} else {
				replaceNum = 1;
			}
		}
		
	}
	
	/**
	 * used by LRU, sets the replaceNumLru counter that handles what frame to print to 
	 */
	private void setReplaceNumLru() {
		replaceNumLru = Integer.parseInt(numOfFrames.getText());
	}
	
	/**
	 * LRU page removal method
	 * Structure similar to FIFO
	 */
	private void lru() {
		frameNum = Integer.parseInt(numOfFrames.getText());
		name = addNewJob.getText();

		if(sim.checkJob(name) != 1) {
			if(count <= frameNum) {
				sim.addJob(name);
				if(count == 1) {
					pageFrame1.setText(name);
					count++;
					total++;
					totalNum.setText("Total: " + total);
				} else if(count == 2) {
					pageFrame2.setText(name);
					count++;
					total++;
					totalNum.setText("Total: " + total);
				} else if(count == 3){
					pageFrame3.setText(name);
					count++;
					total++;
					totalNum.setText("Total: " + total);
				} else if(count == 4) {
					pageFrame4.setText(name);
					count++;
					total++;
					totalNum.setText("Total: " + total);
				} else if(count == 5) {
					pageFrame5.setText(name);
					count++;
					total++;
					totalNum.setText("Total: " + total);
				}
			} else if(count == frameNum + 1 && replaceNumLru != 0){
				if(sim.checkJob(name) == -1 || sim.checkJob(name) == 0) {
					sim.lru(name);
					sim.addJob(name);
					if(replaceNumLru == 1 ) {
						if(!name.equals(pageFrame1.getText())) {
							pageFrame1.setText(name);
							replaceNumLru = frameNum;
							pageFault++;
							total++;
							rate = pageFault / total;
							faults.setText("Faults: " + pageFault);
							totalNum.setText("Total: " + total);
							rateDisplay.setText("Failure Rate: " + (formatter.format(rate * 100)) + "%" );
						}
					} else if(replaceNumLru == 2) {
						if(!name.equals(pageFrame2.getText())) {
							pageFrame2.setText(name);
							replaceNumLru--;
							pageFault++;
							total++;
							rate = pageFault / total;
							faults.setText("Faults: " + pageFault);
							totalNum.setText("Total: " + total);
							rateDisplay.setText("Failure Rate: " + (formatter.format(rate * 100)) + "%" );
						}
					} else if(replaceNumLru == 3) {
						if(!name.equals(pageFrame3.getText())) {
							pageFrame3.setText(name);
							replaceNumLru--;
							pageFault++;
							total++;
							rate = pageFault / total;
							faults.setText("Faults: " + pageFault);
							totalNum.setText("Total: " + total);
							rateDisplay.setText("Failure Rate: " + (formatter.format(rate * 100)) + "%" );
						}
					} else if(replaceNumLru == 4) {
						if(!name.equals(pageFrame4.getText())) {
							pageFrame4.setText(name);
							replaceNumLru--;
							pageFault++;
							total++;
							rate = pageFault / total;
							faults.setText("Faults: " + pageFault);
							totalNum.setText("Total: " + total);
							rateDisplay.setText("Failure Rate: " + (formatter.format(rate * 100)) + "%" );
						}
					} else if(replaceNumLru == 5) {
						if(!name.equals(pageFrame5.getText())) {
							pageFrame5.setText(name);
							replaceNumLru--;
							pageFault++;
							total++;
							rate = pageFault / total;
							faults.setText("Faults: " + pageFault);
							totalNum.setText("Total: " + total);
							rateDisplay.setText("Failure Rate: " + (formatter.format(rate * 100)) + "%" );
						}
					}
				}
			} else {
				replaceNumLru = frameNum;
			}
		}
		


	}

	/**
	 *Action listener on FIFO button, calls the FIFO method when clicked
	 */
	ActionListener btnFifoListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent ea) {
			fifo();
		}
	};

	/**
	 * Action listener on LRU button, calls the LRU method when clicked 
	 */
	ActionListener btnLruListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent ea) {
			lru();
		}

	};
	
	/**
	 * LRU helper method, calls the setReplaceNumLru method when clicked
	 */
	ActionListener lruHelper = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent ea) {
			setReplaceNumLru();
		}
	};

}

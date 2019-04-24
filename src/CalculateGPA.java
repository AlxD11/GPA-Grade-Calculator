/*----------------------*\
|*     Alex Dierks      *|
|* GPA/Grade Calculator *|
|*  V. 6.0 04/14/2019   *|
\*----------------------*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.*;

public class CalculateGPA extends JPanel implements Constants
{
	private JLabel title, prompt;
	private JLabel hint;
	private JLabel hint2;
	/**Displays the calculated overall GPA.*/
	private JLabel displayGPA;
	private JPanel promptPanel;
	private JPanel containerPanel;
	private JButton calculate;
	
	///**Holds the GPA input boxes.*/
	//private JPanel responseBoxes;
	/**Holds the scroll window and the GPA response boxes inside of it.*/
	private JPanel responsePanel;
	/**Provides input for user to enter how many classes they're taking.*/
	private JTextField numClassesResponse;
	///**Scrolls between the GPA response boxes stored inside of responses.*/
	//private JScrollPane responsePanelScroller;
	
	///**The preferred size of the responsePanel.*/
	//private final Dimension responsePanelSize = new Dimension(300, 300);
	
	private NumListener    numListener    = new NumListener();
	private ButtonListener buttonListener = new ButtonListener();
	
	public static int numClasses = 0;
	/**Gets updates from ResponseBoxes.*/
	public static double[] GPAs;
	private double GPA;
	
	private static final int MAX_CLASSES = 12;
	
	public CalculateGPA()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		hint  = new JLabel("Press enter after every grade, then click Calculate.");
		hint2 = new JLabel("ERR: Max number of classes is " + MAX_CLASSES + ", sorry.");
		calculate = new JButton("Calculate");
		numClassesResponse = new JTextField(5);
		
		title = new JLabel("Calculate GPA");
    	title.setFont(TITLE_FONT);
    	title.setAlignmentX(CENTER_ALIGNMENT);
    	
    	//responseBoxes = new JPanel();
    	//responseBoxes.setLayout(new BoxLayout(responseBoxes, BoxLayout.Y_AXIS));
    	
    	responsePanel = new JPanel();
    	responsePanel.setLayout(new BoxLayout(responsePanel, BoxLayout.Y_AXIS));
    	//responsePanel.setPreferredSize(responsePanelSize);
    	
    	//responsePanelScroller = new JScrollPane(responsePanel);
    	
    	numClassesResponse.addActionListener(numListener);
    	
    	promptPanel = new JPanel();
    	prompt = new JLabel("How many classes are you taking?");
    	prompt.setFont(PROMPT_FONT);
    	promptPanel.add(prompt);
    	promptPanel.add(numClassesResponse);
    	
    	containerPanel = new JPanel();
    	containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
    	containerPanel.setPreferredSize(INTERNAL_SIZE);
    	containerPanel.setBorder(THICK);
    	containerPanel.add(promptPanel);
    	
    	displayGPA = new JLabel("GPA: __");
    	displayGPA.setFont(MAJOR_OUTPUT_FONT);
    	
    	JPanel GPAPanel = new JPanel();
    	GPAPanel.setBorder(ETCHED);
    	GPAPanel.add(displayGPA);
    	
    	add(title);
    	add(containerPanel);
    	add(Box.createRigidArea(PADDING));
    	add(GPAPanel);
	}
	
//-------------------------------------------------------------- Number Listener --------------------------------------------------------------
	
	private class NumListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
				promptPanel.remove(hint2);
				
				calculate.addActionListener(buttonListener);
				calculate.setAlignmentX(CENTER_ALIGNMENT);
				hint.setFont(HINT_FONT);
				hint.setAlignmentX(CENTER_ALIGNMENT);
				hint2.setFont(ERROR_FONT);
				
				numClasses = Integer.parseInt(numClassesResponse.getText());
				if(numClasses > MAX_CLASSES)
				{
					numClasses = MAX_CLASSES; //To prevent idiots from blowing it up with 110,000 classes
					promptPanel.add(hint2);
				}
					
				GPAs = new double[numClasses];
				
				promptPanel.remove(hint);
				containerPanel.remove(calculate); //Resetting Panels
				responsePanel.removeAll();
				
				//responsePanel.setLayout(new BoxLayout(responsePanel, BoxLayout.Y_AXIS));
				//responseBoxPanel.add(Box.createRigidArea(padding));
    			
				for (int x = 0; x < numClasses; x++)
					responsePanel.add(new ResponseBox(x));

				responsePanel.revalidate();
				//responsePanelScroller.remove(responsePanel);
				//responsePanelScroller.add(responsePanel);
				promptPanel.add(responsePanel); //responsePanelScroller);
				promptPanel.add(hint);
				containerPanel.add(calculate);
				
				//responsePanelScroller.updateUI();
				//responsePanelScroller.revalidate();
				//responsePanelScroller.repaint();
				promptPanel.updateUI();
				promptPanel.revalidate();
				promptPanel.repaint(); //I kinda don't know what these do but to be safe LET'S CALL ALL OF THEM
				containerPanel.updateUI();
				containerPanel.revalidate();
				containerPanel.repaint();
		}
	}
	
//-------------------------------------------------------------- Button Listener --------------------------------------------------------------
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			GPA = 0.0;
			for (int x = 0; x < numClasses; x++)
				GPA += GPAs[x];
			GPA = GPA/numClasses;
			displayGPA.setText("GPA: " + formatLikeDouble.format(GPA));
		}
	}
}
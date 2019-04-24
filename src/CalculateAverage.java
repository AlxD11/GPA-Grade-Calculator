/*----------------------*\
|*     Alex Dierks      *|
|* GPA/Grade Calculator *|
|*  V. 6.0 04/14/2019   *|
\*----------------------*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculateAverage extends JPanel implements Constants
{
	private JLabel title, prompt, hint, hint2;
    private JPanel askForTests, askForDailies, hintPanel;
	private JLabel displayTestAvg, displayDailyAvg, displayClassAvg;

	/**Provides input for the user to enter their test grades.*/
	private JTextField testGradeResponse;
	/**Provides input for the user to enter their daily grades.*/
	private JTextField dailyGradeResponse;
	
	private GradeListener gradeListener = new GradeListener();
    
    public CalculateAverage()
    {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	
    	title = new JLabel("Calculate Average");
    	title.setFont(TITLE_FONT);
    	title.setAlignmentX(CENTER_ALIGNMENT);
    	
    	displayClassAvg = new JLabel("Class Average: __");
    	displayClassAvg.setFont(MAJOR_OUTPUT_FONT);
    	displayClassAvg.setAlignmentY(CENTER_ALIGNMENT);
    	
    	JPanel displayAverage = new JPanel();
    	displayAverage.setBorder(ETCHED);
    	displayAverage.add(displayClassAvg);
    	
    	initAskForTests();
    	initAskForDailies();
    	
    	JPanel askForPanels = new JPanel();
    	askForPanels.setLayout(new BoxLayout(askForPanels, BoxLayout.Y_AXIS));
    	askForPanels.setPreferredSize(INTERNAL_SIZE);
    	askForPanels.add(Box.createRigidArea(PADDING));
    	askForPanels.add(askForTests);
    	askForPanels.add(Box.createRigidArea(PADDING));
    	askForPanels.add(askForDailies);
    	askForPanels.add(Box.createRigidArea(PADDING));
    	
    	//Keeps internal panel borders from hitting the edges.
    	JPanel containerPanel = new JPanel ();
    	containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
    	containerPanel.setBorder(THICK);
    	containerPanel.add(Box.createRigidArea(PADDING));
    	containerPanel.add(askForPanels);
    	containerPanel.add(Box.createRigidArea(PADDING));
    	
    	add(title);
    	add(containerPanel);
    	add(Box.createRigidArea(PADDING));
    	add(displayAverage);
    }
    
//-------------------------------------------------- Creating Ask for Tests / Dailies Panels --------------------------------------------------
    
    public void initAskForTests()
    {
    	askForTests = new JPanel();
    	askForTests.setLayout(new BoxLayout(askForTests, BoxLayout.Y_AXIS));
    	askForTests.setBorder(ETCHED);
    	
    	displayTestAvg = new JLabel("Test Average: __");
    	displayTestAvg.setFont(MINOR_OUTPUT_FONT);
    	displayTestAvg.setAlignmentX(CENTER_ALIGNMENT);
    	
    	prompt = new JLabel("Enter your Test grades.");
    	prompt.setFont(PROMPT_FONT);
    	
    	testGradeResponse = new JTextField(30);
    	testGradeResponse.addActionListener(gradeListener);
    	
    	JPanel promptPanel = new JPanel();
    	promptPanel.add(prompt);
    	promptPanel.add(testGradeResponse);

    	askForTests.add(Box.createRigidArea(PADDING));
    	askForTests.add(promptPanel);
    	askForTests.add(hintPanel());
    	askForTests.add(displayTestAvg);
    }
    
    public void initAskForDailies()
    {
    	askForDailies = new JPanel();
    	askForDailies.setLayout(new BoxLayout(askForDailies, BoxLayout.Y_AXIS));
    	askForDailies.setBorder(ETCHED);
    	
    	displayDailyAvg = new JLabel("Daily Average: __");
    	displayDailyAvg.setFont(MINOR_OUTPUT_FONT);
    	displayDailyAvg.setAlignmentX(CENTER_ALIGNMENT);
    	
    	prompt = new JLabel("Enter your Daily grades.");
    	prompt.setFont(PROMPT_FONT);
    	
    	dailyGradeResponse = new JTextField(30);
    	dailyGradeResponse.addActionListener(gradeListener);
    	
    	JPanel promptPanel = new JPanel();
    	promptPanel.add(prompt);
    	promptPanel.add(dailyGradeResponse);
    	
    	askForDailies.add(Box.createRigidArea(PADDING));
    	askForDailies.add(promptPanel);
    	askForDailies.add(hintPanel());
    	askForDailies.add(displayDailyAvg);
    }
    
    public JPanel hintPanel()
    {
    	hintPanel = new JPanel();
    	
    	hint  = new JLabel("Type all your grades here, separated by a space.");
    	hint2 = new JLabel("Press enter when all your grades are typed.");
    	hint.setFont(HINT_FONT);
    	hint2.setFont(HINT_FONT);
    	
    	hintPanel.add(hint);
    	hintPanel.add(hint2);
    	
    	return hintPanel;
    }
    
//-------------------------------------------------------------- Grade Listener ---------------------------------------------------------------

	/**Handles calculating and displaying the class average every time the user presses enter after inputting their grades.*/
    private class GradeListener implements ActionListener
	{
		private int amtTestGrades = 0, amtDailyGrades = 0;
		private double testAverage = 0, dailyAverage = 0;
		private double classAverage = 0;
		
		//Updates entire component if either field changes.
		public void actionPerformed(ActionEvent event)
		{
			if (event.getSource() == testGradeResponse || event.getSource() == dailyGradeResponse)
			{
			//------------------------------------ Update Test Grades ------------------------------------//
				int sumTests = 0;
				int sumDailies = 0;
				boolean includeTests = true;
				boolean includeDailies = true;
				String[] grades;
				String input = testGradeResponse.getText().trim();
				
				if (!input.isEmpty())
				{
					grades = input.split(" ");
					amtTestGrades = grades.length;
					
					sumTests = 0;
					for (int x = 0; x < amtTestGrades; x++)
						sumTests += (Integer.parseInt(grades[x]));
					
					testAverage = sumTests/amtTestGrades;
					displayTestAvg.setText ("Test Average: " + formatLikeInt.format(testAverage));
				}
				else
				{
					includeTests = false;
					displayTestAvg.setText("Test Average: __");
				}
				
			//------------------------------------ Update Daily Grades ------------------------------------//
				input = dailyGradeResponse.getText().trim();
				if (!input.isEmpty())
				{
					grades = input.split(" ");
					amtDailyGrades = grades.length;
					
					sumDailies = 0;
					for (int x = 0; x < amtDailyGrades; x++)
						sumDailies += (Integer.parseInt(grades[x]));
					
					dailyAverage = sumDailies/amtDailyGrades;
					displayDailyAvg.setText("Daily Average: " + formatLikeInt.format(dailyAverage));
				}
				else
				{
					includeDailies = false;
					displayDailyAvg.setText("Daily Average: __");
				}
				
				if (includeTests && includeDailies)
					classAverage = (Type.TEST.weight * testAverage) + (Type.DAILY.weight * dailyAverage);
				else if (!includeTests)
					classAverage = dailyAverage;
				else if (!includeDailies)
					classAverage = testAverage;
				
				displayClassAvg.setText("Class Average: " + formatLikeDouble.format(classAverage));
			}
		}
	}
}
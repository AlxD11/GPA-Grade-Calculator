/*----------------------*\
|*     Alex Dierks      *|
|* GPA/Grade Calculator *|
|*  V. 6.0 04/14/2019   *|
\*----------------------*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculateWhatPasses extends JPanel implements Constants
{
	private JLabel title, prompt, prompt2, hint, hint2;
	private JLabel displayAvg, displayOppositeAvg, displayPassing;
	private JPanel containerPanel, selectionPanel, calculationPanel, hintPanel;
	private JPanel responseBox1, responseBox2, askGrades1, askGrades2;
	private JButton calculateTest, calculateDaily, back;
	
	private JTextField gradesBox         = new JTextField(30);
	private JTextField gradesOppositeBox = new JTextField(30);
	
	private ButtonListener buttonListener = new ButtonListener();
	private GradeListener  gradeListener  = new GradeListener();
	
	private Type gradeType, oppositeGradeType;
	private double weight = 0, oppositeWeight = 0;
	
	public CalculateWhatPasses()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		title = new JLabel("Calculate What You'd Need to Pass");
    	title.setFont(TITLE_FONT);
    	title.setAlignmentX(CENTER_ALIGNMENT);

    	initSelectionPanel();
    	
    	containerPanel = new JPanel();
    	containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
    	containerPanel.setPreferredSize(INTERNAL_SIZE);
    	containerPanel.add(selectionPanel);
    	
    	//Contains the container panel. Keeps internal boarders from hitting the edges.
    	JPanel paddingPanel = new JPanel();
    	paddingPanel.setBorder(THICK);
    	paddingPanel.setLayout(new BoxLayout(paddingPanel, BoxLayout.X_AXIS));
    	paddingPanel.add(Box.createRigidArea(PADDING));
    	paddingPanel.add(containerPanel);
    	paddingPanel.add(Box.createRigidArea(PADDING));
    	
    	displayPassing = new JLabel("To Make a " + formatLikeInt.format(PASSING_AVG) + ", You'll Need...");
    	displayPassing.setFont(MAJOR_OUTPUT_FONT);
    	displayPassing.setAlignmentY(CENTER_ALIGNMENT);
    	
    	JPanel displayGrade = new JPanel();
    	displayGrade.setBorder(ETCHED);
    	displayGrade.add(displayPassing);
    	
    	add(title);
    	add(paddingPanel);
    	add(Box.createRigidArea(PADDING));
    	add(displayGrade);
	}
	
//------------------------------------------------------------- Creating Panel ----------------------------------------------------------------
	
	public void initSelectionPanel()
	{
		selectionPanel = new JPanel();
		//selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		
		prompt = new JLabel("Calculates the grade you need on your next assignment in order to make a " + formatLikeInt.format(PASSING_AVG) + '.');
		prompt.setFont(PROMPT_FONT);
		prompt.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel prompt2 = new JLabel("Do you need to...");
		prompt2.setFont(PROMPT_FONT);
		prompt2.setAlignmentX(CENTER_ALIGNMENT);
		
    	calculateTest  = new JButton("Calculate My Next Test Grade");
    	calculateTest.setAlignmentX(CENTER_ALIGNMENT);
    	calculateTest.addActionListener(buttonListener);
    	
    	calculateDaily = new JButton("Calculate My Next Daily Grade");
    	calculateDaily.setAlignmentX(CENTER_ALIGNMENT);
    	calculateDaily.addActionListener(buttonListener);
    	
    	JPanel promptPanel = new JPanel();
    	promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.Y_AXIS));
    	promptPanel.setAlignmentX(CENTER_ALIGNMENT);
    	promptPanel.add(prompt);
    	promptPanel.add(prompt2);
    	promptPanel.add(Box.createRigidArea(PADDING));
    	promptPanel.add(calculateTest);
    	promptPanel.add(Box.createRigidArea(PADDING));
    	promptPanel.add(calculateDaily);
    	
    	selectionPanel.add(Box.createRigidArea(PADDING));
    	selectionPanel.add(promptPanel);
    	selectionPanel.add(Box.createRigidArea(PADDING));
	}
	
	public void initCalculationPanel()
	{
		calculationPanel = new JPanel();
		calculationPanel.setLayout(new BoxLayout(calculationPanel, BoxLayout.Y_AXIS));
		
		prompt = new JLabel();
		prompt.setFont(PROMPT_FONT);
		prompt.setAlignmentX(CENTER_ALIGNMENT);
		
		back = new JButton("Back");
		back.addActionListener(buttonListener);
		back.setAlignmentX(CENTER_ALIGNMENT);
		
		initResponseBox1();
		initResponseBox2();
		
		calculationPanel.add(Box.createRigidArea(PADDING));
		calculationPanel.add(responseBox1);
		calculationPanel.add(Box.createRigidArea(PADDING));
		calculationPanel.add(responseBox2);
		calculationPanel.add(Box.createRigidArea(PADDING));
		calculationPanel.add(Box.createVerticalGlue());
		calculationPanel.add(back);
	}
	
	/**Handles components for the grades opposite of the type of the one to be calculated.*/
	public void initResponseBox1() //Doesn't include grade to be calculated.
	{
		responseBox1 = new JPanel();
		responseBox1.setLayout(new BoxLayout(responseBox1, BoxLayout.Y_AXIS));
		responseBox1.setBorder(ETCHED);
		
		prompt2 = new JLabel("Enter your " + oppositeGradeType.string + " grades.");
		prompt2.setFont(PROMPT_FONT);
    	
    	gradesOppositeBox.addActionListener(gradeListener);
		
		askGrades1 = new JPanel();
		askGrades1.add(prompt2);
		askGrades1.add(gradesOppositeBox);
		
		displayOppositeAvg = new JLabel(oppositeGradeType.string + " Average: __");
		displayOppositeAvg.setFont(MINOR_OUTPUT_FONT);
		displayOppositeAvg.setAlignmentX(CENTER_ALIGNMENT);
		
		responseBox1.add(Box.createRigidArea(PADDING));
		responseBox1.add(askGrades1);
		responseBox1.add(hintPanel());
		responseBox1.add(displayOppositeAvg);
	}
	
	/**Handles components for the needed grade type.*/
	public void initResponseBox2() //Includes grade to be calculated.
	{
		responseBox2 = new JPanel();
		responseBox2.setLayout(new BoxLayout(responseBox2, BoxLayout.Y_AXIS));
		responseBox2.setBorder(ETCHED);
		
		prompt2 = new JLabel("Enter your " + gradeType.string + " grades.");
		prompt2.setFont(PROMPT_FONT);
    	
    	gradesBox.addActionListener(gradeListener);
		
		askGrades2 = new JPanel();
		askGrades2.add(prompt2);
		askGrades2.add(gradesBox);

		displayAvg = new JLabel(gradeType.string + " Average: __");
		displayAvg.setFont(MINOR_OUTPUT_FONT);
    	displayAvg.setAlignmentX(CENTER_ALIGNMENT);
		
		responseBox2.add(Box.createRigidArea(PADDING));
		responseBox2.add(askGrades2);
		responseBox2.add(hintPanel());
		responseBox2.add(displayAvg);
	}
	
	public JPanel hintPanel()
    {
    	hintPanel = new JPanel();
    	
    	hint  = new JLabel("Type all your grades here, separated by a space.");
    	hint2 = new JLabel("Press enter only when all your grades are typed.");
    	hint.setFont(HINT_FONT);
    	hint2.setFont(HINT_FONT);
    	
    	hintPanel.add(hint);
    	hintPanel.add(hint2);
    	
    	return hintPanel;
    }
	
//-------------------------------------------------------------- Button Listener --------------------------------------------------------------
	
	/**Handles button presses to toggle between calculating a passing test or passing daily grade.*/
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			containerPanel.removeAll();
			
			if (event.getSource() == calculateTest)
			{
				gradeType         = Type.TEST;
				oppositeGradeType = Type.DAILY;
				weight         = Type.TEST.weight;
				oppositeWeight = Type.DAILY.weight;
				initCalculationPanel();
				containerPanel.add(calculationPanel);
				title.setText("Calculate Next Test Grade");
			}
				
			else if (event.getSource() == calculateDaily)
			{
				gradeType         = Type.DAILY;
				oppositeGradeType = Type.TEST;
				weight         = Type.DAILY.weight;
				oppositeWeight = Type.TEST.weight;
				initCalculationPanel();
				containerPanel.add(calculationPanel);
				title.setText("Calculate Next Daily Grade");
			}
			
			else if (event.getSource() == back)
			{
				containerPanel.add(selectionPanel);
				title.setText("Calculate What You'd Need to Pass");
				displayPassing.setText("To Make a " + formatLikeInt.format(PASSING_AVG) + ", You'll Need...");
			}
				
			updateUI();
			repaint();
		}
	}
	
//-------------------------------------------------------------- Grade Listener ---------------------------------------------------------------
	
	/*(To find daily) Average the test grades, add up all the daily grades, then
    double GradeX = (((70-(.75 * TestAvg))/.25)*amountOfDailies)-sumOfDailies;
    (To find test) Average the daily grades, add up all the test grades, then
   	double GradeX = (((70-(.25*DailyAvg))/.75)*amountOfTests)-sumOfTests;*/
   	
   	//Average all the opposite grades, add up all the other grades, then
   	//double GradeX = (((70-(OppWeight * OppAvg))/Weight)*amtGrades)-sumGrades;
	
	/**Gets the grade averages from the input boxes and then calculates the needed passing grade.*/
	private class GradeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//Update entire component if either field changes.
			if (event.getSource() == gradesOppositeBox || event.getSource() == gradesBox)
			{
				int amtGrades = 0;
				int sumGrades = 0;
				int amtOppositeGrades = 0;
				int sumOppositeGrades = 0;
				double avgOppositeGrade = 0;
				double passingGrade = 0;
				boolean includeGrades = true;
				boolean includeOpposites = true;
				
				String[] grades;
				String gradesEntered = gradesOppositeBox.getText().trim();
				
				if (!gradesEntered.isEmpty())
				{
					grades = gradesEntered.split(" ");
					amtOppositeGrades = grades.length;
					
					sumOppositeGrades = 0;
					for (int x = 0; x < amtOppositeGrades; x++)
						sumOppositeGrades += (Integer.parseInt(grades[x]));
					
					avgOppositeGrade = ((double)sumOppositeGrades/(double)amtOppositeGrades);
					displayOppositeAvg.setText(oppositeGradeType.string + " Average: " + formatLikeInt.format(avgOppositeGrade));
				}
				else //There were no grades entered.
				{
					displayOppositeAvg.setText(oppositeGradeType.string + " Average: __");
					includeOpposites = false;
				}
				
				gradesEntered = gradesBox.getText().trim();
				
				if (!gradesEntered.isEmpty())
				{
					grades = gradesEntered.split(" ");
					amtGrades = grades.length;
				
					sumGrades = 0;
					for (int x = 0; x < amtGrades; x++)
						sumGrades += (Integer.parseInt(grades[x]));
					
					displayAvg.setText(gradeType.string + " Average: " + formatLikeInt.format(sumGrades/amtGrades));
				}
				else
				{
					displayAvg.setText(gradeType.string + " Average: __");
					includeGrades = false;
				}
				
				System.out.println("Include Grades = " + includeGrades);
				System.out.println("Include Opposites = " + includeOpposites + '\n');
				
				if (includeGrades && includeOpposites)
					passingGrade = (((PASSING_AVG - (oppositeWeight * avgOppositeGrade)) / weight) * (amtGrades + 1)) - sumGrades;
				else if (!includeOpposites)
					passingGrade = (PASSING_AVG * (amtGrades + 1)) - sumGrades;
				else if (!includeGrades)
					passingGrade = (((PASSING_AVG - (oppositeWeight * avgOppositeGrade)) / weight) * (amtGrades + 1));
					
				passingGrade = Math.ceil(passingGrade); //Round grade up to nearest int.
				displayPassing.setText("Next " + gradeType.string + " Needed To Make a " + formatLikeInt.format(PASSING_AVG) + ": " + formatLikeInt.format(passingGrade));
			}
		}
	}
}
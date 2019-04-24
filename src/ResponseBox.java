/*----------------------*\
|*     Alex Dierks      *|
|* GPA/Grade Calculator *|
|*  V. 6.0 04/14/2019   *|
\*----------------------*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**Handles calculating the GPA for a single class.*/
public class ResponseBox extends JPanel implements Constants
{
	private JLabel prompt;
	/**Displays the calculated class GPA.*/
	private JLabel displayGPA;
	
	private final String[] levels = {"Developmental Level", "Basic/Support Level", "Regular Level", "Honor Level", "A.P./I.B. Level"};
	/**Allows the user to switch between the different class types.*/
	private JComboBox levelSelection = new JComboBox(levels);
	/**Stores the class level selected from levelSelection.*/
	private int classLevel = 0;
	
	/**Provides user with place to input their class average.*/
	private JTextField gradeResponse = new JTextField(5);
	
	private GradeListener gradeListener = new GradeListener();
	private LevelListener levelListener = new LevelListener();
	
	/**The index of CalculateGPA's GPA array this component will use to store the calculated GPA.*/
	private int index = 0;
	/**The calculated GPA.*/
	private double GPA = 0.0;
	
	public ResponseBox(int index)
	{
		this.index = index;
		setBorder(ETCHED);
		
		levelSelection.addActionListener(levelListener);
		levelSelection.setSelectedIndex(2); //Defaults to Regular Level.
		gradeResponse.addActionListener(gradeListener);
		
		prompt = new JLabel("Select the class level.");
		prompt.setFont(PROMPT_FONT);
		
		JLabel prompt2 = new JLabel("Enter your grade.");
		prompt2.setFont(PROMPT_FONT);
		
		displayGPA = new JLabel("__");
		displayGPA.setFont(MINOR_OUTPUT_FONT);
		
		add(prompt);
		add(levelSelection);
		add(prompt2);
		add(gradeResponse);
		add(displayGPA);
	}
	
//----------------------------------------------------------- GPA Calculating Methods ---------------------------------------------------------
	
	public double getDevelopmentalGPA(int grade)
	{
		if (isBetween(grade,97,100))
			return 2.5;
		else if (isBetween(grade,93,96))
			return 2.3;
		else if (isBetween(grade,90,92))
			return 2.1;
		else if (isBetween(grade,87,89))
			return 1.9;
		else if (isBetween(grade,83,86))
			return 1.7;
		else if (isBetween(grade,80,82))
			return 1.5;
		else if (isBetween(grade,77,79))
			return 1.3;
		else if (isBetween(grade,73,76))
			return 1.1;
		else if (isBetween(grade,70,72))
			return 1.0;
		else if (grade == 70)
			return 1.0;
		else
			return 0.0;
	}
	
	public double getBasicSupportGPA(int grade)
	{
		if (isBetween(grade,97,100))
			return 3.5;
		else if (isBetween(grade,93,96))
			return 3.3;
		else if (isBetween(grade,90,92))
			return 3.1;
		else if (isBetween(grade,87,89))
			return 2.9;
		else if (isBetween(grade,83,86))
			return 2.7;
		else if (isBetween(grade,80,82))
			return 2.5;
		else if (isBetween(grade,77,79))
			return 2.3;
		else if (isBetween(grade,73,76))
			return 2.1;
		else if (isBetween(grade,70,72))
			return 1.9;
		else if (grade == 70)
			return 1.5;
		else
			return 0.0;
	}
	
	public double getRegularGPA(int grade)
	{
		if (isBetween(grade,97,100))
			return 4.0;
		else if (isBetween(grade,93,96))
			return 3.8;
		else if (isBetween(grade,90,92))
			return 3.6;
		else if (isBetween(grade,87,89))
			return 3.4;
		else if (isBetween(grade,83,86))
			return 3.2;
		else if (isBetween(grade,80,82))
			return 3.0;
		else if (isBetween(grade,77,79))
			return 2.8;
		else if (isBetween(grade,73,76))
			return 2.6;
		else if (isBetween(grade,70,72))
			return 2.4;
		else if (grade == 70)
			return 2.0;
		else
			return 0.0;
	}
	
	public double getHonorGPA(int grade)
	{
		if (isBetween(grade,97,100))
			return 4.5;
		else if (isBetween(grade,93,96))
			return 4.3;
		else if (isBetween(grade,90,92))
			return 4.1;
		else if (isBetween(grade,87,89))
			return 3.9;
		else if (isBetween(grade,83,86))
			return 3.7;
		else if (isBetween(grade,80,82))
			return 3.5;
		else if (isBetween(grade,77,79))
			return 3.3;
		else if (isBetween(grade,73,76))
			return 3.1;
		else if (isBetween(grade,70,72))
			return 2.9;
		else if (grade == 70)
			return 2.5;
		else
			return 0.0;
	}
	
	public double getAPIBGPA(int grade)
	{
		if (isBetween(grade,97,100))
			return 5.0;
		else if (isBetween(grade,93,96))
			return 4.8;
		else if (isBetween(grade,90,92))
			return 4.6;
		else if (isBetween(grade,87,89))
			return 4.4;
		else if (isBetween(grade,83,86))
			return 4.2;
		else if (isBetween(grade,80,82))
			return 4.0;
		else if (isBetween(grade,77,79))
			return 3.8;
		else if (isBetween(grade,73,76))
			return 3.6;
		else if (isBetween(grade,70,72))
			return 3.4;
		else if (grade == 70)
			return 3.0;
		else
			return 0.0;
	}
	
	public boolean isBetween(int grade, int low, int high)
	{ return low <= grade && grade <= high; }
	
//--------------------------------------------------------------- Level Listener --------------------------------------------------------------
	
	private class LevelListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			classLevel = levelSelection.getSelectedIndex();
		}
	}
	
//--------------------------------------------------------------- Grade Listener --------------------------------------------------------------
	
	private class GradeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			int grade = Integer.parseInt(gradeResponse.getText());
			
			if (isBetween(grade, 0, 100))
			{
				if (classLevel == 0)
					GPA = getDevelopmentalGPA(grade);
				else if (classLevel == 1)
					GPA = getBasicSupportGPA(grade);
				else if (classLevel == 2)
					GPA = getRegularGPA(grade);
				else if (classLevel == 3)
					GPA = getHonorGPA(grade);
				else if (classLevel == 4)
					GPA = getAPIBGPA(grade);
				
				displayGPA.setText(Double.toString(GPA));
				CalculateGPA.GPAs[index] = GPA; //Sending GPA back to the array in CalculateGPA.
			}
			else
			{
				displayGPA.setText("!?");
				CalculateGPA.GPAs[index] = 0.0;
			}
		}
	}
}
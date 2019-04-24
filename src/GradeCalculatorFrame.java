/*----------------------*\
|*     Alex Dierks      *|
|* GPA/Grade Calculator *|
|*  V. 6.0 04/14/2019   *|
\*----------------------*/

import javax.swing.JFrame;

public class GradeCalculatorFrame
{
	public static void main(String[] args)
	{
        JFrame calculator = new JFrame("Grade Calculator");
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.getContentPane().add(new MainPanel());
        calculator.pack();
        calculator.setVisible(true);
    }
}
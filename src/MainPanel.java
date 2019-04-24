/*----------------------*\
|*     Alex Dierks      *|
|* GPA/Grade Calculator *|
|*  V. 6.0 04/14/2019   *|
\*----------------------*/

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainPanel extends JPanel implements Constants
{
	private JLabel copyright;
	private JPanel controlPanel, interfaceContainer, copyrightPanel;
	private JPanel calculateAverage, calculateWhatPasses, calculateGPA;
	private JButton calcAverage, calcWhatPasses, calcGPA;
	
	private ButtonListener buttonListener = new ButtonListener();
	
    public MainPanel()
    {
		initInterfaceContainer();
		initControlPanel();
		initCopyrightPanel();
		
		Box panels = new Box(BoxLayout.Y_AXIS);
		panels.add(interfaceContainer);
		panels.add(controlPanel);
		panels.add(copyright);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setPreferredSize(WINDOW_SIZE);
		add(Box.createRigidArea(PADDING));
		add(panels);
		add(Box.createRigidArea(PADDING));
    }
    
//--------------------------------------------------- Creating Interface and Control Panels ---------------------------------------------------

    /**Contains all the main calc panels.*/
    private void initInterfaceContainer()
    {
		interfaceContainer = new JPanel();
		interfaceContainer.setLayout(new CardLayout());
		interfaceContainer.setPreferredSize(INTERNAL_SIZE);
		
		calculateAverage    = new CalculateAverage();
		calculateGPA        = new CalculateGPA();
		calculateWhatPasses = new CalculateWhatPasses();
    	
		interfaceContainer.add(calculateAverage, "Average");
		interfaceContainer.add(calculateWhatPasses, "What Passes");
		interfaceContainer.add(calculateGPA, "GPA");
    }
    
    /**Contains buttons to switch between panels.*/
    private void initControlPanel()
    {
    	controlPanel = new JPanel();
    	
    	calcAverage    = new JButton("Calculate my Average");
		calcWhatPasses = new JButton("Calculate What I Need to Pass");
		calcGPA        = new JButton("Calculate my GPA");
		
		calcAverage.addActionListener(buttonListener);
		calcWhatPasses.addActionListener(buttonListener);
		calcGPA.addActionListener(buttonListener);
		
		controlPanel.add(calcAverage);
		controlPanel.add(calcWhatPasses);
		controlPanel.add(calcGPA);
    }
    
    private void initCopyrightPanel()
    {
    	copyright = new JLabel("Copyright 2015 Alex Dierks.");
    	copyright.setHorizontalAlignment(SwingConstants.RIGHT); //This is doing nothing.
    	
    	copyrightPanel = new JPanel(); //Spacing got messed up when putting this into panels
    	copyrightPanel.setLayout(new BoxLayout(copyrightPanel, BoxLayout.X_AXIS));
    	copyrightPanel.add(Box.createHorizontalGlue());
    	copyrightPanel.add(copyright);
    	copyrightPanel.add(Box.createHorizontalStrut(PADDING_SIZE));
    }

//-------------------------------------------------------------- Button Listener --------------------------------------------------------------

    /**Handles buttons of the controlPanel.*/
    private class ButtonListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent event)
    	{
			CardLayout cardLayout = (CardLayout) interfaceContainer.getLayout();
			
			if (event.getSource() == calcAverage)
				cardLayout.show(interfaceContainer, "Average");
			else if (event.getSource() == calcWhatPasses)
				cardLayout.show(interfaceContainer, "What Passes");
			else if (event.getSource() == calcGPA)
				cardLayout.show(interfaceContainer, "GPA");
    	}
    }
}
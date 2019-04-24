/*----------------------*\
|*     Alex Dierks      *|
|* GPA/Grade Calculator *|
|*  V. 6.0 04/14/2019   *|
\*----------------------*/

import java.awt.Font;
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**Contains constants that all components access, like Fonts and Dimensions.*/
public interface Constants
{
	public static final Font TITLE_FONT  = new Font("Cooper Black", Font.PLAIN, 20);
	public static final Font PROMPT_FONT = new Font("Garamond", Font.BOLD, 16);
    public static final Font HINT_FONT   = new Font("Garamond", Font.PLAIN, 15);
	public static final Font ERROR_FONT  = new Font("Courier", Font.PLAIN, 14);
	public static final Font MAJOR_OUTPUT_FONT = new Font("Cooper Black", Font.PLAIN, 24);
	public static final Font MINOR_OUTPUT_FONT = new Font("Garamond", Font.BOLD, 20);
	
    public static final Border ETCHED = BorderFactory.createEtchedBorder();
    public static final Border THICK  = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder());

    public static final int PADDING_SIZE = 15;
    public static final Dimension PADDING       = new Dimension(15,15);
    public static final Dimension WINDOW_SIZE   = new Dimension(650, 500);
    public static final Dimension INTERNAL_SIZE = new Dimension(400, 400);
    
    public static final DecimalFormat formatLikeDouble = new DecimalFormat("0.00");
    public static final DecimalFormat formatLikeInt    = new DecimalFormat("0");
    
    public static final double PASSING_AVG = 70;
}

/**Holds the grade type, it's weight, and String representation.*/
enum Type
{
	TEST("Test", .75), DAILY("Daily", .25);
	
	public final String string;
	public final double weight;
	
	private Type(String string, double weight)
	{
		this.string = string;
		this.weight = weight;
	}
}
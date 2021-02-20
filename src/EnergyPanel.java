/**
* Title: EnergyPanel.java
* Description : Panel for input any guessing energy.
*
* @author: Wach R
*@ version 0.1
* Date : May 29, 2011
* Last Update: June 9, 2011
*===========================
 */
package se1d;
import java.awt.*;
import java.awt.event.*;
import static se1d.SEConstants.*;
import javax.swing.*;
//import javax.swing.border.Border;
import javax.swing.text.*;
import java.text.DecimalFormat;
 //import classes.MathTools.Common.Function;


/**
 * The header panel that displays title or any messages.
 */
class EnergyPanel extends InputPanel
{
 JLabel  label1 = new JLabel("Input value of Energy whose status is Eigen state by GUESSING....");
 String str1 = "  The value begins from ";
 JLabel label2 = new JLabel();
private double minEn = -10.0;
private double maxEn = 0;
private double inputEnergy;
JTextField  textField1 = new  JTextField("0.0");
DecimalFormat decFormat;

//private BasePanel basePanel;
private BasePanel basePanel;;
    /**
     * Constructor.
     * @param headerText the header label text
     */
EnergyPanel(BasePanel basePanel)
    {
		super();
		this.basePanel = basePanel;
		controlPanel.setLayout(null);
		controlPanel.setBackground(BACKGROUND_COLOR);
		label1.setForeground(FOREGROUND_COLOR);
		label1.setBounds(10,15,400,25);  // setBounds at  x, y, width , height
		controlPanel.add(label1);
		label2.setFont(new Font("Dialog", Font.PLAIN, 12));
		label2.setForeground(FOREGROUND_COLOR);
		label2.setBounds(20, 35,350,25);
		label2.setText(str1+(minEn) + " to  " + maxEn+ " in arbitary unit ");
		controlPanel.add(label2);
		decFormat =new DecimalFormat("0.000");

		
		textField1.setForeground(FOREGROUND_COLOR);
		textField1.setBounds(410,15,100,25);
		controlPanel.add(textField1);
		textField1.setDocument( new  FloatFilter());
		textField1.addActionListener( new  ActionListener (){
		public void actionPerformed(ActionEvent e) {
			processCurrentEnergy();
			EnergyPanel.this.basePanel.doDrawing();

			
      }
    });
	  calButton.addActionListener(    new ActionListener() {
        public void actionPerformed(ActionEvent e) {
					processCurrentEnergy();
					EnergyPanel.this.basePanel.doDrawing();
        }
    });

    }
	public void setEnergyLimit(double minEn, double maxEn)
	{
		this.minEn = minEn;
		this.maxEn = maxEn;
	String	minE = decFormat.format(minEn);
	String maxE = decFormat.format(maxEn);
	label2.setText(str1+ minE +" to  " + maxE+ " in arbitary unit ");
	}
public void processCurrentEnergy()
	{
		try  {
		inputEnergy =  Double.parseDouble(textField1.getText());
		if  (( inputEnergy < minEn) || (inputEnergy > maxEn)) {
				EnergyPanel.this.basePanel.displayUserError("Your guessing energy  is out of limitation");
				return ;
		}
	EnergyPanel.this.basePanel.findWaveFunction(inputEnergy);
		} catch ( Exception ev) {
				EnergyPanel.this.basePanel.displayUserError("You SHOULD input any guessing value of energy.....!");

		}
}

class  FloatFilter extends PlainDocument
{
public static final String allowedCharacters ="0123456789.-";

public void insertString( int offset, String string, AttributeSet attr) throws BadLocationException{
	if(string == null) return;
	for(int i = 0; i <string.length(); i++) {
		if(allowedCharacters.indexOf(string.valueOf(string.charAt(i)))==-1)
			return;
	} //for
	if(string.indexOf(".") != -1) {
			if(getText(0,getLength()).indexOf(".") != -1) return;
		}
	if(string.indexOf("-") != -1) {
		if(string.indexOf("-") != 0 || offset != 0) return;
	}
	super.insertString(offset, string, attr);
}

} // end of subclass FloatFilter

  }
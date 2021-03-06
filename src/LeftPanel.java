/* File:LeftPanel.java
* Project :Potential Well Simulation
* Author  : Wachara R.
* First Released: 19 Jan 2011
* Last Updated :  May, 05, 2011
*/

package se1d;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.Border;
import static se1d.SEConstants.*;



/**
 * The left panel that displays potential functions or
 *  some parameters of program.
 */
class LeftPanel extends JPanel
{
  // public static final Color BACKGROUND_COLOR = Color.orange;
//	public static final Color FOREGROUND_COLOR = Color.blue;
	private JComboBox<String> functionChoices;
	private JLabel selectedLabel;
	private  JPanel subPanel1;  // contain selected Label and  JCombobox
	private String names[ ] = {"F1.gif", "F2.gif", "F3.gif", "F4.gif", "F5.gif"};
	private FunctionImagePanel imagePanel;
	private String functionNames[] = {"Square Well","Parabolic Well", "Triangular Well", "Tangent Square Well",
		"Inverse Hyperbolic  Cosine Well"};
	private Graphics gr;

	private  int choice=1;



    /** parent base panel */           private BasePanel   basePanel;
    /**
     * Constructor.
     * @param basePanel the parent for all panel
     */
    LeftPanel( BasePanel basePanel)
    {
        this();
        this.basePanel = basePanel;
		subPanel1 = new JPanel();  // contains  selectedLabel and functionChoice Combo Box
		subPanel1.setLayout( new GridLayout(2,1));
		subPanel1.setBackground(BACKGROUND_COLOR);

		imagePanel = new FunctionImagePanel(names);

		selectedLabel = new JLabel("Please Select  Potential Well ...");
		selectedLabel.setVerticalAlignment(SwingConstants.CENTER);
	//	selectedLabel.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOR));
		selectedLabel.setFont(new Font("Arial", Font.BOLD, 14));
		selectedLabel.setForeground(FOREGROUND_COLOR);
		subPanel1.add(selectedLabel);

		functionChoices = new JComboBox<>(functionNames);
		functionChoices.setBackground(BACKGROUND_COLOR);
		functionChoices.setForeground(FOREGROUND_COLOR);
		subPanel1.add(functionChoices);
		add(subPanel1, BorderLayout.NORTH);
		add(imagePanel, BorderLayout.CENTER);


		functionChoices.addItemListener(
			new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED)  	{
					choice = functionChoices.getSelectedIndex();
					imagePanel.display(choice);
					LeftPanel.this.basePanel.setHeaderLabel("An Electron in "+functionNames[choice], FOREGROUND_COLOR);
				//	LeftPanel.this.basePanel.setChoiceLabel("Function choice = "+choice);
					LeftPanel.this.basePanel.setFunction(choice);
					double		minEn = LeftPanel.this.basePanel.getMinEnergy();
					double	maxEn = LeftPanel.this.basePanel.getMaxEnergy();
					LeftPanel.this.basePanel.setMinAndMaxEnergy(minEn, maxEn);
					LeftPanel.this.basePanel.setEigenEnergyLabel("Eigen energy  = 0.0  in arbitary unit.",Color.white);
					LeftPanel.this.basePanel.setPsiFlag(false);
				} // if
			}
		}
		);
    }

    /**
     * Constructor.
     */
 LeftPanel()
    {
        setBackground(BACKGROUND_COLOR);
		Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder
                              (raisedBevel, loweredBevel);
        setBorder(compound);
		setLayout(new BorderLayout());

    }
int getChoice() {
		return choice;
	}

private class FunctionImagePanel extends JPanel
    {
        /** image buffer */             private BufferedImage[ ] bufferedImage= new  BufferedImage[5];   ;
        /** buffer graphics context private Graphics gr; */ 
														private int index=0;

FunctionImagePanel(String[ ] fileName ) {
//	String folder ="./JApp/SE1D/"; //original path
//	String folder = "c:/MyJavaProject/QuantumWell/classes/se1d/"; // this works well
//	String folder = "./classes/se1d/";
	String folder = "./se1d/";
	for(int i = 0 ; i <  fileName.length; i++) {
		String file = folder+fileName[i];
			try{
				bufferedImage[i]= ImageIO.read(new File(file));
		} catch (IOException e) { }
	}
          	setBackground(Color.orange);
}
public void display( int i) {
	index =i;
	repaint();
}
  public void paintComponent(Graphics g)
        {
	  super.paintComponent(g);
	  g.drawImage(bufferedImage[index],0,0,null);
        }
    }
}
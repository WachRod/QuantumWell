/**
* Title: InputPanel.java
* Description : Parent class for input Panel.
*
* @author: Wach R
*@ version 0.1
* Date : May 29, 2011
*===========================
 */
package se1d;
import java.awt.*;
import static se1d.SEConstants.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * The header panel that displays title or any messages.
 */
class InputPanel extends JPanel
{
JButton  calButton = new JButton("Calculate");
JPanel  buttonPanel = new JPanel();
JPanel controlPanel = new JPanel();
JPanel blankPanel = new JPanel();
JPanel calButtonPanel = new JPanel();

    /**
     * Constructor.
     * @param headerText the header label text
     */
   InputPanel()
    {
   		setLayout( new GridLayout(1,2));
	  setBackground(BACKGROUND_COLOR);	
		setForeground(FOREGROUND_COLOR);
		calButton.setForeground(FOREGROUND_COLOR);

		// GridLayout -- specified number of rows and columns and also
		//	with the specified horizontal and vertical gap.
		buttonPanel.setLayout(new GridLayout(3,1,10,10)); 
		controlPanel.setBackground(BACKGROUND_COLOR);	
		blankPanel.setBackground(BACKGROUND_COLOR);
		buttonPanel.setBackground(BACKGROUND_COLOR);	
		calButtonPanel.setBackground(BACKGROUND_COLOR);	
		add(controlPanel);
		calButtonPanel.add(calButton);
		buttonPanel.add(blankPanel);
		buttonPanel.add(calButtonPanel);
		add(buttonPanel);
		

    }

  
	
  }
/**
* Title: HeaderPanel.java
* Description Simple harmonic oscillator potential.
*
* @author: Wach R
*@ version 0.1
* Date : April 23, 2011
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
class HeaderPanel extends JPanel
{

    /** header      label */    private JLabel label = new JLabel("",SwingConstants.CENTER);
	/** subHeader label */ private JLabel  sublabel = new JLabel("presented by RMUT Physics (www.rmutphysics.com)"
																							,SwingConstants.CENTER);
	/** subHeader label/ */ private JLabel sublabel2 = new JLabel("Dept of Physics, Rajamangala University, Thanyaburi"
																							,SwingConstants.CENTER);
    /**
     * Constructor.
     * @param headerText the header label text
     */
    HeaderPanel(String headerText)
    {
        this();
        label.setText(headerText);
		 Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder
                              (raisedBevel, loweredBevel);
        setBorder(compound);

    }

    /**
     * Constructor.
     */
    private HeaderPanel()
    {
		setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        label.setFont(new Font("Dialog", Font.BOLD, 16));
        label.setForeground(FOREGROUND_COLOR);
		label.setBackground(BACKGROUND_COLOR);
		label.setOpaque(true);
        sublabel.setFont(new Font("Dialog", Font.PLAIN, 14));
       sublabel.setForeground(FOREGROUND_COLOR);
	   sublabel.setBackground(BACKGROUND_COLOR);
		sublabel.setOpaque(true);

        sublabel2.setFont(new Font("Dialog", Font.PLAIN, 14));
       sublabel2.setForeground(FOREGROUND_COLOR);
		sublabel.setOpaque(true);


		add(label,BorderLayout.NORTH);
		add(sublabel, BorderLayout.CENTER);
		add(sublabel2, BorderLayout.SOUTH);

    }

    /**
     * Set the header label text in color.
     * @param text the text
     * @param color the color
     */
    void setLabel(String text, Color color)
    {
        label.setForeground(color);
        label.setText(text);
		label.setBackground(BACKGROUND_COLOR);
    }
  }
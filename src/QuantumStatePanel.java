/**
* Title: QuantumStatePanel.java
* Description : Panel for eigen energy calculation 
* at any quantum number.
* @author: Wach R
*@ version 0.1
* Date : May 30, 2011
*===========================
 */
package se1d;
import java.awt.*;
import java.awt.event.*;
import static se1d.SEConstants.*;
import javax.swing.*;
//import javax.swing.border.Border;
//import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.DecimalFormat;


/**
 * The header panel that displays title or any messages.
 */
class QuantumStatePanel extends InputPanel
{
private int minState = 1;
private int maxState = 10;
private int quantumState=1; // n = 1 at first start
private double eigenEnergy = 0;

 JLabel  label1 = new JLabel("               Input Quantum Number (n)  for Calculating Energy ....");
 String str1 = "                   n begins from ";
 JLabel label2 = new JLabel();
 JLabel blankLabel = new JLabel( "   ");

 JLabel energyLabel = new  JLabel("Eigen Energy  =  "+ eigenEnergy + "   in arbitary unit");
 JPanel labelPanel = new JPanel();
JSlider slider = new JSlider (minState, maxState,1); // min, max, init value

private BasePanel basePanel;;

DecimalFormat decFormat;

    /**
     * Constructor.
     * @param headerText the header label text
     */
QuantumStatePanel(BasePanel basePanel)
    {
		super();
		this.basePanel = basePanel;
		energyLabel.setForeground(Color.white);
		energyLabel.setFont( new Font("Dialog",Font.BOLD, 16));
		blankPanel.add(energyLabel);
		controlPanel.setLayout(new BorderLayout());
		controlPanel.setBackground(BACKGROUND_COLOR);
		labelPanel.setLayout(new GridLayout(4,1));
		labelPanel.setBackground(BACKGROUND_COLOR);
		calButton.setText("    OK    ");
		calButton.addActionListener(    new ActionListener() {
        public void actionPerformed(ActionEvent e) {
					QuantumStatePanel.this.basePanel.findEnergyAtQuantumState( quantumState);
					eigenEnergy =QuantumStatePanel.this.basePanel.getEigenEnergy();
					setEigenEnergyLabel ("Eigen Energy  =  "+ decFormat.format(eigenEnergy )+ "   in arbitary unit", Color.white);
					QuantumStatePanel.this.basePanel.doDrawing();
        }
    });
		label1.setForeground(FOREGROUND_COLOR);
		labelPanel.add(blankLabel);
		labelPanel.add(label1);
		label2.setFont(new Font("Dialog", Font.PLAIN, 14));
		label2.setForeground(FOREGROUND_COLOR);
		label2.setText(str1+(minState) + "  to  " + maxState);
		labelPanel.add(label2);
		controlPanel.add(labelPanel, BorderLayout.NORTH);

		
		slider.setForeground(FOREGROUND_COLOR);
		slider.setFont(new Font("Dialog",Font.PLAIN,12));
		slider.setBackground(BACKGROUND_COLOR);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);

//	slider.setPreferredSize(new Dimension(50, 20));
    slider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent event) {
        quantumState = slider.getValue();
      }
    });
		controlPanel.add(slider,BorderLayout.CENTER);
		decFormat =new DecimalFormat("0.000000");
    }
  public void setEigenEnergyLabel(String txt , Color color) {

		energyLabel.setForeground(color);
		energyLabel.setText(txt);

  }
	
  }
/**
* Title: FooterrPanel.java
* Description the lower Panel of base potential.
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * The header panel that displays title or any messages.
 */
class FooterPanel extends JPanel
{
private JTabbedPane tabPane = new JTabbedPane();
private EnergyPanel  enPanel ; 
private QuantumStatePanel statePanel;
private BasePanel basePanel;
private int tabIndex =0;
    /**
     * Constructor.
     */
 FooterPanel(BasePanel basePanel)
    {
		this.basePanel =basePanel;
		setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
		 Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder
                              (raisedBevel, loweredBevel);
        setBorder(compound);
		tabPane.setBackground(BACKGROUND_COLOR);
		enPanel = new EnergyPanel(basePanel);
		statePanel = new QuantumStatePanel(basePanel);
		tabPane.addTab("<html><font color=blue> Energy by Guessing...</font></html>", enPanel);
		tabPane.addTab("<html><font color=blue>Eigen Energy Calculation..</font></html>", statePanel);
		add(tabPane, BorderLayout.CENTER);
		tabPane.addChangeListener(new ChangeListener() {
				  public void stateChanged(ChangeEvent evt) {
					JTabbedPane sourcePane = (JTabbedPane)evt.getSource();
					 // Get current tab
					 tabIndex = sourcePane.getSelectedIndex();
			//		 System.out.println( "Selected tab is  " + tabIndex);
    }
});

    }
	/*
	double getInputEnergy() {
		System.out.println(" In footer , Input energy = " + enPanel.getInputEnergy());
		return enPanel.getInputEnergy();
	}
	*/
	void setMinAndMaxEnergyLabel(double min, double max) {
		enPanel.setEnergyLimit(min, max);
	}
	public void setEigenEnergyLabel(String txt, Color color) {
		statePanel.setEigenEnergyLabel(txt, color);
	}
	public  int getTabIndex() {
		return tabIndex;
	}

  }
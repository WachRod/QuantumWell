/* File:SEPanel.java
* Project :Potential Well Simulation
* Author  : Wachara R.
* First Released: 19 Jan 2011
* Last Updated :  June, 07, 2011
*/

package se1d;


import static se1d.SEConstants.*;
import javax.swing.*;

public class SEPanel extends BasePanel{
int potentialChoice = 0;
 int numberOfPoints = NUMBER_OF_POINTS;
 int n = 1;
 double xmin = -5, xmax = +5;
 int widthPanel;
 int heightPanel;
 double  yFactor;
Schrodinger1D se; 
double energy;


public SEPanel()  {
	super("Potential Well  Simulation in Quantum Mechanics");
	se = new Schrodinger1D( new SquareWell(2.0, -10),xmin, xmax, numberOfPoints);
	double[] psi = se.solveEigenEnergy(n);
    double[] x = se.getXCoordinates();
	//widthPanel = 400; // getSize().width;
//	heightPanel = 300; //getSize().height;
	double maxAmplitude = se.getMaxAmplitude();
	double energy = se.getEnergy();
	yFactor = heightPanel/maxAmplitude;
	}

}

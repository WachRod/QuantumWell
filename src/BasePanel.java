/* File:BasePanel.java
* Project :Potential Well Simulation
* Author  : Wachara R.
* First Released:  Aug 12,  2010
* Last Updated :  July 03, 2011
*/

package se1d;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static se1d.SEConstants.*;
//import common.Function;


/**
 * The base panel for all graph demo panels.
 */
public  class BasePanel extends JPanel
                                
{
    /** header panel */    private HeaderPanel headerPanel;
  /** plot panel  */			 private PlotPanel plotPanel; 
    /** Footer  panel */    private FooterPanel  footerPanel ; 
	/** Left handside panel */	 private LeftPanel leftPanel ;

	/** Potential Function */  protected Function[ ]  functions= new Function[ ] { new SquareWell(2.0, -10.0), new Quadratic(), 
		new  Triangular(), new  TanSquare(), new  InverseCosh() };
	private Function currentFunction;
	int functionChoice = 0;
	double maxXValue = MAX_X_VALUE;
	double minXValue = MIN_X_VALUE;
	int numberOfPoints =NUMBER_OF_POINTS;

	Schrodinger1D se = new  Schrodinger1D(functions[0], minXValue, maxXValue, numberOfPoints);
	double minEn=-10;
	double maxEn = 0;
	double [ ] waveFunction;
 // psiFlag  --  true,   if  wave function has been evaluated.  
 //					--  fasle  if  user change potentail  function and wave Function shoud be recalculated
	boolean psiFlag = false ; 
	double[ ] x;
	int tabIndex=0; // Selected tab in InputPanel
	double guessingEnergy=0; // keep user's input  energy for drawing wave function in plotPanel;

/**
     * Constructor.
     * @param graphAttrs the plot properties
     * @param xorMode if true, set XOR mode
     * @param drawXequalsY if true, draw the X=Y line
  
 */ 
	  protected BasePanel( String headerText) 
	 {
      
		headerPanel = new HeaderPanel(headerText );
		leftPanel = new LeftPanel(this);
		footerPanel = new FooterPanel(this);
		plotPanel = new PlotPanel(this);
        init();
    }


    /**
     * Initialize the graph panel.
     */
    private void init()    {
		currentFunction = functions[functionChoice];
        setLayout(new BorderLayout());
        if (headerPanel != null) add(headerPanel, BorderLayout.NORTH);
		add(plotPanel,    BorderLayout.CENTER);
		 if (footerPanel != null) add(footerPanel, BorderLayout.SOUTH);
		if(leftPanel != null) add(leftPanel, BorderLayout.WEST);
    }
  
    protected void setHeaderLabel(String text, Color color)
    {
        headerPanel.setLabel(text, color);
    }

	void setEigenEnergyLabel(String text, Color color) {
		footerPanel.setEigenEnergyLabel( text, color);
	}

	protected void setFunction( int choice) {
		functionChoice= leftPanel.getChoice();
		currentFunction = functions[functionChoice];
		se = new Schrodinger1D(currentFunction, minXValue,maxXValue,numberOfPoints);
		minEn = se.getMinPotential();
		maxEn = se.getMaxPotential();
	//	System.out.println(" function choice in Base panel = "+ functionChoice);
	}

	public void setPsiFlag (boolean flag) {
		psiFlag = flag;
	}
	public void setMinAndMaxEnergy(double minEn, double maxEn) {
		footerPanel.setMinAndMaxEnergyLabel(minEn, maxEn);
}
protected void  findWaveFunction(double inputEnergy) {
			int crossing =se.solveWaveFunction(inputEnergy);	
			guessingEnergy = inputEnergy;
			waveFunction =  se.getWaveFunction();
			psiFlag = true;
			x = se.getXCoordinates();
}
protected void findEnergyAtQuantumState(int n) {
			waveFunction = se.solveEigenEnergy( n);
			psiFlag = true;
			x = se.getXCoordinates();
	//		System.out.println ("I am in BasePanel.findEnergyAtQuantumState(n)");
	//		for  (int i =0 ;  i < 50; i++)
	//									System.out.println("  x   =  " + x[i]+"        psi =  "+ waveFunction[i]   );

	//		System.out.println("energy="+se.getEnergy());
}
void doDrawing ()  {
		plotPanel.doDrawing();
}

int getTabIndex () {
		return footerPanel.getTabIndex();
}
double getMinEnergy() {
		return minEn;
	}
 double getMaxEnergy() {
		return maxEn;
	}
public Function getCurrentFunction()
	{
		return currentFunction;
	}

double[ ] getWaveFunction() {
	return waveFunction;
}

 double[] getXCoordinates() {
	return x;
}
double getEigenEnergy() {
	return se.getEnergy();
}
public double getGuessingEnergy() {
	return guessingEnergy;
}

protected double getDeltaX() {
		return se.getDeltaX();
}

double getMinX() { 
		return  minXValue;
}

double getMaxX() { 
		return maxXValue;
}

double getMaxAmplitude() { 
		return se.getMaxAmplitude();
}
public boolean getPsiFlag() {
		return psiFlag;
}



	   //----------------//
    // Event handlers //
    //----------------//

    /**
     * Mouse clicked event handler.
     * (Callback from the plot panel.  Do nothing here.)
     * @param ev the mouse event
     */
    public void mouseClickedOnPlot (MouseEvent ev) {}

    /**
     * Mouse pressed event handler.
     * (Callback from the plot panel.  Do nothing here.)
     * @param ev the mouse event
     */
    public void mousePressedOnPlot (MouseEvent ev) {}

    /**
     * Mouse released event handler.
     * (Callback from the plot panel.  Do nothing here.)
     * @param ev the mouse event
     */
    public void mouseReleasedOnPlot(MouseEvent ev) {}

    /**
     * Mouse dragged event handler.
     * (Callback from the plot panel.  Do nothing here.)
     * @param ev the mouse event
     */
    public void mouseDraggedOnPlot(MouseEvent ev) {}

    /**
     * Choose a function.
     * (Callback from the function frame.  Do nothing here.)
     */
    public void chooseFunction(int index) {}

    /**
     * Notification that the plot bounds changed.
     * (Callback from the plot bounds panel.  Do nothing here.)
     */
    public void conditionChanged() {}
 /**
     * Process a user input error.
     * @param message the error message
     */
    protected void displayUserError(String message)
    {
      //  headerPanel.displayError(message);
	  JOptionPane.showMessageDialog(null,message ,"Error",JOptionPane.WARNING_MESSAGE);

     //   userErrorOccurred();
    }

    /**
     * Notification that a user input error occurred.  (Do nothing here.)
     */
    protected void userErrorOccurred() {}
}

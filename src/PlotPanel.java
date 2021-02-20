/* File:PlotPanel.java
* Project :Potential Well Simulation
* Author  : Wachara R.
* First Released: 02 June 2011
* Last Updated :  , 2011
*/

package se1d;

import java.awt.*;
import javax.swing.*;
import static java.lang.Math.*;
import static se1d.SEConstants.*;
//import common.Function;


/**
 * The panel that draws a set of axes, and plots points and lines.
 */
class PlotPanel extends JPanel
{
    private static final int TICK_SIZE = 5;

           /** font metrics */             private FontMetrics fontMetrics;


    /** label font */   private Font charFont = new Font("Dialog", Font.BOLD, 10); // 10 is the best look

	/*  width of Plot panel */  int panelWidth;
												int panelHeight;
	/** x-axis row */           private int   xAxisRow;
    /** y-axis column */        private int   yAxisCol;
    /** minimum x value */      private double xMin;
    /** maximum x value */      private double xMax;
    /** minimum y value */      private double yMin;
    /** maximum y value */      private double yMax;
    /** x delta per pixel */    private double panelDeltaX;
    /** y delta per pixel */    private double panelDeltaY;


    /** parent graph panel */   private BasePanel basePanel;
								private Function currentFunction;
 /** Flag for  drawing wave function */  boolean  psiFlag = false;
 /** selected tab in Footer Panel */  int tabIndex = 0;

double[] psi;  // wave function of the particle.
double[] xForPsi;  // positions on x axis
double guessingEnergy;  // Energy which user input in energy panel for finding wave function
double eigenEnergy;


    PlotPanel(BasePanel basePanel)
    {
        this.basePanel = basePanel;
        setBackground(new Color (255, 255, 199));
		psi = new double[NUMBER_OF_POINTS];
		xForPsi= new double[NUMBER_OF_POINTS];

    }

	public void setGraphProperties()  {

			xMin		=      PlotPanel.this.basePanel.getMinX();
			xMax    =	PlotPanel.this.basePanel.getMaxX();
			psiFlag = PlotPanel.this.basePanel.getPsiFlag();
			yMin      = PlotPanel.this.basePanel.getMinEnergy();
			 yMax    = PlotPanel.this.basePanel.getMaxEnergy();
			 if(yMax > 8)  { yMax = 14; yMin =-2;	 }	 else {	 yMax = 2;	yMin=-12; }
		//	 System.out.println("y min = " + yMin + "       y max = " + yMax);
			 panelWidth = getWidth();
			panelHeight = getHeight();
			panelDeltaX =  (xMax-xMin)/panelWidth;
			panelDeltaY      =    (yMax-yMin)/panelHeight;
	//		 System.out.println("x Delta = " + panelDeltaX + "       y Delta = " + panelDeltaY);

			xAxisRow =  (int)Math.round( yMax/panelDeltaY);
			yAxisCol	=      (int) Math.round(-xMin/panelDeltaX);
	//		 System.out.println("xAxisrow = " + xAxisRow + "       y axis column = " + yAxisCol);
	//		System.out.println("===============\n\n");
			tabIndex = PlotPanel.this.basePanel.getTabIndex();
			if (psiFlag == true) {
							 psi = PlotPanel.this.basePanel.getWaveFunction();
							 xForPsi= PlotPanel.this.basePanel.getXCoordinates();
							 		//			for  (int i =0 ;  i < 100; i++)
										//				System.out.println("  x   =  " + xForPsi[i]+"        psi =  "+ psi[i]   );

			}

	}

   public void paintComponent(Graphics bg)
   {
	 //  int pointCount;
	   super.paintComponent( bg);
	   setGraphProperties();
	   bg.setPaintMode();
		bg.setFont(charFont);
		fontMetrics= bg.getFontMetrics();
		 bg.setColor(Color.green);
		drawAxes(bg);
		 bg.setColor(Color.blue);
		drawPotential(bg);
		if (tabIndex == 0 )
				guessingEnergy =PlotPanel.this.basePanel.getGuessingEnergy();
		else
				eigenEnergy = PlotPanel.this.basePanel.getEigenEnergy( );

		 bg.setColor(Color.red);
		drawWaveFunction(bg);

   }
   void drawAxes(Graphics bg)
	{ 
		bg.drawLine(0, xAxisRow, panelWidth, xAxisRow);  //draw x axis
		// X axis ticks
		for ( int i =(int) round(xMin); i < round(xMax); i++) {
			int column =(int)round((i-xMin)/panelDeltaX);
			bg.drawLine(column, xAxisRow -TICK_SIZE, column, xAxisRow+TICK_SIZE);
			if(i !=0) {
				String number = Integer.toString(i);
				int w = fontMetrics.stringWidth(number);
				int x = column -w/2;
				int y = xAxisRow +TICK_SIZE + fontMetrics.getAscent();
				bg.drawString(number, x , y);
			}
		}
	// draw y axis
		bg.drawLine(yAxisCol,0, yAxisCol, panelHeight); 
		// Y Axis ticks
		for ( int i =(int) round(yMin); i < round(yMax); i++) {
			int row =(int)round((yMax-i)/panelDeltaY);
			bg.drawLine(yAxisCol -TICK_SIZE, row, yAxisCol+TICK_SIZE,row);
			if(i !=0) {
				String number = Integer.toString(i);
				int w = fontMetrics.stringWidth(number);
				int x = yAxisCol - TICK_SIZE- w;
				int y = row + fontMetrics.getAscent()/2;
				bg.drawString(number, x , y);
			}
		} // for 
	}
void drawPotential(Graphics bg)
	{
int[] xScreen = new int[panelWidth];
int[] yScreen = new int[panelWidth];

	    currentFunction =PlotPanel.this.basePanel.getCurrentFunction();
		for(int column = 0; column  < panelWidth; column++) {
			 double tempX = xMin +column*panelDeltaX;
			 double tempY = currentFunction.Of(tempX);
		 if(( tempY >= yMin ) && ( tempY <= yMax) ) {
				 yScreen[column]= (int) round((yMax-tempY)/panelDeltaY);
				 xScreen[column] = column;
		///		 System.out.println( xScreen[column] + "         "+yScreen[column] );
				 
		 }
		} // for column
		 bg.drawPolyline( xScreen, yScreen, panelWidth-1);
	}
void drawWaveFunction(Graphics bg)
	{
int [ ] xScreen4Psi = new int[NUMBER_OF_POINTS];
int [ ] yScreen4Psi = new int[NUMBER_OF_POINTS];
double scaleFactor = 4;
int yOffset;

	if( tabIndex== 0 )
			 yOffset = (int) round((yMax -  guessingEnergy)/panelDeltaY) - xAxisRow;
	else
			 yOffset = (int) round((yMax -  eigenEnergy)/panelDeltaY) - xAxisRow;
		
		if(psiFlag) {
			for(int i =0; i < NUMBER_OF_POINTS ; i++) {
				xScreen4Psi[i] = (int) round( (xForPsi[i] + xMax)/panelDeltaX);
				yScreen4Psi[i] = (int) round( ((yMax - scaleFactor*psi[i])/panelDeltaY )+ yOffset);
			}
		 bg.drawPolyline( xScreen4Psi, yScreen4Psi, NUMBER_OF_POINTS-1);
		 bg.drawLine(0, xAxisRow+yOffset, panelWidth-1, xAxisRow+yOffset);
		}// if (psiFlag)

	}

void doDrawing( ) {
	repaint();
}
}

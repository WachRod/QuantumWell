/**
* Title: Schrodinger1D
* Description compute energy, eigen state (Quantum number) and Wave function
*
* @author: Wach R
*@ version 0.1
* Date : April 23, 2011
* Last updated: May 2, 2011
*===========================
* Schrodinger Eq :   d^2psi/dx^2 = 2*m/(hbar)^2(E-V)*psi
*  define  k^2 = 2*m/(hbar)^2(E-V)
* in atomic unit:  hbar = 1, m = 1
*/
 package se1d;


public class  Schrodinger1D 
{

 public static final int NO_ERROR = 0;
 public static final int MAXIMUM_NODE=20;
 int errorCode = 0;

double energy;   
double potentialMin;
double potentialMax;
double[] psi;  // wave function of the particle.
double[] x;  // positions on x axis
double xmin, xmax;  // min and max values of x

double tolerance = 1.0e-3;
//  double tolerance =DEFAULT_TOLERANCE;
 double maxAmplitude;  // maximum amplitude of psi
  int firstPoint, lastPoint;
  double dx;
  Function potential;

  double psiState ;   // keep current psi
  double  slopeState;  // keep d psi / dx 
  double  xState;    // keep x value

public Schrodinger1D (Function potential, double xmin, double xmax, int numberOfPoints) {
this.potential = potential;
psi = new double[numberOfPoints];
x= new double[numberOfPoints];
this.xmin = xmin;
this. xmax = xmax;
dx = (xmax -xmin)/(numberOfPoints -1);
firstPoint = 0;
lastPoint = numberOfPoints;
findMinAndMaxPotential();
}

private void  findMinAndMaxPotential() {
	double xValue = xmin;
	double newPotential;
    potentialMin = potential.Of(xValue);
	potentialMax = potentialMin;
    for(int i = 0; i <  psi.length; i++) {
      x[i] = xValue;
      newPotential = potential.Of(xValue);
      if(potentialMin > newPotential) potentialMin = newPotential;
	  if(potentialMax < newPotential) potentialMax = newPotential;
      xValue += dx;
    }

}
/*
   * finds  the area where the wave function is non-zero.
   * and  bounded by the firstPoint and lastPoint.
  */
  protected void findFirstAndLastPoint(double energy) {
    double  current_x = xmin;
	double startX=0, stopX=0;

	if(( energy <= potentialMin) || (energy >= potentialMax))  {
		System.out.println( "It's  not a bound state, check your Potential and Energy again");
		return;
	}

    // find the first point where E>V 

    for(int i = 0; i< psi.length;  i++) {
		 if((energy - potential.Of(current_x))>0)  {
        firstPoint = i;
		startX = current_x;
        break;
      }
     current_x += dx;
    }
    current_x = xmax;

    // find the last point where E>V
    for(int i = psi.length; i>firstPoint; i--) {
		 if((energy - potential.Of(current_x)) > 0) {
		lastPoint = i;
		stopX= current_x;
        break;
      }
      current_x -= dx;
    }

// Actually, wave function value is not zero immediately 
// it still decay at the both sides
// Left side :
    double decay = 1;
    current_x = xmin+firstPoint*dx;
    while(decay > tolerance && firstPoint > 0) {
      firstPoint--;
     current_x -= dx;
	 // this region E < V  the result shoud be minus sign
   double k = Math.sqrt(2*Math.abs(energy-potential.Of(current_x)));
      decay *= Math.exp(-k*dx);
//	  System.out.print(" left side: k =  " + k + "  decay  =  " +decay);

    }
	// Right side:
   decay = 1;
   current_x = xmin+lastPoint*dx;
    // assume exponential decay on right hand side
    while(decay > tolerance && lastPoint < psi.length) {
      lastPoint++;
      current_x += dx;
   double k = Math.sqrt(2*Math.abs(energy-potential.Of(current_x)));
	
     decay *= Math.exp(-k*dx);

    }
  }

  /**
   * Solves the Schroedinger Equation with the given energy.
   * @param energy double the energy
   * @return int node  ( number of zero crossing of wave function
   */
public int solveWaveFunction(double energy) {
    int node = 0;  // count the number of zero crossings.
    boolean slopeChanged = false;
    maxAmplitude = 0;
    double integral = 0;
	double lastPsiState, lastSlopeState,lastXState;
	double k2;   //  k^2 = 2*(E-V)
	double  k2LastState, k2State;
	double slope;

	this.energy = energy;
    findFirstAndLastPoint(energy);
    if(lastPoint - firstPoint <3) { 
		// if number of point in range < 3  cannot use with Numerov method
      return 0;
    }
   // set wave function to zero outside the bound region
    for(int i = 0;i<firstPoint;i++) {
      psi[i] = 0; 
      x[i] = xmin+i*dx;
    }
	// state of the firstPoint
	lastPsiState=0;
	lastSlopeState = 0;
	lastXState =xmin+firstPoint*dx;
	psi[firstPoint]=lastPsiState;
	x[firstPoint] = lastXState;
	k2LastState = 2.0*(energy - potential.Of(lastXState));
	// state of the firstPoint +1
	psiState = dx;
   slopeState = 1.0;  // initial derivative of psi by dx
   xState =  xmin + (firstPoint+1)*dx;   // initial value of x
	psi[firstPoint+1]=psiState;
	x[firstPoint+1] = xState;
	k2State = 2.0*(energy - potential.Of(xState));
	slopeState = (psiState - lastPsiState)/dx;
// find wave function (psi) with Numberov method
// the differential eq must be in a form
//			d^2 psi /dx^2  = -k^2 (psi)
//			 define  k^2 = 2*m/(hbar)^2(E-V)
// ------------------------------------
double  c =  dx*dx/12.0;  // coeficient for Numerov Algorithm
    for(int i = firstPoint+2; i<lastPoint; i++) {
		x[i]= xmin+i*dx;
		k2 = 2.0*(energy - potential.Of(x[i]));
	//  System.out.print ( " i =  "  + i + "     k in loop =  " + k2);

		psi[i] = ((2.0 -10.0*c*k2State)*psiState - (1+ c*k2LastState)*lastPsiState)/(1+c*k2);
		slope = (psi[i] - psiState)/dx;

		// keep previous values for next step
	lastPsiState=psiState;
	lastSlopeState = slopeState;
	lastXState =xState;
k2LastState = k2State;
 
 // Now store current values
	psiState = psi[i];
	k2State = k2;
	 xState = x[i] ;
	 slopeState = slope;

      if(maxAmplitude<Math.abs(psiState)) {
        maxAmplitude = Math.abs(psiState);
      }
// Normalize  wave function  with trapezoidal rule
//  Integrate f(x) dx =  deltaX ( summation fi - f0/2 -fn/2)

	 integral += psiState*psiState;

	if(slopeState*lastSlopeState < 0) 
        slopeChanged = true;
    
	  if(psiState*lastPsiState < 0)  { //  if psiState changes sign that means we get  a node
        node++;
        slopeChanged = false;
      } 
      if(Math.abs(psiState)>1.0e12) {  // prevent diverging so much
        for(int m = i+1;m<lastPoint; m++) {  // fill remaining values
          psi[m] = psiState;
		x[m] = xmin + m*dx;
        }
        break;
      }
    } // end of ---->  for(int i = firstPoint+2

    integral *= dx;
	integral = integral -( psi[firstPoint]/2.0 + psi[lastPoint-1]/2.0)*dx;
	// find the magnitude of wave function
	double sqrtIntegral = Math.sqrt(integral);

	// fill the rest point at the right side
    for(int i = lastPoint; i < psi.length; i++) {
      psi[i] = psi[lastPoint-1];
      x[i] = xmin+i*dx;
    }
/*
    // check to see if last value is close enough to a crossing
    if(slopeChanged&&(Math.abs(phi[phi.length-1])<=tolerance*maxAmp)) {
      crossing++;
    }
*/

    normalize(sqrtIntegral);

	// check normalize it shoulld equals  1
 double sumOfPsi2 =0;
	for(int i = firstPoint; i <lastPoint; i++) {
	  sumOfPsi2  += psi[i]*psi[i];
	}
//	System.out.println(" sum of  Psi ^2 = " + sumOfPsi2*dx );
    return node;
  }
//  Normalize wave function
 private void normalize(double magnitude) {
    if(magnitude==0) {
      return;
    }
    for(int i = 0; i< psi.length; i++) {
      psi[i] /= magnitude;
      psiState /= magnitude;
     slopeState /= magnitude;
    }
    maxAmplitude /= magnitude;
  }

  /**
   * Solves the eigen energy  with the given energy level (Quanutum number).

   */
public double[ ] solveEigenEnergy( int quantumNumber)
{
double eMin;
double eMax;
int nodeCount=0;
int counter = 0;

	if(quantumNumber > MAXIMUM_NODE)  {
	System.out.println("Quantum  Number must not  greater than  " + MAXIMUM_NODE);
	return new double[psi.length];
}
	eMin = potentialMin;
	eMax = eMin +1;
while (counter < 20 && nodeCount <= quantumNumber) {
	nodeCount = solveWaveFunction(eMax);

	// Increase energy until the number of node is greater than quantum number 
	eMax  = eMax +( eMax-eMin);
	counter++;
}
counter = 0;

do{
	double tempEnergy = (eMax + eMin)/2.0;
	int crossing = solveWaveFunction(tempEnergy);
//	  System.out.println( counter + "  energy =  " + tempEnergy + " and crossing zero =   " + crossing);

	if((crossing == quantumNumber) && (Math.abs(psi[psi.length-1]) <= tolerance*maxAmplitude)) {
		errorCode = 0;
	//	System.out.println("in IF #1:  psi[psi.lenth-1] = " + psi[psi.length-1] + "     tolerance*maxAmp = " + tolerance*maxAmplitude );
		return psi;
	}
	if((crossing > quantumNumber) || ((crossing == quantumNumber) &&(checkEvenNumber(crossing)*psi[psi.length-1] > 0)) ) {
		eMax = tempEnergy;
	//	System.out.println("in IF #2 :  Emax =" + eMax+ "  pasi[psi.lenth-1] = " + psi[psi.length-1] + " checkEvenNumber*psi[psi.length-1] = " +checkEvenNumber(crossing)*psi[psi.length-1]  );

	}
	else {
		eMin = tempEnergy;
	//	System.out.println("in Else #2 : Emin = " + eMin+ "  pasi[psi.lenth-1] = " + psi[psi.length-1] + " checkEvenNumber*psi[psi.length-1] = " +checkEvenNumber(crossing)*psi[psi.length-1]  );

	}
	counter ++;
//	System.out.println();
} while (counter < 32 && (eMax-eMin )> CommonConstants.ZERO_APPROACH);
errorCode = 1;
return new double[psi.length];  
}
  /**
   * Gets the x coordinates 
   */
  public double[] getXCoordinates() {
    return x;
  }
  public double[] getWaveFunction() {
    return psi;
  }
public double getEnergy() {
	return energy;
}
public double getMaxAmplitude() {
	return maxAmplitude;
}
public double getMinPotential() {
	return potentialMin;
}
public double getMaxPotential() {
	return potentialMax;
}
public double getDeltaX() {
	return dx;
}
 /**
   * Gets the error code from computation.
   * @return int
   */
  public int getError() {
    return errorCode;
  }
  private int checkEvenNumber(int number) {
	  if(number%2==0) 
		  return 1;  // Even
	  else
		  return -1; //Odd
  }
}

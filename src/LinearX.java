/**
* Title: LinearX.java
* Description triangular potential.  V(x) = (x)
*
* @author: Wach R
*@ version 0.1
* Date : May 16, 2011
*===========================
 */
 package se1d;

 //import common.Function;

public class LinearX  extends Function {
	double startWall;

public LinearX( double start) {
	startWall = start;
}

  public double Of(double x) {
if(x > startWall)
  return x;
else
	return 10e+12;
  }


}

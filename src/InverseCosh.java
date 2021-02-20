/**
* Title: InverseCosh.java
* Description triangular potential.  V(x) = abs(x)
*
* @author: Wach R
*@ version 0.1
* Date : May 16, 2011
*===========================
 */
 package se1d;


public class InverseCosh  extends Function {
  public double Of(double x) {
//  return (3-6.0/(Math.cosh(x)*Math.cosh(x)));
	return(-10.0/(Math.cosh(x)*Math.cosh(x)) );
  }
//  public  double DerivativeOf(double x) {  return 1.0; }


}

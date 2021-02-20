/**
* Title: InverseCosh.java
* Description triangular potential.  V(x) = abs(x)
*
* @author: Wach R
*@ version 0.1
* Date : May 16, 2011
*Last Updated: July 3, 2011 : National Election days.
*===========================
 */
 package se1d;



public class TanSquare  extends Function {
  public double Of(double x) {
	//  if(Math.abs(x) > 1.56)  return 9.995;
	  if (Math.abs(x) > 1.50 )return 9.995;
	  else
  			return((1.0/560.0)*Math.tan(x)*Math.tan(x) );
  }


}

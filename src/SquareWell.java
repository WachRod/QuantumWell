/**
* Title: SquareWell.java
* Description Square well potential.
* 
* @author: Wach R
*@ version 0.1
* Date : April 24, 2011
*===========================
 */
  package se1d;



public class SquareWell  extends Function {
	double halfWidth;  // halfWidth of potential well  ( -a <= x <= a )  halfwidth = a
	double dept;			//the depth of well  must be minus sign
public	SquareWell( double halfWidth, double dept) {
		this.halfWidth = halfWidth;
		this.dept = dept;
	}

  public double Of(double x) {
	  if(  Math.abs(x) >= halfWidth)
		  return 0;
	  else
			return  dept;
  }

}

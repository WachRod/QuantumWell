/**
* Title: Triangular.java
* Description triangular potential.  V(x) = abs(x)
*
* @author: Wach R
*@ version 0.1
* Date : May 16, 2011
*===========================
 */
 package se1d;



public class Triangular  extends Function {
  public double Of(double x) {
  return Math.abs(x)*2.0;
  }
  public  double DerivativeOf(double x) {  return 1.0; }


}

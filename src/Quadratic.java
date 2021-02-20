/**
* Title: Quadratic.java
* Description Simple harmonic oscillator potential.
*
* @author: Wach R
*@ version 0.1
* Date : April 23, 2011
*===========================
 */
 package se1d;

public class Quadratic  extends Function {
  public double Of(double x) {
  return (x*x)/2;
  }
  public  double DerivativeOf(double x) {  return 0.0; }


}

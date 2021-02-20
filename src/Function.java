
/*File : Function.java
 * Project: Math Tools in Java
 * by Wachara R.
 * First Released: 01 Dec 2006
 * Last Updated : 15 Dec 2006
 */
/** The mother class for function  with finding value of function
 * and its deritvatives
 *  @author Wachara R. ( Dec 14, 2006.)
 */
package se1d;
public abstract class Function {
    /** return the value of f(x)
     * @param x  the value of x
     * @return function of x
     */
    public abstract double Of(double x);

    /** return the derivative of x
     *	@param x  the value of x
     *	@return derivative at x
     */
    public double derivativeOf(double x)
    { return 0.0;}
    /** return the second derivative of x
     *	@param x  the value of x
     *	@return derivative at x
     */
    public double secondDerivativeOf(double x)
    { return 0.0;}



}
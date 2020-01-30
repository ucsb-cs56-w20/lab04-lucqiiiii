/**
 * A class to represent a rational number with a numerator and denominator
 *
 * @author P. Conrad for CS56 F16
 *
 */
package edu.ucsb.cs56.ratcalc.model;
import java.lang.Math;

public class Rational {

    private int num;
    private int denom;

    /**
     * greatest common divisor of a and b
     *
     * @param a first number
     * @param b second number
     * @return gcd of a and b
     */
    public static int gcd(int a, int b) {
        if(a < 0)
	    a = -a;
        if(b < 0)
	    b = -b;
	if (a == 0)
            return b;
        else if (b == 0)
            return a;
        else
            return gcd(b % a, a);
    }
    // Default Constructor
    public Rational() {
        this.num = 1;
        this.denom = 1;
    }
    // Constructor
    public Rational(int num, int denom) {
        if (denom == 0) {
            throw new IllegalArgumentException("denominator may not be zero");
        }
        this.num = num;
        this.denom = denom;
        if (num != 0) {
            int gcd = Rational.gcd(num, denom);
            this.num /= gcd;
            this.denom /= gcd;
        }
	if(this.denom < 0){
	    this.num = -this.num;
	    this.denom = -this.denom;
	}
    }
    //ToString Method
    public String toString() {
        if (denom == 1 || num == 0)
            return "" + num;
        return num + "/" + denom;
    }
    // Getters
    public int getNumerator() {
        return this.num;
    }

    public int getDenominator() {
        return this.denom;
    }
    // Times Method
    public Rational times(Rational r) {
        return new Rational(this.num * r.num, this.denom * r.denom);
    }
    // Product Method
    public static Rational product(Rational a, Rational b) {
        return new Rational(a.num * b.num, a.denom * b.denom);
    }

    /**
     * least common multiple of a and b
     *
     * @param a first number
     * @param b second number
     * @return lcm of a and b
     */
    public static int lcm(int a, int b) {
        if(a == 0 || b == 0){
	     throw new IllegalArgumentException("The least common multiple of 0 is undefined.");
	}
	int den = gcd(a, b);
	return (Math.abs(a * b)) / den;    
    }

    public Rational plus(Rational r){
	int top = this.num * r.denom + this.denom * r.num;
	int down = this.denom * r.denom;
	return new Rational(top, down);
	//int den = lcm(this.denom, r.denom);
	//return new Rational(this.num*(den/this.denom) + r.num*(den/r.denom), den);
    }

    public static Rational sum(Rational a, Rational b){
	return a.plus(b);
	//int den = lcm(a.denom, b.denom);
	//return new Rational(a.num*(den/a.denom) + b.num*(den/b.denom), den);
    }

    public Rational minus(Rational r){
	int den = lcm(this.denom, r.denom);
	return new Rational(this.num*(den/this.denom) - r.num*(den/r.denom), den);
    }

    public static Rational difference(Rational a, Rational b){
	int den = lcm(a.denom, b.denom);
	return new Rational(a.num*(den/a.denom) - b.num*(den/b.denom), den);
    }
   
    public Rational reciprocalOf(){
	if(this.num == 0){
	    throw new ArithmeticException("Cannot compute reciprocal of 0.");
	}
	return new Rational(this.denom, this.num);
    }

    public Rational dividedBy(Rational r){
	if(r.num == 0){
	    throw new ArithmeticException("Cannot divide a rational number by 0.");
	}
	return this.times(r.reciprocalOf());
    }

    public static Rational quotient(Rational a, Rational b){
	if(b.num == 0){
	    throw new ArithmeticException("Cannot divide a rational number by 0.");
	}
        return a.times(b.reciprocalOf());
    }

    /**
     * For testing getters.
     *
     * @param args unused
     */

    public static void main(String[] args) {
        Rational r = new Rational(5, 7);
        System.out.println("r.getNumerator()=" + r.getNumerator());
        System.out.println("r.getDenominator()=" + r.getDenominator());
    }

}

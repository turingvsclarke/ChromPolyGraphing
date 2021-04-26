// Polynomial.java
// Defines the Polynomial as an object, then includes a bunch of static methods for operations on those polynomials
import java.util.*;

public class Polynomial extends ArrayList{

    public static void main(String args[]){
        // Create a cubic polynomial
        int[] c1 = {-6,0,1};
        Polynomial cube = new Polynomial(c1);
	      int[] c2 = {3,1};
	      Polynomial cube2 = new Polynomial(c2);
	      System.out.println(cube.getPolyString("x"));
	      System.out.println(cube2.getPolyString("x"));
	      System.out.println(Polynomial.multiplyPolys(cube,cube2).getPolyString("x"));
    } // end main

    public Polynomial(){
        super();
    } // end null-valued constructor

    public Polynomial(int order){
        super();
        this.add(1);
        this.increaseOrder(order);
    } // end order constructor

    public Polynomial(int[] coeff){
	     for(int i=0;i<coeff.length;i++){
		       this.add(coeff[i]);
	     } // end for
    } // end array constructor

    public Polynomial clone(){
	     Polynomial c = new Polynomial();
	     for(int i=0;i<this.size();i++){
		       c.add((int)this.get(i));
	     } // end for
	     return c;
    } // end clone

    public static Polynomial addPolys(Polynomial a, Polynomial b){
        // Add all the elements of the same order, then append the remaining elements to the polynomial
        Polynomial result = new Polynomial();
        int size = Math.max(a.size(),b.size());
        int i = 0;
        for(;i<size;i++){
            try{
                // Add the two coefficients at i;
                result.add((int)a.get(i)+(int)b.get(i));
            } // end try
            catch(Exception e){
                // Once it fails to get i for one of them, just add on the elements of whichever one doesn't work
                try{
                    result.add(a.get(i));
                } // end try
                catch(Exception ex){
                    result.add(b.get(i));
                } // end catch
            } // end catch
        } // end for
        return result;
    } // end addPoly
    
    // Multiply the polynomial by an integer
    public static Polynomial polyByInt(Polynomial p,int x){
        Polynomial result = new Polynomial();
        for(int i=0;i<p.size();i++){
            result.add(x*((int)p.get(i)));
        } // end for
	      return result;
    } // end polyByInt

    public static Polynomial intByPoly(int x,Polynomial p){
        return Polynomial.polyByInt(p,x);
    } // end intByPoly
    
    public static Polynomial subtractPolys(Polynomial a, Polynomial b){
        Polynomial p = Polynomial.polyByInt(b,-1);
        return Polynomial.addPolys(a,p);
    } // end subtractPolys

    // Multiply two polynomials.
    public static Polynomial multiplyPolys(Polynomial a,Polynomial b){
        Polynomial result = new Polynomial();
        for(int i=0;i<a.size();i++){
  	      Polynomial p = b.clone();
  	      p.increaseOrder(i);
  	      System.out.println(p.getPolyString("x"));
              // Increase b by the order of the ith element(which is i), then multiply by the coefficient of that element
          result = Polynomial.addPolys(result,Polynomial.intByPoly(((int)a.get(i)),p));
        } // end for
        return result;
    } // end multiplyByPolynomial


//////// RETURNS THE POLYNOMIAL AS A STRING /////////////////////////////
    public String getPolyString(String variable){
        int n = this.size();
        String polyString = "";
        for(;n>0;n--){
	         int coeff = (int)this.get(n-1);
	         if(coeff!=0){
            	// Insert a plus sign before a positive term if it isnt the highest term
            	if((n<this.size())&&(coeff>0)){
                	polyString+="+";
              } // end if
		          // Only write non unitary coefficients unless its the constant term
		          if(coeff!=1 || n==1){
				if(coeff==-1 && n>1){
					polyString+="-";
				} // end if
				else{
            	   			polyString+=coeff;
				} // end else
		          } // end if
            	// Print the variable and an order if its not the constant term
            	if(n>1){
                polyString+=variable;
            	   if(n>2){
				              polyString+="^" + (n-1);
			          } // end if
		          } // end if
	    } // end if
         } // end for
         return polyString;
    } // end getPolyString
///////////////////////////////////////////////////////////////////////////////

// Increase degree of all the terms of a polynomial
public void increaseOrder(int n){
    if(n>0){
    	// Put n zeroes at the end of the string
    	for(int i=0;i<n;i++){
        	this.add(0);
    	} // end for
    	// Starting with the last element, push each element down n places
    	for(int i=this.size()-n-1;i>-1;i--){
        	// Take the element at i and put it n places farther
        	this.set(i+n,(int)(this.get(i)));
		// Make the ith element now a zero
		this.set(i,0);
    	} // end for
     } // end if
} // end increaseOrder

} // end class definition

package assignment2;

import java.math.BigInteger;

public class Polynomial 
{
	private SLinkedList<Term> polynomial;
	
	public int size()
	{	
		return polynomial.size();
	}
	
	private Polynomial(SLinkedList<Term> p)
	{
		polynomial = p;
	}
	
	public Polynomial()
	{
		polynomial = new SLinkedList<Term>();
	}
	
	// Returns a deep copy of the object.
	public Polynomial deepClone()
	{	
		return new Polynomial(polynomial.deepClone());
	}
	
	/* TODO: Test these cases
	* 	-> If coefficients are the same
	* 	-> If coefficients add to zero
	*/ 
	public void addTerm(Term t)
	{	
		
		int size = polynomial.size();

		// The polynomial is empty -> set as first term
		if (size == 0) {
			polynomial.addFirst(t);
			return;
		}
		
		int i = 0;
		int termExponent = t.getExponent();
		int currentExponent;

		for (Term currentTerm: polynomial)
		{	
			currentExponent = currentTerm.getExponent();

			// The term's exponent is largest in list -> add first
			if (i == 0 && currentExponent < termExponent) {
				polynomial.addFirst(t);
				return;
			}
			// The current term has same exponent -> add coefficients
			else if (currentExponent == termExponent) {
				currentTerm.setCoefficient(currentTerm.getCoefficient().add(t.getCoefficient()));
				return;
			} 
			// The term's exponent is smallest in list
			else if (i + 1 == size && currentExponent > termExponent) {
				polynomial.addLast(t);
				return;
			}
			// The term is added before the current one
			else if (currentExponent < termExponent) {
				polynomial.add(i,t);
				return;
			}
			i++;
		}
	}
	
	public Term getTerm(int index)
	{
		return polynomial.get(index);
	}
	
	//TODO: Add two polynomial without modifying either
	public static Polynomial add(Polynomial p1, Polynomial p2)
	{
		/**** ADD CODE HERE ****/
		for (int i = 0; i < p2.size(); i++) {
			p1.addTerm(p2.getTerm(i));
		}
		
		return p1;
	}
	
	//TODO: multiply this polynomial by a given term.
	private void multiplyTerm(Term t)
	{	
		/**** ADD CODE HERE ****/
		
	}
	
	//TODO: multiply two polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2)
	{
		/**** ADD CODE HERE ****/
		
		return null;
	}
	
	//TODO: evaluate this polynomial.
	// Hint:  The time complexity of eval() must be order O(m), 
	// where m is the largest degree of the polynomial. Notice 
	// that the function SLinkedList.get(index) method is O(m), 
	// so if your eval() method were to call the get(index) 
	// method m times then your eval method would be O(m^2).
	// Instead, use a Java enhanced for loop to iterate through 
	// the terms of an SLinkedList.

	public BigInteger eval(BigInteger x)
	{
		/**** ADD CODE HERE ****/
		return new BigInteger("0");
	}
	
	// Checks if this polynomial is same as the polynomial in the argument.
	// Used for testing whether two polynomials have same content but occupy disjoint space in memory.
	// Do not change this code, doing so may result in incorrect grades.
	public boolean checkEqual(Polynomial p)
	{	
		// Test for null pointer exceptions!!
		// Clearly two polynomials are not same if they have different number of terms
		if (polynomial == null || p.polynomial == null || size() != p.size())
			return false;
		
		int index = 0;
		// Simultaneously traverse both this polynomial and argument. 
		for (Term term0 : polynomial)
		{
			// This is inefficient, ideally you'd use iterator for sequential access.
			Term term1 = p.getTerm(index);
			
			if (term0.getExponent() != term1.getExponent() || // Check if the exponents are not same
				term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || // Check if the coefficients are not same
				term1 == term0) // Check if the both term occupy same memory location.
					return false;
			
			index++;
		}
		return true;
	}
	
	// This method blindly adds a term to the end of LinkedList polynomial. 
	// Avoid using this method in your implementation as it is only used for testing.
	// Do not change this code, doing so may result in incorrect grades.
	public void addTermLast(Term t)
	{	
		polynomial.addLast(t);
	}
	
	// This is used for testing multiplyTerm.
	// Do not change this code, doing so may result in incorrect grades.
	public void multiplyTermTest(Term t)
	{
		multiplyTerm(t);
	}
	
	@Override
	public String toString()
	{	
		if (polynomial.size() == 0) return "0";
		return polynomial.toString();
	}
}

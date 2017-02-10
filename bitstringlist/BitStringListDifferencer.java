/*
 * BitStringListDifferencer.java	1.0 07/01/17
 *
 * Copyright 2006 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.bitstring.IBitString;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.Disjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.BitStringListDisjointerException;

/**
 * This IProblem imposes the constraint that IBitString T is the difference
 * between IBitStrings A and B, i.e. that T = A $ B.
 * 
 * @author ksoileau
 */
public class BitStringListDifferencer extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringListDifferencer(IBitStringList A, IBitStringList B, IBitStringList aMinusB) throws Exception
  {
  	if(A==null)
  		throw new BitStringListDisjointerException("Passed a null IBitStringList to constructor.");
  	if(B==null)
  		throw new BitStringListDisjointerException("Passed a null IBitStringList to constructor.");
    if(aMinusB==null)
    	throw new BitStringListDisjointerException("Passed a null IBitStringList to constructor.");
    
    //T=A$B <=> (B ^ T = null) and (B v T = A v B)
    //in T: notInB and inA
    //in A: (inB or inT) and (notInB or notInT)
    //in B: notInT and (inA or inB)
    IProblem problem=null;
    for(int i=0;i<aMinusB.size();i++)
    {
    	IBitString curr=aMinusB.getBitString(i);
    	problem=new Conjunction(problem,
    			new BitStringListNonmembershipper(curr,B),
    			new BitStringListMembershipper(curr,A));
    }
    for(int i=0;i<A.size();i++)
    {
    	IBitString curr=A.getBitString(i);
    	problem=new Conjunction(problem,
    			new Disjunction(
    					new BitStringListNonmembershipper(curr,B),
    					new BitStringListNonmembershipper(curr,aMinusB)),
    			new Disjunction(
    					new BitStringListMembershipper(curr,B),
    					new BitStringListMembershipper(curr,aMinusB)));
    }
    for(int i=0;i<B.size();i++)
    {
    	IBitString curr=B.getBitString(i);
    	problem=new Conjunction(problem,
    			new BitStringListNonmembershipper(curr,aMinusB),
    			new Disjunction(
    					new BitStringListMembershipper(curr,A),
    					new BitStringListMembershipper(curr,B)));
    }
    this.setClauses(problem.getClauses());
  }
}
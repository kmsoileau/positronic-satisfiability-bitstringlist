/*
 * BitStringListSubsetter.java	1.0 05/04/11
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.BitStringListException;

public class BitStringListSubsetter extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 3880192538685152084L;

	//Ensures that every IBitString in A is also in B
  public BitStringListSubsetter(IBitStringList A, IBitStringList B) throws Exception
  {
    if(A==null)
      throw new BitStringListException("Passed a null IBitStringList to constructor.");
    if(B==null)
      throw new BitStringListException("Passed a null IBitStringList to constructor.");
    
    IProblem res[]=new IProblem[A.size()];
    for(int i=0;i<A.size();i++)
      res[i]=new BitStringListMembershipper(A.getBitString(i),B);
    IProblem total=new Conjunction(res);
    total=new Conjunction(total,
    		new BitStringListNonrepeater(A),
    		new BitStringListNonrepeater(B));
    this.setClauses(total.getClauses());
  }
}
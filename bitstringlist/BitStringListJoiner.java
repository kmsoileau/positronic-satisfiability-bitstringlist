/*
 * BitStringListJoiner.java	1.0 06/02/10
 *
 * Copyright 2006 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.bitstring.BitStringEqualizer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.BitStringListException;

public class BitStringListJoiner extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;
  
  public BitStringListJoiner(IBitStringList join,IBitStringList A,IBitStringList B) throws Exception
  {
    if(join==null || A==null || B==null)
      throw new BitStringListException("Passed null IBitStringList to BitStringListJoiner constructor.");
    if(join.size()!=A.size()+B.size())
    	throw new BitStringListException("IBitStringList join is incorrect size to hold the IBitStringList formed by the concatenation of IBitStringLists A and B.");
    	
    IProblem problem=null;
    for(int i=0;i<A.size();i++)
    	problem=new Conjunction(problem,new BitStringEqualizer(A.getBitString(i),join.getBitString(i)));
    for(int i=A.size();i<A.size()+B.size();i++)
    	problem=new Conjunction(problem,new BitStringEqualizer(B.getBitString(i-A.size()),join.getBitString(i)));
    
    this.setClauses(problem.getClauses());
  }
}
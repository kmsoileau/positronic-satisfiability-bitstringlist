package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.bitstring.BitStringEqualizer;
import positronic.satisfiability.bitstring.IBitString;
import positronic.satisfiability.elements.Disjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.BitStringListException;

/*
 * BitStringListMembershipper.java	1.0 05/04/11
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

public class BitStringListMembershipper extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1374955023026675466L;

  public BitStringListMembershipper(IBitString string, IBitStringList bsl) throws Exception
  {
    if(bsl==null)
      throw new BitStringListException("Passed a null IBitStringList to constructor.");
    if(string==null)
      throw new BitStringListException("Passed a null IBitString to constructor.");
    IProblem[] res=new IProblem[bsl.size()];
    for(int i=0;i<bsl.size();i++)
    {
      IBitString curr=bsl.getBitString(i);
      res[i]=new BitStringEqualizer(curr,string);
    }
    IProblem problem=new Disjunction(res);
    this.setClauses(problem.getClauses());
  }
}
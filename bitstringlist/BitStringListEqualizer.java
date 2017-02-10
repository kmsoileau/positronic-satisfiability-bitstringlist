/*
 * BitStringListEqualizer.java	1.0 05/04/11
 *
 * Copyright 2004-2005 Positronic Software.
 *
 * Note that for two IBitStringLists to be equal, in the sense enforced by this IProblem, means for their contents to be
 * equal. This does not enforce that the two IBitStringLists present their contents in the same order.
 *
 */

package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.BitStringListException;

public class BitStringListEqualizer extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = 1L;

	public BitStringListEqualizer(IBitStringList A, IBitStringList B) throws Exception
  {
    if(A==null)
      throw new BitStringListException("Passed a null IBitStringList to constructor.");
    if(B==null)
      throw new BitStringListException("Passed a null IBitStringList to constructor.");
    IProblem problem=new Conjunction(new BitStringListSubsetter(A,B),new BitStringListSubsetter(B,A));
    this.setClauses(problem.getClauses());
  }
}
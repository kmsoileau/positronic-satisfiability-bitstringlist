/*
 * BitStringListReverser.java	1.0 05/11/29
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */
 /**
 * Constrains two IBitStringLists to have the same entries in opposite 
 * order. Note: The IBitStringLists must have entries (IBitStrings) of
 * fixed size.
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.0, 05/11/29
 * @see BitStringEqualizer
 * @see Conjunction
 * @see IProblem
 * @see MetaProblem
 */

package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.bitstring.BitStringEqualizer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringListReverser extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringListReverser(IBitStringList X, IBitStringList Y) throws Exception
  {
    if(X.size()!=Y.size())
      this.setClauses(MetaProblem.unsolvableProblem().getClauses());
    else
    {
      int commonsize=X.size();
      IProblem[] p=new IProblem[commonsize];
      int count=0;
      for(int i=0;i<commonsize;i++)
      	p[count++]=new BitStringEqualizer(X.getBitString(i),Y.getBitString(commonsize-1-i));
        
      this.setClauses(new Conjunction(p).getClauses());
    }
  }
}
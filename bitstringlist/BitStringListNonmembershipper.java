/**
 * <p>Title: BitStringListNonmembershipper</p>
 * <p>Description: This is a sample application showing the use of the 
 * BitStringListNonmembershipper class.</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.bitstring.BitStringUnequalizer;
import positronic.satisfiability.bitstring.IBitString;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.BitStringListException;

public class BitStringListNonmembershipper extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringListNonmembershipper(IBitString string, IBitStringList bsl) throws Exception
  {
    if(bsl==null || bsl.isEmpty())
      throw new BitStringListException("Passed a null or empty IBitStringList to constructor.");
    if(string==null)
      throw new BitStringListException("Passed a null IBitString to constructor.");
    IProblem[] res=new IProblem[bsl.size()];
    for(int i=0;i<bsl.size();i++)
    {
      IBitString curr=bsl.getBitString(i);
      res[i]=new BitStringUnequalizer(curr,string);
    }
    IProblem problem=new Conjunction(res);
    this.setClauses(problem.getClauses());
  }
}
/*
 * BitStringListDeranger.java	1.0 09/05/20
 *
 * Copyright 2009 Positronic Software.
 *
 */
 /**
 * An extension of the Problem class which imposes a derangement 
 * relationship on IBitStringList source and IBitStringList target. 
 * Specifically, the instance new BitStringListSorter(source, target) is 
 * satisfied if and only if source and target have the same membership and 
 * the members at each list position, the source entry is not equal to the 
 * target entry.
 * Note: All of the BitStrings in the source list must have the same 
 * length, and there should be a corresponding allocation for the 
 * BitStrings in the target.
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau@yahoo.com
 * http://kerrysoileau.com/index.htm
 * </pre></blockquote>
 * @version 1.0, 09/05/20
 * @see BitStringException
 * @see BitStringListEqualizer
 * @see BitStringListException
 * @see BooleanLiteralException
 * @see BitStringUnequalizer
 * @see Conjunction
 * @see IBitString
 * @see IBitStringList
 */

package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.bitstring.BitStringUnequalizer;
import positronic.satisfiability.bitstring.IBitString;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.BitStringException;
import positronic.satisfiability.exceptions.BitStringListException;
import positronic.satisfiability.exceptions.BooleanLiteralException;

public class BitStringListDeranger extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = -1402069971005969450L;

	public BitStringListDeranger(IBitStringList source, IBitStringList target) throws Exception
	{
	    if(source==null)
	      throw new BitStringListException("Passed a null IBitStringList to constructor.");
	    if(target==null)
	      throw new BitStringListException("Passed a null IBitStringList to constructor.");
	
	    IProblem problem=null;
	    //The source and target BitStringLists must have exactly the same elements:
	    IProblem same=new BitStringListEqualizer(source,target);
	    //The two lists must be deranged:
	    IProblem[] compare=new IProblem[target.size()];
	    for(int i=0;i<target.size();i++)
	    {
	      IBitString before=source.getBitString(i);
	      IBitString after=target.getBitString(i);
	      compare[i]=new BitStringUnequalizer(before,after);
	    }
	    problem=new Conjunction(same,new Conjunction(compare));
	    this.setClauses(problem.getClauses());
	}
}
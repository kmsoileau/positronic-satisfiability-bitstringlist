/*
 * BitStringListNonrepeater.java	1.0 05/12/17
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

/**
 * A class which imposes the restriction of being a Set on a BitStringList. 
 * That is, that the BitStringList has no repetition of members; each member 
 * appears only once in the BitStringList.
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.0, 05/12/17
 * @see BitStringUnequalizer
 * @see IBitString
 * @see Conjunction
 * @see IClause
 * @see IProblem
 * @see MetaProblem
 */

package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.bitstring.BitStringUnequalizer;
import positronic.satisfiability.bitstring.IBitString;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IClause;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringListNonrepeater extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = 1L;

	public BitStringListNonrepeater(IBitStringList list) throws Exception
	{
		IProblem p=null;
		for(int i=0;i<list.size();i++)
		{
			IBitString b=list.getBitString(i);
			for(int j=i+1;j<list.size();j++)
				p=new Conjunction(p,new BitStringUnequalizer(b,list.getBitString(j)));
		}
		if(p==null)
			this.setClauses((IClause[])null);
		else
			this.setClauses(p.getClauses());
	}
}

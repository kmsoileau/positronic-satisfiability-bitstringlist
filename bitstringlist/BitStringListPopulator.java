package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.BitStringListException;

public class BitStringListPopulator extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = -388391718064931108L;

	public BitStringListPopulator(
			IBitStringList source,IBitStringList target) throws Exception 
	{
		if(source==null)
			throw new BitStringListException("Passed a null IBitStringList to constructor.");
		if(target==null)
			throw new BitStringListException("Passed a null IBitStringList to constructor.");
	      
		IProblem res[]=new IProblem[source.size()];
	    for(int i=0;i<source.size();i++)
	    	res[i]=new BitStringListMembershipper(source.getBitString(i),target);
	    this.setClauses(new Conjunction(res).getClauses());
	}
}



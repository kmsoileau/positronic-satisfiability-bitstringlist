package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.bitstring.BitString;
import positronic.satisfiability.bitstring.BitStringFixer;
import positronic.satisfiability.bitstring.IBitString;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.Disjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BinaryBitStringListOperationer extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws Exception
	{
		BinaryBitStringListOperationer o=new BinaryBitStringListOperationer();
		o.addRule(new BitString("a","0"),new BitString("b","0"),new BitString("c","0"));
		o.addRule(new BitString("a","0"),new BitString("b","1"),new BitString("c","1"));
		o.addRule(new BitString("a","1"),new BitString("b","0"),new BitString("c","1"));
		o.addRule(new BitString("a","1"),new BitString("b","1"),new BitString("c","0"));
		System.out.println(o);
	}
	
	public void addRule(IBitString a,IBitString b,IBitString c) throws Exception
	{
		IProblem newRule=new Conjunction(new BitStringFixer(a),new BitStringFixer(b),new BitStringFixer(c));
		if(this.isEmpty())
			this.setClauses(newRule.getClauses());
		else
			this.setClauses(new Disjunction(this,newRule).getClauses());
	}
}

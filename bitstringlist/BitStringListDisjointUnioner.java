package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.bitstring.IBitString;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.Disjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.BitStringListException;

public class BitStringListDisjointUnioner extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;
  
  public BitStringListDisjointUnioner(IBitStringList T,IBitStringList A,IBitStringList B) throws Exception
  {
    if(T==null || A==null || B==null)
      throw new BitStringListException("Passed null IBitStringList to BitStringListAnder constructor.");
    
    IProblem problem=null;
    for(int i=0;i<T.size();i++)
    {
    	IBitString curr=T.getBitString(i);
    	IProblem inA=new BitStringListMembershipper(curr,A);
    	IProblem notInA=new BitStringListNonmembershipper(curr,A);
    	IProblem inB=new BitStringListMembershipper(curr,B);
    	IProblem notInB=new BitStringListNonmembershipper(curr,B);
    	IProblem p1=new Disjunction(new Conjunction(inA,notInB),
    			new Conjunction(inB,notInA));
    	//IProblem p2=new Disjunction(inA,inB);
    	//problem=new Conjunction(problem,new Disjunction(p1,p2));
    	problem=new Conjunction(problem,p1);
    }
    for(int i=0;i<A.size();i++)
    {
    	IBitString curr=A.getBitString(i);
    	IProblem inB=new BitStringListMembershipper(curr,B);
    	IProblem notInB=new BitStringListNonmembershipper(curr,B);
    	IProblem inT=new BitStringListMembershipper(curr,T);
    	IProblem notInT=new BitStringListNonmembershipper(curr,T);
    	IProblem p1=new Disjunction(notInT,notInB);
    	IProblem p2=new Disjunction(inB,inT);
    	problem=new Conjunction(problem,p1,p2);
    }
    for(int i=0;i<B.size();i++)
    {
    	IBitString curr=B.getBitString(i);
    	IProblem inA=new BitStringListMembershipper(curr,A);
    	IProblem notInA=new BitStringListNonmembershipper(curr,A);
    	IProblem inT=new BitStringListMembershipper(curr,T);
    	IProblem notInT=new BitStringListNonmembershipper(curr,T);
    	IProblem p1=new Disjunction(notInT,notInA);
    	IProblem p2=new Disjunction(inA,inT);
    	problem=new Conjunction(problem,p1,p2);
    }
    this.setClauses(problem.getClauses());
  }
}
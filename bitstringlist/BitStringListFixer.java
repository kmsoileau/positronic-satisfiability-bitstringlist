/*
 * BitStringListFixer.java	1.0 05/04/11
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstringlist;

import positronic.satisfiability.elements.BitFixer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.BitStringListFixerException;

public class BitStringListFixer extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringListFixer(IBitStringList target) throws Exception
  {
  	/*if(target==null)
  		this.setClauses(Problem.trivialProblem().getClauses());
  	else
  		if(target.size()==0)
  			this.setClauses(Problem.trivialProblem().getClauses());
  		else
	  	{
		    IProblem problem=null;
		    for(int i=0;i<target.size();i++)
		      for(int j=0;j<target.getBitString(i).size();j++)
		        problem=new Conjunction(problem,new BitFixer(target.getBitString(i).getBooleanVariable(j),
		          target.getBitString(i).getBooleanVariable(j).getValue()));
		    if(problem!=null)
		    	this.setClauses(problem.getClauses());
		    else
		    	this.setClauses(Problem.trivialProblem().getClauses());
	  	}*/
    if(target==null)
      throw new BitStringListFixerException("Passed null IBitStringList to constructor.");
    IProblem problem=null;
    for(int i=0;i<target.size();i++)
      for(int j=0;j<target.getBitString(i).size();j++)
        problem=new Conjunction(problem,new BitFixer(target.getBitString(i).getBooleanVariable(j),
          target.getBitString(i).getBooleanVariable(j).getValue()));
    this.setClauses(problem.getClauses());
  }

  /*
  public BitStringListFixer(IBitStringList target, boolean[][] data) throws BooleanLiteralException,BitStringException,BitStringListException
  {
    if(data==null)
      throw new BitStringListException("Passed null boolean[][] to constructor.");
    IProblem problem=null;
    for(int i=0;i<data.length;i++)
      for(int j=0;j<data[i].length;j++)
        problem=new Conjunction(problem,new BitFixer(target.getBitString(i).getBooleanVariable(j),data[i][j]));
    this.setClauses(problem.getClauses());
  }

  public BitStringListFixer(IBitStringList target, IBitString[] data) throws BooleanLiteralException,BitStringException,BitStringListException
  {
    boolean[][] bdata=new boolean[data.length][];
    for(int i=0;i<bdata.length;i++)
    {
      bdata[i]=new boolean[data[i].size()];
      for(int j=0;j<bdata[i].length;j++)
        bdata[i][j]=data[i].getBooleanVariable(j).getValue();
    }
    BitStringListFixer res=new BitStringListFixer(target,bdata);
    this.setClauses(res.getClauses());
  }
  */
}
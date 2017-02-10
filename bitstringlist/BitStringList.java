/*
 * BitStringList.java	1.0 05/04/28
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */
package positronic.satisfiability.bitstringlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Vector;

import positronic.satisfiability.bitstring.BitString;
import positronic.satisfiability.bitstring.IBitString;
import positronic.satisfiability.elements.BooleanVariable;
import positronic.satisfiability.elements.IBooleanVariable;
import positronic.satisfiability.exceptions.BitStringListException;
import positronic.util.ArrayListSet;

public class BitStringList implements IBitStringList
{
	private static int bSLCount;

	public static IBitStringList add(IBitStringList first,IBitStringList second) throws Exception
	{
		return BitStringList.add("bsl"+ bSLCount++,first,second);
	}

	public static IBitStringList add(String name,IBitStringList first,IBitStringList second) throws Exception
	{
		if(name==null)
			throw new BitStringListException("Passed null String to constructor.");
		if(first==null || second==null)
			throw new BitStringListException("Passed null String to constructor.");

		Collection<IBitString> a=first.toList();
		Collection<IBitString> b=second.toList();
		a.addAll(b);
		IBitString[] newArray=a.toArray(new IBitString[0]);
		IBitStringList res=new BitStringList(name,newArray);
		return res;
	}

	private ArrayListSet<IBitString> listData;
	private String name;

	public BitStringList() throws Exception
	{
		this("bsl"+ bSLCount++,0,0);
	}

	public BitStringList(boolean[][] bdata) throws Exception
	{
		this("bsl"+ bSLCount++,bdata);
	}

	public BitStringList(IBitString[] data) throws Exception
	{
		this("bsl"+ bSLCount++,data);
	}

	public BitStringList(IBitStringList list) throws Exception
	{
		this("bsl"+ bSLCount++,list.toArray(new IBitString[0]));
	}

	public BitStringList(int n) throws Exception
	{
		this("bsl"+ bSLCount++,n,0);
	}

	public BitStringList(String name) throws Exception
	{
		this.setName(name);
	}

	public BitStringList(String name, ArrayList<ArrayList<Boolean>> adata) throws Exception
	{
		if(name==null)
			throw new BitStringListException("Passed null String to constructor.");
		if(adata==null)
			throw new BitStringListException("Passed null ArrayList<ArrayList<Boolean>> to constructor.");
		if(adata.size()==0)
			throw new BitStringListException("Passed ArrayList<ArrayList<Boolean>> with adata.size()==0 to constructor.");
		this.setName(name);
		this.setData(new ArrayListSet<IBitString>(adata.size()));
		for(int i=0;i<adata.size();i++)
		{
			IBitString o=new BitString(name+"$"+i,new IBooleanVariable[adata.get(i).size()]);
			this.getData().add(o);
			for(int j=0;j<this.getBitString(i).size();j++)
				this.getBitString(i).setBooleanVariable(j,
						BooleanVariable.getBooleanVariable(name+"$"+i+"$"+j,adata.get(i).get(j)));
		}
	}

	public BitStringList(String name, boolean[][] bdata) throws Exception
	{
		if(name==null)
			throw new BitStringListException("Passed null String to constructor.");
		if(bdata==null)
			throw new BitStringListException("Passed null boolean[][] to constructor.");
		if(bdata.length==0)
			throw new BitStringListException("Passed boolean[][] with bdata.length==0 to constructor.");
		this.setName(name);
		this.setData(new ArrayListSet<IBitString>(bdata.length));
		for(int i=0;i<bdata.length;i++)
		{
			IBitString o=new BitString(name+"$"+i,new IBooleanVariable[bdata[i].length]);
			this.getData().add(o);
			for(int j=0;j<this.getBitString(i).size();j++)
				this.getBitString(i).setBooleanVariable(j,
						BooleanVariable.getBooleanVariable(name+"$"+i+"$"+j,bdata[i][j]));
		}
	}

	public BitStringList(String name, IBitString[] data) throws Exception
	{
		if(name==null)
			throw new BitStringListException("Passed null String to constructor.");
		if(data==null)
			throw new BitStringListException("Passed null IBitString[] to constructor.");
		this.setName(name);
		boolean[][] bdata=new boolean[data.length][];
		for(int i=0;i<bdata.length;i++)
		{
			if(data[i]==null)
				throw new BitStringListException("Attempted to initialize with null IBitString.");
			bdata[i]=new boolean[data[i].size()];
			for(int j=0;j<bdata[i].length;j++)
				bdata[i][j]=data[i].getBooleanVariable(j).getValue();
		}
		this.setData(new BitStringList(name,bdata).getData());
	}
	
	public BitStringList(String name, int n, int bits) throws Exception
	{
		this.setName(name);
		this.setData(new ArrayListSet<IBitString>(n));
		for(int i=0;i<n;i++)
		{
			IBitString o=new BitString(name+"$"+i,new boolean[bits]);
			//this.getData().add(o);
			this.getData().add(o);
		}
	}

	public BitStringList(String name, Vector<IBitString> vdata) throws Exception
	{
		if(name==null)
			throw new BitStringListException("Passed null String to constructor.");
		if(vdata==null)
			throw new BitStringListException("Passed null Vector<IBitString> to constructor.");
		this.setName(name);
		boolean[][] bdata=new boolean[vdata.size()][];
		for(int i=0;i<bdata.length;i++)
		{
			if(vdata.get(i)==null)
				throw new BitStringListException("Attempted to initialize with null IBitString.");
			bdata[i]=new boolean[vdata.get(i).size()];
			for(int j=0;j<bdata[i].length;j++)
				bdata[i][j]=vdata.get(i).getBooleanVariable(j).getValue();
		}
		this.setData(new BitStringList(name,bdata).getData());
	}

	@Override
	public boolean add(IBitString o)
	{
		System.out.println("Attempting to add IBitString "+o+" to BitStringList "+this);
		try
		{
			System.out.println("Before, this.getData()= "+this.getData());
		} catch (Exception e1)
		{
			e1.printStackTrace();
		}
		try
		{
			return this.getData().add(o);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public IBitStringList add(IBitStringList list) throws Exception
	{
		return BitStringList.add(this.getName(),this,list);
	}

	@Override
	public void add(int j, BitString bitString) 
	{
		add(j, (IBitString)bitString);
	}

	public void add(int n, IBitString o)
	{
		try
		{
			//((ArrayListSet<Object>)(this.getData())).add(n,o);
			this.getData().add(n,o);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean addAll(Collection<? extends IBitString> c)
	{
		try
		{
			return this.getData().addAll(c);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean addAll(int n,Collection<IBitString> c)
	{
		try
		{
			return this.getData().addAll(n,c);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public void clear()
	{
		try
		{
			this.getData().clear();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		try
		{
			return new BitStringList(this.getName(),this.getData().toArray(new IBitString[0]));
		}
		catch(CloneNotSupportedException err)
		{
			throw err;
		}
		catch(Exception err)
		{
			System.err.println("Attempt to clone BitStringList failed.");
			return null;
		}
	}

	public boolean contains(Object o)
	{
		try
		{
			return this.getData().contains(o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean containsAll(Collection<?> c)
	{
		try
		{
			return this.getData().containsAll(c);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean equals(Object anObject)
	{
		if(anObject==null)
			return false; //this is never equal to null.
		if(!(anObject instanceof BitStringList))
			return false;
		try
		{
			if(!this.getData().containsAll(((BitStringList)anObject).getData()))
				return false;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			if(!((BitStringList)anObject).getData().containsAll(this.getData()))
				return false;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

	public IBitString get(int n)
	{
		try
		{
			return this.getData().get(n);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public IBitString getBitString(int i) throws Exception
	{
		if(i<0 || i>this.size()-1)
			throw new BitStringListException("Attempted to index out of range.");
		return (this.getData().get(i));
	}

	public ArrayListSet<IBitString> getData() throws Exception
	{
		if(this.listData==null)
			throw new BitStringListException("Attempted to access listData while listData==null.");
		return this.listData;
	}

	@Override
	public String getName()
	{
		return name;
	}

	public int indexOf(Object o)
	{
		try
		{
			return this.getData().indexOf(o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean isEmpty()
	{
		return this.listData==null;
	}

	public Iterator<IBitString> iterator()
	{
		try
		{
			return this.getData().iterator();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public int lastIndexOf(Object o)
	{
		try
		{
			return this.getData().lastIndexOf(o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	public ListIterator<IBitString> listIterator()
	{
		try
		{
			return this.getData().listIterator();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public ListIterator<IBitString> listIterator(int n)
	{
		try
		{
			return this.getData().listIterator(n);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public IBitString remove(int n)
	{
		try
		{
			return this.getData().remove(n);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public boolean remove(Object o)
	{
		try
		{
			return this.getData().remove(o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeAll(Collection<?> c)
	{
		try
		{
			return this.getData().removeAll(c);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean retainAll(Collection<?> c)
	{
		try
		{
			return this.getData().retainAll(c);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void set(int i, BitString bitString)
	{
		set(i,(IBitString)bitString);
	}

	public IBitString set(int n, IBitString o)
	{
		try 
		{
			if(n<0 || n>this.getData().size()-1)
				throw new BitStringListException("Attempted to index out of range.");
		} catch (BitStringListException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try
		{
			//return ((ArrayListSet<Object>)this.getData()).set(n,o);
			return this.getData().set(n,o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void setBitString(int i,IBitString bitString) throws Exception
	{
		if(i<0 || i>this.size()-1)
			throw new BitStringListException("Attempted to index out of range.");
		if(bitString==null)
			throw new BitStringListException("Passed null IBitString to setBitString method.");
		this.getData().set(i,bitString);
	}

	public void setData(Set<IBitString> data) throws Exception
	{
		if(data==null)
			throw new BitStringListException("Passed null ArrayListSet to setData method.");
		this.listData = (ArrayListSet<IBitString>)data;
	}

	public void setName(String name) throws Exception
	{
		if(name==null)
			throw new BitStringListException("Passed null String to setName method.");

		this.name = name;
	}

	public int size()
	{
		try
		{
			Set<IBitString> dt=this.getData();
			return dt.size();

		} 
		catch (Exception e)
		{
			return 0;
		}
	}

	public List<IBitString> subList(int m, int n)
	{
		try
		{
			return this.getData().subList(m,n);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public IBitString[] toArray(IBitString[] iBitStrings) 
	{
		try
		{
			return this.getData().toArray(iBitStrings);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toBits()
	{
		String res="$";
		if(this.size()>0)
		{
			try
			{
				res+=this.getBitString(0).toBits();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			for(int i=1;i<this.size();i++)
				try
			{
					res+=","+this.getBitString(i).toBits();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return res+"$";
	}

	@Override
	public ArrayListSet<IBitString> toList() throws Exception
	{
		return this.listData;
	}

	@Override
	public String toString()
	{
		String res="$";
		if(this.size()>0)
		{
			try
			{
				res+=this.getBitString(0).toString();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			for(int i=1;i<this.size();i++)
				try
			{
					res+=","+this.getBitString(i);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return res+"$";
	}
}
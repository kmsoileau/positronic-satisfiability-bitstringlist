package positronic.satisfiability.bitstringlist.Rubik;

import java.util.HashMap;

import positronic.satisfiability.bitstring.BitString;
import positronic.satisfiability.bitstring.IBitString;
import positronic.satisfiability.bitstringlist.BitStringList;

public class RubikStatus extends BitStringList
{
	public RubikStatus(String moveName) throws Exception
	{
		super(moveName,48,3);
	}
	
	public IBitString getFace(int i) throws Exception
	{
		return this.getBitString(i-1);
	}
	
	public void initializeCube() throws Exception 
	{
		this.setFaceYellow(1);
		this.setFaceYellow(2);
		this.setFaceYellow(3);
		this.setFaceYellow(4);
		this.setFaceYellow(5);
		this.setFaceYellow(6);
		this.setFaceYellow(7);
		this.setFaceYellow(8);
		this.setFaceRed(9);
		this.setFaceRed(10);
		this.setFaceRed(11);
		this.setFaceRed(21);
		this.setFaceRed(22);
		this.setFaceRed(29);
		this.setFaceRed(30);
		this.setFaceRed(31);
		this.setFaceBlue(12);
		this.setFaceBlue(13);
		this.setFaceBlue(14);
		this.setFaceBlue(23);
		this.setFaceBlue(24);
		this.setFaceBlue(32);
		this.setFaceBlue(33);
		this.setFaceBlue(34);
		this.setFaceOrange(15);
		this.setFaceOrange(16);
		this.setFaceOrange(17);
		this.setFaceOrange(25);
		this.setFaceOrange(26);
		this.setFaceOrange(35);
		this.setFaceOrange(36);
		this.setFaceOrange(37);
		this.setFaceGreen(18);
		this.setFaceGreen(19);
		this.setFaceGreen(20);
		this.setFaceGreen(27);
		this.setFaceGreen(28);
		this.setFaceGreen(38);
		this.setFaceGreen(39);
		this.setFaceGreen(40);
		this.setFaceWhite(41);
		this.setFaceWhite(42);
		this.setFaceWhite(43);
		this.setFaceWhite(44);
		this.setFaceWhite(45);
		this.setFaceWhite(46);
		this.setFaceWhite(47);
		this.setFaceWhite(48);
	}
	
	public void setBlues(int[] blues) throws Exception
	{
		for(int i : blues)
			this.setFaceBlue(i);
	}
	
	public void setFaceBlue(int face) throws Exception
	{
		for(int i=0;i<3;i++)
			this.getFace(face).getBooleanVariable(i).setValue(
					new BitString(RubikColors.blue).getBooleanVariable(i).getValue());
	}
	
	public void setFaceGreen(int face) throws Exception
	{
		for(int i=0;i<3;i++)
			this.getFace(face).getBooleanVariable(i).setValue(
					new BitString(RubikColors.green).getBooleanVariable(i).getValue());
	}
	
	public void setFaceOrange(int face) throws Exception
	{
		for(int i=0;i<3;i++)
			this.getFace(face).getBooleanVariable(i).setValue(
					new BitString(RubikColors.orange).getBooleanVariable(i).getValue());
	}
	
	public void setFaceRed(int face) throws Exception
	{
		for(int i=0;i<3;i++)
			this.getFace(face).getBooleanVariable(i).setValue(
					new BitString(RubikColors.red).getBooleanVariable(i).getValue());
	}
	
	public void setFaceWhite(int face) throws Exception
	{
		for(int i=0;i<3;i++)
			this.getFace(face).getBooleanVariable(i).setValue(
					new BitString(RubikColors.white).getBooleanVariable(i).getValue());
	}
	
	public void setFaceYellow(int face) throws Exception
	{
		for(int i=0;i<3;i++)
			this.getFace(face).getBooleanVariable(i).setValue(
					new BitString(RubikColors.yellow).getBooleanVariable(i).getValue());
	}
	
	public void setGreens(int[] greens) throws Exception
	{
		for(int i : greens)
			this.setFaceGreen(i);
	}
	
	public void setOranges(int[] oranges) throws Exception
	{
		for(int i : oranges)
			this.setFaceOrange(i);
	}
	
	public void setReds(int[] reds) throws Exception
	{
		for(int i : reds)
			this.setFaceRed(i);
	}
	
	public void setWhites(int[] whites) throws Exception
	{
		for(int i : whites)
			this.setFaceWhite(i);
	}
	
	public void setYellows(int[] yellows) throws Exception
	{
		for(int i : yellows)
			this.setFaceYellow(i);
	}

	public String toCode()
	{
		HashMap<String,String> h=new HashMap<String,String>();
		h.put(RubikColors.yellow, "this.setFaceYellow(");
		h.put(RubikColors.red, "this.setFaceRed(");
		h.put(RubikColors.blue, "this.setFaceBlue(");
		h.put(RubikColors.orange, "this.setFaceOrange(");
		h.put(RubikColors.green, "this.setFaceGreen(");
		h.put(RubikColors.white, "this.setFaceWhite(");
		String ret="";
		try 
		{
			for(int i=1;i<=48;i++)
				ret+="\n"+h.get(this.getFace(i).toBits())+i+");";
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public String toString()
	{
		HashMap<String,String> h=new HashMap<String,String>();
		h.put(RubikColors.yellow, "Y");
		h.put(RubikColors.red, "R");
		h.put(RubikColors.blue, "B");
		h.put(RubikColors.orange, "O");
		h.put(RubikColors.green, "G");
		h.put(RubikColors.white, "W");
		String ret="";
		try 
		{
			ret+="\n   "+h.get(this.getFace(1).toBits());
			ret+=h.get(this.getFace(2).toBits());
			ret+=h.get(this.getFace(3).toBits());
			ret+="\n   "+h.get(this.getFace(4).toBits())+" ";
			ret+=h.get(this.getFace(5).toBits());
			ret+="\n   "+h.get(this.getFace(6).toBits());
			ret+=h.get(this.getFace(7).toBits());
			ret+=h.get(this.getFace(8).toBits());
			ret+="\n"+h.get(this.getFace(9).toBits());
			ret+=h.get(this.getFace(10).toBits());
			ret+=h.get(this.getFace(11).toBits());
			ret+=h.get(this.getFace(12).toBits());
			ret+=h.get(this.getFace(13).toBits());
			ret+=h.get(this.getFace(14).toBits());
			ret+=h.get(this.getFace(15).toBits());
			ret+=h.get(this.getFace(16).toBits());
			ret+=h.get(this.getFace(17).toBits());
			ret+=h.get(this.getFace(18).toBits());
			ret+=h.get(this.getFace(19).toBits());
			ret+=h.get(this.getFace(20).toBits());
			ret+="\n"+h.get(this.getFace(21).toBits())+" ";
			ret+=h.get(this.getFace(22).toBits());
			ret+=h.get(this.getFace(23).toBits())+" ";
			ret+=h.get(this.getFace(24).toBits());
			ret+=h.get(this.getFace(25).toBits())+" ";
			ret+=h.get(this.getFace(26).toBits());
			ret+=h.get(this.getFace(27).toBits())+" ";
			ret+=h.get(this.getFace(28).toBits());
			ret+="\n"+h.get(this.getFace(29).toBits());
			ret+=h.get(this.getFace(30).toBits());
			ret+=h.get(this.getFace(31).toBits());
			ret+=h.get(this.getFace(32).toBits());
			ret+=h.get(this.getFace(33).toBits());
			ret+=h.get(this.getFace(34).toBits());
			ret+=h.get(this.getFace(35).toBits());
			ret+=h.get(this.getFace(36).toBits());
			ret+=h.get(this.getFace(37).toBits());
			ret+=h.get(this.getFace(38).toBits());
			ret+=h.get(this.getFace(39).toBits());
			ret+=h.get(this.getFace(40).toBits());
			ret+="\n   "+h.get(this.getFace(41).toBits());
			ret+=h.get(this.getFace(42).toBits());
			ret+=h.get(this.getFace(43).toBits());
			ret+="\n   "+h.get(this.getFace(44).toBits())+" ";
			ret+=h.get(this.getFace(45).toBits());
			ret+="\n   "+h.get(this.getFace(46).toBits());
			ret+=h.get(this.getFace(47).toBits());
			ret+=h.get(this.getFace(48).toBits());
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}

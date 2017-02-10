package positronic.satisfiability.bitstringlist;

import junit.framework.TestCase;

public class BitStringListTest extends TestCase
{

	public static void main(String[] args) throws Exception
	{
		BitStringListTest test=new BitStringListTest("first");
		test.testBitStringList();
	}

	public BitStringListTest(String name)
	{
		super(name);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	/*
	 * Test method for 'positronic.satisfiability.bitstringlist.BitStringList.BitStringList()'
	 */
	public final void testBitStringList() throws Exception
	{
		IBitStringList b1=new BitStringList();
		
		System.out.println("getName() = "+b1.getName());
		System.out.println("isEmpty() = "+b1.isEmpty());
		System.out.println("size() = "+b1.size());
		System.out.println("toList() = "+b1.toList());
		System.out.println("toString() = "+b1.toString());
	}

	/*
	 * Test method for 'positronic.satisfiability.bitstringlist.BitStringList.BitStringList(boolean[][])'
	 */
	public final void testBitStringListBooleanArrayArray()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'positronic.satisfiability.bitstringlist.BitStringList.BitStringList(IBitString[])'
	 */
	public final void testBitStringListIBitStringArray()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'positronic.satisfiability.bitstringlist.BitStringList.BitStringList(IBitStringList)'
	 */
	public final void testBitStringListIBitStringList()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'positronic.satisfiability.bitstringlist.BitStringList.BitStringList(int)'
	 */
	public final void testBitStringListInt()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'positronic.satisfiability.bitstringlist.BitStringList.BitStringList(String)'
	 */
	public final void testBitStringListString()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'positronic.satisfiability.bitstringlist.BitStringList.BitStringList(String, boolean[][])'
	 */
	public final void testBitStringListStringBooleanArrayArray()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'positronic.satisfiability.bitstringlist.BitStringList.BitStringList(String, IBitString[])'
	 */
	public final void testBitStringListStringIBitStringArray()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'positronic.satisfiability.bitstringlist.BitStringList.BitStringList(String, int)'
	 */
	public final void testBitStringListStringInt()
	{
		// TODO Auto-generated method stub

	}

}

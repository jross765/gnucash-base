package org.gnucash.base.basetypes.complex;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class TestGCshSecID_SecIdType {
	public static void main(String[] args) throws Exception {
		junit.textui.TestRunner.run(suite());
	}

	@SuppressWarnings("exports")
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestGCshSecID_SecIdType.class);
	}

	// -----------------------------------------------------------------

	@Test
	public void test02() throws Exception {
		GCshSecID_SecIdType cmdty = new GCshSecID_SecIdType(GCshCmdtyNameSpace.SecIdType.ISIN, "DE0007100000");

		assertEquals(GCshCmdtyID.Type.SECURITY, cmdty.getType());
		assertEquals(GCshSecID.SubType.SECIDTYPE, cmdty.getSubType());
		assertEquals(GCshCmdtyNameSpace.SecIdType.ISIN, cmdty.getSecIdType());
		assertEquals("DE0007100000", cmdty.getCode());
		assertEquals("ISIN:DE0007100000", cmdty.toString());
	}

	@Test
	public void test03() throws Exception {
		GCshSecID_SecIdType cmdty1 = new GCshSecID_SecIdType(GCshCmdtyNameSpace.SecIdType.ISIN, "DE0007100000");
		GCshSecID_SecIdType cmdty2 = new GCshSecID_SecIdType("ISIN", "DE0007100000");

		assertEquals(cmdty1.toString(), cmdty2.toString());
		assertEquals(cmdty1.toStringLong(), cmdty2.toStringLong());
		assertEquals(cmdty1, cmdty2);

		// ---

//    CommodityID_SecIdType commCurr31 = new CommodityID_SecIdType(CmdtyCurrNameSpace.SecIdType.NYSE, "DE0007100000");
//    CommodityID_SecIdType commCurr32 = new CommodityID_SecIdType(CmdtyCurrNameSpace.SecIdType.EURONEXT, "DIS");
//    
//    assertNotEquals(commCurr1, commCurr31);
//    assertNotEquals(commCurr1, commCurr32);
//    assertNotEquals(commCurr31, commCurr32);
	}

	@Test
	public void test04_2() throws Exception {
		GCshSecID_SecIdType cmdtyPrs = GCshSecID_SecIdType.parse("ISIN:DE0007164600");
		GCshSecID_SecIdType cmdtyRef = new GCshSecID_SecIdType(GCshCmdtyNameSpace.SecIdType.ISIN,
				"DE0007164600");

		assertEquals(GCshCmdtyID.Type.SECURITY, cmdtyPrs.getType());
		assertEquals(GCshSecID.SubType.SECIDTYPE, cmdtyPrs.getSubType());
		assertEquals("ISIN:DE0007164600", cmdtyPrs.toString());
		assertEquals(cmdtyRef.toString(), cmdtyPrs.toString());
		assertEquals(cmdtyRef.toStringLong(), cmdtyPrs.toStringLong());
		assertEquals(cmdtyRef, cmdtyPrs);

//      // ---
//      
//      commCurrPrs = CommodityID_SecIdType.parse("CURRENCY:USD");
//      commCurrRef = new CommodityID_SecIdType(Currency.getInstance("USD"));
//      
//      assertEquals("CURRENCY:USD", commCurrPrs.toString());
//      assertEquals(commCurrRef, commCurrPrs);
	}

	@Test
	public void test04_3() throws Exception {
		try {
			GCshSecID_SecIdType cmdtyPrs = GCshSecID_SecIdType.parse("FUXNSTUELL:BURP");
			assertEquals(1, 0);
		} catch (Exception exc) {
			assertEquals(0, 0);
		}
	}
}

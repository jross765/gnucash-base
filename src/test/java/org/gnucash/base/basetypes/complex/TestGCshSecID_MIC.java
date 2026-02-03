package org.gnucash.base.basetypes.complex;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class TestGCshSecID_MIC {
	public static void main(String[] args) throws Exception {
		junit.textui.TestRunner.run(suite());
	}

	@SuppressWarnings("exports")
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestGCshSecID_MIC.class);
	}

	// -----------------------------------------------------------------

	@Test
	public void test02() throws Exception {
		GCshSecID_MIC cmdty = new GCshSecID_MIC(GCshCmdtyNameSpace.MIC.XFRA, "MBG");

		assertEquals(GCshCmdtyID.Type.SECURITY, cmdty.getType());
		assertEquals(GCshSecID.SubType.MIC, cmdty.getSubType());
		assertEquals(GCshCmdtyNameSpace.MIC.XFRA, cmdty.getMIC());
		assertEquals("MBG", cmdty.getCode());
		assertEquals("XFRA:MBG", cmdty.toString());
	}

	@Test
	public void test03() throws Exception {
		GCshSecID_MIC cmdty1 = new GCshSecID_MIC(GCshCmdtyNameSpace.MIC.XFRA, "MBG");
		GCshSecID_MIC cmdty2 = new GCshSecID_MIC("XFRA", "MBG");

		assertEquals(cmdty1.toString(), cmdty2.toString());
		assertEquals(cmdty1.toStringLong(), cmdty2.toStringLong());
		assertEquals(cmdty1, cmdty2);

		// ---

//    CommodityID_MIC commCurr31 = new CommodityID_MIC(CmdtyCurrNameSpace.MIC.NYSE, "MBG");
//    CommodityID_MIC commCurr32 = new CommodityID_MIC(CmdtyCurrNameSpace.MIC.EURONEXT, "DIS");
//    
//    assertNotEquals(commCurr1, commCurr31);
//    assertNotEquals(commCurr1, commCurr32);
//    assertNotEquals(commCurr31, commCurr32);
	}

	@Test
	public void test04_2() throws Exception {
		GCshSecID_MIC cmdtyPrs = GCshSecID_MIC.parse("XFRA:SAP");
		GCshSecID_MIC cmdtyRef = new GCshSecID_MIC(GCshCmdtyNameSpace.MIC.XFRA, "SAP");

		assertEquals(GCshCmdtyID.Type.SECURITY, cmdtyPrs.getType());
		assertEquals(GCshSecID.SubType.MIC, cmdtyPrs.getSubType());
		assertEquals("XFRA:SAP", cmdtyPrs.toString());
		assertEquals(cmdtyRef.toString(), cmdtyPrs.toString());
		assertEquals(cmdtyRef.toStringLong(), cmdtyPrs.toStringLong());
		assertEquals(cmdtyRef, cmdtyPrs);

//      // ---
//      
//      commCurrPrs = CommodityID_MIC.parse("CURRENCY:USD");
//      commCurrRef = new CommodityID_MIC(Currency.getInstance("USD"));
//      
//      assertEquals("CURRENCY:USD", commCurrPrs.toString());
//      assertEquals(commCurrRef, commCurrPrs);
	}

	@Test
	public void test04_3() throws Exception {
		try {
			GCshSecID_MIC cmdtyPrs = GCshSecID_MIC.parse("FUXNSTUELL:BURP");
			assertEquals(1, 0);
		} catch (Exception exc) {
			assertEquals(0, 0);
		}
	}
}

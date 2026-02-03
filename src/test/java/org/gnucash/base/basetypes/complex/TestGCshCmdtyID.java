package org.gnucash.base.basetypes.complex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class TestGCshCmdtyID {
	public static void main(String[] args) throws Exception {
		junit.textui.TestRunner.run(suite());
	}

	@SuppressWarnings("exports")
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestGCshCmdtyID.class);
	}

	// -----------------------------------------------------------------

	@Test
	public void test01() throws Exception {
		GCshCmdtyID commCurr = new GCshCmdtyID(GCshCmdtyNameSpace.CURRENCY, "EUR");

		assertEquals(GCshCmdtyID.Type.CURRENCY, commCurr.getType());
		assertEquals(GCshCmdtyNameSpace.CURRENCY, commCurr.getNameSpace());
		assertEquals("EUR", commCurr.getCode());
		assertEquals("CURRENCY:EUR", commCurr.toString());

		// ---

		commCurr = new GCshCmdtyID(GCshCmdtyNameSpace.CURRENCY, "USD");

		assertEquals(GCshCmdtyID.Type.CURRENCY, commCurr.getType());
		assertEquals(GCshCmdtyNameSpace.CURRENCY, commCurr.getNameSpace());
		assertEquals("USD", commCurr.getCode());
		assertEquals("CURRENCY:USD", commCurr.toString());

		// ---

		commCurr = new GCshCmdtyID(GCshCmdtyNameSpace.CURRENCY, "XYZ"); // Wrong, but no check on this level

		assertEquals(GCshCmdtyID.Type.CURRENCY, commCurr.getType());
		assertEquals(GCshCmdtyNameSpace.CURRENCY, commCurr.getNameSpace());
		assertEquals("XYZ", commCurr.getCode());
		assertEquals("CURRENCY:XYZ", commCurr.toString());

	}

	@Test
	public void test02() throws Exception {
		GCshCmdtyID commCurr = new GCshCmdtyID("EURONEXT", "MBG");

		assertEquals(GCshCmdtyID.Type.SECURITY, commCurr.getType());
		assertEquals("EURONEXT", commCurr.getNameSpace());
		assertEquals("MBG", commCurr.getCode());
		assertEquals("EURONEXT:MBG", commCurr.toString());
	}

	@Test
	public void test03() throws Exception {
		GCshCmdtyID commCurr1 = new GCshCmdtyID("EURONEXT", "MBG");
		GCshCmdtyID commCurr2 = new GCshCmdtyID("EURONEXT", "MBG");

		assertEquals(commCurr1.toString(), commCurr2.toString());
		assertEquals(commCurr1.toStringLong(), commCurr2.toStringLong());
		assertEquals(commCurr1, commCurr2);

		// ---

		GCshCmdtyID commCurr31 = new GCshCmdtyID("NYSE", "MBG");
		GCshCmdtyID commCurr32 = new GCshCmdtyID("EURONEXT", "DIS");

		assertNotEquals(commCurr1, commCurr31);
		assertNotEquals(commCurr1, commCurr32);
		assertNotEquals(commCurr31, commCurr32);

		// ---

		GCshCmdtyID commCurr4 = new GCshCmdtyID(GCshCmdtyNameSpace.CURRENCY, "EUR");
		GCshCmdtyID commCurr5 = new GCshCmdtyID(GCshCmdtyNameSpace.CURRENCY, "EUR");

		assertEquals(commCurr4, commCurr5);
		assertNotEquals(commCurr1, commCurr4);
		assertNotEquals(commCurr2, commCurr4);
		assertNotEquals(commCurr31, commCurr4);
		assertNotEquals(commCurr32, commCurr4);

		GCshCmdtyID commCurr6 = new GCshCmdtyID(GCshCmdtyNameSpace.CURRENCY, "JPY");

		assertNotEquals(commCurr4, commCurr6);
	}

	@Test
	public void test04_1() throws Exception {
		GCshCmdtyID commCurrPrs = GCshCmdtyID.parse("CURRENCY:EUR");
		GCshCmdtyID commCurrRef = new GCshCmdtyID(GCshCmdtyNameSpace.CURRENCY, "EUR");

		assertEquals(GCshCmdtyID.Type.CURRENCY, commCurrPrs.getType());
		assertEquals("CURRENCY:EUR", commCurrPrs.toString());
		assertEquals(commCurrRef.toString(), commCurrPrs.toString());
		assertEquals(commCurrRef.toStringLong(), commCurrPrs.toStringLong());
		assertEquals(commCurrRef, commCurrPrs);

		// ---

		commCurrPrs = GCshCmdtyID.parse("CURRENCY:USD");
		commCurrRef = new GCshCmdtyID(GCshCmdtyNameSpace.CURRENCY, "USD");

		assertEquals(GCshCmdtyID.Type.CURRENCY, commCurrPrs.getType());
		assertEquals("CURRENCY:USD", commCurrPrs.toString());
		assertEquals(commCurrRef.toString(), commCurrPrs.toString());
		assertEquals(commCurrRef.toStringLong(), commCurrPrs.toStringLong());
		assertEquals(commCurrRef, commCurrPrs);
	}

	@Test
	public void test04_2() throws Exception {
		GCshCmdtyID commCurrPrs = GCshCmdtyID.parse("EURONEXT:SAP");
		GCshCmdtyID commCurrRef = new GCshCmdtyID("EURONEXT", "SAP");

		assertEquals(GCshCmdtyID.Type.SECURITY, commCurrPrs.getType());
		assertEquals("EURONEXT:SAP", commCurrPrs.toString());
		assertEquals(commCurrRef.toString(), commCurrPrs.toString());
		assertEquals(commCurrRef.toStringLong(), commCurrPrs.toStringLong());
		assertEquals(commCurrRef, commCurrPrs);

//      // ---
//      
//      commCurrPrs = CmdtyCurrID.parse("CURRENCY:USD");
//      commCurrRef = new CmdtyCurrID(Currency.getInstance("USD"));
//      
//      assertEquals("CURRENCY:USD", commCurrPrs.toString());
//      assertEquals(commCurrRef, commCurrPrs);
	}

	@Test
	public void test04_3() throws Exception {
		GCshCmdtyID commCurrPrs = GCshCmdtyID.parse("FUXNSTUELL:BURP"); // Wrong, but not check on this level
		GCshCmdtyID commCurrRef = new GCshCmdtyID();
		commCurrRef.setType(GCshCmdtyID.Type.SECURITY);
		commCurrRef.setNameSpace("FUXNSTUELL");
		commCurrRef.setCode("BURP");

		assertEquals(GCshCmdtyID.Type.SECURITY, commCurrPrs.getType());
		assertEquals("FUXNSTUELL:BURP", commCurrPrs.toString());
		assertEquals(commCurrRef, commCurrPrs);

//      // ---
//      
//      commCurrPrs = CmdtyCurrID.parse("CURRENCY:USD");
//      commCurrRef = new CmdtyCurrID(Currency.getInstance("USD"));
//      
//      assertEquals("CURRENCY:USD", commCurrPrs.toString());
//      assertEquals(commCurrRef, commCurrPrs);
	}

	@Test
	public void test05_1_1() throws Exception {
		GCshSecID_Exchange cmdtyID = new GCshSecID_Exchange(GCshCmdtyNameSpace.Exchange.EURONEXT, "MBG");
		assertEquals(GCshCmdtyID.Type.SECURITY, cmdtyID.getType());
		assertEquals(GCshSecID.SubType.EXCHANGE, cmdtyID.getSubType());
		
		GCshCmdtyID cmdtyCurrID = new GCshCmdtyID(cmdtyID);
//		assertEquals(GCshCmdtyCurrID.Type.SECURITY_SECIDTYPE, cmdtyCurrID.getType());
		assertEquals(null, cmdtyCurrID.getType()); // sic ::CHECK
		assertEquals("EURONEXT", cmdtyCurrID.getNameSpace());
		assertEquals("MBG", cmdtyCurrID.getCode());
	}

	@Test
	public void test05_1_2() throws Exception {
		GCshSecID_MIC cmdtyID = new GCshSecID_MIC(GCshCmdtyNameSpace.MIC.XFRA, "MBG");
		assertEquals(GCshCmdtyID.Type.SECURITY, cmdtyID.getType());
		assertEquals(GCshSecID.SubType.MIC, cmdtyID.getSubType());
		
		GCshCmdtyID cmdtyCurrID = new GCshCmdtyID(cmdtyID);
//		assertEquals(GCshCmdtyCurrID.Type.SECURITY_SECIDTYPE, cmdtyCurrID.getType());
		assertEquals(null, cmdtyCurrID.getType()); // sic ::CHECK
		assertEquals("XFRA", cmdtyCurrID.getNameSpace());
		assertEquals("MBG", cmdtyCurrID.getCode());
	}
	
	@Test
	public void test05_1_3() throws Exception {
		GCshSecID_SecIdType cmdtyID = new GCshSecID_SecIdType(GCshCmdtyNameSpace.SecIdType.ISIN, "DE0007100000");
		assertEquals(GCshCmdtyID.Type.SECURITY, cmdtyID.getType());
		assertEquals(GCshSecID.SubType.SECIDTYPE, cmdtyID.getSubType());
		
		GCshCmdtyID cmdtyCurrID = new GCshCmdtyID(cmdtyID);
//		assertEquals(GCshCmdtyCurrID.Type.SECURITY_SECIDTYPE, cmdtyCurrID.getType());
		assertEquals(null, cmdtyCurrID.getType()); // sic ::CHECK
		assertEquals("ISIN", cmdtyCurrID.getNameSpace());
		assertEquals("DE0007100000", cmdtyCurrID.getCode());
	}

	@Test
	public void test05_2() throws Exception {
		GCshCurrID currID = new GCshCurrID("JPY");
		assertEquals(GCshCmdtyID.Type.CURRENCY, currID.getType());
		
		GCshCmdtyID cmdtyCurrID = new GCshCmdtyID(currID);
//		assertEquals(GCshCmdtyCurrID.Type.SECURITY_SECIDTYPE, cmdtyCurrID.getType());
		assertEquals(null, cmdtyCurrID.getType()); // sic ::CHECK
		assertEquals(GCshCmdtyNameSpace.CURRENCY, cmdtyCurrID.getNameSpace());
		assertEquals("JPY", cmdtyCurrID.getCode());
	}	
}

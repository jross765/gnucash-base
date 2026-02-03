package org.gnucash.base.basetypes.complex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class TestGCshSecID {
	public static void main(String[] args) throws Exception {
		junit.textui.TestRunner.run(suite());
	}

	@SuppressWarnings("exports")
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestGCshSecID.class);
	}

	// -----------------------------------------------------------------

	@Test
	public void test01() throws Exception {
		try {
			GCshSecID commCurr = new GCshSecID(GCshCmdtyNameSpace.CURRENCY, "EUR");
			assertEquals(1, 0);
		} catch (Exception exc) {
			assertEquals(0, 0);
		}
	}

	@Test
	public void test02() throws Exception {
		GCshSecID commCurr = new GCshSecID("EURONEXT", "MBG");

		assertEquals(GCshCmdtyID.Type.SECURITY, commCurr.getType());
		assertEquals(GCshSecID.SubType.GENERAL, commCurr.getSubType());
		assertEquals("EURONEXT", commCurr.getNameSpace());
		assertEquals("MBG", commCurr.getCode());
		assertEquals("EURONEXT:MBG", commCurr.toString());
	}

	@Test
	public void test03() throws Exception {
		GCshSecID commCurr1 = new GCshSecID("EURONEXT", "MBG");
		GCshSecID commCurr2 = new GCshSecID("EURONEXT", "MBG");

		assertEquals(commCurr1.toString(), commCurr2.toString());
		assertEquals(commCurr1.toStringLong(), commCurr2.toStringLong());
		assertEquals(commCurr1, commCurr2);

		// ---

		GCshSecID commCurr31 = new GCshSecID("NYSE", "MBG");
		GCshSecID commCurr32 = new GCshSecID("EURONEXT", "DIS");

		assertNotEquals(commCurr1, commCurr31);
		assertNotEquals(commCurr1, commCurr32);
		assertNotEquals(commCurr31, commCurr32);

		// ---

		GCshCurrID commCurr4 = new GCshCurrID("EUR");

		assertNotEquals(commCurr1, commCurr4);
		assertNotEquals(commCurr2, commCurr4);
		assertNotEquals(commCurr31, commCurr4);
		assertNotEquals(commCurr32, commCurr4);
	}

	@Test
	public void test04_1() throws Exception {
		try {
			GCshSecID commCurrPrs = GCshSecID.parse("CURRENCY:EUR");
		} catch (Exception exc) {
			assertEquals(0, 0);
		}

		// ---

		try {
			GCshSecID commCurrPrs = GCshSecID.parse("CURRENCY:USD");
		} catch (Exception exc) {
			assertEquals(0, 0);
		}

	}

	@Test
	public void test04_2() throws Exception {
		GCshSecID commCurrPrs = GCshSecID.parse("XFRA:SAP");
		GCshSecID commCurrRef = new GCshSecID("XFRA", "SAP");

		assertEquals(GCshCmdtyID.Type.SECURITY, commCurrPrs.getType());
		assertEquals(GCshSecID.SubType.GENERAL, commCurrPrs.getSubType());
		assertEquals("XFRA:SAP", commCurrPrs.toString());
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
		GCshSecID commCurrPrs = GCshSecID.parse("FUXNSTUELL:BURP"); // Wrong, but not check on this level
		GCshSecID commCurrRef = new GCshSecID();
		commCurrRef.setType(GCshCmdtyID.Type.SECURITY);
		commCurrRef.setSubType(GCshSecID.SubType.GENERAL);
		commCurrRef.setNameSpace("FUXNSTUELL");
		commCurrRef.setCode("BURP");

		assertEquals(GCshCmdtyID.Type.SECURITY, commCurrPrs.getType());
		assertEquals(GCshSecID.SubType.GENERAL, commCurrPrs.getSubType());
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
}

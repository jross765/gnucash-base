package org.gnucash.base.tuples;

import org.gnucash.base.basetypes.simple.GCshAcctID;

import xyz.schnorxoborx.base.numbers.FixedPointNumber;

@Deprecated
public record AcctIDAmountFPPair(GCshAcctID accountID, FixedPointNumber amount) {

	private final static double UNSET_VALUE = -999999;
	private final static int    SCALE       = 2;
	
	// ---------------------------------------------------------------
	
	@Deprecated
	public boolean isNotNull() {
		if ( accountID == null)
			return false;
		
		if ( amount == null)
			return false;
		
		return true;
	}

	@Deprecated
	public boolean isSet() {
		return accountID.isSet() && ( amount.doubleValue() != UNSET_VALUE );
	}

	// ---------------------------------------------------------------
	
	@Deprecated
	@Override
	public String toString() {
		return "AcctIDAmountFPPair [account-id=" + accountID + 
								  ", amount=" + String.format("%." + SCALE + "f", amount.doubleValue() ) + "]";
	}

}

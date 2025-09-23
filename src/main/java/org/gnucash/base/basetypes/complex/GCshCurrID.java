package org.gnucash.base.basetypes.complex;

import java.util.Currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A fully-qualified currency ID (name space "CURRENCY").
 */
public class GCshCurrID extends GCshCmdtyCurrID {

	private static final Logger LOGGER = LoggerFactory.getLogger(GCshCurrID.class);

	// ---------------------------------------------------------------

	private Currency currency;

	// ---------------------------------------------------------------

	public GCshCurrID() {
		super();
		type = Type.CURRENCY;
	}

	public GCshCurrID(Currency curr) {

		super(GCshCmdtyCurrNameSpace.CURRENCY, curr.getCurrencyCode());

		setType(Type.CURRENCY);
		setCurrency(curr);
	}

	public GCshCurrID(String currStr) {

		super(GCshCmdtyCurrNameSpace.CURRENCY, currStr);

		setType(Type.CURRENCY);
		setCurrency(currStr);
	}

	public GCshCurrID(String nameSpaceFree, String code) {

		super(nameSpaceFree, code);

		if ( getType() != Type.CURRENCY )
			throw new InvalidCmdtyCurrTypeException();

		setType(Type.CURRENCY);
		setCurrency(code);
	}

	public GCshCurrID(GCshCmdtyCurrID cmdtyCurrID) {

		super(cmdtyCurrID.getNameSpace(), cmdtyCurrID.getCode());

		if ( getType() != Type.CURRENCY )
			throw new InvalidCmdtyCurrTypeException();

		setType(Type.CURRENCY);
		setCurrency(code);
	}

	// ---------------------------------------------------------------

	@Override
	public void setType(Type type) {
//        if ( type != Type.CURRENCY )
//            throw new InvalidCmdtyCurrIDException();

		super.setType(type);
	}

	// ----------------------------

	public Currency getCurrency() {
		if ( type != Type.CURRENCY )
			throw new InvalidCmdtyCurrTypeException();

		return currency;
	}

	public void setCurrency(Currency currency) {
		if ( type != Type.CURRENCY )
			throw new InvalidCmdtyCurrTypeException();

		if ( currency == null )
			throw new IllegalArgumentException("Argument currency is null");

		this.currency = currency;
	}

	public void setCurrency(String iso4217CurrCode) {
		if ( iso4217CurrCode == null )
			throw new IllegalArgumentException("Argument string is null");

		setCurrency(Currency.getInstance(iso4217CurrCode));
	}

	// ---------------------------------------------------------------

	public static GCshCurrID parse(String str) {
		if ( str == null )
			throw new IllegalArgumentException("Argument string is null");

		if ( str.equals("") )
			throw new IllegalArgumentException("Argument string is empty");

		GCshCurrID result = new GCshCurrID();

		int posSep = str.indexOf(SEPARATOR);
		// Plausi ::MAGIC
		if ( posSep <= 3 || 
			 posSep >= str.length() - 2 )
			throw new InvalidCmdtyCurrIDException();

		String nameSpaceLoc = str.substring(0, posSep).trim();
		String currSecCodeLoc = str.substring(posSep + 1, str.length()).trim();

		if ( nameSpaceLoc.equals(GCshCmdtyCurrNameSpace.CURRENCY) ) {
			result.setType(Type.CURRENCY);
			result.setNameSpace(nameSpaceLoc);
			result.setCode(currSecCodeLoc);
			result.setCurrency(Currency.getInstance(currSecCodeLoc));
		} else {
			throw new InvalidCmdtyCurrIDException();
		}

		return result;
	}

	// ---------------------------------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		GCshCurrID other = (GCshCurrID) obj;
		if ( type != other.type )
			return false;
		if ( currency == null ) {
			if ( other.currency != null )
				return false;
		} else if ( !currency.equals(other.currency) )
			return false;
		return true;
	}

	// ---------------------------------------------------------------

	@Override
	public String toString() {
		return toStringShort();
	}

	@Override
	public String toStringShort() {
		if ( type != Type.CURRENCY )
			return "ERROR";

		String result = GCshCmdtyCurrNameSpace.CURRENCY.toString() + SEPARATOR + currency.getCurrencyCode();

		return result;
	}

	@Override
	public String toStringLong() {
		if ( type != Type.CURRENCY )
			return "ERROR";

		String result = "GCshCurrID [";

		result += "nameSpace='" + getNameSpace() + "'";
		result += ", secCode='" + getCode() + "'";

		try {
			result += ", currency='" + getCurrency().getCurrencyCode() + "'";
		} catch (InvalidCmdtyCurrTypeException e) {
			result += ", currency=" + "ERROR";
		}

		result += "]";

		return result;
	}

}

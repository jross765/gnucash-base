package org.gnucash.base.basetypes.complex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A fully-qualified (real) commodity ID (name space
 * {@link GCshCmdtyNameSpace.Exchange}).
 */
public class GCshSecID_Exchange extends GCshSecID {

	private static final Logger LOGGER = LoggerFactory.getLogger(GCshSecID_Exchange.class);

	// ---------------------------------------------------------------

	private GCshCmdtyNameSpace.Exchange exchange;

	// ---------------------------------------------------------------

	public GCshSecID_Exchange() {
		super();
		// setType(Type.SECURITY);
		setSubType(SubType.EXCHANGE);
		setExchange(GCshCmdtyNameSpace.Exchange.UNSET);
	}

	public GCshSecID_Exchange(GCshCmdtyNameSpace.Exchange exchange, String secCode) {
		super(exchange.toString(), secCode);

		// setType(Type.SECURITY);
		setSubType(SubType.EXCHANGE);
		setExchange(exchange);
	}

	public GCshSecID_Exchange(String exchangeStr, String code) {
		super(exchangeStr, code);

		// setType(Type.SECURITY);
		setSubType(SubType.EXCHANGE);
		setExchange(exchangeStr);
	}

	// ---------------------------------------------------------------

	@Override
	public void setType(Type type) {
//        if ( type != Type.SECURITY_EXCHANGE )
//            throw new InvalidCmdtyCurrIDException();

		super.setType(type);
	}

	// ----------------------------

	public GCshCmdtyNameSpace.Exchange getExchange() {
		if ( type != Type.SECURITY )
			throw new InvalidCmdtyTypeException();

		if ( subType != SubType.EXCHANGE )
			throw new InvalidCmdtySubTypeException();

		return exchange;
	}

	public void setExchange(GCshCmdtyNameSpace.Exchange exchange) {
		if ( type != Type.SECURITY )
			throw new InvalidCmdtyTypeException();

		if ( subType != SubType.EXCHANGE )
			throw new InvalidCmdtySubTypeException();

		this.exchange = exchange;
	}

	public void setExchange(String exchangeStr) {
		if ( exchangeStr == null )
			throw new IllegalArgumentException("Exchange string is null");

		if ( exchangeStr.trim().equals("") )
			throw new IllegalArgumentException("Exchange string is empty");

		setExchange(GCshCmdtyNameSpace.Exchange.valueOf(exchangeStr.trim()));
	}

	// ---------------------------------------------------------------

	public static GCshSecID_Exchange parse(String str) {
		if ( str == null )
			throw new IllegalArgumentException("Argument string is null");

		if ( str.equals("") )
			throw new IllegalArgumentException("Argument string is empty");

		GCshSecID_Exchange result = new GCshSecID_Exchange();

		int posSep = str.indexOf(SEPARATOR);
		// Plausi ::MAGIC
		if ( posSep <= 3 || 
			 posSep >= str.length() - 2 )
			throw new InvalidCmdtyIDException();

		String nameSpaceLoc = str.substring(0, posSep).trim();
		String currSecCodeLoc = str.substring(posSep + 1, str.length()).trim();

		if ( nameSpaceLoc.equals(GCshCmdtyNameSpace.CURRENCY) ) {
			throw new InvalidCmdtyTypeException();
		} else {
			result.setType(Type.SECURITY);
			result.setSubType(SubType.EXCHANGE);
			result.setNameSpace(nameSpaceLoc);
			result.setExchange(nameSpaceLoc);
			result.setCode(currSecCodeLoc);
		}

		return result;
	}

	// ---------------------------------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((nameSpace == null) ? 0 : nameSpace.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((exchange == null) ? 0 : exchange.hashCode());
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
		GCshSecID_Exchange other = (GCshSecID_Exchange) obj;
		if ( type != other.type )
			return false;
		if ( exchange != other.exchange )
			return false;
		if ( nameSpace == null ) {
			if ( other.nameSpace != null )
				return false;
		} else if ( !nameSpace.equals(other.nameSpace) )
			return false;
		if ( code == null ) {
			if ( other.code != null )
				return false;
		} else if ( !code.equals(other.code) )
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
		if ( type != Type.SECURITY )
			return "ERROR";

		if ( subType != SubType.EXCHANGE )
			return "ERROR";

		String result = exchange.toString() + SEPARATOR + code;

		return result;
	}

	@Override
	public String toStringLong() {
		if ( type != Type.SECURITY )
			return "ERROR";

		if ( subType != SubType.EXCHANGE )
			return "ERROR";

		String result = "GCshCmdtyID_Exchange [";

		result += "namespace='" + getNameSpace() + "'";

		try {
			result += ", exchange='" + getExchange() + "'";
		} catch (InvalidCmdtyTypeException e) {
			result += ", exchange=" + "ERROR";
		}

		result += ", secCode='" + getCode() + "'";

		result += "]";

		return result;
	}

}

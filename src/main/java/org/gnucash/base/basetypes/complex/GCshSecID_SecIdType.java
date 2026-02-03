package org.gnucash.base.basetypes.complex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A fully-qualified (real) commodity ID (name space
 * {@link GCshCmdtyNameSpace.SecIdType}).
 */
public class GCshSecID_SecIdType extends GCshSecID {

	private static final Logger LOGGER = LoggerFactory.getLogger(GCshSecID_SecIdType.class);

	// ---------------------------------------------------------------

	private GCshCmdtyNameSpace.SecIdType secIdType;

	// ---------------------------------------------------------------

	public GCshSecID_SecIdType() {
		super();
		
		// setType(Type.SECURITY);
		setSubType(SubType.SECIDTYPE);
		setSecIdType(GCshCmdtyNameSpace.SecIdType.UNSET);
	}

	public GCshSecID_SecIdType(GCshCmdtyNameSpace.SecIdType secIdType, String secCode) {
		super(secIdType.toString(), secCode);

		// setType(Type.SECURITY);
		setSubType(SubType.SECIDTYPE);
		setSecIdType(secIdType);
	}

	public GCshSecID_SecIdType(String secIDTypeStr, String code) {
		super(secIDTypeStr, code);

		// setType(Type.SECURITY);
		setSubType(SubType.SECIDTYPE);
		setSecIdType(secIDTypeStr);
	}

	// ---------------------------------------------------------------

	@Override
	public void setType(Type type) {
//        if ( type != Type.SECURITY_SECIDTYPE )
//            throw new InvalidCmdtyCurrIDException();

		super.setType(type);
	}

	// ----------------------------

	public GCshCmdtyNameSpace.SecIdType getSecIdType() {
		if ( type != Type.SECURITY )
			throw new InvalidCmdtyTypeException();

		if ( subType != SubType.SECIDTYPE )
			throw new InvalidCmdtySubTypeException();

		return secIdType;
	}

	public void setSecIdType(GCshCmdtyNameSpace.SecIdType secIdType) {
		if ( type != Type.SECURITY )
			throw new InvalidCmdtyTypeException();

		if ( subType != SubType.SECIDTYPE )
			throw new InvalidCmdtySubTypeException();

		this.secIdType = secIdType;
	}

	public void setSecIdType(String secIdTypeStr) {
		if ( secIdTypeStr == null )
			throw new IllegalArgumentException("Security ID type string is null");

		if ( secIdTypeStr.trim().equals("") )
			throw new IllegalArgumentException("Security ID type string is empty");

		setSecIdType(GCshCmdtyNameSpace.SecIdType.valueOf(secIdTypeStr.trim()));
	}

	// ---------------------------------------------------------------
	
	public void set(GCshSecID_SecIdType val) {
		setType(val.getType());
		setSecIdType(val.getSecIdType());
		setCode(val.getCode());
	}

	// ---------------------------------------------------------------

	public static GCshSecID_SecIdType parse(String str) {
		if ( str == null )
			throw new IllegalArgumentException("Argument string is null");

		if ( str.equals("") )
			throw new IllegalArgumentException("Argument string is empty");

		GCshSecID_SecIdType result = new GCshSecID_SecIdType();

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
			result.setSubType(SubType.SECIDTYPE);
			result.setNameSpace(nameSpaceLoc);
			result.setSecIdType(nameSpaceLoc);
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
		result = prime * result + ((secIdType == null) ? 0 : secIdType.hashCode());
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
		GCshSecID_SecIdType other = (GCshSecID_SecIdType) obj;
		if ( type != other.type )
			return false;
		if ( secIdType != other.secIdType )
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

		if ( subType != SubType.SECIDTYPE )
			return "ERROR";

		String result = secIdType.toString() + SEPARATOR + code;

		return result;
	}

	@Override
	public String toStringLong() {
		if ( type != Type.SECURITY )
			return "ERROR";

		if ( subType != SubType.SECIDTYPE )
			return "ERROR";

		String result = "GCshCmdtyID_SecIdType [";

		result += "namespace='" + getNameSpace() + "'";

		try {
			result += ", secidtype='" + getSecIdType() + "'";
		} catch (InvalidCmdtyTypeException e) {
			result += ", secidtype=" + "ERROR";
		}

		result += ", secCode='" + getCode() + "'";

		result += "]";

		return result;
	}

}

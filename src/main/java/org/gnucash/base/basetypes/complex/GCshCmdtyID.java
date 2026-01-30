package org.gnucash.base.basetypes.complex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A fully-qualified (real) commodity ID (name space can be freely chosen). More
 * specific ones are:
 * <ul>
 * <li>{@link GCshCmdtyID_Exchange}</li>
 * <li>{@link GCshCmdtyID_MIC}</li>
 * <li>{@link GCshCmdtyID_SecIdType}</li>
 * </ul>
 */
public class GCshCmdtyID extends GCshCmdtyCurrID {

    public enum SubType {
    	EXCHANGE,  // name space is semi-formal abbrev. of major world exchange
    	MIC,       // name space is formal abbrev. of major world exchange (ISO 10383)
    	SECIDTYPE, // name space is widely-used security ID type/scheme (ISIN, CUSIP, SEDOL, WKN, ...)
    	GENERAL,   // name space can be freely chosen
    	UNSET
    }
    
	// ---------------------------------------------------------------

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(GCshCmdtyID.class);

	// ---------------------------------------------------------------

	protected SubType subType;

	// ---------------------------------------------------------------

	public GCshCmdtyID() {
		super();
		
		setType(Type.SECURITY);
		setSubType (SubType.GENERAL);
	}

	public GCshCmdtyID(String nameSpaceFree, String code) {
		super(nameSpaceFree, code);
		
		if ( getType() != Type.SECURITY )
			throw new InvalidCmdtyCurrTypeException();
		
		setType(Type.SECURITY);
		setSubType (SubType.GENERAL);
	}

	public GCshCmdtyID(GCshCmdtyCurrID cmdtyCurrID) {
		super(cmdtyCurrID.getNameSpace(), cmdtyCurrID.getCode());

		if ( getType() != Type.SECURITY )
			throw new InvalidCmdtyCurrTypeException();
		
		setType(Type.SECURITY);
		setSubType (SubType.GENERAL);
	}

	// ---------------------------------------------------------------

//	@Override
//	public void setType(Type type) {
//        if ( type != Type.SECURITY )
//            throw new InvalidCmdtyCurrIDException();
//
//		super.setType(type);
//	}

    public SubType getSubType() {
        return subType;
    }
    
	public void setSubType(SubType subType) {
		this.subType = subType;
	}

	// ---------------------------------------------------------------

	public static GCshCmdtyID parse(String str) {
		if ( str == null )
			throw new IllegalArgumentException("Argument string is null");

		if ( str.equals("") )
			throw new IllegalArgumentException("Argument string is empty");

		GCshCmdtyID result = new GCshCmdtyID();

		int posSep = str.indexOf(SEPARATOR);
		// Plausi ::MAGIC
		if ( posSep <= 3 || 
			 posSep >= str.length() - 2 )
			throw new InvalidCmdtyCurrIDException();

		String nameSpaceLoc = str.substring(0, posSep).trim();
		String currSecCodeLoc = str.substring(posSep + 1, str.length()).trim();

		if ( nameSpaceLoc.equals(GCshCmdtyCurrNameSpace.CURRENCY) ) {
			throw new InvalidCmdtyCurrIDException();
		} else {
			result.setType(Type.SECURITY);
			result.setNameSpace(nameSpaceLoc);
			result.setCode(currSecCodeLoc);
		}

		return result;
	}

	// ---------------------------------------------------------------

	// ::EMPTY

	// ---------------------------------------------------------------

	@Override
	public String toString() {
		return toStringShort();
	}

	@Override
	public String toStringShort() {
		if ( type != Type.SECURITY )
			return "ERROR";

		String result = super.toStringShort();

		return result;
	}

	@Override
	public String toStringLong() {
		if ( type != Type.SECURITY )
			return "ERROR";

		String result = "GCshCmdtyID [";

		result += "nameSpace='" + getNameSpace() + "', ";
		result += "subtype='"   + getSubType() + "', ";
		result += "secCode='" + getCode() + "'";

		result += "]";

		return result;
	}

}

package org.gnucash.base.basetypes.complex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A fully-qualified (real) commodity ID (name space
 * {@link GCshCmdtyNameSpace.MIC}).
 */
public class GCshSecID_MIC extends GCshSecID {

	private static final Logger LOGGER = LoggerFactory.getLogger(GCshSecID_MIC.class);

	// ---------------------------------------------------------------

	private GCshCmdtyNameSpace.MIC mic;

	// ---------------------------------------------------------------

	public GCshSecID_MIC() {
		super();
		
		// setType(Type.SECURITY);
		setSubType(SubType.MIC);
		setMIC(GCshCmdtyNameSpace.MIC.UNSET);
	}

	public GCshSecID_MIC(GCshCmdtyNameSpace.MIC mic, String secCode) {
		super(mic.toString(), secCode);

		// setType(Type.SECURITY);
		setSubType(SubType.MIC);
		setMIC(mic);
	}

	public GCshSecID_MIC(String micStr, String code) {
		super(micStr, code);

		// setType(Type.SECURITY);
		setSubType(SubType.MIC);
		setMIC(micStr);
	}

	// ---------------------------------------------------------------

	@Override
	public void setType(Type type) {
//        if ( type != Type.SECURITY_MIC )
//            throw new InvalidCmdtyCurrIDException();

		super.setType(type);
	}

	// ----------------------------

	public GCshCmdtyNameSpace.MIC getMIC() {
		if ( type != Type.SECURITY )
			throw new InvalidCmdtyTypeException();

		if ( subType != SubType.MIC )
			throw new InvalidCmdtySubTypeException();

		return mic;
	}

	public void setMIC(GCshCmdtyNameSpace.MIC mic) {
		if ( type != Type.SECURITY )
			throw new InvalidCmdtyTypeException();

		if ( subType != SubType.MIC )
			throw new InvalidCmdtySubTypeException();

		this.mic = mic;
	}

	public void setMIC(String micStr) {
		if ( micStr == null )
			throw new IllegalArgumentException("MIC string is null");

		if ( micStr.isBlank() )
			throw new IllegalArgumentException("MIC string is blank");

		setMIC(GCshCmdtyNameSpace.MIC.valueOf(micStr.trim()));
	}

	// ---------------------------------------------------------------

	public static GCshSecID_MIC parse(String str) {
		if ( str == null )
			throw new IllegalArgumentException("Argument string is null");

		if ( str.isBlank() )
			throw new IllegalArgumentException("Argument string is blank");

		GCshSecID_MIC result = new GCshSecID_MIC();

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
			result.setSubType(SubType.MIC);
			result.setNameSpace(nameSpaceLoc);
			result.setMIC(nameSpaceLoc);
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
		result = prime * result + ((mic == null) ? 0 : mic.hashCode());
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
		GCshSecID_MIC other = (GCshSecID_MIC) obj;
		if ( type != other.type )
			return false;
		if ( mic != other.mic )
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

		if ( subType != SubType.MIC )
			return "ERROR";

		String result = mic.toString() + SEPARATOR + code;

		return result;
	}

	@Override
	public String toStringLong() {
		if ( type != Type.SECURITY )
			return "ERROR";

		if ( subType != SubType.MIC )
			return "ERROR";

		String result = "GCshSecID_MIC [";

		result += "namespace='" + getNameSpace() + "'";

		try {
			result += ", mic='" + getMIC() + "'";
		} catch (InvalidCmdtyTypeException e) {
			result += ", mic=" + "ERROR";
		}

		result += ", secCode='" + getCode() + "'";

		result += "]";

		return result;
	}

}

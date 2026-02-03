package org.gnucash.base.basetypes.complex;

import java.util.Currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A fully-qualified (real) commodity ID <strong>or</strong> currency ID 
 * (the most generic type of this kind). The name space is unspecified.
 * More specific ones are: 
 * <ul>
 *   <li>{@link GCshSecID} and its children</li>
 *   <li>{@link GCshCurrID}</li>
 * </ul>
 * <br>
 * Please note that your normally should not use this class when
 * creating instances -- it is, however, advisable to use it as a 
 * type for objects returned by methods in case it is not not (yet)
 * clear of which more specific type it is. 
 * 
 * @see GCshSecID
 * @see GCshCurrID
 */
public class GCshCmdtyID {
    
    // https://github.com/GnuCash/gnucash/blob/stable/libgnucash/engine/gnc-commodity.h#L108
    // We do not use the GnuCash-internally used "NONCURRENCY"
    public enum Type {
    	CURRENCY,
    	SECURITY,
    	UNSET
    }
    
    // ---------------------------------------------------------------
    
    @SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(GCshCmdtyID.class);

    public static final char SEPARATOR = ':';

    // ---------------------------------------------------------------

    protected Type    type;
    protected String  nameSpace;
    protected String  code;

    // ---------------------------------------------------------------
    
    public GCshCmdtyID() {
    	this.type = Type.UNSET;
    }

    public GCshCmdtyID(String nameSpaceFree, String code) {
    	if ( nameSpaceFree == null )
    		throw new IllegalArgumentException("Name space is null");

    	if ( nameSpaceFree.trim().equals("") )
    		throw new IllegalArgumentException("Name space is empty");

    	if ( code == null )
    		throw new IllegalArgumentException("Security code is null");

    	if ( code.trim().equals("") )
    		throw new IllegalArgumentException("Security code is empty");

    	if ( nameSpaceFree.trim().equals(GCshCmdtyNameSpace.CURRENCY) ) {
    		this.type = Type.CURRENCY;
    	} else {
    		this.type = Type.SECURITY;
    	}

    	setNameSpace(nameSpaceFree.trim());
    	setCode(code.trim());
    }

//    public CmdtyCurrID(String nameSpaceFree, String code) {
//	
//	if ( nameSpaceFree == null )
//	    throw new IllegalArgumentException("Name space is null");
//
//	if ( nameSpaceFree.trim().equals("") )
//	    throw new IllegalArgumentException("Name space is empty");
//	
//	if ( nameSpaceFree.trim().equals(CmdtyCurrNameSpace.CURRENCY) ) {
//	    this.type = Type.CURRENCY;
//	    setCurrency(code);
//	    this.exchange      = CmdtyCurrNameSpace.Exchange.UNSET;
//	    this.secCode       = null;
//	    this.nameSpaceFree = null;
//	} else {
//	    this.type = Type.SECURITY_GENERAL;
//	    setNameSpaceFree(nameSpaceFree);
//	    setSecCode(code);
//	    this.currency = null;
//	    this.exchange = CmdtyCurrNameSpace.Exchange.UNSET;
//	}
//    }

    public GCshCmdtyID(GCshSecID cmdtyID) {
    	if ( cmdtyID == null )
    		throw new IllegalArgumentException("argument <cmdtyID> is null");

    	if ( ! cmdtyID.isSet() )
    		throw new IllegalArgumentException("argument <cmdtyID> is not set");

    	setNameSpace(cmdtyID.getNameSpace());
    	setCode(cmdtyID.getCode());
    }

    public GCshCmdtyID(GCshCurrID currID) {
    	if ( currID == null )
    		throw new IllegalArgumentException("argument <currID> is null");

    	if ( ! currID.isSet() )
    		throw new IllegalArgumentException("argument <currID> is not set");

    	setNameSpace(currID.getNameSpace());
    	setCode(currID.getCode());
    }

    public GCshCmdtyID(Currency curr) {
    	if ( curr == null )
    		throw new IllegalArgumentException("argument <curr> is null");

    	if ( curr.getSymbol().equals("") )
    		throw new IllegalArgumentException("argument <curr> is empty");

    	setNameSpace(GCshCmdtyNameSpace.CURRENCY);
    	setCode(curr.getCurrencyCode());
    }

    // ---------------------------------------------------------------
    
    public boolean isSet() {
    	if ( this.type == Type.UNSET )
    		return false;
    	
    	if ( this.code.trim().equals("") )
    		return false;
    	
    	return true;
    }

    public void reset() {
    	this.type = Type.UNSET;
    	this.code = "";
    }

    // ---------------------------------------------------------------

    public Type getType() {
        return type;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    public String getNameSpace() {
        return nameSpace;
    }
    
    public void setNameSpace(String nameSpace) {
	if ( nameSpace == null )
	    throw new IllegalArgumentException("Name space is null");

	if ( nameSpace.trim().equals("") )
	    throw new IllegalArgumentException("Name space is empty");

        this.nameSpace = nameSpace.trim();
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String secCode) {
	if ( secCode == null )
	    throw new IllegalArgumentException("Security code is null");

	if ( secCode.trim().equals("") )
	    throw new IllegalArgumentException("Security code is empty");

        this.code = secCode.trim();
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
	    throw new InvalidCmdtyIDException();
	
	String nameSpaceLoc = str.substring(0, posSep).trim();
	String currSecCodeLoc = str.substring(posSep + 1, str.length()).trim();
	
	if ( nameSpaceLoc.equals(GCshCmdtyNameSpace.CURRENCY) ) {
	    result.setType(Type.CURRENCY);
	    result.setNameSpace(GCshCmdtyNameSpace.CURRENCY);
	    result.setCode(currSecCodeLoc);
	} else {
	    result.setType(Type.SECURITY);
	    result.setNameSpace(nameSpaceLoc);
	    result.setCode(currSecCodeLoc);
	}
	
	return result;
    }
    
    // ---------------------------------------------------------------

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nameSpace == null) ? 0 : nameSpace.hashCode());
	result = prime * result + ((code == null) ? 0 : code.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	GCshCmdtyID other = (GCshCmdtyID) obj;
	if (type != other.type)
	    return false;
	if (nameSpace == null) {
	    if (other.nameSpace != null)
		return false;
	} else if (!nameSpace.equals(other.nameSpace))
	    return false;
	if (code == null) {
	    if (other.code != null)
		return false;
	} else if (!code.equals(other.code))
	    return false;
	return true;
    }

    // ---------------------------------------------------------------
    
    @Override
    public String toString() {
    	return toStringShort();
    }

    public String toStringShort() {
    	String result = nameSpace + SEPARATOR + code;

    	return result;
    }

    public String toStringLong() {
    	String result = "GCshCmdtyCurrID [";
	
    	result += "type='"      + getType() + "', ";
    	result += "nameSpace='" + getNameSpace() + "', ";
    	result += "code='"      + getCode() + "'";
	
    	result += "]";
	
    	return result;
    }

}

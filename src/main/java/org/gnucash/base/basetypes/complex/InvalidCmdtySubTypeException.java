package org.gnucash.base.basetypes.complex;

public class InvalidCmdtySubTypeException extends RuntimeException {

    private static final long serialVersionUID = 203721830295848011L;

    // ---------------------------------------------------------------
    
	public InvalidCmdtySubTypeException() {
		super();
    }

    public InvalidCmdtySubTypeException(String msg) {
    	super(msg);
    }

}

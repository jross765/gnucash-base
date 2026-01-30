package org.gnucash.base.basetypes.complex;

public class InvalidCmdtyCurrSubTypeException extends RuntimeException {

    private static final long serialVersionUID = 203721830295848011L;

    // ---------------------------------------------------------------
    
	public InvalidCmdtyCurrSubTypeException() {
		super();
    }

    public InvalidCmdtyCurrSubTypeException(String msg) {
    	super(msg);
    }

}

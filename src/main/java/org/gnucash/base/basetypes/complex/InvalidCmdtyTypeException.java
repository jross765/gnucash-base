package org.gnucash.base.basetypes.complex;

public class InvalidCmdtyTypeException extends RuntimeException {

    private static final long serialVersionUID = 6595261905782442716L;
    
    // ---------------------------------------------------------------
    
    public InvalidCmdtyTypeException() {
    	super();
    }

    public InvalidCmdtyTypeException(String msg) {
    	super(msg);
    }

}

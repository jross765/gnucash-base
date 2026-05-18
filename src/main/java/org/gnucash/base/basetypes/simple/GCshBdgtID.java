package org.gnucash.base.basetypes.simple;

import java.util.UUID;

public class GCshBdgtID extends GCshID {

    public GCshBdgtID() {
    	super();
    }

    public GCshBdgtID(String idStr) throws InvalidGCshIDException {
   		super(idStr);
    }

    public GCshBdgtID(UUID uuid) throws InvalidGCshIDException {
   		super(uuid);
    }

    public GCshBdgtID(GCshID id) {
    	super();
    	try {
    		set(id);
    	} catch ( Exception exc ) {
    		// We do not want to throw an exception here, 
    		// and it is unnecessary anyway.
    		// This branch can only be reached theoretically.
    		System.err.println("GCshBdgtID: Severe error: Copy constructor");
    	}
    }

}

package com.acme.ejb;

import javax.ejb.Local;

@Local
public interface HolaLocal {
	
	String mensaje();
	
}

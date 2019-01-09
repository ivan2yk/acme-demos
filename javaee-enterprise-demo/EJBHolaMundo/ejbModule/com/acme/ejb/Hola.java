package com.acme.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Hola
 */
@Stateless
public class Hola implements HolaLocal {

    /**
     * Default constructor. 
     */
    public Hola() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String mensaje(){
    	return "Hola desde un EJB de session";
    }
    

}

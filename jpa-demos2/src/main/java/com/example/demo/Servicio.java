package com.example.demo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Servicio {

    @PersistenceContext
    private EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Persona consulta(long id) {
        Persona persona = em.find(Persona.class, id);
        return persona;
    }

    public Persona getReference(long id) {
        Persona reference = em.getReference(Persona.class, id);
        logger.info("End getReference");
        return reference;
    }

    //debio a la tx realiza un flush automatico y actualiza el campo 'nombre'
    //@Transactional(readOnly = true) - deshabilita el flush automatico
    public void modifica(long id, String nombre) {
        Persona persona = consulta(id);
        persona.setNombre(nombre);
    }

    public Persona crea(Persona persona) {
        em.persist(persona);
        return persona;
    }

}

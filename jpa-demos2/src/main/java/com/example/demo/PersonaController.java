package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("personas")
public class PersonaController {

    @Autowired
    private Servicio servicio;

    @Value("${spring.jpa.open-in-view}")
    private String openInView;

    @GetMapping(path = "/consulta/{id}")
    public boolean consulta(@PathVariable long id) {
        log();
        Persona primera = servicio.consulta(id);
        System.out.println("primera : " + primera);

        Persona segunda = servicio.consulta(id);
        System.out.println("segunda : " + segunda);

        System.out.println("primera == segunda: " + (primera == segunda));

        return primera == segunda;
    }

    @GetMapping(path = "/{id}")
    public PersonaDto get(@PathVariable long id) {
        log();
        Persona persona = servicio.getReference(id);

        PersonaDto personaDto = new PersonaDto();
        personaDto.setNombre(persona.getNombre());
        personaDto.setApellido(persona.getApellido());

        return personaDto;
    }

    @PostMapping
    public PersonaDto create(@RequestBody PersonaDto personaDto){
        Persona persona = new Persona();
        persona.setNombre(personaDto.getNombre());
        servicio.crea(persona);
        personaDto.setId(persona.getId());
        return personaDto;
    }

    @PatchMapping("/{id}")
    public PersonaDto modifica(@PathVariable long id, @RequestBody PersonaDto personaDto) {
        log();
        servicio.modifica(id, personaDto.getNombre());

        Persona consulta = servicio.consulta(id);

        PersonaDto personaDto1 = new PersonaDto();
        personaDto1.setId(consulta.getId());
        personaDto1.setNombre(consulta.getNombre());
        personaDto1.setApellido(consulta.getApellido());

        return personaDto1;

    }

    private void log() {
        System.out.println("------- usando : spring.jpa.open-in-view=" + openInView);
    }

}

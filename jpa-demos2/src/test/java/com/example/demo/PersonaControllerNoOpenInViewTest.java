package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("nopeninview")
public class PersonaControllerNoOpenInViewTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void exists() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/personas/consulta/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void consulta() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/personas/1"))
        .andExpect(MockMvcResultMatchers.status().is(500));

    }

    @Test
    public void modifica() throws Exception {

        PersonaDto personaDto = new PersonaDto();
        personaDto.setNombre("Juana Maria");
        personaDto.setApellido("Diaz Torres");

        mockMvc.perform(MockMvcRequestBuilders.patch("/personas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(personaDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(personaDto.getNombre()));

    }

}
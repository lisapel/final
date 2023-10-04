package groupassignment.controller;

import groupassignment.SmokeTests;
import groupassignment.repository.PersonRepository;
import groupassignment.model.Person;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Tag("IntegrationTest")
@WebMvcTest(PersonController.class)
class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonRepository personRepository;



    @BeforeEach
    void init() throws ServletException {

        Person person = new Person("Tobias");
        Person person2 = new Person("Lisa");
        Person person3 = new Person("Lemonia");

        personRepository.save(person);
        personRepository.save(person2);
        personRepository.save(person3);
        when(personRepository.findAll()).thenReturn(Arrays.asList(person, person2, person3));
    }


    @Test
    void whenCallingSayHelloThenReturnHello() throws Exception {
        this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Hello")));
    }


    @Test
    void whenGetAllPersonsThenReturnPersonNames() throws Exception {
        this.mockMvc.perform(get("/all")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Lisa")));
    }

    @Test
    void whenAddingNewPersonThenReturnNameOfPerson() throws Exception {
        String jsonRequestBody = "{\"name\":\"Alice\"}";

        mockMvc.perform(post("/add")
                        .contentType("application/json")
                        .content(jsonRequestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name", is("Alice")));
    }


}
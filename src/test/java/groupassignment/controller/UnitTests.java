package groupassignment.controller;

import groupassignment.model.Person;
import groupassignment.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
class UnitTests {

    @MockBean
    PersonRepository personRepository;

    @BeforeEach
    void init() {
        Person person = new Person("Tobias");
        Person person2 = new Person("Lisa");
        Person person3 = new Person("Lemonia");

        personRepository.save(person);
        personRepository.save(person2);
        personRepository.save(person3);

        when(personRepository.findAll()).thenReturn(Arrays.asList(person, person2, person3));
    }

    @Test
    void whenGetAllPersonsThenReturnCorrectSize() {
        assertThat(personRepository.findAll(), hasSize(3));
    }
}
package ua.in.lbn.sb2.rest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import ua.in.lbn.sb2.FlywayIT;
import ua.in.lbn.sb2.domain.Person;
import ua.in.lbn.sb2.service.PersonService;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PersonRestIT extends FlywayIT {

    private static final Logger log = LoggerFactory.getLogger(PersonRestIT.class);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PersonService personService;

    @Test
    void all() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/" + PersonRest.PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
        ;
    }

    @Test
    void create() throws Exception {
        String json = "" +
                "{\n" +
                "    \"name\": \"C-name\"\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/" + PersonRest.PATH)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(4)))
        ;
    }

    @Test
    void createWithNewLocations() throws Exception {
        String json = "" +
                "{\n" +
                "    \"name\": \"C-name\",\n" +
                "    \"locations\": [\n" +
                "        {\n" +
                "            \"zip\": 5,\n" +
                "            \"city\": \"C-city\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/" + PersonRest.PATH)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.locations", hasSize(1)))
        ;
    }

    @Test
    void read() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/" + PersonRest.PATH + "/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.locations", hasSize(1)))
        ;
    }

    @Test
    void update() throws Exception {
        String json = "" +
                "{\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"U-name\",\n" +
                "    \"locations\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"zip\": 1,\n" +
                "            \"city\": \"U-city-1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"zip\": 5,\n" +
                "            \"city\": \"U-city-5\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.put("/" + PersonRest.PATH + "/{id}", "1")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.locations", hasSize(3)))
                .andExpect(jsonPath("$.locations[*].id", containsInAnyOrder(1, 2, 5)))
        ;

        Optional<Person> optionalPerson_Eva = personService.read(2);

        assertTrue(optionalPerson_Eva.isPresent());
        assertEquals("Eva", optionalPerson_Eva.get().getName());
        assertTrue(optionalPerson_Eva.get().getLocations().isEmpty());

        log.debug("person(2): {}", optionalPerson_Eva.orElseThrow(null));
    }

    @Test
    void updateWithLocationIds() throws Exception {
        String json = "" +
                "{\n" +
                "    \"name\": \"U-name\",\n" +
                "    \"locations\": [1,2]\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.put("/" + PersonRest.PATH + "/{id}", "1")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.locations", hasSize(2)))
                .andExpect(jsonPath("$.locations[*].id", containsInAnyOrder(1, 2)))
        ;

        Optional<Person> optionalPerson_Eva = personService.read(2);

        assertTrue(optionalPerson_Eva.isPresent());
        assertTrue(optionalPerson_Eva.get().getLocations().isEmpty());

        log.debug("person(2): {}", optionalPerson_Eva.orElseThrow(null));
    }

    @Test
    void updateNonExistingPerson() throws Exception {
        String json = "" +
                "{\n" +
                "    \"name\": \"U-name\",\n" +
                "    \"locations\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"zip\": 1,\n" +
                "            \"city\": \"U-city-1\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.put("/" + PersonRest.PATH + "/{id}", "111")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("No Person with id = 111")))
        ;
    }

    @Test
    void updateNonExistingLocation() throws Exception {
        String json = "" +
                "{\n" +
                "    \"name\": \"U-name\",\n" +
                "    \"locations\": [\n" +
                "        {\n" +
                "            \"id\": 111,\n" +
                "            \"zip\": 1,\n" +
                "            \"city\": \"U-city-1\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.put("/" + PersonRest.PATH + "/{id}", "1")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("No Location with id = 111")))
        ;
    }
}
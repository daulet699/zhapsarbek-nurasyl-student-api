package kz.iitu.devops.nurasyl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class StudentApiIntegrationTests {
    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper mapper;

    private static final String NURASYL = """
        {"fullName":"Nurasyl Zhapsarbek","email":"nurasyl.student@example.com",
         "groupName":"IT2-2308","studentNumber":"37841"}
        """;

    private long createStudent() throws Exception {
        String json = mvc.perform(post("/api/students")
            .contentType(MediaType.APPLICATION_JSON).content(NURASYL))
            .andExpect(status().isCreated())
            .andReturn().getResponse().getContentAsString();
        return mapper.readTree(json).get("id").asLong();
    }

    @Test void createStudentReturns201() throws Exception {
        mvc.perform(post("/api/students").contentType(MediaType.APPLICATION_JSON).content(NURASYL))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.fullName").value("Nurasyl Zhapsarbek"));
    }

    @Test void listStudentsReturns200() throws Exception {
        createStudent();
        mvc.perform(get("/api/students"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Nurasyl Zhapsarbek")));
    }

    @Test void getStudentByIdReturns200() throws Exception {
        long id = createStudent();
        mvc.perform(get("/api/students/{id}", id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.studentNumber").value("37841"));
    }

    @Test void updateStudentReturns200() throws Exception {
        long id = createStudent();
        mvc.perform(put("/api/students/{id}", id)
            .contentType(MediaType.APPLICATION_JSON).content("""
                {"fullName":"Nurasyl Zhapsarbek","email":"updated.student@example.com",
                 "groupName":"IT2-2308","studentNumber":"37841"}
                """))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email").value("updated.student@example.com"));
    }

    @Test void deleteStudentReturns204() throws Exception {
        long id = createStudent();
        mvc.perform(delete("/api/students/{id}", id)).andExpect(status().isNoContent());
        mvc.perform(get("/api/students/{id}", id)).andExpect(status().isNotFound());
    }

    @Test void missingStudentReturns404() throws Exception {
        mvc.perform(get("/api/students/999999"))
            .andExpect(status().isNotFound());
    }

    @Test void invalidRequestReturns400() throws Exception {
        mvc.perform(post("/api/students")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"fullName":"","email":"bad","groupName":"","studentNumber":""}
                """))
            .andExpect(status().isBadRequest());
    }

    @Test void infoIdentifiesStudent() throws Exception {
        mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.student").value("Nurasyl Zhapsarbek"));
    }
}

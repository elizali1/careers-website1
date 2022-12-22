import com.elevate.careerportal.User;
import com.elevate.careerportal.UserController;
import com.elevate.careerportal.UserDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(UserController.class)
@ContextConfiguration(locations="classpath:beans.xml")

public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserDAO UserDAO;
    @MockBean
    JdbcTemplate template;

    @Test
    void testWelcome(){
        try {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Welcome to the PeopleShores CareerPortal!"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getUserById() {
        when(UserDAO.getById(0)).thenReturn(new User(0, "Eliza", "Li", "abcdefg", "el@gmail.com"));

        try {
            mockMvc.perform(get("/users/0"))
                    .andExpect(MockMvcResultMatchers.jsonPath("email")
                            .value("el@gmail.com"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void makeNewUser() {
        User user = new User(0, "Eliza", "Li", "abcdefg", "el@gmail.com");
        when(UserDAO.addUsers(any(User.class))).thenReturn(user);
        try {
            mockMvc.perform(post("/register")
                            .content("""
                            {"firstName" : "Eliza"
                            "lastName" : "Li"
                            "password" : "abcdefg"
                            "email" : "el@gmail.com"
                            "hiringManager" : false}
                            """)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("userId").value(0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
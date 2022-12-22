import com.elevate.careerportal.DBUserDAO;
import com.elevate.careerportal.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(DBUserDAO.class)
@WebMvcTest(DBUserDAO.class)
@ContextConfiguration(locations="classpath:beans.xml")

public class DBUserDAOTest {

    @MockBean
    JdbcTemplate template;
    @Test
    public void returnUserByID(){
        DBUserDAO u = new DBUserDAO();
        User user = new User(0, "Shab","K","password","sk@gmail.com");
        ReflectionTestUtils.setField(u, "template", template);
        when(template.queryForObject(anyString(), any(RowMapper.class), any())).thenReturn(user);
        assertEquals(user, u.getById(0));
    }
}

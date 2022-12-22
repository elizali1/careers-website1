package com.elevate.careerportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

@Component
public class DBUserDAO implements UserDAO{
    @Autowired
    JdbcTemplate template;

    @Override
    public User addUsers(User users) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                                    "careerportal.user " +
                                    "(firstName,lastName,password," +
                                    "email,hiringManager) VALUES (?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, users.getFirstName());
                    ps.setString(2, users.getLastName());
                    ps.setString(3, users.getPassword());
                    ps.setString(4, users.getEmail());
                    ps.setBoolean(5, users.isHiringManager());
                    return ps;}
                ,keyHolder);
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO careerportal.user(userid) VALUES (?)");
            ps.setInt(1, users.getUserId());
            ps.setInt(2, (Integer)keyHolder.getKeys().get("userid"));
            return ps;});
        return getById((Integer)keyHolder.getKeys().get("id"));
    }

    @Override
    public User getById(int id) {
        User u = template.queryForObject(
                "SELECT userid, email, hiringManager, firstName, lastName  FROM careerportal.user where userid = ?",
                ((rs, rowNum) -> new User(rs.getInt("userid"),
                        rs.getString("email"),
                        rs.getString("hiringManager"),
                        rs.getString("firstName"),
                        rs.getString("lastName"))),
                id
        );
        return u;
    }

    @Override
    public User login(String email, String password) {
        User u = template.queryForObject(
                "SELECT userid, email, hiringManager, firstName, lastName  FROM careerportal.user WHERE (username = ? AND password = ?)",
                ((rs, rowNum) -> new User(rs.getInt("userid"),
                        rs.getString("email"),
                        rs.getString("hiringManager"),
                        rs.getString("firstName"),
                        rs.getString("lastName"))),
                email,password
        );
        return u;
    }

    @Override
    public User getUser() {
        return null;
    }


//    public boolean login(String email, String password)
////    {
//        String query;
//        boolean login = false;
//
//        try {
//            query = "SELECT (email, password) FROM User WHERE (username = ? AND password = ?)";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, email);
//            ps.setString(2, password);
//            ps.executeQuery();
//            ResultSet rs = ps.executeQuery();
//
//            String checkEmail = rs.getString(1);
//            String checkPassword = rs.getString(2);
//
//            if((checkEmail.equals(email)) && (checkPassword.equals(password)))
//            {
//                login = true;
//            }
//            else
//            {
//                login = false;
//            }
//
//            connection.close();
//        }

//        catch (Exception error) {
//            System.out.println("ERROR: " + error);
//        }

    //        return login;
//    }
    @Override
    public Collection<User> getAllUsers() {
//        return template.query("SELECT * FROM careerportal.user",
//                 (rs, rowNum) -> new User(rs.getInt("userid"), rs.) )
//                );

        return null;
    }

}

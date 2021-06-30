package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> getAll();

    User getById(int userId);

    User getByEmail(String email);

    void create(User user);

    void update(int id, User user);
}

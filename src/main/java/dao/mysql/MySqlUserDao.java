package dao.mysql;

import dao.RoleDao;
import dao.UserDao;
import lombok.SneakyThrows;
import model.Role;
import model.User;
import util.QueryConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static util.MySQLConnector.CONNECTION;

public class MySqlUserDao implements UserDao {

    private RoleDao roleDao;

    public MySqlUserDao() {
        roleDao = new MySqlRoleDao();
    }

    @Override
    @SneakyThrows
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = CONNECTION.prepareStatement(QueryConstants.GET_ALL_USERS);
             ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Role role = roleDao.getById(result.getInt("role_id"));
                    users.add(getUserFromResult(result, role));
            }
        }
        return users;
    }

    @Override
    @SneakyThrows
    public User getById(int userId) {
        User user = null;
        try (PreparedStatement statement = CONNECTION.prepareStatement(QueryConstants.GET_USER)) {
            statement.setInt(1, userId);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Role role = roleDao.getById(resultSet.getInt("role_id"));
                    user = getUserFromResult(resultSet, role);
                }
            }
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    @SneakyThrows
    public void create(User user) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(QueryConstants.CREATE_USER)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setInt(6, user.getRole().getId());
            statement.execute();
        }
    }

    @Override
    @SneakyThrows
    public void update(int previousId, User user) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(QueryConstants.UPDATE_USER)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, previousId);
            statement.execute();
        }
    }

    @SneakyThrows
    private static User getUserFromResult(ResultSet result, Role role) {
        User user = new User();
        user.setId(result.getInt("id"));
        user.setEmail(result.getString("email"));
        user.setPassword(result.getString("password"));
        user.setFirstName(result.getString("first_name"));
        user.setLastName(result.getString("last_name"));
        user.setRole(role);
        return user;
    }
}

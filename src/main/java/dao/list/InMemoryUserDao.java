package dao.list;

import dao.UserDao;
import model.Role;
import model.RoleName;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDao implements UserDao {

    private static final List<User> USERS = init();


    @Override
    public List<User> getAll() {
        return new ArrayList<>(USERS);
    }

    @Override
    public User getById(int userId) {
        return USERS.stream().filter(f -> f.getId() == userId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return USERS.stream().filter(f -> email.equals(f.getEmail()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void create(User user) {
        USERS.add(user);
    }

    @Override
    public void update(int id, User user) {

    }

    private static List<User> init() {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .id(1)
                .email("test@email.com")
                .firstName("Ivan")
                .lastName("Petrenko")
                .role(new Role(1, RoleName.USER))
                .password("testpassword")
                .build());
        return users;
    }
}

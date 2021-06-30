package service;

import dao.UserDao;
import model.LoginStatus;
import model.Role;
import model.RoleName;
import model.User;
import model.UserCreationResponse;
import model.UserInput;

import java.util.Comparator;

public class BaseUserService implements UserService{

    private UserDao userDao;

    public BaseUserService(UserDao userDao) {
        this.userDao = userDao;
    }



    @Override
    public UserCreationResponse create(UserInput user) {
        User byEmail = userDao.getByEmail(user.getEmail());
        if (byEmail != null) {
            return UserCreationResponse.builder().message("User with entered email already exist").build();
        }

        User latestUser = userDao.getAll().stream().max(Comparator.comparing(User::getId)).orElse(null);

        int id = latestUser == null ? 1 : latestUser.getId() + 1;
        Role role = new Role(1, RoleName.USER);

        User userToCreate = User.builder()
                .id(id)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(role)
                .build();


        userDao.create(userToCreate);

        return UserCreationResponse.builder().success(true).build();
    }


    @Override
    public LoginStatus authenticate(String email, String password) {
        User byEmail = userDao.getByEmail(email);

        if (byEmail == null) {
            return LoginStatus.USER_NOT_FOUND;
        }

        return password.equals(byEmail.getPassword()) ? LoginStatus.AUTHENTICATED : LoginStatus.INCORRECT_PASSWORD;
    }

}

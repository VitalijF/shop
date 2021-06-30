package service;

import model.LoginStatus;
import model.UserCreationResponse;
import model.UserInput;

public interface UserService {

    UserCreationResponse create(UserInput user);

    LoginStatus authenticate(String email, String password);
}

package bg.softuni.mapping.service.user;

import bg.softuni.mapping.dtos.UserLoginDto;
import bg.softuni.mapping.dtos.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto user);

    void loginUser(UserLoginDto user);

    void logoutUser();

    Boolean isUserLogedIn();
}

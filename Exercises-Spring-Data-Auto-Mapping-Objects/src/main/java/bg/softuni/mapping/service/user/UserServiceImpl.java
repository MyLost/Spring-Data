package bg.softuni.mapping.service.user;

import bg.softuni.mapping.adapters.UserAdapter;
import bg.softuni.mapping.dtos.UserDto;
import bg.softuni.mapping.dtos.UserLoginDto;
import bg.softuni.mapping.dtos.UserRegisterDto;
import bg.softuni.mapping.entities.UserEntity;
import bg.softuni.mapping.providers.CurrentUserProvider;
import bg.softuni.mapping.repository.UserRepository;
import org.springframework.stereotype.Service;

import static bg.softuni.mapping.constants.Validations.PASSWORD_NOT_VALID_LOGIN_MESSAGE;

@Service
public class UserServiceImpl implements UserService {

    private UserAdapter userAdapter;

    private final UserRepository userRepository;

    public UserServiceImpl(UserAdapter userAdapter, UserRepository userRepository) {
        this.userAdapter = userAdapter;
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserRegisterDto userDto) {

        UserEntity userEntity = userAdapter.entityFromDto(userDto);
        this.userRepository.save(userEntity);

        System.out.println(String.format("%s was registered", userEntity.getFullName()));

    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {

        UserEntity userEntity = this.userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow();

        if(userEntity.getPassword().equals(userLoginDto.getPassword())) {
            UserDto user = userAdapter.entityToDto(userEntity);
            CurrentUserProvider.setCurrentUser(user);
            System.out.println(String.format("Successfully logged in %s", user.getFullName()));
        } else {
            throw new RuntimeException(PASSWORD_NOT_VALID_LOGIN_MESSAGE);
        }

    }

    @Override
    public void logoutUser() {

        UserDto user = CurrentUserProvider.getCurrentUser();

        if(user == null) {
            throw new RuntimeException("There is no currently logged user!");
        }

        CurrentUserProvider.setCurrentUser(null);

        System.out.println(String.format("User %s successfully logged out", user.getFullName()));

    }

    @Override
    public Boolean isUserLogedIn() {

        return CurrentUserProvider.isUserLoggedIn();
    }
}

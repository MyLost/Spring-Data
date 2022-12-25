package bg.softuni.workshop.services;

import bg.softuni.workshop.models.User;
import bg.softuni.workshop.models.dtos.LoginDto;
import bg.softuni.workshop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User login(LoginDto loginDto) {
       var user =  this.userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());

        // check password if hashed
        //marked user as logged if data is correct;

        return user.orElseThrow(() -> new RuntimeException("User not found!!!"));
    }
}

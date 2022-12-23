package bg.softuni.json.services;

import bg.softuni.json.dtos.user.UserSoldProductsDto;
import bg.softuni.json.dtos.user.UsersCountWrapperDto;

import java.io.IOException;
import java.util.List;

public interface UserService {

    void seedUsers() throws IOException;

    List<UserSoldProductsDto> findUsersWithSoldProducts();

    UsersCountWrapperDto findUsersSoldProductsWithCount();
}
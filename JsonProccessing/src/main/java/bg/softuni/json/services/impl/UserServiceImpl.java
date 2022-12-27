package bg.softuni.json.services.impl;

import bg.softuni.json.constatns.PathFiles;
import bg.softuni.json.dtos.user.*;
import bg.softuni.json.entities.User;
import bg.softuni.json.repositories.CategoryRepository;
import bg.softuni.json.repositories.ProductRepository;
import bg.softuni.json.repositories.UserRepository;
import bg.softuni.json.services.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final Gson gson;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ProductRepository productRepository,
                           CategoryRepository categoryRepository, ModelMapper mapper, Gson gson) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public void seedUsers() throws IOException {
        if (userRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PathFiles.USERS_FILE_PATH.toFile());

            final List<User> users = Arrays.stream(gson.fromJson(fileReader, UserImportDto[].class))
                    .map(userImportDto -> mapper.map(userImportDto, User.class))
                    .toList();

            fileReader.close();

            this.userRepository.saveAllAndFlush(users);
        }
    }

    @Override
    public List<UserSoldProductsDto> findUsersWithSoldProducts() {

        List<UserSoldProductsDto> userSoldProductsDtos = this.userRepository
                .findAllByOrderByLastNameAscFirstNameAsc()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> mapper.map(user, UserSoldProductsDto.class))
                .toList();

        userSoldProductsDtos
                .forEach(u -> u.getSoldProducts()
                        .removeIf(p -> p.getBuyerFirstName() == null && p.getBuyerLastName() == null));

        return userSoldProductsDtos;
    }

    @Override
    public UsersCountWrapperDto findUsersSoldProductsWithCount() {
        List<UserDto> users = this.userRepository
                .findAllByOrderByLastNameAscFirstNameAsc()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> mapper.map(user, UserDto.class))
                .toList();

        users.
                forEach(u -> u.getSoldProducts()
                        .removeIf(p -> p.getBuyer() == null));


        List<UserWrapperDetailsDto> usersWrapper =
                users
                        .stream()
                        .map(UserDto::userWrapperDetailsDto)
                        .toList();

        List<UserWrapperDetailsDto> sorted = usersWrapper
                .stream()
                .sorted(Comparator.comparing(UserWrapperDetailsDto::soldProductsCount)
                        .reversed()
                        .thenComparing(UserWrapperDetailsDto::getLastName))
                .toList();

        return new UsersCountWrapperDto(sorted);
    }
}
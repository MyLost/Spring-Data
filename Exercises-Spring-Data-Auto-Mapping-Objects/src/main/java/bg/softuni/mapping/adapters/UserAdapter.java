package bg.softuni.mapping.adapters;

import bg.softuni.mapping.dtos.UserDto;
import bg.softuni.mapping.dtos.UserLoginDto;
import bg.softuni.mapping.dtos.UserRegisterDto;
import bg.softuni.mapping.entities.UserEntity;
import bg.softuni.mapping.service.mapper.CustomMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserAdapter {

    private CustomMapper customMapper;

    public UserAdapter(CustomMapper customMapper) {
        this.customMapper = customMapper;
    }

    public UserDto entityToDto (UserEntity entity) {

        return UserDto.builder()
                .id(entity.getId())
                .games(entity.getGames())
                .email(entity.getEmail())
                .isAdmin(entity.getIsAdmin())
                .fullName(entity.getFullName())
                .orders(entity.getOrders()).build();

    }


    public UserEntity entityFromDto (UserRegisterDto dto) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userEntity.setPassword(dto.getPassword());
        userEntity.setFullName(dto.getFullName());
        userEntity.setGames(new HashSet<>());
        userEntity.setOrders(new HashSet<>());
        userEntity.setIsAdmin(false);

        return userEntity;
    }

}

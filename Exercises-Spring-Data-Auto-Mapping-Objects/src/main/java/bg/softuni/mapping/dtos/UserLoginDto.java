package bg.softuni.mapping.dtos;

import bg.softuni.mapping.entities.GameEntity;
import bg.softuni.mapping.entities.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginDto {

    private String email;
    private String password;

}

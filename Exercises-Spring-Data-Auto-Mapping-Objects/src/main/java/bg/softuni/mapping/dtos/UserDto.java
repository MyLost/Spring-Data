package bg.softuni.mapping.dtos;

import bg.softuni.mapping.entities.GameEntity;
import bg.softuni.mapping.entities.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String fullName;
    private Set<GameEntity> games;
    private Set<OrderEntity> orders;
    private Boolean isAdmin;
}

package bg.softuni.json.dtos.user;

import bg.softuni.json.dtos.product.ProductWithBayerNameDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSoldProductsDto {
    private String firstName;
    private String lastName;
    private Set<ProductWithBayerNameDto> soldProducts;
}
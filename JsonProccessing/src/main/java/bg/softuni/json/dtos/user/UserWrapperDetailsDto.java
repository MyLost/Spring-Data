package bg.softuni.json.dtos.user;

import bg.softuni.json.dtos.product.ProductWrapperDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserWrapperDetailsDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private ProductWrapperDto soldProducts;

    public int soldProductsCount(){
        return soldProducts.getCount();
    }
}
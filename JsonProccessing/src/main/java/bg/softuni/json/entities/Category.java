package bg.softuni.json.entities;

import bg.softuni.json.constatns.Messages;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;


@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category extends BaseEntity {

    @Size(min = 3, max = 15, message = Messages.NAME_CONSTAINT)
    @Column(nullable = false, length = 15)
    private String name;

}

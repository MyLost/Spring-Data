package bg.softuni.json.repositories;

import bg.softuni.json.dtos.category.CategoryCountProductsDto;
import bg.softuni.json.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from `json-example`.categories order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Category> getRandomEntity();

    @Query("select new  bg.softuni.json.dtos.category.CategoryCountProductsDto(" +
            "c.name, count(p.id), avg(p.price), sum(p.price)) " +
            "FROM Product p " +
            "JOIN p.categories c " +
            "GROUP BY c.id " +
            "ORDER BY count(p.id) DESC")
    Optional<List<CategoryCountProductsDto>> getCategoriesSummary();
}
package bg.softuni.xmlProccessing.repositories;

import bg.softuni.xmlProccessing.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
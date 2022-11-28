package bg.softuni.workshop.repositories;

import bg.softuni.workshop.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}

package bg.softuni.xmlProccessing.repositories;

import bg.softuni.xmlProccessing.domain.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}
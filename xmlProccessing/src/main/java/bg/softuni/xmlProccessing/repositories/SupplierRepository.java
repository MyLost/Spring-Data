package bg.softuni.xmlProccessing.repositories;

import bg.softuni.xmlProccessing.domain.dtos.exportDto.SupplierSimpleExportDto;
import bg.softuni.xmlProccessing.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("select new bg.softuni.xmlProccessing.domain.dtos.exportDto.SupplierSimpleExportDto(" +
            "s.id, s.name, count(p.id)) " +
            "from Supplier s " +
            "join Part p " +
            "on p.supplier.id = s.id " +
            "where s.isImporter = false " +
            "group by s.id " +
            "order by count(p.id) desc ")
    List<SupplierSimpleExportDto> findAllByIsImporterFalse();
}

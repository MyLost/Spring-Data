package bg.softuni.xmlProccessing.service;

import bg.softuni.xmlProccessing.domain.dtos.exportDto.SupplierSimpleExportWrapperDto;
import bg.softuni.xmlProccessing.domain.entities.Supplier;

public interface SupplierService {
    void save(Supplier supplier);

    long count();
    Supplier getRandomSupplier();

    SupplierSimpleExportWrapperDto getLocalSuppliers();
}

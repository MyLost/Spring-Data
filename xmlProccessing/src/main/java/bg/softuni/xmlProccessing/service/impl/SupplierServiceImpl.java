package bg.softuni.xmlProccessing.service.impl;

import bg.softuni.xmlProccessing.domain.dtos.exportDto.SupplierSimpleExportWrapperDto;
import bg.softuni.xmlProccessing.domain.entities.Supplier;
import bg.softuni.xmlProccessing.repositories.SupplierRepository;
import bg.softuni.xmlProccessing.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository repository;
    private final Random random;
    private final ModelMapper mapper;

    @Override public void save(Supplier supplier) {
        repository.save(supplier);
    }

    @Override public long count() {
        return repository.count();
    }

    @Override public Supplier getRandomSupplier() {
        long id = random.nextLong(repository.count()) + 1L;
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override public SupplierSimpleExportWrapperDto getLocalSuppliers() {
        SupplierSimpleExportWrapperDto toReturn = new SupplierSimpleExportWrapperDto();
        repository.findAllByIsImporterFalse().forEach(s -> toReturn.getSuppliers().add(s));
        return toReturn;
    }
}

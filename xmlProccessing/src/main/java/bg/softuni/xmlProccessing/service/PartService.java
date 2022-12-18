package bg.softuni.xmlProccessing.service;

import bg.softuni.xmlProccessing.domain.entities.Part;

import java.util.List;

public interface PartService {
    void save(Part part);

    long count();
    List<Part> getRandomParts();
}
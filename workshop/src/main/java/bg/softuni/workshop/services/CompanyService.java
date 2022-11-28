package bg.softuni.workshop.services;

import bg.softuni.workshop.models.Company;
import bg.softuni.workshop.models.dtos.ImportCompaniesDto;
import bg.softuni.workshop.repositories.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private ModelMapper mapper;

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyService(ModelMapper mapper, CompanyRepository companyRepository) {
        this.mapper = mapper;
        this.companyRepository = companyRepository;
    }

    public String getXMLContent() throws IOException {

        Path path = Path.of("src","main","resources", "xmls", "companies.xml");
        List<String> lines = Files.readAllLines(path);

        return String.join("\n", lines);
    }

    public void importCompanies() throws IOException, JAXBException {
        String xmlContent = this.getXMLContent();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlContent.getBytes());
        JAXBContext jaxbContext = JAXBContext.newInstance(ImportCompaniesDto.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ImportCompaniesDto companies = (ImportCompaniesDto) unmarshaller.unmarshal(byteArrayInputStream);

        List<Company> entities = companies
                .getCompanies().stream().map(dto -> this.mapper.map(dto, Company.class))
                .collect(Collectors.toList());

        this.companyRepository.saveAll(entities);

    }




}

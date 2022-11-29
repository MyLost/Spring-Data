package bg.softuni.workshop.controllers;


import bg.softuni.workshop.services.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ImportXMLController {

    private CompanyService companyService;

    public ImportXMLController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/import/xml")
    public String index(Model model) {

        model.addAttribute("areImported", new boolean[] {false,false,false});
        return "xml/import-xml";
    }

    @GetMapping("/import/companies")
    public String importCompanies(Model model) throws IOException {
        String companiesXML = this.companyService.getXMLContent();
        model.addAttribute("companies", companiesXML);

        return "xml/import-companies";
    }

    @PostMapping("/import/companies")
    public String importCompanies() throws IOException, JAXBException {
        this.companyService.importCompanies();

        return "redirect:/import/xml";
    }
}

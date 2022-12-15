package bg.softuni.advancedqueries;

import bg.softuni.advancedqueries.entities.Size;
import bg.softuni.advancedqueries.repositories.ShampooRepository;
import bg.softuni.advancedqueries.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {

    ShampooService shampooService;

    @Autowired
    public Main(ShampooService shampooService) {
        this.shampooService = shampooService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Hello");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter size");
        String size = scanner.nextLine();

//        this.shampooService.findByBrand("Silk Comb").forEach(shampoo -> {
//            System.out.println(shampoo.getId());
//        });

        this.shampooService.findByBrandAndSize("Cotton Fresh", Size.SMALL).forEach(shampoo -> {
            System.out.println(shampoo.getId());
        });

        this.shampooService.findByBrandAndSize("Cotton Fresh", size).forEach(System.out::println);



    }
}

package bg.softuni.exercise;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Main implements CommandLineRunner {

    private ConsoleApplication consoleApplication;


    public Main(ConsoleApplication consoleApplication) {
        this.consoleApplication = consoleApplication;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Running....");

        consoleApplication.startApplication();
    }


}

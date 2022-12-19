package softuni.exam.constraints;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path COUNTRIES_PATH = Path.of("src/main/resources/files/json/countries.json");

    public static final Path CITY_PATH = Path.of("src/main/resources/files/json/cities.json");

    public static final Path FORECASTS_PATH = Path.of("src/main/resources/files/xml/forecasts.xml");

}
package bg.softuni.modelMapperAplication.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class Config {

    @Bean
    public ModelMapper createModelMapper() {
        // Do whatever you want!
        return new ModelMapper();
    }
}

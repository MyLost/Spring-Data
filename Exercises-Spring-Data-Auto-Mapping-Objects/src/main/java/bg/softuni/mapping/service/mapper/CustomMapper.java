package bg.softuni.mapping.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomMapper {

    private ModelMapper modelMapper;

    public ModelMapper getCustomMapper() {

        if(modelMapper == null) {
            modelMapper= new ModelMapper();
        }
        return modelMapper;
    }
}

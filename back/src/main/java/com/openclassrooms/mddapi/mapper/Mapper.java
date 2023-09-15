package com.openclassrooms.mddapi.mapper;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
/**
 * The type Mapper.
 */
@Component
public class Mapper {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}

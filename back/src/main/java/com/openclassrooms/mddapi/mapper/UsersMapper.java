package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UsersDto;
import com.openclassrooms.mddapi.models.Users;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UsersMapper extends EntityMapper<UsersDto, Users> {
}

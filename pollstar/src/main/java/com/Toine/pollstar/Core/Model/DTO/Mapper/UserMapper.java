package com.Toine.pollstar.Core.Model.DTO.Mapper;

import com.Toine.pollstar.Core.Model.DTO.UserDetails.UserDTO;
import com.Toine.pollstar.Core.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper
{
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );


    UserDTO userToUserDTO(User user);
}

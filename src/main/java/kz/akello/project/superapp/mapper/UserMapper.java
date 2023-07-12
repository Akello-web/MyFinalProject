package kz.akello.project.superapp.mapper;

import kz.akello.project.superapp.dto.UserDTO;
import kz.akello.project.superapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "fullName", target = "userName")
    UserDTO toDTO(User user);

    @Mapping(source = "userName", target = "fullName")
    User toModel(UserDTO userDTO);

    List<UserDTO> toDtoList(List<User> userList);

    List<User> toModelList(List<UserDTO> userDtoList);
}

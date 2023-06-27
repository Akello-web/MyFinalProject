package kz.akello.project.superapp.mapper;

import kz.akello.project.superapp.dto.UserDTO;
import kz.akello.project.superapp.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    User toModel(UserDTO userDTO);

    List<UserDTO> toDtoList(List<User> userList);

    List<User> toModelList(List<UserDTO> userDtoList);
}

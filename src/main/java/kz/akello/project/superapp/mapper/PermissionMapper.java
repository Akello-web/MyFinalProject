package kz.akello.project.superapp.mapper;

import kz.akello.project.superapp.dto.PermissionDTO;
import kz.akello.project.superapp.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, PermissionMapper.class})
public interface PermissionMapper {
  PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);
  PermissionDTO toDTO(Permission permission);

  Permission toModel(PermissionDTO permissionDTO);

  List<PermissionDTO> toDtoList(List<Permission> permissionList);

  List<Permission> toModelList(List<PermissionDTO> permissionDTOList);
}

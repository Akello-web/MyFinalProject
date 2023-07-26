package kz.akello.project.superapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private int age;
    private double money;
    private List<PermissionDTO> permissionList;
}

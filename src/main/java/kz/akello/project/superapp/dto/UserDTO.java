package kz.akello.project.superapp.dto;

import kz.akello.project.superapp.model.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private int age;
    private double money;
    private List<Permission> permissionList;
}

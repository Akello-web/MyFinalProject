package kz.akello.project.superapp.service;

import kz.akello.project.superapp.dto.UserDTO;
import kz.akello.project.superapp.mapper.UserMapper;
import kz.akello.project.superapp.model.Permission;
import kz.akello.project.superapp.model.User;
import kz.akello.project.superapp.repository.PermissionRepository;
import kz.akello.project.superapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;


public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user!=null){
            return user;
        }else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }

    public List<UserDTO> getUsers(){
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserDTO getUser(Long id){
        return userMapper.toDTO(userRepository.findById(id).orElse(null));
    }

    public UserDTO updateUser(UserDTO user){
        return userMapper.toDTO(userRepository.save(userMapper.toModel(user)));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User addUser(User user){

        User checkUser = userRepository.findByEmail(user.getEmail());
        if(checkUser==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Permission defaultPermission = permissionRepository.findByRole("ROLE_USER");

            if (defaultPermission == null) {
                defaultPermission = new Permission();
                defaultPermission.setRole("ROLE_USER");
                defaultPermission = permissionRepository.save(defaultPermission);
            }
            user.setPermissionList(Collections.singletonList(defaultPermission));
            return userRepository.save(user);
        }
        return null;
    }

    public User addSeller(User user){
        User checkSeller = userRepository.findByEmail(user.getEmail());
        if(checkSeller==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Permission defaultPermission = permissionRepository.findByRole("ROLE_SELLER");

            if (defaultPermission == null) {
                defaultPermission = new Permission();
                defaultPermission.setRole("ROLE_SELLER");
                defaultPermission = permissionRepository.save(defaultPermission);
            }
            user.setPermissionList(Collections.singletonList(defaultPermission));
            return userRepository.save(user);
        }
        return null;
    }

    public User updatePassword(String newPassword, String oldPassword){
        User currentUser = getCurrentSessionUser();
        if(passwordEncoder.matches(oldPassword, currentUser.getPassword())){
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(currentUser);
        }
        return null;
    }

    public User updateParams(String fullName, int age){
        User currentUser = getCurrentSessionUser();
        currentUser.setFullName(fullName);
        currentUser.setAge(age);
        return userRepository.save(currentUser);
    }

    public User getCurrentSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            User user = (User) authentication.getPrincipal();
            if (user != null) {
                return user;
            }
        }
        return null;
    }

}

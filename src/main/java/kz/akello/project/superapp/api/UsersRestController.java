package kz.akello.project.superapp.api;

import kz.akello.project.superapp.model.User;
import kz.akello.project.superapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UsersRestController {
  private final UserService userService;

  @GetMapping
  public List<User> getUsers(){
    return userService.getUsers();
  }

  @GetMapping(value = "{id}")
  public User getUser(@PathVariable(name = "id") Long id){
    return userService.getUser(id);
  }

//  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @DeleteMapping("{id}")
  public void deleteUser(@PathVariable(name = "id") Long id){
    userService.deleteUser(id);
  }
}

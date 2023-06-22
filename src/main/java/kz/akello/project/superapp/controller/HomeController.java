package kz.akello.project.superapp.controller;

import kz.akello.project.superapp.model.User;
import kz.akello.project.superapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping(value = "/")
    public String indexPage(){
        return "index";
    }

    @GetMapping(value = "/sign-in-page")
    public String signInPage(){
        return "signInPage";
    }

    @GetMapping(value = "/sign-up-page")
    public String signUpPage(){
        return "signUpPage";
    }

    @PostMapping(value = "/to-sign-up")
    public String toSignUp(@RequestParam("user_full_name") String fullName,
                           @RequestParam("user_age") int userAge,
                           @RequestParam("user_email") String email,
                           @RequestParam("user_password") String password,
                           @RequestParam("user_repeat_password") String rePassword){
        if(userAge >= 16){
            if(password.equals(rePassword)){
                User user = new User();

                user.setAge(userAge);
                user.setFullName(fullName);
                user.setEmail(email);
                user.setPassword(password);

                User newUser = userService.addUser(user);
                if(newUser!=null){
                    return "redirect:/sign-up-page?success";
                }else {
                    return "redirect:/sign-up-page?emailError";
                }

            }else {
                return "redirect:/sign-up-page?passwordMismatch";
            }
        }else {
            return "redirect:/sign-up-page?tooYoung";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profilePage(){
        return "profile";
    }


}

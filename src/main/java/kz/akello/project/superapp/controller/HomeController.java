package kz.akello.project.superapp.controller;

import kz.akello.project.superapp.model.User;

import kz.akello.project.superapp.service.NewsService;
import kz.akello.project.superapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final NewsService newsService;
    @GetMapping(value = "/")
    public String indexPage() {
        return "index";
    }

    @GetMapping(value = "/products-page")
    public String productPage(){
        return "marketPage";
    }
    @GetMapping(value = "/product-request-page")
    public String productRequestPage(){
        return "requestsPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin-panel")
    public String adminPage(){
        return "adminPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/categories-panel")
    public String categoryPage(){
        return "categoryPage";
    }

    @GetMapping(value = "/{id}")
    public String newsDetails(Model model, @PathVariable(name = "id") Long id){
        model.addAttribute("thenew", newsService.getNew(id));
        return "newsDetails";
    }

    @GetMapping(value = "/sign-in-page")
    public String signInPage(){
        return "signInPage";
    }

    @GetMapping(value = "/sign-up-page")
    public String signUpPage(){
        return "signUpPage";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profilePage(){
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/settings-page")
    public String settingsPage(){
        return "settingsPage";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/currency-all")
    public String getAllCurrency(){
        return "exchangeRate";
    }

    @PostMapping(value = "/change-params")
    public String toChangeParams(@RequestParam(name = "fullName") String fullName,
                                 @RequestParam(name = "age") int age){
        userService.updateParams(fullName, age);
        return "redirect:/settings-page";
    }

    @PostMapping(value = "/to-sign-up")
    public String toSignUp(
            @RequestParam(name = "user_full_name") String fullName,
            @RequestParam(name = "user_age") int userAge,
            @RequestParam(name = "user_email") String email,
            @RequestParam(name = "user_password") String password,
            @RequestParam(name = "user_repeat_password") String rePassword,
            @RequestParam(name = "user_money") Double money,
            @RequestParam(name = "sellerChecked", required = false) String checkBox){

        if(password.equals(rePassword)){
            User user = new User();
            user.setAge(userAge);
            user.setFullName(fullName);
            user.setEmail(email);
            user.setMoney(money);
            user.setPassword(password);
            if(checkBox!=null){
                if(userAge >= 18){
                    user.setMoney(150);
                    User newUser = userService.addSeller(user);
                    if(newUser!=null){
                        return "redirect:/sign-up-page?successSeller";
                    }else {
                        return "redirect:/sign-up-page?emailError";
                    }
                }else {
                    return "redirect:/sign-up-page?sellerAgeError";
                }

            }else {
                if(userAge>=16){
                    User newUser = userService.addUser(user);
                    if(newUser!=null){
                        return "redirect:/sign-up-page?successUser";
                    }else {
                        return "redirect:/sign-up-page?emailError";
                    }
                }else {
                    return "redirect:/sign-up-page?userAgeError";
                }
            }
        }else {
            return "redirect:/sign-up-page?passwordMismatch";
        }
    }

    @PostMapping(value = "/to-update-password")
    public String updatePassword(@RequestParam("user_old_password") String oldPassword,
                                 @RequestParam("user_new_password") String newPassword,
                                 @RequestParam("user_repeat_new_password") String rePassword){
        if(newPassword.equals(rePassword)) {
            User user = userService.updatePassword(newPassword, oldPassword);
            if(user!=null){
                return "redirect:/settings-page?success";
            }else {
                return "redirect:/settings-page?oldPasswordError";
            }
        }else {
            return "redirect:/settings-page?passwordMismatch";
        }
    }
}

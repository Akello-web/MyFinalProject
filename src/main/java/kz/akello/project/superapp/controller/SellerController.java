package kz.akello.project.superapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellerController {
    @PreAuthorize("hasAnyRole('ROLE_SELLER')")
    @GetMapping(value = "/seller-panel")
    public String sellerPage(){
        return "sellerPage";
    }
}

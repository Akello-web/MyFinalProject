package kz.akello.project.superapp.controller;

import kz.akello.project.superapp.model.Product;
import kz.akello.project.superapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;

  @GetMapping
  public String productPage(Model model){
    model.addAttribute("products", productService.getProducts());
    return "marketPage";
  }

  @PostMapping(value = "/add-product")
  public String addProduct(Product product){
    productService.addProduct(product);
    return "redirect:/seller-panel";
  }

  @GetMapping(value = "{id}")
  public String productDetails(@PathVariable(name = "id") Long id, Model model){
    model.addAttribute("product", productService.getProduct(id));
    return "productDetails";
  }

  @PostMapping(value = "/update-product")
  public String updateProduct(Product product, @RequestParam(name = "id") Long id){
   productService.updateProduct(product);
   return "redirect:/products/" + id;
  }

  @PostMapping(value = "/delete-product")
  public String deleteProduct(@RequestParam(name = "id") Long id){
    productService.deleteProduct(id);
    return "redirect:/products";
  }

}

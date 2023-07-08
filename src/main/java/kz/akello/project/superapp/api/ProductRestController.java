package kz.akello.project.superapp.api;

import kz.akello.project.superapp.model.Product;
import kz.akello.project.superapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductRestController {
  private final ProductService productService;

  @GetMapping
  public List<Product> productPage(){
    return productService.getProducts();
  }

//  @PreAuthorize("hasAnyRole('ROLE_SELLER')")
  @PostMapping
  public Product addProduct(@RequestBody Product product){
    return productService.addProduct(product);
  }

  @GetMapping(value = "{id}")
  public Product getProduct(@PathVariable(name = "id") Long id){
    return productService.getProduct(id);
  }

//  @PreAuthorize("hasAnyRole('ROLE_SELLER')")
  @PutMapping
  public Product updateProduct(@RequestBody Product product){
    return productService.updateProduct(product);
  }

//  @PreAuthorize("hasAnyRole('ROLE_SELLER')")
  @DeleteMapping("{id}")
  public void deleteProduct(@PathVariable(name = "id") Long id){
    productService.deleteProduct(id);
  }
}

package kz.akello.project.superapp.api;

import kz.akello.project.superapp.dto.ProductDTO;
import kz.akello.project.superapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductRestController {
  private final ProductService productService;

  @GetMapping
  public List<ProductDTO> productPage(){
    return productService.getProducts();
  }

  @PreAuthorize("hasAnyRole('ROLE_SELLER')")
  @PostMapping
  public ProductDTO addProduct(@RequestBody ProductDTO product){
    return productService.addProduct(product);
  }

  @GetMapping(value = "{id}")
  public ProductDTO getProduct(@PathVariable(name = "id") Long id){
    return productService.getProduct(id);
  }

  @PreAuthorize("hasAnyRole('ROLE_SELLER', 'ROLE_ADMIN')")
  @PutMapping
  public ProductDTO updateProduct(@RequestBody ProductDTO product){

    return productService.updateProduct(product);
  }

  @PreAuthorize("hasAnyRole('ROLE_SELLER', 'ROLE_ADMIN')")
  @DeleteMapping("{id}")
  public void deleteProduct(@PathVariable(name = "id") Long id){
    productService.deleteProduct(id);
  }
}

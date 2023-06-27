package kz.akello.project.superapp.service;

import kz.akello.project.superapp.model.Product;
import kz.akello.project.superapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public List<Product> getProducts(){
    return productRepository.findAll();
  }

  public Product addProduct(Product product){
    return productRepository.save(product);
  }

  public Product getProduct(Long id){
    return productRepository.findById(id).orElse(null);
  }

  public Product updateProduct(Product product){

    return productRepository.save(product);
  }

  public void deleteProduct(Long id){
    productRepository.deleteById(id);
  }


}

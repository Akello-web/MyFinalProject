package kz.akello.project.superapp.service;

import kz.akello.project.superapp.dto.ProductDTO;
import kz.akello.project.superapp.mapper.ProductMapper;
import kz.akello.project.superapp.model.Product;
import kz.akello.project.superapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public List<ProductDTO> getProducts(){
    return productMapper.toProductDtoList(productRepository.findAll());
  }

  public ProductDTO addProduct(ProductDTO product){
    return productMapper.toDto(productRepository.save(productMapper.fromDto(product)));
  }

  public ProductDTO getProduct(Long id){

    return productMapper.toDto(productRepository.findById(id).orElse(null));
  }

  public ProductDTO updateProduct(ProductDTO product){

    return productMapper.toDto(productRepository.save(productMapper.fromDto(product)));
  }

  public void deleteProduct(Long id){
    productRepository.deleteById(id);
  }


}

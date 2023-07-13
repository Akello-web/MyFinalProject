package kz.akello.project.superapp.mapper;

import kz.akello.project.superapp.dto.CategoryDTO;
import kz.akello.project.superapp.dto.ProductDTO;
import kz.akello.project.superapp.model.Category;
import kz.akello.project.superapp.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  @Mapping(source = "category", target = "category")
  ProductDTO toDto(Product product);

  @Mapping(source = "category", target = "category")
  Product fromDto(ProductDTO productDTO);

  List<ProductDTO> toProductDtoList(List<Product> courseList);
  List<Product> toProductModelList(List<ProductDTO> coursesDTO);

  @Mapping(source = "id", target = "id")
  CategoryDTO toDto(Category category);

  @Mapping(source = "id", target = "id")
  Category fromDto(CategoryDTO categoryDTO);

  List<CategoryDTO> toCategoryDtoList(List<Category> courseList);
  List<Category> toCategoryModelList(List<CategoryDTO> coursesDTO);
}

package kz.akello.project.superapp.service;

import kz.akello.project.superapp.dto.CategoryDTO;
import kz.akello.project.superapp.mapper.ProductMapper;
import kz.akello.project.superapp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final ProductMapper categoryMapper;

  public List<CategoryDTO> getCategories(){
    return categoryMapper.toCategoryDtoList(categoryRepository.findAll());
  }

  public CategoryDTO getCategory(Long id){

    return categoryMapper.toDto(categoryRepository.findById(id).orElse(null));
  }

  public CategoryDTO addCategory(CategoryDTO category){

    return categoryMapper.toDto(categoryRepository.save(categoryMapper.fromDto(category)));
  }

  public CategoryDTO updateCategory(CategoryDTO category){

    return categoryMapper.toDto(categoryRepository.save(categoryMapper.fromDto(category)));
  }

  public void deleteCategory(Long id){
    categoryRepository.deleteById(id);
  }
}

package kz.akello.project.superapp.service;

import kz.akello.project.superapp.model.Category;
import kz.akello.project.superapp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public List<Category> getCategories(){
    return categoryRepository.findAll();
  }

  public Category getCategory(Long id){
    return categoryRepository.findById(id).orElse(null);
  }

  public Category addCategory(Category category){
    return categoryRepository.save(category);
  }

  public Category updateCategory(Category category){
    return categoryRepository.save(category);
  }

  public void deleteCategory(Long id){
    categoryRepository.deleteById(id);
  }
}

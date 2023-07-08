package kz.akello.project.superapp.api;

import kz.akello.project.superapp.model.Category;
import kz.akello.project.superapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryRestController {

  private final CategoryService categoryService;

  @GetMapping
  public List<Category> getCategories(){
    return categoryService.getCategories();
  }

  @GetMapping(value = "{id}")
  public Category getCategory(@PathVariable(name = "id") Long id){
    return categoryService.getCategory(id);
  }

  @PostMapping
  public Category addCategory(Category category){
    return categoryService.addCategory(category);
  }

  @PutMapping
  public Category updateCategory(Category category){
    return categoryService.addCategory(category);
  }

  @DeleteMapping(value = "{id}")
  public void deleteCategory(@PathVariable(name = "id") Long id){
    categoryService.deleteCategory(id);
  }

}

package kz.akello.project.superapp.api;

import kz.akello.project.superapp.dto.CategoryDTO;
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
  public List<CategoryDTO> getCategories(){
    return categoryService.getCategories();
  }

  @GetMapping(value = "{id}")
  public CategoryDTO getCategory(@PathVariable(name = "id") Long id){
    return categoryService.getCategory(id);
  }

  @PostMapping
  public CategoryDTO addCategory(@RequestBody CategoryDTO category){
    return categoryService.addCategory(category);
  }

  @PutMapping
  public CategoryDTO updateCategory(CategoryDTO category){
    return categoryService.updateCategory(category);
  }

  @DeleteMapping(value = "{id}")
  public void deleteCategory(@PathVariable(name = "id") Long id){
    categoryService.deleteCategory(id);
  }

}

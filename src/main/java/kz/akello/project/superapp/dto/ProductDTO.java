package kz.akello.project.superapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
  private Long id;
  private String nameSeller;
  private String name;
  private String description;
  private double price;
  private CategoryDTO category;
}

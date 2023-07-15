package kz.akello.project.superapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_products")
@Getter
@Setter
public class Product extends BaseModel{

  @Column(name = "product_name")
  private String name;

  @Column(name = "product_description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "product_price")
  private double price;

  @Column(name = "product_handled")
  private boolean handled;

  @ManyToOne
  private User userProduct;

  @ManyToOne
  private Category category;
}

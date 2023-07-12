package kz.akello.project.superapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class NewsDTO {
  private Long id;
  private String newsAuthor;
  private String title;
  private String description;
  private Timestamp timePost;
}

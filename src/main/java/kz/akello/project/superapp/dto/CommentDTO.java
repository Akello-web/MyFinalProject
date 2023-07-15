package kz.akello.project.superapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentDTO {
  private Long id;
  private String commentary;
  private Timestamp postDate;
  private String userName;
  private NewsDTO news;
}

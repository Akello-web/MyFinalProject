package kz.akello.project.superapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "t_news")
@Getter
@Setter
public class News extends BaseModel{
  @Column(name = "news_author")
  private String newsAuthor;

  @Column(name = "news_title")
  private String title;

  @Column(name = "news_description", columnDefinition = "TEXT")
  private String description;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "news_postdate")
  private Timestamp postDate;

  public Timestamp getPostDate() {
    return postDate;
  }

  public void setPostDate(Timestamp postDate) {
    this.postDate = postDate;
  }
}

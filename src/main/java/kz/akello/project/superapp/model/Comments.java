package kz.akello.project.superapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "t_comments")
@Getter
@Setter
public class Comments extends BaseModel{
  @Column(name = "commentary")
  private String commentary;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "comment_date")
  private Timestamp postDate;

  @Column(name = "userName")
  private String userName;

  @ManyToOne(fetch = FetchType.LAZY)
  private News news;
}

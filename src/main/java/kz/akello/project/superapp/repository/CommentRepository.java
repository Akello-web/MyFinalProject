package kz.akello.project.superapp.repository;

import kz.akello.project.superapp.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
  @Query(value = "SELECT * FROM t_comments ORDER BY id DESC", nativeQuery = true)
  List<Comments> findAllOrderedByIdDesc();
}

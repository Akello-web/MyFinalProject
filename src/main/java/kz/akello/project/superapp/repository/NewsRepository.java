package kz.akello.project.superapp.repository;

import kz.akello.project.superapp.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
  @Query(value = "SELECT * FROM t_news ORDER BY news_postdate DESC", nativeQuery = true)
  List<News> findAllOrderedByDateDesc();
}

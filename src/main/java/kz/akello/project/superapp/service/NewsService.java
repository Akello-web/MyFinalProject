package kz.akello.project.superapp.service;

import kz.akello.project.superapp.model.News;
import kz.akello.project.superapp.model.Product;
import kz.akello.project.superapp.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
  private final NewsRepository newsRepository;
  public List<News> getNews(){
    return newsRepository.findAll();
  }

  public News addNews(News news){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName = authentication.getName();
    news.setNewsAuthor(currentUserName);
    news.setPostDate(new Timestamp(System.currentTimeMillis()));
    return newsRepository.save(news);
  }

  public void deleteNews(Long id){
    newsRepository.deleteById(id);
  }


}

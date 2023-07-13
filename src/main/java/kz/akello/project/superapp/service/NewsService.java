package kz.akello.project.superapp.service;

import kz.akello.project.superapp.dto.NewsDTO;
import kz.akello.project.superapp.mapper.NewsMapper;
import kz.akello.project.superapp.model.News;
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
  private final NewsMapper newsMapper;
  public List<NewsDTO> getNews(){
    return newsMapper.toNewsDtoList(newsRepository.findAllOrderedByDateDesc());
  }

  public NewsDTO getNew(Long id){
    return newsMapper.toDto(newsRepository.findById(id).orElse(null));
  }

  public NewsDTO addNews(NewsDTO news){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName = authentication.getName();
    news.setNewsAuthor(currentUserName);
    news.setPostDate(new Timestamp(System.currentTimeMillis()));
    return newsMapper.toDto(newsRepository.save(newsMapper.fromDto(news)));
  }

  public NewsDTO updateNews(NewsDTO news){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName = authentication.getName();
    news.setNewsAuthor(currentUserName);
    news.setPostDate(new Timestamp(System.currentTimeMillis()));
    return newsMapper.toDto(newsRepository.save(newsMapper.fromDto(news)));
  }

  public void deleteNews(Long id){
    newsRepository.deleteById(id);
  }


}

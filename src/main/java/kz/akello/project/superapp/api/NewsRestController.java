package kz.akello.project.superapp.api;

import kz.akello.project.superapp.model.News;
import kz.akello.project.superapp.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/news")
public class NewsRestController {

  private final NewsService newsService;

  @GetMapping
  public List<News> getNews(){
    return newsService.getNews();
  }

  @GetMapping(value = "{id}")
  public News getNew(@PathVariable("id") Long id){
    return newsService.getNew(id);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping
  public News addNews(@RequestBody News news){
    return newsService.addNews(news);
  }

  @PreAuthorize("isAuthenticated()")
  @PutMapping
  public News updateNews(@RequestBody News news){
    return newsService.updateNews(news);
  }

  @PreAuthorize("isAuthenticated()")
  @DeleteMapping(value = "{id}")
  public void deleteNews(@PathVariable(name = "id") Long id){
    newsService.deleteNews(id);
  }

}

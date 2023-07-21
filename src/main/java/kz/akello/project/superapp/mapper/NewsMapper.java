package kz.akello.project.superapp.mapper;

import kz.akello.project.superapp.dto.CommentDTO;
import kz.akello.project.superapp.dto.NewsDTO;
import kz.akello.project.superapp.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {
  @Mapping(source = "postDate", target = "postDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
  NewsDTO toDto(News news);

  @Mapping(source = "postDate", target = "postDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
  News fromDto(NewsDTO newsDTO);

  List<NewsDTO> toNewsDtoList(List<News> courseList);
  List<News> toNewsModelList(List<NewsDTO> coursesDTO);

}

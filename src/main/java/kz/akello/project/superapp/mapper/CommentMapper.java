package kz.akello.project.superapp.mapper;

import kz.akello.project.superapp.dto.CommentDTO;
import kz.akello.project.superapp.model.Comments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
  @Mapping(source = "news", target = "news")
  CommentDTO toDto(Comments comments);

  @Mapping(source = "news", target = "news")
  Comments fromDto(CommentDTO commentDTO);

  List<CommentDTO> toCommentDtoList(List<Comments> commentsList);
  List<Comments> toCommentModelList(List<CommentDTO> commentsDTO);
}

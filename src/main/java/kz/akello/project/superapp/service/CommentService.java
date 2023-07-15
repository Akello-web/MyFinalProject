package kz.akello.project.superapp.service;

import kz.akello.project.superapp.dto.CommentDTO;
import kz.akello.project.superapp.mapper.CommentMapper;
import kz.akello.project.superapp.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;
  private final CommentMapper commentMapper;

  public List<CommentDTO> getComments(){
    return commentMapper.toCommentDtoList(commentRepository.findAllOrderedByIdDesc());
  }

  public CommentDTO getComment(Long id){
    return commentMapper.toDto(commentRepository.findById(id).orElse(null));
  }

  public CommentDTO addComment(CommentDTO comment){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName = authentication.getName();
    comment.setUserName(currentUserName);
    comment.setPostDate(new Timestamp(System.currentTimeMillis()));
    return commentMapper.toDto(commentRepository.save(commentMapper.fromDto(comment)));
  }

  public CommentDTO updateComment(CommentDTO comment){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName = authentication.getName();
    comment.setUserName(currentUserName);
    comment.setPostDate(new Timestamp(System.currentTimeMillis()));
    return commentMapper.toDto(commentRepository.save(commentMapper.fromDto(comment)));
  }

  public void deleteComment(Long id){
    commentRepository.deleteById(id);
  }

}

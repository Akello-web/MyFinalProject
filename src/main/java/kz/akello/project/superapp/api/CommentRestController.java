package kz.akello.project.superapp.api;

import kz.akello.project.superapp.dto.CommentDTO;
import kz.akello.project.superapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "comments")
public class CommentRestController {
  private final CommentService commentService;

  @GetMapping
  public List<CommentDTO> getComments(){
    return commentService.getComments();
  }

  @GetMapping(value = "{id}")
  public CommentDTO getComment(@PathVariable(name = "id") Long id){
    return commentService.getComment(id);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping
  public CommentDTO addComment(@RequestBody CommentDTO comment){
    return commentService.addComment(comment);
  }

  @PreAuthorize("isAuthenticated()")
  @PutMapping
  public CommentDTO updateComment(@RequestBody CommentDTO comment){
    return commentService.updateComment(comment);
  }

  @PreAuthorize("isAuthenticated()")
  @DeleteMapping(value = "{id}")
  public void deleteComment(@PathVariable(name = "id") Long id){
    commentService.deleteComment(id);
  }

}

package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.kosiorski.dto.CommentDto;
import pl.kosiorski.exception.NoAuthenticationException;
import pl.kosiorski.service.AuthService;
import pl.kosiorski.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class CommentController {

  private final AuthService authService;
  private final CommentService commentService;

  @Autowired
  public CommentController(AuthService authService, CommentService commentService) {
    this.authService = authService;
    this.commentService = commentService;
  }

  @PostMapping("/{taskId}/comments")
  public CommentDto save(
      @RequestBody @Valid CommentDto commentDto,
      @PathVariable Long taskId,
      @RequestHeader("Authorization") String token) {

    try {
      if (authService.validateToken(token)) {
        return commentService.save(commentDto, taskId);
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    }
    return null;
  }
}

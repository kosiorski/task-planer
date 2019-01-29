package pl.kosiorski.service;

import pl.kosiorski.dto.CommentDto;

public interface CommentService {

  CommentDto save(CommentDto commentDto, Long taskId);
}

package pl.kosiorski.service;

import pl.kosiorski.dto.CommentDto;
import pl.kosiorski.model.Task;

public interface CommentService {

  CommentDto save(CommentDto commentDto, Task task);
}

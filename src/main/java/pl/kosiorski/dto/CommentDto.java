package pl.kosiorski.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    Long id;
    String content;
    LocalDateTime created;
}

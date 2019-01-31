package pl.kosiorski.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.model.User;

import java.util.List;

public interface CategoryService {

  CategoryDto save(CategoryDto categoryDto, User user);

  void delete(Long id) throws EmptyResultDataAccessException;

  CategoryDto findOneById(Long categoryId) throws ObjectNotFoundException;

  CategoryDto update(CategoryDto categoryDto, User user);

  List<CategoryDto> findAllByUserToken(String token);

  boolean categoryBelongToUser (String userToken, Long categoryId) throws NoAuthorizationException;
}

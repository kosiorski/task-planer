package pl.kosiorski.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.model.User;

import java.util.List;

public interface CategoryService {

  CategoryDto save(CategoryDto categoryDto, User user);

  void delete(User user, Long id) throws EmptyResultDataAccessException;

  CategoryDto findOneByUserAndCategoryId(String token, Long categoryId) throws ObjectNotFoundException;

  CategoryDto update(CategoryDto categoryDto, User user);

  List<CategoryDto> findAll();

  List<CategoryDto> findAllByUserToken(String token);
}

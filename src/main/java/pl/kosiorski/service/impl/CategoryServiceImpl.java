package pl.kosiorski.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.model.Category;
import pl.kosiorski.model.User;
import pl.kosiorski.model.mapper.CategoryMapper;
import pl.kosiorski.repository.CategoryRepository;
import pl.kosiorski.repository.UserRepository;
import pl.kosiorski.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;
  private final UserRepository userRepository;

  @Autowired
  public CategoryServiceImpl(
      CategoryRepository categoryRepository,
      CategoryMapper categoryMapper,
      UserRepository userRepository) {
    this.categoryRepository = categoryRepository;
    this.categoryMapper = categoryMapper;
    this.userRepository = userRepository;
  }

  @Override
  public CategoryDto save(CategoryDto categoryDto, User user) {

    if (categoryDto != null) {

      Category category = categoryMapper.map(categoryDto, Category.class);
      category.setUser(user);
      Category categorySaved = categoryRepository.save(category);
      if (categorySaved != null) {
        return categoryMapper.map(categorySaved, CategoryDto.class);
      }
    }
    return null;
  }

  @Override
  @Transactional(readOnly = true)
  public CategoryDto findOneByUserAndCategoryId(String token, Long categoryId)
      throws ObjectNotFoundException {

    Category category =
        categoryRepository.findByUserAndId(userRepository.findByToken(token), categoryId);

    if (category != null) {
      return categoryMapper.map(category, CategoryDto.class);
    } else {
      throw new ObjectNotFoundException(categoryId, "Category not found");
    }
  }

  @Override
  public CategoryDto update(CategoryDto categoryDto, User user) {
    if (categoryDto != null) {

      Category categoryToUpdate = categoryMapper.map(categoryDto, Category.class);
      categoryToUpdate.setUser(user);
      Category updated = categoryRepository.save(categoryToUpdate);
      return categoryMapper.map(updated, CategoryDto.class);
    }
    return null;
  }

  @Override
  public List<CategoryDto> findAll() {
    List<Category> allCategories = categoryRepository.findAll();
    return categoryMapper.mapAsList(allCategories, CategoryDto.class);
  }

  @Override
  public List<CategoryDto> findAllByUser(String token) {
    List<Category> allByUser = categoryRepository.findAllByUser(userRepository.findByToken(token));
    return categoryMapper.mapAsList(allByUser, CategoryDto.class);
  }

  @Override
  public void delete(Long id) throws EmptyResultDataAccessException {
    categoryRepository.deleteById(id);
  }
}

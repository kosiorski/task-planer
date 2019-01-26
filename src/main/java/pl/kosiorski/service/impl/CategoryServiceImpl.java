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
import pl.kosiorski.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
    this.categoryRepository = categoryRepository;
    this.categoryMapper = categoryMapper;
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
  public CategoryDto findById(Long id) throws ObjectNotFoundException {

    Optional<Category> category = categoryRepository.findById(id);

    if (category.isPresent()) {
      return categoryMapper.map(category.get(), CategoryDto.class);
    } else {
      throw new ObjectNotFoundException(id, "Category not found");
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
  public void delete(Long id) throws EmptyResultDataAccessException {

    categoryRepository.deleteById(id);
  }
}

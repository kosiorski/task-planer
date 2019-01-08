package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.model.Category;
import pl.kosiorski.model.User;
import pl.kosiorski.repository.CategoryRepository;
import pl.kosiorski.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Category save(CategoryDto categoryDto) {
    Category toSave = categoryDto.toSave();
    return categoryRepository.save(toSave);
  }

  @Override
  public List<CategoryDto> getAll() {
    return null;
  }

  @Override
  public CategoryDto findById(Long id) {
    return null;
  }

  @Override
  public String removeById(Long id) {
    return null;
  }

  @Override
  public CategoryDto updateById(Long id, CategoryDto categoryDto) {
    return null;
  }

  @Override
  public List<Category> findAllByUserId(Long id) {
    return categoryRepository.findAllByUserId(id);
    //    return categories.stream().map(Category::toCategoryDto).collect(Collectors.toList());
  }

  @Override
  public List<CategoryDto> findAll() {
    List<Category> categories = categoryRepository.findAll();
    return categories.stream().map(Category::toCategoryDto).collect(Collectors.toList());
  }
}

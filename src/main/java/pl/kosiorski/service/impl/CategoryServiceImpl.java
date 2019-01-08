package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.model.Category;
import pl.kosiorski.repository.CategoryRepository;
import pl.kosiorski.service.CategoryService;

import java.util.List;

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
}

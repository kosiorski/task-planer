package pl.kosiorski.service;

import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.model.Category;

import java.util.List;

public interface CategoryService {

  Category save(CategoryDto categoryDto);

  List<CategoryDto> getAll();

  CategoryDto findById(Long id);

  String removeById(Long id);

  CategoryDto updateById(Long id, CategoryDto categoryDto);
}

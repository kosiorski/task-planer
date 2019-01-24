package pl.kosiorski.service;

import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.model.Category;

import java.util.List;

public interface CategoryService {

  void save(Category category, String token);

  CategoryDto findById(Long id);

  String removeById(Long id);

  CategoryDto updateById(Long id, Category category);

  List<CategoryDto> findAllByUserId(Long id);




}

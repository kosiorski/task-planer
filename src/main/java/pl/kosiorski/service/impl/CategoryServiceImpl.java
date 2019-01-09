package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.model.Category;
import pl.kosiorski.repository.CategoryRepository;
import pl.kosiorski.repository.UserRepository;
import pl.kosiorski.service.CategoryService;
import pl.kosiorski.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final UserRepository userRepository;

  @Autowired
  public CategoryServiceImpl(
      CategoryRepository categoryRepository,
      UserRepository userRepository,
      UserService userService) {
    this.categoryRepository = categoryRepository;
    this.userRepository = userRepository;
  }

  @Override
  public void save(Category category, String token) {

    category.setUser(userRepository.findByToken(token));
    categoryRepository.save(category);
  }

  @Override
  public CategoryDto findById(Long id) {
    return categoryRepository.findById(id).get().toCategoryDto();
  }

  @Override
  public String removeById(Long id) {
    if (categoryRepository.removeById(id)) {
      return "Deleted " + id.toString();
    } else {
      return "Not Deleted ";
    }
  }

  @Override
  public CategoryDto updateById(Long id, Category category) {
    Category byId = categoryRepository.findById(id).get();
    categoryRepository.save(byId);
    return categoryRepository.save(byId).toCategoryDto();
  }

  @Override
  public List<CategoryDto> findAllByUserId(Long id) {
    List<Category> categories = categoryRepository.findAllByUserId(id);
    return categories.stream().map(Category::toCategoryDto).collect(Collectors.toList());
  }
}

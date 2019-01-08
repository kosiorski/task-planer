package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.model.Category;
import pl.kosiorski.model.User;
import pl.kosiorski.repository.UserRepository;
import pl.kosiorski.service.AuthService;
import pl.kosiorski.service.CategoryService;
import pl.kosiorski.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;
  private final AuthService authService;
  private final UserService userService;

  @Autowired
  public CategoryController(
      CategoryService categoryService,
      AuthService authService,
      UserRepository userRepository,
      UserService userService) {
    this.categoryService = categoryService;
    this.authService = authService;
    this.userService = userService;
  }

  @GetMapping("")
  public List<Category> getAll(@RequestHeader("Authorization") String token)
      throws NoAuthorizationException {

    List<Category> allByUserId;

    try {
      allByUserId = categoryService.findAllByUserId(userService.findByToken(token).getId());
    } catch (Exception e) {
      return null;
    }

    if (authService.valid(token)) {

      return allByUserId;

    } else return null;


  }

  @GetMapping("/{id}")
  public CategoryDto getOne(@PathVariable Long id) {
    return categoryService.findById(id);
  }

  @PostMapping("")
  public CategoryDto save(@RequestBody @Valid CategoryDto CategoryDto) {

    categoryService.save(CategoryDto);
    return CategoryDto;
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id) {
    return categoryService.removeById(id);
  }

  @PutMapping("/{id}")
  public CategoryDto updade(@PathVariable Long id, @Valid @RequestBody CategoryDto CategoryDto) {
    return categoryService.updateById(id, CategoryDto);
  }
}

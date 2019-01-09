package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.model.Category;
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
      CategoryService categoryService, AuthService authService, UserService userService) {
    this.categoryService = categoryService;
    this.authService = authService;
    this.userService = userService;
  }

  @GetMapping("")
  public List<CategoryDto> getAll(@RequestHeader("Authorization") String token)
      throws NoAuthorizationException {

    if (authService.valid(token)) {

      List<CategoryDto> allByUserId = categoryService.findAllByUserId(userService.findByToken(token).getId());
      userService.generateNewToken(token);
      return allByUserId;

    } else {
      throw new NoAuthorizationException("You dont have authorization, try to login");
    }
  }

  @GetMapping("/{id}")
  public CategoryDto getOne(@PathVariable Long id, @RequestHeader("Authorization") String token)
      throws NoAuthorizationException {

    if (authService.valid(token)) {

      userService.generateNewToken(token);
      return categoryService.findById(id);

    } else {
      throw new NoAuthorizationException("You dont have authorization, try to login");
    }
  }

  @PostMapping("")
  public CategoryDto save(
      @RequestBody @Valid Category category, @RequestHeader("Authorization") String token)
      throws NoAuthorizationException {

    if (authService.valid(token)) {

      categoryService.save(category, token);
      userService.generateNewToken(token);
      return category.toCategoryDto();

    } else {
      throw new NoAuthorizationException("You dont have authorization, try to login");
    }
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id, @RequestHeader("Authorization") String token)
      throws NoAuthorizationException {
    if (authService.valid(token)) {

      userService.generateNewToken(token);
      return categoryService.removeById(id);

    } else {
      throw new NoAuthorizationException("You dont have authorization, try to login");
    }
  }

  @PutMapping("/{id}")
  public CategoryDto updade(
      @PathVariable Long id,
      @Valid @RequestBody Category category,
      @RequestHeader("Authorization") String token)
      throws NoAuthorizationException {

    if (authService.valid(token)) {

      userService.generateNewToken(token);
      return categoryService.updateById(id, category);

    } else {
      throw new NoAuthorizationException("You dont have authorization, try to login");
    }
  }
}

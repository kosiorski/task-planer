package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.exception.NoAuthenticationException;
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
  public List<CategoryDto> getAll(@RequestHeader("Authorization") String token) {

    try {
      if (authService.validateToken(token)) {
        return categoryService.findAll();
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    }
    return null;
  }

  @GetMapping("/{id}")
  public CategoryDto getOne(@PathVariable Long id, @RequestHeader("Authorization") String token) {

    try {
      if (authService.validateToken(token)) {
        return categoryService.findById(id);
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    }
    return null;
  }

  @PostMapping("")
  public CategoryDto save(
      @RequestBody @Valid CategoryDto categoryDto, @RequestHeader("Authorization") String token) {

    try {
      if (authService.validateToken(token)) {
        return categoryService.save(categoryDto, userService.findByToken(token));
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    }
    return null;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(
      @PathVariable Long id, @RequestHeader("Authorization") String token) {
    try {
      if (authService.validateToken(token)) {
        categoryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);

    } catch (EmptyResultDataAccessException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Category with the id: " + id + " does not exist");
    }
    return null;
  }

  @PutMapping("")
  public CategoryDto updade(
      @Valid @RequestBody CategoryDto categoryDto, @RequestHeader("Authorization") String token) {

    try {
      if (authService.validateToken(token)) {
        return categoryService.update(categoryDto, userService.findByToken(token));
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    }
    return null;
  }
}

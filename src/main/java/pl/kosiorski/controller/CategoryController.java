package pl.kosiorski.controller;

import org.hibernate.ObjectNotFoundException;
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
  public List<CategoryDto> getAllByUserToken(@RequestHeader("Authorization") String token) {

    try {
      if (authService.validateToken(token)) {
        return categoryService.findAllByUserToken(token);
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    }
    return null;
  }

  @GetMapping("/{id}")
  public CategoryDto getOneByUserToken(
      @RequestHeader("Authorization") String token, @PathVariable Long id) {

    try {
      if (authService.validateToken(token)) {
        return categoryService.findOneByUserAndCategoryId(token, id);
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    } catch (ObjectNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized");
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

  // TODO fix statuses


  @DeleteMapping("/{id}")
  public ResponseEntity delete(
      @PathVariable Long id, @RequestHeader("Authorization") String token) {
    try {
      if (authService.validateToken(token)) {
        categoryService.delete(userService.findByToken(token), id);
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

  @PutMapping()
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

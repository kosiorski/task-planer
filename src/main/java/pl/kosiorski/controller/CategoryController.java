package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<CategoryDto> getAll() {
        return categoryService.getAll();
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
    public CategoryDto updade(@PathVariable Long id, @Valid @RequestBody CategoryDto CategoryDto){
        return categoryService.updateById(id, CategoryDto);
    }
}

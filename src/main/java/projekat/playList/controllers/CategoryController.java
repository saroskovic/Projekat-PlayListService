package projekat.playList.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projekat.playList.converters.CategoryConverter;
import projekat.playList.dto.CategoryDto;
import projekat.playList.entities.Category;
import projekat.playList.services.CategoryService;

@RestController
@RequestMapping(path = "/api/v1/category")
public class CategoryController{

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryConverter converter;
	
	/*@RequestMapping(method = RequestMethod.GET)
	public List<CategoryDto> fetchCategoryList(){
		return categoryService.fetchCategoryList()
				.stream()
				.map(categoryMapper::toDto)
				.collect(Collectors.toList());
	}*/
	@RequestMapping(method = RequestMethod.GET)
	public List<CategoryDto> fetchCategoryList(){
		List<Category> fetchAll = categoryService.fetchCategoryList();
		return converter.entityToDto(fetchAll);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{categoryId}")
	public CategoryDto findCategoryById(@PathVariable Long categoryId){
		Category category = categoryService.getCategoryById(categoryId);
		return converter.entityToDto(category);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public CategoryDto saveCategory(@RequestBody CategoryDto dto) {
		Category category = converter.dtoToEntity(dto);
		categoryService.saveCategory(category);
		return converter.entityToDto(category);
	}
		
	@RequestMapping(method = RequestMethod.PUT, value = "/{categoryId}")
	public CategoryDto updateCategory(@RequestBody CategoryDto dto, @PathVariable Long categoryId) {
		Category category = converter.dtoToEntity(dto);
		Category newCategory = categoryService.updateCategory(category, categoryId);
		return converter.entityToDto(newCategory);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{categoryId}")
	public void deleteCategory(@PathVariable Long categoryId) {
		categoryService.deleteCategoryById(categoryId);
	}
	
}

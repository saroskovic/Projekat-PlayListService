package projekat.playList.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projekat.playList.entities.Category;
import projekat.playList.services.CategoryService;

@RestController
@RequestMapping(path = "/api/v1/category")
public class CategoryController{

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Category> fetchCategoryList(){
		return categoryService.fetchCategoryList();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{categoryId}")
	public Category findCategoryById(@PathVariable Long categoryId){
		return categoryService.getCategoryById(categoryId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Category saveCategory(@RequestBody Category category) {
		return categoryService.saveCategory(category);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Category updateCategory(@RequestBody Category category) {
		return categoryService.updateCategory(category);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{categoryId}")
	public void deleteCategory(@PathVariable Long categoryId) {
		categoryService.deleteCategoryById(categoryId);
	}
	
}

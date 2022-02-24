package projekat.playList.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projekat.playList.entities.Category;
import projekat.playList.services.CategoryService;

@RestController
@RequestMapping(path = "/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Category> fetchCategoryList(){
		return categoryService.fetchCategoryList();
	}
	
}

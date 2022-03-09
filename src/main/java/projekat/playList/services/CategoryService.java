package projekat.playList.services;

import java.util.List;


import projekat.playList.entities.Category;
import projekat.playList.dto.CategoryDto;

public interface CategoryService{

	Category saveCategory(Category category);
	
	List<Category> fetchCategoryList();
	
	Category getCategoryById(Long categoryId);
	
	Category updateCategory(Category category, Long categoryId);
	
	void deleteCategoryById(Long categoryId);
	
}

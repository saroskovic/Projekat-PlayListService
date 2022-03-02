package projekat.playList.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import projekat.playList.entities.Category;

public interface CategoryService{

	Category saveCategory(Category category);
	
	List<Category> fetchCategoryList();
	
	Category getCategoryById(Long categoryId);
	
	Category updateCategory(Category category);
	
	void deleteCategoryById(Long categoryId);
	
}

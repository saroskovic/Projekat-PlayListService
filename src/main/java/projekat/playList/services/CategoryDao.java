package projekat.playList.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.playList.entities.Category;

public interface CategoryDao {

	Category saveCategory(Category category);
	
	List<Category> fetchCategoryList();
	
	Category updateCategory(Category category, Integer categoryId);
	
	void deleteCategoryById(Integer categoryId);
	
}

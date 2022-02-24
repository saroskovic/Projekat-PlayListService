package projekat.playList.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.playList.entities.Category;
import projekat.playList.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> fetchCategoryList() {
		return categoryRepository.findAll();
	}

	@Override
	public Category updateCategory(Category category, Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategoryById(Long categoryId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category getCategoryById(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}

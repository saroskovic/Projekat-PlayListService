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
		if(category.getName() == null || category.getChannel() == null)
			return null;
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> fetchCategoryList() {
		return categoryRepository.findAll();
	}

	@Override
	public Category updateCategory(Category category) {
		if(categoryRepository.existsById(category.getId())) {
			Category newCategory = categoryRepository.getById(category.getId());
			if(category.getName() != null)
				newCategory.setName(category.getName());
			if(category.getChannel() != null)
				newCategory.setChannel(category.getChannel());
			return categoryRepository.save(newCategory);
		}
		return null;
	}

	@Override
	public void deleteCategoryById(Long categoryId) {
		if(categoryRepository.existsById(categoryId)) {
			Category category = categoryRepository.findById(categoryId).get();
			categoryRepository.delete(category);
		}
	}

	@Override
	public Category getCategoryById(Long categoryId) {
		if(categoryRepository.existsById(categoryId))
		return categoryRepository.getById(categoryId);
		return null;
	}

}

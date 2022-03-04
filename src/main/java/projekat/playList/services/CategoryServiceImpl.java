package projekat.playList.services;

import java.util.List;
import java.util.NoSuchElementException;

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
	public Category updateCategory(Category category) {
			Category newCategory = categoryRepository.findById(category.getId())
					.orElseThrow(() -> new NoSuchElementException(String.format("Could not find category: &d", category.getId())));
			if(category.getName() != null)
				newCategory.setName(category.getName());
			if(category.getChannel() != null)
				newCategory.setChannel(category.getChannel());
			return categoryRepository.save(newCategory);
		
	}

	@Override
	public void deleteCategoryById(Long categoryId){
			Category category = categoryRepository.findById(categoryId)
					.orElseThrow(() -> new NoSuchElementException(String.format("Could not find category: &d", categoryId)));
			categoryRepository.delete(category);
		
	}

	@Override
	public Category getCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not find category: &d", categoryId)));
	}

}

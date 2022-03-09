package projekat.playList.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.playList.entities.Category;
import projekat.playList.dto.CategoryDto;
import projekat.playList.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	private ModelMapper modelMapper;
	
		
	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Category saveCategory(Category category) {

		log.info("New Category saved!");
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> fetchCategoryList() {
		log.info("All categories returned!");
		return categoryRepository.findAll();
	}

	@Override
	public Category updateCategory(Category category, Long categoryId) {
			Category newCategory = categoryRepository.findById(categoryId)
					.orElseThrow(() -> new NoSuchElementException(String.format("Could not find category: %d", categoryId)));
			if(category.getName() != null)
				newCategory.setName(category.getName());
			if(category.getChannel() != null)
				newCategory.setChannel(category.getChannel());
			log.info("Category with id {} updated succesfully!", categoryId);
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

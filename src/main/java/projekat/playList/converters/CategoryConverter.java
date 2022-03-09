package projekat.playList.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projekat.playList.dto.CategoryDto;
import projekat.playList.entities.Category;


@Component
public class CategoryConverter {

	@Autowired
	ModelMapper modelMapper;
	
	public CategoryDto entityToDto(Category category) {
	//CategoryDto dto = new CategoryDto();
	//dto.setId(category.getId());
	//dto.setName(category.getName());
	CategoryDto dto = modelMapper.map(category, CategoryDto.class);
	return dto;
	}
	
	public List<CategoryDto> entityToDto(List<Category> categories) {
		return categories.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
		}
	
	
	public Category dtoToEntity(CategoryDto dto) {
		//Category category = new Category();
		//category.setId(dto.getId());
		//category.setName(dto.getName());
		Category category = modelMapper.map(dto, Category.class);
		return category;
	}
	
	public List<Category> dtoToEntity(List<CategoryDto> dto) {
		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
	
}

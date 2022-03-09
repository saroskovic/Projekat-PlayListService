package projekat.playList.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projekat.playList.dto.UserDto;
import projekat.playList.entities.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

        @Autowired
        ModelMapper modelMapper;

        public UserDto entityToDto(User user) {
            UserDto dto = modelMapper.map(user, UserDto.class);
            return dto;
        }

        public List<UserDto> entityToDto(List<User> users) {
            return users.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
        }


        public User dtoToEntity(UserDto dto) {
            User user = modelMapper.map(dto,User.class);
            return user;
        }

        public List<User> dtoToEntity(List<UserDto> dto) {
            return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
        }
}

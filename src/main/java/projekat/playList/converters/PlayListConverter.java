package projekat.playList.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import projekat.playList.dto.PlayListDto;
import projekat.playList.entities.PlayList;


import java.util.List;
import java.util.stream.Collectors;

public class PlayListConverter {

    @Autowired
    ModelMapper modelMapper;

    public PlayListDto entityToDto(PlayList playList) {
        PlayListDto dto = modelMapper.map(playList, PlayListDto.class);
        return dto;
    }

    public List<PlayListDto> entityToDto(List<PlayList> playList) {
        return playList.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }


    public PlayList dtoToEntity(PlayListDto dto) {
        PlayList playList = modelMapper.map(dto, PlayList.class);
        return playList;
    }

    public List<PlayList> dtoToEntity(List<PlayListDto> dto) {
        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }

}

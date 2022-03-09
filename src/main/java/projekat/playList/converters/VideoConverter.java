package projekat.playList.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projekat.playList.dto.VideoDto;
import projekat.playList.entities.Video;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VideoConverter {

        @Autowired
        ModelMapper modelMapper;

        public VideoDto entityToDto(Video video) {
            VideoDto dto = modelMapper.map(video, VideoDto.class);
            return dto;
        }

        public List<VideoDto> entityToDto(List<Video> videos) {
            return videos.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
        }


        public Video dtoToEntity(VideoDto dto) {
            Video video = modelMapper.map(dto, Video.class);
            return video;
        }

        public List<Video> dtoToEntity(List<VideoDto> dto) {
            return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
        }
}

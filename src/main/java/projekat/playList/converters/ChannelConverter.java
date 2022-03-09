package projekat.playList.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projekat.playList.dto.ChannelDto;
import projekat.playList.entities.Channel;

@Component
public class ChannelConverter {

	@Autowired
	ModelMapper modelMapper;
	
	public ChannelDto entityToDto(Channel channel) {
		ChannelDto dto = modelMapper.map(channel, ChannelDto.class);
		return dto;
		}
		
	public List<ChannelDto> entityToDto(List<Channel> channels) {
			return channels.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
			}
		
		
	public Channel dtoToEntity(ChannelDto dto) {
			Channel channel = modelMapper.map(dto, Channel.class);
			return channel;
		}
		
	public List<Channel> dtoToEntity(List<ChannelDto> dto) {
			return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
		}
}

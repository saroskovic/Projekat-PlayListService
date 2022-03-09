package projekat.playList;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlayListApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	private static final Logger log = LoggerFactory.getLogger(PlayListApplication.class);
	
	public static void main(String[] args){
		SpringApplication.run(PlayListApplication.class, args);
	}

}

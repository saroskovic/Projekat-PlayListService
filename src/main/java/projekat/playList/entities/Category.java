package projekat.playList.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Category{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "channel")
	private Channel channel;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "videoCategory", joinColumns =
				{@JoinColumn(name = "categoryId", nullable = false, updatable = false)},
				inverseJoinColumns = {@JoinColumn(name = "videoId", nullable = false, updatable = false)})
	private List<Video> videos = new ArrayList<>();
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Category(Long id, String name, Channel channel, List<Video> videos) {
		super();
		this.id = id;
		this.name = name;
		this.channel = channel;
		this.videos = videos;
	}



	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		Category category = (Category) obj;
		return (this.id == category.id && category.name.equals(this.name));
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}*/
	
}

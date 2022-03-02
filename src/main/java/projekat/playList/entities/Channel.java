package projekat.playList.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Channel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	
	@OneToMany(mappedBy = "channel", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<PlayList> channelItems = new ArrayList<>();
	
	@OneToMany(mappedBy = "channel", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<Category> categories = new ArrayList<>();

	public Channel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Channel(Long id, String name, List<PlayList> channelItems, List<Category> categories) {
		super();
		this.id = id;
		this.name = name;
		this.channelItems = channelItems;
		this.categories = categories;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		Channel ch = (Channel) obj;
		return (this.id == ch.id && ch.name.equals(this.name));
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	
	public Long getId() {
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

	public List<PlayList> getChannelItems() {
		return channelItems;
	}

	public void setChannelItems(List<PlayList> channelItems) {
		this.channelItems = channelItems;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	
	
}

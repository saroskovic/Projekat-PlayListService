package projekat.playList.entities;

import java.util.ArrayList;
import java.util.List;

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
	
	private String channelName;
	
	
	@OneToMany(mappedBy = "channel", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<PlayList> channelItems = new ArrayList<>();
	
	@OneToMany(mappedBy = "channel", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<Category> categories = new ArrayList<>();

	public Channel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
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

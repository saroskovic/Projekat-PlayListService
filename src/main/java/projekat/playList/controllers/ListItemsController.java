package projekat.playList.controllers;

import java.util.Collections;
import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projekat.playList.entities.ListItems;
import projekat.playList.entities.PlayList;
import projekat.playList.entities.User;
import projekat.playList.entities.Video;
import projekat.playList.repositories.ListItemsRepository;
import projekat.playList.repositories.PlayListRepository;
import projekat.playList.repositories.UserRepository;
import projekat.playList.repositories.VideoRepository;

@RestController
@RequestMapping(path = "/api/v1/playListItems")
public class ListItemsController {

	@Autowired
	ListItemsRepository listItemsRepository;
	@Autowired
	PlayListRepository playListRepository;
	@Autowired
	VideoRepository videoRepository;

	@RequestMapping(method = RequestMethod.POST) // Dodavanje elemenata u playlistu
	public ListItems addListItems(@RequestParam String playListId, @RequestParam String videoId) {
		if (playListRepository.existsById(playListId)) {
			if (videoRepository.existsById(videoId)) {
				Video video = videoRepository.findById(videoId).get();
				PlayList playList = playListRepository.findById(playListId).get();
				ListItems newListItems = new ListItems();
				newListItems.setPlayList(playList);
				;
				newListItems.setVideo(video);
				Integer orderNo = listItemsRepository.findAllByPlayListId(playListId).size() + 1;
				newListItems.setOrderNo(orderNo);
				return listItemsRepository.save(newListItems);
			}
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET) // Svi elementi playListe sortirani po rednom broju
	public List<ListItems> userPlayList(@RequestParam String userId) {
		return listItemsRepository.findAllByPlayListIdOrderByOrderNoAsc(userId);
	}

	@RequestMapping(method = RequestMethod.DELETE) // Brisanje elemenata playliste
	public ListItems dellListItems(@RequestParam Integer listItemsId) {
		if (listItemsRepository.existsById(listItemsId)) {
			ListItems listItem = listItemsRepository.findById(listItemsId).get();
			listItemsRepository.delete(listItem);
			List<ListItems> listItems = listItemsRepository.findAllByPlayListId(listItem.getPlayList().getId());
			for (ListItems item : listItems) {
				if (item.getOrderNo() > listItem.getOrderNo()) {
					item.setOrderNo(item.getOrderNo() - 1);
					listItemsRepository.save(item);
				}
			}
			return listItem;
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.PUT) // Izmena redosleda elemenata u playlisti
	public ListItems updateListItems(@RequestParam Integer listItemsId, @RequestParam Integer newOrderNo) {
		if (listItemsRepository.existsById(listItemsId)) {
			ListItems listItem = listItemsRepository.findById(listItemsId).get();
			List<ListItems> listItems = listItemsRepository.findAllByPlayListId(listItem.getPlayList().getId());
			listItemsRepository.delete(listItem);
			for (ListItems item : listItems) {
				if (item.getOrderNo() > listItem.getOrderNo()) {
					item.setOrderNo(item.getOrderNo() - 1);
					listItemsRepository.save(item);
				}
			}
			for (ListItems item : listItems) {
				if (item.getOrderNo() > newOrderNo - 1) {
					item.setOrderNo(item.getOrderNo() + 1);
				}
				
				

			}
			listItem.setOrderNo(newOrderNo);
			return listItemsRepository.save(listItem);
		}
		return null;
	}
}

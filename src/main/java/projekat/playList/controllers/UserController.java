package projekat.playList.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projekat.playList.entities.User;
import projekat.playList.repositories.UserRepository;
import projekat.playList.services.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<User> allUsers(){
		return userService.fetchUserList();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public User getUserById(@PathVariable Long userId) {
		return userService.getUserById(userId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public User addUser(@RequestBody User newUser) {
		return userService.saveUser(newUser);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
	public User updateUser(@PathVariable Long userId, @RequestBody User user) {
		return userService.updateUser(user, userId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUserById(userId);
	}
}

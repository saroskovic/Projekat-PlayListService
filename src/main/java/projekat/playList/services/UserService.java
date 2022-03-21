package projekat.playList.services;

import java.util.List;

import projekat.playList.entities.Role;
import projekat.playList.entities.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> fetchUserList();

	User getUserByEmail(String email);
	
	User getUserById(Long userId);
	
	User updateUser(User user, Long userId);
	
	void deleteUserById(Long userId);

	Role saveRole(Role role);

	void addRoleToUser(String userEmail, String roleName);
}

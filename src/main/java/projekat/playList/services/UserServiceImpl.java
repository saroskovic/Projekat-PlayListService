package projekat.playList.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.playList.entities.User;
import projekat.playList.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {
		if(user.getName() == null)
			throw new NullPointerException("Name cannot be null!");
		return userRepository.save(user);
	}

	@Override
	public List<User> fetchUserList() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new NoSuchElementException(String.format("could not find user: %d", userId)));
		
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User updateUser(User user, Long userId) {
		User newUser = userRepository.findById(userId)
						.orElseThrow(() -> new NoSuchElementException(String.format("could not find user: %d", userId)));
		if(user.getName() != null)
			newUser.setName(user.getName());
		if(user.getPlayList() != null)
			newUser.setPlayList(user.getPlayList());
		return userRepository.save(newUser);
	}

	@Override
	public void deleteUserById(Long userId) {
		User user = userRepository.findById(userId)
						.orElseThrow(() -> new NoSuchElementException(String.format("could not find user: %d", userId)));
		userRepository.delete(user);
		
	}

}

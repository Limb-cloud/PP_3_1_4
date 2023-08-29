package web.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import web.models.User;

public interface UserService extends UserDetailsService {

  List<User> listUsers();

  void addUser(User user, String role);

  void updateUser(User user, String role);

  void removeUser(Long id);

  User getUserById(Long id);

  User getUserByUsername(String userName);

  boolean isUniqueUsername(String userName);
}

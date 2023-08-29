package web.repository;

import java.util.List;
import web.models.User;

public interface UserRepository {

  List<User> findAll();
  void save(User user);
  void update(User user);
  void deleteById(Long id);
  User getById(Long id);
  User findByUserName(String userName);
}

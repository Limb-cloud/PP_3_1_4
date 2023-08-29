package web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.models.User;
import web.repository.UserRepository;
import web.service.RoleService;
import web.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final RoleService roleService;
  private final PasswordEncoder encoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, RoleService roleRepository,
      PasswordEncoder passwordEncoder) {
    this.repository = userRepository;
    this.roleService = roleRepository;
    this.encoder = passwordEncoder;
  }

  @Override
  public List<User> listUsers() {
    return repository.findAll();
  }

  @Override
  public void addUser(User user, String role) {
    user.setRoles(roleService.storeSetRolesByIds(role));
    user.setPassword(encoder.encode(user.getPassword()));
    repository.save(user);
  }

  @Override
  public void updateUser(User user, String role) {
    user.setRoles(roleService.storeSetRolesByIds(role));

    if(user.getPassword().equals("") || user.getPassword() == null) {
      user.setPassword(getUserByUsername(user.getUsername()).getPassword());
    }

    repository.update(user);
  }

  @Override
  public void removeUser(Long id) {
    repository.deleteById(id);
  }

  @Override
  public User getUserById(Long id) {
    return repository.getById(id);
  }

  @Override
  public User getUserByUsername(String username) {
    return repository.findByUserName(username);
  }

  @Override
  public boolean isUniqueUsername(String userName) {
    return getUserByUsername(userName) != null;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = getUserByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("Пользователь не найден");
    }

    return user;
  }
}

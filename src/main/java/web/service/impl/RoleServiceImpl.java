package web.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.models.Role;
import web.repository.RoleRepository;
import web.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository repository;

  @Autowired
  public RoleServiceImpl(RoleRepository roleRepository) {
    this.repository = roleRepository;
  }

  @Override
  public List<Role> listRoles() {
    return repository.findAll();
  }

  @Override
  public Role getById(Long id) {
    return repository.findById(id);
  }

  @Override
  public Set<Role> storeSetRolesByIds(String rolesId) {
    Set<Role> roles = new HashSet<>();

    for(String string : rolesId.split(",")) {
      roles.add(getById(Long.parseLong(string)));
    }

    return roles;
  }
}

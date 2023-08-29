package web.service;

import java.util.List;
import java.util.Set;
import web.models.Role;

public interface RoleService {

  List<Role> listRoles();

  Role getById(Long id);

  Set<Role> storeSetRolesByIds(String rolesId);
}

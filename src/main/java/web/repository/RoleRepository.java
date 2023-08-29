package web.repository;

import java.util.List;
import web.models.Role;

public interface RoleRepository {

  List<Role> findAll();
  Role findById(Long id);
}

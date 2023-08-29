package web.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import web.models.Role;
import web.repository.RoleRepository;

public class RoleRepositoryImpl implements RoleRepository {
  private final EntityManager em;

  @Autowired
  public RoleRepositoryImpl(EntityManager entityManager) {
    this.em = entityManager;
  }

  @Override
  public List<Role> findAll() {
    return em.createQuery("from Role", Role.class).getResultList();
  }

  @Override
  public Role findById(Long id) {
    return em.find(Role.class, id);
  }
}

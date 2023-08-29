package web.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;
import web.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private final EntityManager em;

  @Autowired
  public UserRepositoryImpl(EntityManager entityManager) {
    this.em = entityManager;
  }

  @Override
  public List<User> findAll() {
    return em.createQuery("from User", User.class).getResultList();
  }

  @Override
  @Transactional
  public void save(User user) {
    em.persist(user);
  }

  @Override
  @Transactional
  public void update(User user) {
    em.merge(user);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    em.remove(getById(id));
  }

  @Override
  public User getById(Long id) {
    return em.find(User.class, id);
  }

  @Override
  public User findByUserName(String userName) {
      try {
         return em.createQuery("Select u from User u left join fetch u.roles where u.username=:userName", User.class)
             .setParameter("userName", userName).getSingleResult();
      } catch (NoResultException exception) {
        return null;
      }
  }
}

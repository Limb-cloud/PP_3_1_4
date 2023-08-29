package web.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.repository.RoleRepository;
import web.repository.UserRepository;
import web.repository.impl.RoleRepositoryImpl;
import web.repository.impl.UserRepositoryImpl;

@Configuration
@EnableTransactionManagement
public class DbConfig {

  @PersistenceContext
  private EntityManager em;

  @Bean
  public UserRepository userRepository() {
    return new UserRepositoryImpl(em);
  }

  @Bean
  public RoleRepository roleRepository() {
    return new RoleRepositoryImpl(em);
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(em.getEntityManagerFactory());
    return transactionManager;
  }
}

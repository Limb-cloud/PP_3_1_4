package web.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name", nullable = false)
  @NotNull(message = "Поле Имя не может быть пустым")
  @Pattern(message = "Поле Имя может содержать только буквы", regexp = "^[a-zA-Zа-яА-Я]+$")
  private String firstName;

  @Column(name = "last_name", nullable = false)
  @NotNull(message = "Поле Фамилия не может быть пустым")
  @Pattern(message = "Поле Фамилия может содержать только буквы", regexp = "^[a-zA-Zа-яА-Я]+$")
  private String lastName;

  @Column(name = "email")
  @Email(message = "Укажите E-mail в корректном формате")
  private String email;

  @Column(name = "age", nullable = false)
  @Max(message = "Максимально допустимый возраст 100 лет", value = 100)
  @Min(message = "Минимально допустимый возраст 1 год", value = 1)
  @NotNull(message = "Поле Возраст не может быть пустым")
  private Integer age;

  @Column(name = "username", unique = true, nullable = false)
  @Length(min = 5, max = 14, message = "Минимальная длина логина может быть 7, а максимальная 14")
  @Pattern(message = "Логин может содержать только английские нижнего регистра, а так же числа",
      regexp = "^[a-z0-9]+$")
  @NotNull(message = "Логин не должен быть пустым")
  private String username;

  @Column(name = "password", nullable = false)
  @NotNull(message = "Пароль не должен быть пустым")
  private String password;

  @Column(name = "enabled", nullable = false)
  private Boolean enabled = true;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  @Fetch(FetchMode.JOIN)
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String firstName, String lastName, String email, Integer age, String userName,
      String password, Set<Role> roles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
    this.username = userName;
    this.password = password;
    this.enabled = true;
    this.roles = roles;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public void setUsername(String userName) {
    this.username = userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return (int) (this.id * firstName.hashCode() & 2);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.getRoles();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public String toString() {
    return this.firstName + " " + this.lastName + " " + this.email + " " + this.age + " " + this.username + " " + this.password;
  }

}

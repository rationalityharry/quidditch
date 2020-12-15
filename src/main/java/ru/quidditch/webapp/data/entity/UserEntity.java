package ru.quidditch.webapp.data.entity;

import ru.quidditch.webapp.data.enums.Faculty;
import ru.quidditch.webapp.data.enums.Roles;

import javax.persistence.*;

@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    @Column
    private String patronimic;

    @Column(nullable = false)
    private Roles role;

    @Column(nullable = false)
    private String email;

    @Column
    private Faculty faculty;

    @OneToOne
    private TeamEntity team;

    @Column
    private boolean enabled;

    @Column
    private boolean disabled;

    @Column
    private String phone;

    @Column
    private String info;

    @Column
    private String birthday;

    @OneToOne
    @JoinColumn(name = "avatarImageId")
    private ImageEntity userKey;

    public UserEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronimic() {
        return patronimic;
    }

    public void setPatronimic(String patronimic) {
        this.patronimic = patronimic;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles roles) {
        this.role = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ImageEntity getUserKey() {
        return userKey;
    }

    public void setUserKey(ImageEntity userKey) {
        this.userKey = userKey;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }
}

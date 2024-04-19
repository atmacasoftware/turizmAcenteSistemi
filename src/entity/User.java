package entity;

import java.sql.Date;
import java.time.LocalDate;
public class User {
    private int user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String mobile_phone;
    private LocalDate created_at;
    private LocalDate updated_at;
    private Role role;

    public enum Role{
        HEPSI,
        ADMIN,
        PERSONEL
    }

    public User(){}

    public User(int user_id, String first_name, String last_name, String email, String mobile_phone, String password, LocalDate created_at, LocalDate updated_at, Role role) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile_phone = mobile_phone;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.role = role;
    }

    public User(String first_name, String last_name, String email, String mobile_phone, String password, LocalDate created_at, LocalDate updated_at, Role role) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile_phone = mobile_phone;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", role=" + role +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

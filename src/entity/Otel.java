package entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Otel {
    private int otel_id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String star;
    private String city;
    private String region;
    private LocalDate created_at;
    private LocalDate updated_at;

    public Otel() {}

    public Otel(int otel_id, String name, String address, String email, String phone, String star, String city, String region, LocalDate created_at, LocalDate updated_at) {
        this.otel_id = otel_id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.star = star;
        this.city = city;
        this.region = region;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Otel(String name, String address, String email, String phone, String star, String city, String region, LocalDate created_at, LocalDate updated_at) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.star = star;
        this.city = city;
        this.region = region;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getOtel_id() {
        return otel_id;
    }

    public void setOtel_id(int otel_id) {
        this.otel_id = otel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

}
